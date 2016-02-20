package com.ttnd.bootcamp

class ResourceRating {

    Date dateCreated
    Date lastUpdated

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
