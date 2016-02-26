package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.Resource
import com.ttnd.bootcamp.VO.RatingInfoVO

class ResourceController {

    def index() {}

    def deleteResource(Long id) {
        Resource resource = Resource.load(id)
        if (resource) {
            resource.delete(flush: true)
            render "Resource Deleted"
        } else {
            render "Resource not deleted--- ${resource.errors.allErrors.collect { message(error: it) }.join(',')}"
        }
    }

    def searchResource(ResourceSearchCO co) {
        if (co.q) {
            co.visibility = Visibility.PUBLIC
        }
        render co.visibility
    }


    def showResource(Long id) {
        Resource resource = Resource.get(id)
        if (resource) {
            RatingInfoVO ratingInfoVO = resource.getResourceInfo()
            render "$ratingInfoVO"
        } else {
            render "Resource could not be found "
        }

    }

    def showTrendingTopics(){
        Topic topic=new Topic()
        List result=topic.getTrendingTopics()
        render "$result"
    }


}
