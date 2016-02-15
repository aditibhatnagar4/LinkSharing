package com.ttnd.bootcamp

class ReadingItem extends BaseDomain {

    Boolean isRead

    static constraints = {
        user(unique:'resource')
    }

    static belongsTo = [
            user: User,
            resource:Resource
    ]
}
