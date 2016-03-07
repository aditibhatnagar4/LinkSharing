package com.ttnd.bootcamp

import groovy.util.logging.Slf4j

@Slf4j
class SubscriptionController {

    def index() {}

    def deleteSubscription(Long id) {
        Subscription subscription = Subscription.load(id)
        if (subscription != null) {
            subscription.delete(flush: true)
            //render "Success"
            flash.message = "Subscription deleted successfully."
        } else {
            // render "subscription not found $subscription.errors.allErrors"
            flash.error = "Subscription not found"

        }
        redirect(uri: '/')
    }

    def saveSubscription(Long id) {
        Topic topic = Topic.findById(id)
        User user = session["user"]
        log.info "topic=$topic, user=$user"
        Subscription subscription = new Subscription(
                user: user,
                topic: topic

        )
        log.info "subscription=$subscription, validate=${subscription.validate()}"
        if (subscription.validate()) {
            log.info "subscription=$subscription, $subscription.seriousness"
            subscription.save(flush: true)
            log.info "subscription=$subscription, $subscription.seriousness"
            // render "Subscription saved successfully."
            flash.message = "Subscription saved successfully."
        } else {
            //render "Could not save subscription $subscription.errors.allErrors"
            flash.error="Could not save subscription"

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
