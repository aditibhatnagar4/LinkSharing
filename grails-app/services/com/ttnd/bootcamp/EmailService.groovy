package com.ttnd.bootcamp

import com.ttnd.bootcamp.DTO.EmailDTO
import grails.transaction.Transactional

@Transactional
class EmailService {

    def groovyPageRenderer
    def mailService

    void sendMail(EmailDTO emailDTO) {

        def content

        if(emailDTO.model) {
            if (emailDTO.model.newPassword)
                content = groovyPageRenderer.render(template: "/email/password",
                        model: [newPassword: emailDTO.model.newPassword])
            else
                content = groovyPageRenderer.render(template: "/email/invite",
                        model: [topicId: emailDTO.model.id, hostURL: emailDTO.model.hostURL])
        }

        mailService.sendMail {
            async true
            to emailDTO.to
            subject emailDTO.subject
           // html content
            html content ?: emailDTO.content
        }
    }

    def sendUnreadResourcesEmail(User user, List<Resource> unreadResources) {
        EmailDTO emailDTO1 = new EmailDTO(to: user.email,
                subject: "Unread items",
                content: groovyPageRenderer.render(view: '/email/unreadResources',
                        model: [user: user, unreadResources: unreadResources]))
        sendMail(emailDTO1)
    }

}