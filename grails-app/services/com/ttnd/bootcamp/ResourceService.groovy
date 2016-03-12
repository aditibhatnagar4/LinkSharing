package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.PostVO
import grails.transaction.Transactional


@Transactional
class ResourceService {

    def serviceMethod() {

    }

    List<PostVO> search(ResourceSearchCO resourceSearchCO){

        List<PostVO> posts = []

        List<Resource> resources = Resource.search(resourceSearchCO).list()

        posts = resources?.collect{ Resource.getPostInfo(it.id) }

        return posts
    }
}
