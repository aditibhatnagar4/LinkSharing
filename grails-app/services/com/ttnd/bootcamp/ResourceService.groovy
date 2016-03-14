package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.PostVO
import grails.transaction.Transactional


@Transactional
class ResourceService {

    List<PostVO> search(ResourceSearchCO resourceSearchCO){

        List<PostVO> posts

        List<Resource> resources = Resource.search(resourceSearchCO).list()

        posts = resources?.collect{ Resource.getPost(it.id) }

        return posts
    }

    def editResourceDescription(Resource resource, String description) {
        if (resource && description) {
            resource.description = description
            return resource.save(flush: true)
        } else {
            return null
        }
    }
}
