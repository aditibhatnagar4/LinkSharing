package com.ttnd.bootcamp

import groovy.util.logging.Slf4j

@Slf4j
class SubscriptionController {

    def deleteSubscription(Long id) {
        Subscription subscription = Subscription.load(id)
        if (subscription != null) {
            subscription.delete(flush: true)
            flash.message = "Subscription deleted successfully."
        } else {
            flash.error = "Subscription not found"
        }
        redirect(uri: '/')
    }

    def saveSubscription(Long id) {
        Topic topic = Topic.findById(id)
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
        } else {
            flash.error = "Could not save subscription"
        }
        redirect(uri: '/')
    }

    def updateSubscription(Long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        if (subscription != null) {
            subscription.seriousness = Seriousness.convertSeriousness(seriousness)
            subscription.save(flush: true)
            render "Subscription updated successfully"
        } else {
            render "Could not update subscription $subscription.errors.allErrors"
        }
    }


}
