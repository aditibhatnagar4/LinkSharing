package com.ttnd.bootcamp

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['permitAll'])
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
            addToReadingItems(resource)
        } else {
            flash.error = "Resource not saved"
            // redirect controller: 'user', action: 'index'
        }
        redirect(uri: '/')
    }
}
