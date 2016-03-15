package com.ttnd.bootcamp

import grails.converters.JSON
import groovy.util.logging.Slf4j

@Slf4j
class SubscriptionController {

    def deleteSubscription(Long id) {
        Topic topic = Topic.get(id)
        User user = session.user

        Subscription subscription = Subscription.findByUserAndTopic(user,topic)
        if (subscription != null && subscription.topic.createdBy.id != session.user.id) {
            subscription.delete(flush: true)
            flash.message = "Subscription deleted successfully."
            render([message: flash.message] as JSON)
        } else {
            flash.error = "Subscription not found"
            render([error: flash.error] as JSON)
        }

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
            flash.error = "Could not update subscription"
            jsonResponse.error = flash.error
        }
        render jsonResponse as JSON
    }


}
