package com.ttnd.bootcamp

class Topic extends BaseDomain {

    String name
    Visibility visibility

    static belongsTo = [
            createdBy: User
    ]

    static hasMany = [
            subscriptions: Subscription, 
            resources: Resource
    ]

    static constraints = {
        name blank: false, unique: true
    }
}
