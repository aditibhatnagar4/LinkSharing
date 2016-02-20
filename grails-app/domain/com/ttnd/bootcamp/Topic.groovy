package com.ttnd.bootcamp

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
            if(subscription.save(flush:true))
            {
                log.info "${subscription}-> ${this.createdBy} subscribed for ${this}"
            }
            else
            {
                log.info "Not subscribed--- ${subscription.errors.allErrors}"
            }
        }

    }

    String toString() {
        return name
    }

}
