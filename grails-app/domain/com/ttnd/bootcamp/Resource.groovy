package com.ttnd.bootcamp

abstract class Resource {

    String description
    Date dateCreated
    Date lastUpdated

    static mapping={
        description(type:'text')
    }

    static constraints = {
    }

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]
}
