package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.UserCO
import com.ttnd.bootcamp.VO.PostVO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserController)
@Mock([User, Subscription, Topic, Resource, ReadingItem])
class UserControllerSpec extends Specification {

    def "CheckRegistration- New user registers for the application"() {
        setup:
        UserCO user = new UserCO(username: userName,
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                confirmPassword: confirmPassword
        )
        String url = "/login/loginHandler?username=${userName}&password=${password}"
        when:
        controller.registerUser(user)

        then:
        User.count()
        response.redirectedUrl == url



        where:
        userName | firstName | lastName    | email                       | password | confirmPassword
        "aditi"  | "aditi"   | "bhatnagar" | "aditi.bhatnagar@tothe.com" | "123abc" | "123abc"
    }


    def "CheckRegistration- New user registers for the application with photo"() {
        setup:
        UserCO user = new UserCO(username: userName,
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                confirmPassword: confirmPassword,
                photo: photo)
        String url = "/login/loginHandler?username=${userName}&password=${password}"
        when:
        controller.registerUser(user)

        then:
        User.count()
        response.redirectedUrl == url



        where:
        userName | firstName | lastName    | email                       | password | confirmPassword | photo
        "aditi"  | "aditi"   | "bhatnagar" | "aditi.bhatnagar@tothe.com" | "123abc" | "123abc"        | "123".getBytes()
    }


    def "CheckUserIndex"() {
        given:
        User user = new User(username: "aditi",
                firstName: "aditi",
                lastName: "bhatnagar",
                email: "aditi@gmail.com",
                password: "password",
                confirmPassword: "password",
                enabled: true,
                admin: false)

        user.metaClass.getSubscribedTopic = {
            [new Topic()]
        }
        controller.session.user = user
        User.metaClass.static.getReadingItems = { User user1 ->
            [new PostVO()]
        }

        when:
        controller.index()

        then:
        view == "/user/myAccount"
        model.subscribedTopics.size() == 1
        model.readingItems.size() == 1
        model.subscribedTopics.size() == 1
    }

//    def "Test save action"() {
//        controller.session.user
//
//    }


}
