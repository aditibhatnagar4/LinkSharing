package com.ttnd.bootcamp

import com.ttnd.bootcamp.VO.TopicVO
import groovy.util.logging.Slf4j

@Slf4j
class LinksharingTagLib {

//  static defaultEncodeAs = [taglib: 'html']
//  static encodeAsForTags = [trendingTopics: [taglib: 'raw']]

    static returnObjectForTags = ['canUpdateTopic']

    static namespace = 'ls'

    def markAsRead = { attrs, body ->
        User user = session.user
        Long resourceId = attrs.id
        if (user) {
            if (attrs.isRead == true) {
                out << "<a href='${createLink(controller: 'readingItem', action: 'changeIsRead', params: [id: resourceId, isRead: false])}' " + ">Mark  as unread</a>"
            } else {
                out << "<a href='${createLink(controller: 'readingItem', action: 'changeIsRead', params: [id: resourceId, isRead: true])}' " + ">Mark  as read</a>"
            }
        }
    }

    def trendingTopics = {
        User user = session.user
        List<TopicVO> topics = []
        if (user) {
            topics = Topic.getTrendingTopics()
        }
        out << render(template: "/topic/show", model: [topics: topics])
    }

    def topPosts = {
        List<Resource> topPosts = Resource.getTopPosts()
        out << render(template: "/login/topPost", model: [topPosts: topPosts])
    }

    def canDeleteResource = { attrs, body ->
        Long resourceId = attrs.resourceId
        User user = session.user
        if (user) {
            Boolean canDelete = User.canDeleteResource(user, resourceId)
            if (canDelete) {
                out << "<a href='${createLink(controller: 'Resource', action: 'deleteResource', params: [id: resourceId])}' " + ">Delete</a>"
            }
        }
    }


    def resourceType = { attrs, body ->
        def resourceId = attrs.resourceId
        def resourceType = Resource.checkResourceType(resourceId)

        if (resourceType == "LinkResource") {
            def resourceLink = attrs.url
            out << "<a href='${resourceLink}' target='_blank' controller='LinkResource' action='saveLinkResource'>View Full Site</a>"
        } else if (resourceType == "DocumentResource") {
            out << "<a href='${createLink(controller: 'DocumentResource', action: 'download', params: [id: resourceId])}'>Download&nbsp;</a>"
        }
    }

    def showSubscribe = { attrs, body ->
        User user = session.user
        Topic topic = Topic.get(attrs.topicId)
        if (user.id != topic.createdBy.id) {
            if (attrs.topicId != null && user != null) {
                if (user.isSubscribed(attrs.topicId)) {
                    out << "<div class='unsubscribe' data-topicId='${attrs.topicId}'><a href='#'>Unsubscribe</a></div>"
                } else {
                    out << "<div class='subscribe' data-topicId='${attrs.topicId}'><a href='${createLink(controller: 'subscription', action: 'saveSubscription', params: [id: attrs.topicId])}'>Subscribe</a></div>"
                }

            }
        }
    }

//    def showDelete = { attrs, body ->
//        User user = session.user
//        if (attrs.topicId != null && user != null) {
//            if (user.isSubscribed(attrs.topicId)) {
//                out << "<span class=\"glyphicon glyphicon-trash col-xs-1 font-size-md\"></span>"
//            }
//
//        }
//    }

    def showDelete = { attrs, body ->
        if (canUpdateTopic(attrs.topicId)) {
            out << "<a href=\"${createLink(controller: 'topic', action: 'delete', params: [topicId: attrs.topicId])}\"><span class=\"glyphicon glyphicon-trash col-xs-1 font-size-md \"></span></a>"
        }
    }


    def showSave = { attrs, body ->
        User user = session.user
        if (attrs.topicId != null && user != null) {
            if (!(user.isSubscribed(attrs.topicId))) {
                out << "<div class=\"col-xs-3\"><g:submitButton name=\"Save\"\n" + "                                 class=\"btn btn-primary\">Save</g:submitButton>\n" + "</div>\n" + "\n" + "<div class=\"col-xs-4\"><button type=\"button\"\n" + "class=\"btn btn-primary\">Cancel</button>\n" + "</div>"
            }

        }
    }


    def subscriptionCount = { attrs, body ->
        Integer subscriptionCount = 0
        if (attrs.topicId) {
            Topic topic = Topic.read(attrs.topicId)
            User user = session.user
            subscriptionCount = Subscription.countByUserAndTopic(user, topic)
        }
        out << subscriptionCount

    }

    def resourceCount = { attrs, body ->
        Integer resourceCount = 0
        if (attrs.topicId) {
            Topic topic = Topic.read(attrs.topicId)
            resourceCount = Resource.countByTopic(topic)

        }
        out << resourceCount
    }

    def topicCount = { attrs, body ->
        Integer topicCount = 0
        if (attrs.userId) {
            User user = User.read(attrs.userId)
            topicCount = Topic.countByCreatedBy(user)

        }
        out << topicCount
    }

    def canEdit = { attrs, body ->
        if (canUpdateTopic(attrs.topicId)) {
            out << "<a href='#'>Edit&nbsp;</a>"
        }
    }


    def userImage = { attrs, body ->
        User user = User.findById(attrs.id)
        if (user) {
            String src = "${createLink(controller: 'user', action: 'image', params: [id: attrs.id])}"
            out << "<a href='${createLink(controller: 'subscription', action: 'index', params: [id: attrs.id])}'><img src=${src} width='64px' height='64px' class='img img-responsive img-thumbnail'></a>"
        }
    }

    def showLink = { attrs, body ->
        if (session.user) {
            out << "<a href='#'>${attrs.linkname}&nbsp;</a>"
        }
    }

//make it into template
    def seriousnessDropdown = { attrs, body ->
//        Long id = attrs.topicId
//        if (session.user) {
//            out << render(template: "/user/seriousnessDropdown")
//        }

        Long topicId = attrs.topicId
        User user = session.user

        if (user != null) {
            Topic topic = Topic.get(topicId)
            Subscription subscription = Subscription.findByTopicAndUser(topic, session.user)

            if (subscription != null) {

                out << g.select(class: 'seriousness btn btn-primary',
                        topicId: topicId,
                        name: 'seriousness',
                        id: 'seriousness',
                        from: com.ttnd.bootcamp.Seriousness.values(),
                        value: subscription.seriousness)
            }
        }
    }

    def visibilityDropdown = { attrs, body ->
        Long topicId = attrs.topicId
        Topic topic = Topic.get(topicId)
        if (topic && canUpdateTopic(topicId)) {
            out << g.select(from: com.ttnd.bootcamp.Visibility.values(),
                    name: 'visibility',
                    id: 'visibility',
                    class: 'visibility btn btn-primary',
                    topicName: topic.name,
                    value: topic.visibility)


        }
    }

    Boolean canUpdateTopic(topicId) {
        Topic topic = Topic.get(topicId)
        return (session.user != null && (session.user.admin == true || topic.createdBy.id == session.user.id))
    }
}




