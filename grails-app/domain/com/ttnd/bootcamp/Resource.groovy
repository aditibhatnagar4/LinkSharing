package com.ttnd.bootcamp

abstract class Resource extends BaseDomain {

    String description

    static mapping={
        description(type:'text')
    }

    static constraints = {
    }

    static belongsTo = [
            createdBy: User,
            topic: Topic
    ]
    static hasMany = [
            ratings: ResourceRating,
            readingItems: ReadingItem
    ]
}
