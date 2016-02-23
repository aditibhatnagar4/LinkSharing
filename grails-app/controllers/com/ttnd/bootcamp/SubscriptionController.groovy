package com.ttnd.bootcamp

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

    def saveSubscription(Long id) {
        Topic topic = Topic.findById(id)
        User user = session["user"]
        Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.VERY_SERIOUS)
        if (subscription.validate()) {
            subscription.save(flush: true)
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
