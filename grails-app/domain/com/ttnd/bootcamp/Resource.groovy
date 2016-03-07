package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.PostVO
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

    //do it on basis of rating
    static List<Resource> getTopPosts() {
        List<Resource> resources = []
        def result = ResourceRating.createCriteria().list(max: 5) {
            projections {
                property('resource.id')
            }

            groupProperty('resource.id')
            count('id', 'totalVotes')
            order('totalVotes', 'desc')
        }

        List list = result.collect { it[0] }
        resources = Resource.getAll(list)

        return resources
    }


    public static def checkResourceType(Long id) {
        Resource resource = Resource.read(id)
        if (resource.class.equals(LinkResource))
            return "LinkResource"
        else if (resource.class.equals(DocumentResource))
            return "DocumentResource"
    }

    public canViewBy(User user) {
        if (this.topic.canViewedBy(user)) {
            return true
        }
        return false
    }


    public static PostVO getPost(Long resourceId) {
        def obj = Resource.createCriteria().get {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                'topic' {
                    property('id')
                    property('name')
                }
                'createdBy' {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                    property('photo')
                }
                property('lastUpdated')
            }
            eq('id', resourceId)
        }
        return new PostVO(resourceId: obj[0], description: obj[1], url: obj[2], filePath: obj[3], topicId:
                obj[4], topicName: obj[5], userId: obj[6], userName: obj[7], userFirstName: obj[8], userLastName: obj[9], userPhoto: obj[10], isRead: "", postDate: obj[11], resourceRating: 0)
    }

}
