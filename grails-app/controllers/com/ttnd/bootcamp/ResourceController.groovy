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
        } else {
            flash.error = "Search criteria not given"
            render flash.error
        }

    }

    def list(ResourceSearchCO co) {

        if (session.user) {
            if (session.user.admin) {

                List<User> users = User.search(co).list(max: co.max,
                        sort: co.sort,
                        order: co.order)

                List<PostVO> usersList = users?.collect {
                    user ->
                        new PostVO(userId: user.id,
                                userName: user.userName,
                                emailId: user.email,
                                firstName: user.firstName,
                                lastName: user.lastName,
                                active: user.active)
                }

                render(view: "/user/list", model: [usersList: usersList])
            } else
                redirect(controller: "login", action: "index")
        } else
            redirect(controller: "login", action: "index")

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

    private addToReadingItems(Resource resource) {
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

    def search(ResourceSearchCO resourceSearchCO) {
        List<PostVO> posts
        if (resourceSearchCO.q) {

            String html = ""

            List<Resource> resources = Resource.search(resourceSearchCO).list()

            posts = resources?.collect{ Resource.getPost(it.id) }

            render(view:'/resource/searchPage', model: [topicPosts: posts, q: resourceSearchCO.q])
        } else
            render "Enter text to be searched"

    }




}
