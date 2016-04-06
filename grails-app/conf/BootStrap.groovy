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
    def springSecurityService
    Role userRole, adminRole

    def init = { servletContext ->
        log.info "init called"
        createRole()
        createUser()
        users = [adminUser, normalUser]
        createUserRole()
        topics = createTopic()
        resources = createResources()
        subscriptions = subscribeTopics(topics, users)
        readingItems = createReadingItems(users, topics)
        resourceRatings = createResourceRatings(readingItems)


    }

    Role createRole() {

        //TODO Refactor
        userRole = new Role(authority: "ROLE_USER")
        adminRole = new Role(authority: "ROLE_ADMIN")
        if (userRole.save()) {
            log.info "Normal role created"
        } else {
            log.info("Could not create role")
        }

        if (adminRole.save()) {
            log.info "Admin role ceated"
        } else {
            log.info("Could not create role")
        }
    }

    UserRole createUserRole() {
        UserRole normalUserRole = new UserRole(user: normalUser, role: userRole)
        UserRole adminUserRole = new UserRole(user: adminUser, role: adminRole)
        if (normalUserRole.save()) {
            log.info "Normal user-role created"
        } else {
            log.info("Could not create role")
        }

        if (adminUserRole.save()) {
            log.info "Admin user-role created"
        } else {
            log.info("Could not create role")
        }

    }


//    User createUser(Boolean admin) {
//
//        String prefix = admin ? "aditi.bhatnagar+admin" : "aditi.bhatnagar"
//        String email = prefix + "@tothenew.com"
//
//        User user = User.findByEmail(email)
//
//        String password = springSecurityService.encodePassword(Constants.DEFAULT_PASSWORD)
//        if (!user) {
//            user = new User(
//                    firstName: prefix,
//                    lastName: prefix,
//                    email: email,
//                    password: password,
//                    username: email,
//                    enabled: true,
//                    confirmPassword: password
//            )
//            if (user.validate()) {
//                log.info "${user}"
//                user.save(flush: true)
//                log.info "User ${user} saved successfully"
//            } else {
//                log.error "Error! User not created ${user.errors.allErrors}"
//            }
//        } else {
//            log.info "User already exists"
//        }
//        log.info "${user.password}"
//        return user
//    }


    void createUser() {
        if (!User.count()) {
            log.info(">>>>>>>>>>Creating dummy user and admin<<<<<<<<<<<<<")
            String password = springSecurityService.encodePassword(Constants.DEFAULT_PASSWORD)
           normalUser = new User(firstName: "Normal",
                    lastName: "User",
                    username: "aditi.bhatnagar@tothenew.com",
                    email: "aditi.bhatnagar@tothenew.com",
                    password: password)
            normalUser.confirmPassword = Constants.DEFAULT_PASSWORD
            if (!normalUser.save(flush: true)) {
                normalUser.errors.allErrors.each {
                    log.error it
                }
            }

           adminUser = new User(firstName: "Admin",
                    lastName: "User",
                    username: "aditi.bhatnagar+admin@tothenew.com",
                    email: "aditi.bhatnagar+admin@tothenew.com",
                    password: password)
            adminUser.confirmPassword = Constants.DEFAULT_PASSWORD
            if (!adminUser.save(flush: true)) {
                adminUser.errors.allErrors.each {
                    log.error it
                }
            }
        }
    }

    List<Topic> createTopic() {
        List<Topic> topics = []
        if (!Topic.count()) {
            users.each { User user ->
                String prefix = user.firstName
                (1..5).each {
                    Topic topic = new Topic(
                            name: "Topic $it ${prefix}",
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
                            description: "Description for $topic $it",
                            url: "https://www.google.com"
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
                            description: "Description for ${topic} $it",
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
                    if (subscription.save(flush: true)) {
                        subscriptions.add(subscription)
                        log.info "${subscription} saved "

                    } else {
                        log.error "subscription not saved ${subscription.errors.allErrors}"
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
                                log.error "${readingItem} is not saved in ${user}'s list--- ${readingItem.errors.allErrors}"
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
                ResourceRating resourceRating = new ResourceRating(score: Constants.DEFAULT_SCORE,
                        user: readingItem.user,
                        resource: readingItem.resource)
                if (resourceRating.save()) {
                    log.info "${resourceRating} rating for ${readingItem.resource} by ${readingItem.user}"
                    resourceRatings.add(resourceRating)
                } else {
                    log.error "${resourceRating} rating not set for ${readingItem.resource} by ${readingItem.user}---" +
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
