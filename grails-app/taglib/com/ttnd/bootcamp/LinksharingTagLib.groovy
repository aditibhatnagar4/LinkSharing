package com.ttnd.bootcamp

import com.ttnd.bootcamp.VO.TopicVO
import groovy.util.logging.Slf4j

@Slf4j
class LinksharingTagLib {

//  static defaultEncodeAs = [taglib: 'html']
//  static encodeAsForTags = [trendingTopics: [taglib: 'raw']]

    static namespace = 'ls'

    def markAsRead = { attrs, body ->
        User user = session.user
        if (user) {
            if (attrs.isRead == true) {
                out << "Mark as unread"
            } else {
                out << "Mark as read"
            }
        }
    }

    def trendingTopics = {
        User user = session.user
        List<TopicVO> trendingTopics = []
        if (user) {
            trendingTopics = Topic.getTrendingTopics()
        }
        out << render(template: "/topic/show", model: [trendingTopics: trendingTopics])
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
                out << "<a href='${createLink(controller: 'Resource', action: 'delete', params: [id: resourceId])}' " + "class='pull-right'>Delete</a>"
            }
        }
    }


    def resourceType = { attrs, body ->
        def resourceId = attrs.resourceId
        def resourceType = Resource.checkResourceType(resourceId)
        def resourceLink = attrs.url
        if (resourceType == "LinkResource") {
            out << "<a href='${resourceLink}' class='pull-right' target='_blank'>View Full Site</a>"
        } else if (resourceType == "DocumentResource") {
            out << "<a href='#' class='pull-right'>Download</a>"
        }
    }

    def showSubscribe = { attrs, body ->
        User user = session.user
        if (attrs.topicId != null && user != null) {
            if (user.isSubscribed(attrs.topicId)) {
                out << "<a href='${createLink(controller: 'subscription', action: 'deleteSubscription', params: [id: attrs.topicId])}'>Unsubscribe</a>"
            } else {
                out << "<a href='${createLink(controller: 'subscription', action: 'saveSubscription', params: [id: attrs.topicId])}'>Subscribe</a>"
            }

        }
    }


    def showDelete = { attrs, body ->
        User user = session.user
        if (attrs.topicId != null && user != null) {
            if (user.isSubscribed(attrs.topicId)) {
                out << "<span class=\"glyphicon glyphicon-trash col-xs-1 font-size-md\"></span>"
            }

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
        if (session.user) {
            out << "<a href='#'>Edit&nbsp;</a>"
        }
    }


    def userImage = { attrs, body ->
        User user = User.findById(attrs.id)
        if (user) {
            String src = "${createLink(controller: 'user', action: 'image', params: [id: attrs.id])}"
            out << "<img src=${src} width='64px' height='64px' class='img img-responsive img-thumbnail'>"
        }
    }

    def showLink = { attrs, body ->
        if (session.user) {
            out << "<a href='#'>${attrs.linkname}&nbsp;</a>"
        }
    }

    def seriousnessDropdown = { attrs, body ->
        if (session.user) {
            out << " <g:select name=\"dropdownSeriousness2\"\n" + "from=\"${com.ttnd.bootcamp.Seriousness.values()}\"\n" + "class=\"btn btn-primary dropdown-toggle\" type=\"button\"\n" + "                               data-toggle=\"dropdown\">Seriousness\n" + "<span class=\"caret\"></span>\n" + "<ul class=\"dropdown-menu\">\n" +
                    "\n" + "</ul></g:select>"
        }
    }

    def visibilityDropdown = { attrs, body ->
        if (session.user) {
            out << "<g:select from=\"${com.ttnd.bootcamp.Visibility.values()}\"\n" + "                                      name=\"dropdownVisibility\"\n" + "class=\"btn btn-primary dropdown-toggle\"\n" + "                                      type=\"button\"\n" + "data-toggle=\"dropdown\">Visibility\n" + "<span class=\"caret\"></span>\n" + "                                <ul class=\"dropdown-menu\">\n" + "\n" + "</ul></g:select>"
        }
    }
}
