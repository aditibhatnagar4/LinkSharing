package com.ttnd.bootcamp

import grails.transaction.Transactional

class LinkResourceController extends ResourceController {

    @Transactional
    def saveLinkResource(String url, String description, String topicName) {
        User user = session.user
        Topic topic= Topic.findByCreatedByAndName(user,topicName)
        Resource resource = new LinkResource(url: url,
                description: description,
                topic: topic,
                createdBy: user
        )
        if (resource.save(flush: true)) {
            flash.message = "Resource saved successfully."
            render flash.message
        } else {
            flash.error = "Topic not saved"
            render "flash.error $topic.errors.allErrors"
            // redirect controller: 'user', action: 'index'
        }
    }
}
