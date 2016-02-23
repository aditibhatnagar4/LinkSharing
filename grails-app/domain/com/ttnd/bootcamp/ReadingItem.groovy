package com.ttnd.bootcamp

class ReadingItem {

    Date dateCreated
    Date lastUpdated

    Boolean isRead

    static constraints = {
        user(unique: 'resource')
    }

    static belongsTo = [
            user    : User,
            resource: Resource
    ]
}
