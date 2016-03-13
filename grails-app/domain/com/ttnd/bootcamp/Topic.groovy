package com.ttnd.bootcamp

import com.ttnd.bootcamp.VO.TopicVO

class Topic {

    Date dateCreated
    Date lastUpdated
    String name
    Visibility visibility

    static belongsTo = [
            createdBy: User
    ]

    static hasMany = [
            subscriptions: Subscription,
            resources    : Resource
    ]

    static constraints = {
        name blank: false, unique: true
    }

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(
                    user: this.createdBy,
                    topic: this,
                    seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save(flush: true)) {
                log.info "${subscription}-> ${this.createdBy} subscribed for ${this}"
            } else {
                log.error "Not subscribed--- ${subscription.errors.allErrors}"
            }
        }

    }

    String toString() {
        return name
    }

    static mapping = {
        sort "name"

    }

    static transients = ['subscribedUser']

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> topicVOs = Resource.createCriteria().list(max: 5) {
            projections {
                'topic' {
                    property('id')
                    property('name', 'name')
                    property('visibility')
                    property('createdBy')
                }
                count('topic', 'count')
            }
            groupProperty('topic')
            'topic' {
                eq('visibility', Visibility.PUBLIC)
            }
            order('count', 'desc')
            order('name', 'asc')
        }.collect {
            new TopicVO(id: it[0], name: it[1], visibility: it[2], createdBy: it[3], count: it[4])
        }
        return topicVOs
    }

    static List<User> getSubscribedUser(Long id) {
        List<User> subscribedUsers = Subscription.createCriteria().list() {
            projections {
                property('user')
            }
            eq('topic.id', id)
        }
        return subscribedUsers
    }

    static List<Resource> getRecentPosts() {
        List<Resource> result = Resource.createCriteria().list(max: 5) {
            'topic' {
                eq('visibility', Visibility.PUBLIC)
            }
            order('dateCreated', 'desc')
        }
        return result
    }

    public Boolean isTopicPublic() {
        if (this.visibility == Visibility.PUBLIC) {
            return true
        }
        return false
    }

    public Boolean canViewedBy(User user) {
        if (this.isTopicPublic() || user.admin || Subscription.findByUserAndTopic(user, this)) {
            return true
        }
        return false
    }


}
