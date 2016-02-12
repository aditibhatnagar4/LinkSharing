package com.ttnd.bootcamp

class Topic {

    String name;
    Date dateCreated;
    Date lastUpdated;
    Visibility visibility;

    static belongsTo = [createdBy: User]

    static hasMany = [subscriptions: Subscription, resources: Resource]

    static constraints = {

        name blank: false, unique: true

    }
}
