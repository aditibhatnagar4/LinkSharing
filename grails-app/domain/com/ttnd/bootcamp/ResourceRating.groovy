package com.ttnd.bootcamp

class ResourceRating {

    User user
    Integer score
    Date dateCreated
    Date lastUpdated

    static constraints = {
        score size: 1..5
        user(unique: 'resource')

    }

    static belongsTo = [resource: Resource]
}
