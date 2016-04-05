package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.SearchCO
import com.ttnd.bootcamp.CO.UserSearchCO
import com.ttnd.bootcamp.VO.PostVO
import com.ttnd.bootcamp.VO.TopicVO
import com.ttnd.bootcamp.VO.UserVO

class User {

    Date dateCreated
    transient springSecurityService
    Date lastUpdated
    String email
    String username
    String password
    String firstName
    String lastName
    Byte[] photo
    // Boolean admin
    boolean enabled = true
    String confirmPassword
    String name

    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    //TODO Refactor properly
    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    boolean isAdmin() {
        //TODO Change String constant to Enum
        authorities.any { it.authority == 'ROLE_ADMIN' }
    }

//    String getPassword() {
//        return decodedPassword
//    }


    static constraints = {
        email email: true, unique: true, blank: false
        password blank: false, minSize: 5
        firstName blank: false
        lastName blank: false
        photo nullable: true
//        admin nullable: true
        enabled nullable: true
        username unique: true
//        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
//            if (!obj.id && (obj.password != val || !val)) {
//                return false
//            }
//        })

    }

    static hasMany = [
            topics         : Topic,
            subscriptions  : Subscription,
            readingItems   : ReadingItem,
            resources      : Resource,
            resourceRatings: ResourceRating
    ]

    String getName() {

        if (firstName && lastName) {
            return "$firstName $lastName"
        } else return ""

    }

    static transients = ['name', 'confirmPassword', 'subscribedTopic','springSecurityService']

    static mapping = {
        photo(sqlType: 'longblob')
        sort id: "desc"
        //topics lazy: false
        //resources lazy: true
        subscriptions lazy: true
    }

    String toString() {
        return username ?: ""
    }

    List<Topic> getSubscribedTopic() {
        List<Topic> subscribedTopics = Subscription.createCriteria().list() {
            projections {
                property('topic')
            }
            eq('user.id', this.id)
        }
        return subscribedTopics
    }

    public static Boolean canDeleteResource(User user, Long resourceId) {
        Resource resource = Resource.read(resourceId)
        if (user.authorities.any { it.authority == 'ROLE_ADMIN' } || resource.createdBy.id == user.id) {
            return true
        }
        return false
    }

    public Integer getScore(Resource resource) {
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(this, resource)
        if (resourceRating)
            return resourceRating.score
        else return 1
    }

    static List<PostVO> getReadingItems(User user) {
        List<ReadingItem> readingItems = ReadingItem.findAllByUserAndIsRead(user, false)
        List<PostVO> readingItemsList = []
        readingItems.each {
            readingItemsList.add(new PostVO(resourceId: it.resource.id,
                    description: it.resource.description,
                    topicId: it.resource.topic.id,
                    topicName: it.resource.topic.name,
                    userId: it.resource.createdBy.id,
                    userName: it.resource.createdBy.username,
                    firstName: it.resource.createdBy.firstName,
                    lastName: it.resource.createdBy.lastName,
                    userPhoto: it.resource.createdBy.photo,
                    isRead: it.isRead,
                    url: it.resource,
                    filePath: it.resource,
                    postDate: it.resource.lastUpdated))
        }
        return readingItemsList
    }

    Boolean isSubscribed(Long topicId) {
        Subscription subscription = Subscription.createCriteria().get() {
            eq('user', this)
            'topic' {
                eq('id', topicId)
            }
        }
        if (subscription != null) {
            return true
        } else {
            return false
        }
    }

    static List<ReadingItem> getUnReadResources(SearchCO co) {
        List<ReadingItem> unreadItems
        if (co.q) {
            unreadItems = ReadingItem.createCriteria().list(max: co.max, offset: co.offset) {
                eq('isRead', false)
                'resource' {
                    ilike("description", "%$co.q%")
                }
            }
        }
        return unreadItems
    }

    public List<TopicVO> getCreatedTopics() {
        List<TopicVO> createdTopicsList = []

        List<Topic> topicList = Topic.createCriteria().list(max: 5) {
            eq('createdBy.id', id)
        }

        topicList.each {
            topic -> createdTopicsList.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
        }

        return createdTopicsList
    }

    public List<TopicVO> getSubscribedTopicsList() {
        List<TopicVO> subscribedTopicsList = []

        List<Topic> topicList = Subscription.createCriteria().list {
            projections {
                property('topic')
            }
            eq('user.id', id)
        }

        topicList.each {
            topic ->
                subscribedTopicsList.add(new TopicVO(id: topic.id,
                        name: topic.name,
                        visibility: topic.visibility,
                        createdBy: topic.createdBy))
        }

        return subscribedTopicsList
    }

    static namedQueries = {
        search { UserSearchCO userSearchCO ->
            if (userSearchCO.q) {
                or {
                    ilike('firstName', "%${userSearchCO.q}%")
                    ilike('lastName', "%${userSearchCO.q}%")
                    ilike('email', "%${userSearchCO.q}%")
                    ilike('username', "%${userSearchCO.q}%")
                }
            }

            if (userSearchCO.active != null) {
                eq('enabled', userSearchCO.active)
            }

        }

    }

    UserVO getUserDetails() {
        return new UserVO(userId: id,
                name: username,
                firstName: firstName,
                lastName: lastName,
                emailId: email,
                photo: photo,
                active: enabled,
                // admin: admin
        )
    }

    public List<Resource> unreadResources() {

        List<Resource> resourceList = ReadingItem.createCriteria().list() {

            projections {
                property('resource')
            }

            eq('isRead', false)
        }

        return resourceList

    }

}
