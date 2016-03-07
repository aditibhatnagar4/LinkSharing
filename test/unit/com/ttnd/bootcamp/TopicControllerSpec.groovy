package com.ttnd.bootcamp

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock([User, Topic, Subscription])
class TopicControllerSpec extends Specification {

    def "CheckTopicShow"() {
        setup:

        User user = new User(userName: "user5",
                active: true,
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword: Constants.DEFAULT_PASSWORD,
                firstName: "FName",
                lastName: "LName",
                email: "user5@ttnd.com")

        Topic topic = new Topic(name: 'groovy',
                visibility: visibility,
                createdBy: user)

        session.user = user
        topic.save(flush: true)

        when:
        controller.showTopic(topic.id)

        then:
        response.text == result

        where:
        visibility         | result
        Visibility.PUBLIC  | "Success, Subscribed to Public Topic"
        Visibility.PRIVATE | "Success, Subscribed to Private Topic"

        //case: topic does not exist ??
    }

    def "CheckTopicShow- User not subscribed to private topic"() {
        setup:
        User user = new User(userName: "user5",
                active: true,
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword:
                Constants.DEFAULT_PASSWORD,
                firstName: "Name",
                lastName: "Lname",
                email: "user5@ttnd.com")

        User user1 = new User(userName: "user6",
                active: true,
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword:
                Constants.DEFAULT_PASSWORD,
                firstName: "Name",
                lastName: "Lname",
                email: "user6@ttnd.com")

        Topic topic = new Topic(name: 'groovy',
                visibility: Visibility.PRIVATE,
                createdBy: user)

        session.user = user1
        topic.save(flush: true)

        when:
        controller.showTopic(topic.id)

        then:
        response.redirectedUrl == "/login/index"
    }


}
