package com.ttnd.bootcamp

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
class ResourceRating extends BaseDomain {

    Integer score

    static constraints = {
        score min: 1, max: 5
        user(unique: 'resource')

    }

    static belongsTo = [
            resource: Resource,
            user: User
    ]


}
