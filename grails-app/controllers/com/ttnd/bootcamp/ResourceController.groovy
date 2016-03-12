package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.VO.PostVO

class ResourceController {

    def changeRating(Long id, Integer score) {
        Resource resource = Resource.get(id)
        ResourceRating resourceRating = ResourceRating.findOrCreateByUserAndResource(session.user, resource)
        resourceRating.score = score
        if (resourceRating.save(flush: true)) {
            render "Success"
        } else {
            render "Failure"
        }

    }

    def deleteResource(Long id) {
        User user = session.user
        if (User.canDeleteResource(user, id)) {
            Resource resource = Resource.load(id)
            if (resource) {
                resource.delete(flush: true)
                resource.deleteFile()
                flash.message = "Resource Deleted"
                render flash.message
                //  redirect(uri: '/login/loginHandler')
            } else {
                flash.error = "Resource not deleted--- ${resource.errors.allErrors.collect { message(error: it) }.join(',')}"
            }
        } else {
            flash.error = "Deletion Not Permissible"
        }
        //  redirect(uri: '/')
    }

    def searchResource(ResourceSearchCO co) {
        if (co.q) {
            co.visibility = Visibility.PUBLIC
            render view: "/resource/searchPage"
        } else {
            flash.error = "Search criteria not given"
            render flash.error
        }

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
        } else {
            flash.error = "Resource does not Exists"
            redirect(uri: '/')
        }
    }

    def showTrendingTopics() {
        Topic topic = new Topic()
        List<Topic> result = topic.getTrendingTopics()
        render "$result"
    }

    private def addToReadingItems(Resource resource) {
        List<User> subscribedUsers = resource.topic.subscriptions.user
        def ctx = startAsync()
        ctx.start {
            subscribedUsers.each {
                ReadingItem readingItem
                if (it == session.user) {
                    readingItem = new ReadingItem(user: it, resource: resource, isRead: true)
                } else {
                    readingItem = new ReadingItem(user: it, resource: resource, isRead: false)
                }
                if (readingItem.save()) {
                    log.info "Reading Item saved"
                } else {
                    log.error "Error saving reading Item $readingItem.errors.allErrors"
                }
            }
            ctx.complete()
        }
    }


    public static PostVO getPostInfo(Long id) {

        PostVO postVO = null

        createCriteria().get {
            eq('id', id)
        }.each
                {
                    resourceInfo ->
                        postVO = new PostVO(userId: resourceInfo.createdBy.id,
                                topicId: resourceInfo.topic.id,
                                resourceId: resourceInfo.id,
                                user: resourceInfo.createdBy.name,
                                userName: resourceInfo.createdBy.userName,
                                topicName: resourceInfo.topic.name,
                                description: resourceInfo.description,
                                url: resourceInfo.class.equals(LinkResource) ? resourceInfo.url : null,
                                filePath: resourceInfo.class.equals(DocumentResource) ? resourceInfo.filePath : null,
                                createdDate: resourceInfo.dateCreated)
                }

        return postVO
    }


}
