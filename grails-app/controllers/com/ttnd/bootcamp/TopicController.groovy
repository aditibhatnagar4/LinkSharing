package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.DTO.EmailDTO
import com.ttnd.bootcamp.VO.TopicVO
import grails.converters.JSON

class TopicController {

    def emailService

    def showTopic(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        if (topic == null) {
            flash.error = "Topic does not exist"
            redirect(controller: 'login', action: 'index')
        } else {
            List<User> subscribedUsers = Topic.getSubscribedUser(co.topicId)
            List<ReadingItem> readingItems = ReadingItem.createCriteria().list(max: 10) {
                'resource' {

                    eq('topic.id', co.topicId)

                }
            }

            if (topic.visibility == Visibility.PUBLIC) {

                render(view: '/topic/topicShowPage', model: [subscribedUsers: subscribedUsers,
                                                             readingItems   : readingItems,
                                                             topic          : topic])
            } else if (topic.visibility == Visibility.PRIVATE) {
                User user = session.user
                Subscription subscription = Subscription.findByUserAndTopic(user, topic)
                if (subscription == null) {
                    flash.error = "Topic is Private, User is not Subscribed to it"
                    redirect(controller: 'login', action: 'index')
                } else {
                    render(view: '/topic/topicShowPage', model: [subscribedUsers: subscribedUsers,
                                                                 readingItems   : readingItems,
                                                                 topic          : topic])
                }
            }
        }
    }

    def saveTopic(String topicName, String visibility) {
        Topic topic = Topic.findOrCreateByNameAndCreatedBy(topicName, session.user)
        topic.visibility = Visibility.convertVisibility(visibility)
        Map jsonResponse = [:]
        if (topic.save(flush: true)) {
            flash.message = "Success!"
            jsonResponse.message = flash.message
        } else {
            flash.error = "Topic not saved"
            jsonResponse.error = flash.error
            //  redirect controller: 'user', action: 'index'
        }
        render jsonResponse as JSON
    }

    def delete(Long topicId) {

        Topic topic = Topic.get(topicId)
        User user = session.user

        if (topic) {
            if (user.admin || (topic.createdBy.id == user.id))
                topic.delete(flush: true)

        } else
            flash.error = "Topic unavailable."

        redirect(controller: 'login', action: 'index')

    }

    def invite(Long topic, String emailID) {
        log.info "=============topic id :${topic}"
        Topic topicInstance = Topic.get(topic)

        String to = emailID
        String subject = "Invitation for a new topic."
        String hostURL = grailsApplication.config.grails.serverURL

        EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [id: topic, hostURL: hostURL])

        if (topicInstance == null) {
            flash.error = "Topic could not be found."
            render flash.error
        } else {
            emailService.sendMail(emailDTO)
            flash.message = "Email sent"
            render flash.message
        }

        //  redirect(controller: "login", action: "index")
    }

    def join(Long id) {

        if (session.user) {

            User user = session.user
            Topic topic = Topic.get(id)
            Subscription subscription = new Subscription(user: user, topic: topic)

            if (subscription.save(flush: true))
                flash.message = "You have subscribed to this topic successfully."
            else
                flash.error = "Failure. Could not subscribe to the topic."

            redirect(controller: "login", action: "index")
        }
    }

    def titleUpdate(Long topicId, String name) {
       // render "${topic} ${name}"
        Topic topic = Topic.get(topicId)
        Map json = [:]
        if (name) {
            topic.name = name
        }
        if (topic.save(flush: true)) {
            json.message = "Topic Name Updated"
        } else {
            json.error = "Unable to update topic name"
        }
        render json as JSON
    }

}
