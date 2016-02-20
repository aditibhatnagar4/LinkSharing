import com.ttnd.bootcamp.*
import com.ttnd.bootcamp.User
import com.ttnd.bootcamp.Constants
import com.ttnd.bootcamp.Topic
import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    def grailsApplication
    User adminUser
    User normalUser
    List<Topic> topics
    List<Resource> resources
    List<Subscription> subscriptions
    List<User> users
    List<ReadingItem> readingItems
    List<ResourceRating> resourceRatings

    def init = { servletContext ->
        log.info "init called"
        adminUser = createUser(true)
        normalUser = createUser(false)
        users = [adminUser, normalUser]
        topics = createTopic()
        resources = createResources()
        subscriptions = subscribeTopics(topics, users)
        readingItems = createReadingItems(users, topics)
        resourceRatings = createResourceRatings(readingItems)


    }

    User createUser(Boolean admin) {

        String prefix = admin ? "aditi.bhatnagar+admin" : "aditi.bhatnagar"
        String email = prefix + "@tothenew.com"

        User user = User.findByEmail(email)

        if (!user) {
            user = new User(
                    firstName: prefix,
                    lastName: prefix,
                    email: email,
                    password: Constants.DEFAULT_PASSWORD,
                    userName: prefix,
                    admin: admin,
                    active: true
            )
            if (user.validate()) {
                user.save(flush: true)
                log.info "User ${user} saved successfully"
            } else {
                log.error "Error! User not created ${user.errors.allErrors}"
            }
        } else {
            log.info "User already exists"
        }

        return user
    }


    List<Topic> createTopic() {
        List<Topic> topics = []
        if (!Topic.count()) {


            users.each { User user ->
                String prefix = user.firstName
                (1..5).each {
                    Topic topic = new Topic(
                            name: "topic $it ${prefix}",
                            visibility: Visibility.PUBLIC,
                            createdBy: user
                    )
                    if (topic.save(flush: true)) {
                        topics.add(topic)
                        log.info "Topic ${topic} saved successfully"
                    } else {
                        log.error "Error saving topic : ${topic.errors.allErrors}"
                    }

                }
            }

        }
        topics


    }


    List<Resource> createResources() {
        List<Resource> resources = []
        if (!Resource.count()) {

            topics.each { Topic topic ->
                (1..2).each {
                    Resource resource = new LinkResource(
                            topic: topic,
                            createdBy: topic.createdBy,
                            description: "desc $topic $it",
                            url: "https://www.link${it}.com"
                    )
                    if (resource.save(flush: true)) {
                        resources.add(resource)
                        log.info "Resource ${resource} saved successfully"
                    } else {
                        log.error "Error saving resource : ${resource.errors.allErrors}"
                    }

                }

                (1..2).each {
                    Resource resource = new DocumentResource(
                            topic: topic,
                            createdBy: topic.createdBy,
                            description: "desc ${topic} $it",
                            filePath: "doc/$it/file/path"
                    )
                    if (resource.save(flush: true)) {
                        resources.add(resource)
                        log.info "Resource ${resource} saved successfully"
                    } else {
                        log.error "Error saving resource : ${resource.errors.allErrors}"
                    }

                }

            }

        }
        resources


    }


    List<Subscription> subscribeTopics(List<Topic> topics, List<User> users) {
        List<Subscription> subscriptions = []
        users.each { User user ->
            topics.each { Topic topic ->
                if (Subscription.findByUserAndTopic(user, topic) == null) {
                    Subscription subscription = new Subscription(user: user,
                            topic: topic,
                            seriousness: Seriousness.VERY_SERIOUS)
                    if (subscription.save(flush: true, failOnError: true)) {
                        subscriptions.add(subscription)
                        log.info "${subscription} saved "

                    } else {
                        log.info "subscription not saved ${subscription.errors.allErrors}"
                    }
                } else {
                    log.info "User ${user} already subscribed to Topic ${topic}"
                }

            }

        }
        subscriptions
    }


    List<ReadingItem> createReadingItems(List<User> users, List<Topic> topics) {
        List<ReadingItem> readingItems = []
        users.each { User user ->
            topics.each { Topic topic ->
                if (Subscription.findByUserAndTopic(user, topic) != null) {

                    List<Resource> resources = []
                    resources = Resource.findAllByTopic(topic)

                    resources.each { Resource resource ->
                        log.info "$user $topic $resource"
                        if (resource.createdBy != user && !user.readingItems?.contains(resource)) {
                            log.info "$user $topic $resource"
                            ReadingItem readingItem = new ReadingItem(user: user,
                                    resource: resource,
                                    isRead: false)
                            if (readingItem.save(flush: true)) {
                                readingItems.add(readingItem)
                                log.info "${readingItem} saved in ${user}'s list"

                            } else {
                                log.info "${readingItem} is not saved in ${user}'s list--- ${readingItem.errors.allErrors}"
                            }
                        } else {
                            log.info "resource created by user ${user} or reading item exists in user's list"
                        }
                    }
                } else {
                    log.info "${user} not subscribed to topic ${topic} "
                }
            }
        }
        return readingItems
    }

    List<ResourceRating> createResourceRatings(List<ReadingItem> readingItems) {
        List<ResourceRating> resourceRatings = []
        readingItems.each { ReadingItem readingItem ->
            if (readingItem.isRead == false) {
                ResourceRating resourceRating = new ResourceRating(score: Constants.SCORE,
                        user: readingItem.user,
                        resource: readingItem.resource)
                if (resourceRating.save()) {
                    log.info "${resourceRating} rating for ${readingItem.resource} by ${readingItem.user}"
                    resourceRatings.add(resourceRating)
                } else {
                    log.info "${resourceRating} rating not set for ${readingItem.resource} by ${readingItem.user}---" +
                            " ${resourceRating.errors.allErrors}"
                }
            } else {
                log.info "The item ${readingItem.resource} has been read."
            }
        }
        return resourceRatings
    }


    def destroy = {
    }
}
