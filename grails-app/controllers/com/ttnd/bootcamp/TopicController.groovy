package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO

class TopicController {

    def index() {
        render view: "/resource/searchPage"
    }

    def showTopic(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        if (topic == null) {
            flash.error = "Topic does not exist"
            redirect(controller: 'login', action: 'index')
        } else {
            List<User> subscribedUsers = Topic.getSubscribedUser(co.topicId)

            if (topic.visibility == Visibility.PUBLIC) {
                render(view: 'topicShowPage', model: [subscribedUsers: subscribedUsers])
            } else if (topic.visibility == Visibility.PRIVATE) {
                User user = session.user
                Subscription subscription = Subscription.findByUserAndTopic(user, topic)
                if (subscription == null) {
                    flash.error = "Topic is Private, User is not Subscribed to it"
                    redirect(controller: 'login', action: 'index')
                } else {
                    render(view: 'topicShowPage', model: [subscribedUsers: subscribedUsers])
                }
            }
        }
    }

    def saveTopic(String topicName, String visibility) {
        User user = session.user
        Topic topic = new Topic(name: topicName,
                visibility: Visibility.convertVisibility(visibility),
                createdBy: user)
        if (topic.save(flush: true)) {
            flash.message = "Success"
            render flash.message
        } else {
            flash.error = "Topic not saved"
            redirect controller: 'user', action: 'index'
        }
    }
}
