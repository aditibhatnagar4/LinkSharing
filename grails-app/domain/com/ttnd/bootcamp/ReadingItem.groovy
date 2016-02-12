package com.ttnd.bootcamp

class ReadingItem {

    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static constraints = {
        user(unique:'resource')
    }

    static belongsTo = [user: User,resource:Resource]
}
