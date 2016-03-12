package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.PostVO
import grails.converters.JSON

class TopicController {

    def index() {

    }

    def showTopic(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        if (topic == null) {
            flash.error = "Topic does not exist"
            redirect(controller: 'login', action: 'index')
        } else {
            List<User> subscribedUsers = Topic.getSubscribedUser(co.topicId)
            List<ReadingItem> readingItems=ReadingItem.createCriteria().list(max: 10){
                'resource'{

                        eq('topic.id', co.topicId)
                      //  eq('topic.visibility',co.visibility)

                }
            }
           // List<Resource> readingItems = Resource.search(co).list(max: 10)
           // List<PostVO> readingItems = User.getReadingItems(session.user)
            if (topic.visibility == Visibility.PUBLIC) {
                render(view: '/topic/topicShowPage', model: [subscribedUsers: subscribedUsers,
                                                             readingItems   : readingItems,
                                                             topic          : topic])
            } else if (topic.visibility == Visibility.PRIVATE) {
                User user = session.user
                Subscription subscription = Subscription.findByUserAndTopic(user, topic)
                if (subscription == null) {
                    flash.error = "Topic is Private, User is not Subscribed to it"
                    redirect(controller: 'login', action: 'index')
                } else {
                    render(view: '/topic/topicShowPage', model: [subscribedUsers: subscribedUsers,
                                                                 readingItems   : readingItems,
                                                                 topic          : topic])
                }
            }
        }
    }

    def saveTopic(String topicName, String visibility) {
        User user = session.user
        Topic topic = Topic.findOrCreateByNameAndCreatedBy(topicName, session.user)
        topic.visibility = Visibility.convertVisibility(visibility)
        Map jsonResponse = [:]
        if (topic.save(flush: true)) {
            flash.message = "Success!"
            jsonResponse.message = flash.message
        } else {
            flash.error = "Topic not saved"
            jsonResponse.error = flash.error
            //  redirect controller: 'user', action: 'index'
        }
        render jsonResponse as JSON
    }

    def delete(Long topicId) {

        Topic topic = Topic.get(topicId)
        User user = session.user

        if (topic) {
            if (user.admin || (topic.createdBy.id == user.id))
                topic.delete(flush: true)

        } else
            flash.error = "Topic unavailable."

        redirect(controller: 'login', action: 'index')

    }
}
