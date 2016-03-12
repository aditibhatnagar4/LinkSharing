package com.ttnd.bootcamp

import com.ttnd.bootcamp.VO.PostVO
import grails.converters.JSON
import groovy.util.logging.Slf4j

@Slf4j
class SubscriptionController {

    def index() {
        User user = User.get(params.id)
        List<PostVO> readingItems = User.getReadingItems(user)
        // List<ReadingItem> readingItems=User.getUnReadResources(q: 'aditi.bhatnagar',max: 10, offset: 0)
        render view: "/user/profile", model: [readingItems: readingItems, id: params.id]
    }

    def deleteSubscription(Long id) {
        Subscription subscription = Subscription.load(id)
        if (subscription != null && subscription.topic.createdBy.id != session.user.id) {
            subscription.delete(flush: true)
            flash.message = "Subscription deleted successfully."
            render([message: flash.message] as JSON)
        } else {
            flash.error = "Subscription not found"
            render([error: flash.error] as JSON)
        }

        //redirect(uri: '/')
    }

    def saveSubscription(Long id) {
        Topic topic = Topic.get(id)
        User user = session.user
        log.info "topic=$topic, user=$user"
        Subscription subscription = new Subscription(
                user: user,
                topic: topic
        )
        log.info "subscription=$subscription, validate=${subscription.validate()}"
        if (subscription.validate()) {
            subscription.save(flush: true)
            flash.message = "Subscription saved successfully."
            render([message: flash.message] as JSON)
        } else {
            flash.error = "Could not save subscription"
            render([error: flash.error] as JSON)
        }
        // redirect(uri: '/')
    }

    def updateSubscription(Long topicId, String seriousness) {
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.load(topicId))
        Map jsonResponse = [:]
        if (subscription != null) {
            subscription.seriousness = Seriousness.convertSeriousness(seriousness)
            subscription.save(flush: true)
            flash.message = "Subscription updated successfully"
            jsonResponse.message = flash.message
        } else {
            flash.error = "Could not update subscription $subscription.errors.allErrors"
            jsonResponse.error = flash.error
        }
        render jsonResponse as JSON
    }


}
