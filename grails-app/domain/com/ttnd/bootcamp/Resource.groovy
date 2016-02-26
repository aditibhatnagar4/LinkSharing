package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.RatingInfoVO

abstract class Resource {

    Date dateCreated
    Date lastUpdated

    String description

    static transients = ['ratingInfo']

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
                'topic' {
                    eq('id', co.topicId)
                    eq('visibility', co.visibility)
                }
                Resource.findAllById(co.topicId)
            }
        }

    }


    RatingInfoVO getResourceInfo() {
        List result = ResourceRating.createCriteria().get {
            projections {
                count('id', 'voteCount')
                sum('score')
                avg('score')
            }
            eq('resource', this)
            order('voteCount', 'desc')
        }

        new RatingInfoVO(totalVotes: result[0], totalScore: result[1], averageScore: result[2])
    }

    static List<Resource> getTopPosts() {
        List<Resource> resources = []
        def result= ResourceRating.createCriteria().list(max: 5){
            projections{
                property('resource.id')
            }

            groupProperty('resource.id')
            count('id','totalVotes')
            order('totalVotes','desc')
        }

        List list=result.collect{it[0]}
        resources=Resource.getAll(list)

        return resources
    }

}
