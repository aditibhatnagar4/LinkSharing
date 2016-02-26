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

//    static List<TopicVO> getTrendingTopics() {
//
//
//        List result = Resource.createCriteria().list() {
//            projections {
//                createAlias("topic", "t")
//                groupProperty("t.id")
//                property("t.name")
//                //count(".id", 'resourceCount')
//                count("topic")
//            }
//            eq('t.visibility',Visibility.PUBLIC)
//            order("resourceCount", "desc")
//            order("t.name", "asc")
//
//            maxResults 5
//            firstResult 1
//        }
//        return result
//
//
//    }

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

}
