package com.ttnd.bootcamp

import groovy.util.logging.Slf4j

@Slf4j
class SubscriptionController {

    def index() {}

    def deleteSubscription(Long id) {
        Subscription subscription = Subscription.load(id)
        if (subscription != null) {
            subscription.delete(flush: true)
            render "Success"
        } else {
            render "subscription not found $subscription.errors.allErrors"

        }
    }

    //DOUBT: want to use default value for seriousness
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
            render "Subscription saved successfully."
        } else {
            render "Could not save subscription $subscription.errors.allErrors"

        }
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
