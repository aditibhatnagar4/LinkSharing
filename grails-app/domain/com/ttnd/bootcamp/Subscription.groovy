package com.ttnd.bootcamp

class Subscription {

    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static constraints = {

        user(unique:'topic')

    }

    static belongsTo=[user: User, topic:Topic]
}
