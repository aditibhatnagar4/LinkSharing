package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO

abstract class Resource {

    Date dateCreated
    Date lastUpdated

    String description

    static mapping = {
        description(type: 'text')
    }

    static constraints = {
    }

    static belongsTo = [
            createdBy: User,
            topic    : Topic
    ]
    static hasMany = [
            ratings     : ResourceRating,
            readingItems: ReadingItem
    ]

    static namedQueries = {
        search { ResourceSearchCO co ->
            if (co.topicId) {
                eq('topicId', co.topicId)
            }
            if (co.balance) {
                ge('balance', co.balance)
            }
        }
    }
}
