package com.ttnd.bootcamp

class Subscription {

    Date dateCreated
    Date lastUpdated
    Seriousness seriousness = Seriousness.SERIOUS

    static constraints = {
        user(unique: 'topic')
    }

    static mapping = {
    }

    static belongsTo = [
            user : User,
            topic: Topic
    ]
}
