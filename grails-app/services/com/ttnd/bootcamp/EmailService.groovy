package com.ttnd.bootcamp

import com.ttnd.bootcamp.DTO.EmailDTO
import grails.transaction.Transactional

@Transactional
class EmailService {

    def groovyPageRenderer
    def mailService

    void sendMail(EmailDTO emailDTO) {

        def content

        if(emailDTO.model.newPassword)
            content = groovyPageRenderer.render(template: "/email/password",
                    model: [newPassword: emailDTO.model.newPassword])
        else
            content = groovyPageRenderer.render(template: "/email/invite",
                    model: [topicId: emailDTO.model.id, hostURL: emailDTO.model.hostURL])

        mailService.sendMail {
            to emailDTO.to
            subject emailDTO.subject
            html content
        }
    }

}