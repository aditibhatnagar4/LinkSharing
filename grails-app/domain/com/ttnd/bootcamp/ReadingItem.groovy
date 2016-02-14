package com.ttnd.bootcamp

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
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
