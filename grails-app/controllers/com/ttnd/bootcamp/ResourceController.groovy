package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.Resource
import com.ttnd.bootcamp.VO.RatingInfoVO
import com.ttnd.bootcamp.VO.PostVO
class ResourceController {

    def index() {
    }

    def deleteResource(Long id) {
        if (User.canDeleteResource(session.user, id)) {
            Resource resource = Resource.load(id)
            if (resource) {
                resource.delete(flush: true)
                flash.message = "Resource Deleted"
                redirect(uri: '/login/loginHandler')
            } else {
                flash.error = "Resource not deleted--- ${resource.errors.allErrors.collect { message(error: it) }.join(',')}"
            }
        }
        else{
            flash.error = "Deletion Not Permissible"
        }
        redirect(uri: '/')
    }

    def searchResource(ResourceSearchCO co) {
        if (co.q) {
            co.visibility = Visibility.PUBLIC
        }
        else{
            flash.error="Search criteria not given"
        }
        render co.visibility
    }


    def showResource(Long id) {
        Resource resource = Resource.get(id)
        User user = session.user
        if (resource) {
            if (resource.canViewBy(user)) {
                PostVO post = Resource.getPost(id)


                if (user) {
                    log.info "before calling getScore()"
                    post.resourceRating = user.getScore(resource)
                }
                flash.message = "User Can Access the Resource"
//                RatingInfoVO ratingInfoVO = resource.getResourceInfo()
//                render "$ratingInfoVO"
                render(view: '/resource/post', model: [post: post])
            } else {
            render "Resource could not be found "
        }
            }
            else {
                flash.error = "Resource does not Exists"
                redirect(uri: '/')
            }



}









        def showTrendingTopics() {
        Topic topic = new Topic()
        List<Topic> result = topic.getTrendingTopics()
        render "$result"
    }

    def saveLinkResource(String url, String description, String topicName) {
        User user = session.user
        Topic topic= Topic.findByCreatedByAndName(user,topicName)
        Resource resource = new LinkResource(url: url,
                description: description,
                topic: topic,
                createdBy: user
        )

        if (resource.save(flush: true)) {
            flash.message = "Resouce saved successfully."
            render flash.message
        } else {
            flash.error = "Topic not saved"
             render "flash.error $topic.errors.allErrors"
           // redirect controller: 'user', action: 'index'


        }
    }


}
