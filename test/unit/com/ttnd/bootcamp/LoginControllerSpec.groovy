package com.ttnd.bootcamp

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(LoginController)
@Mock([User, Resource, ResourceRating, Topic])

class LoginControllerSpec extends Specification {

    def "CheckIndexAction- if user's session is set, then  user should forward to user index action"() {
        setup:
        controller.session.user = new User()

        when:
        controller.index()

        then:
        response.forwardedUrl == "/user/index"
    }

    def "CheckIndexAction- if user's session is not set, then  error should be rendered"() {
        when:
        controller.index()

        then:
        response.text == "Login Failed"
    }

    def "CheckLogout- user's session is invalidated, and is redirected to the login index action"() {
        when:
        controller.logout()

        then:
        session.user == null
        response.forwardedUrl == "/login/index"
    }


    def "CheckLoginHandler- User is able to login "() {
        setup:
        User user = new User(firstName: "aditi",
                lastName: "bhatnagar",
                email: "aditi.bhatnagar@tothenew.com",
                password: Constants.DEFAULT_PASSWORD,
                userName: "aditi",
                confirmPassword: Constants.DEFAULT_PASSWORD,
                active: true)
        user.save(flush: true)

        when:
        controller.loginHandler(user.userName, user.password)

        then:
        response.redirectedUrl == '/login/index'
    }

    def "CheckLoginHandler- User is not Active"() {
        setup:
        User user = new User(userName: "user",
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword: Constants.DEFAULT_PASSWORD,
                firstName: "FName",
                lastName: "LName",
                email: "user@gmail.com")
        user.save(flush: true)

        when:
        controller.loginHandler(user.userName, user.password)

        then:
        response.text == "Your account is not active"
    }

    def "CheckLoginHandler- User is not Found"() {
        setup:
        User user = new User(userName: "user",
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword: Constants.DEFAULT_PASSWORD,
                firstName: "FName",
                lastName: "LName",
                email: "user@gmail.com")

        when:
        controller.loginHandler(user.userName, user.password)

        then:
        response.text == "User not found"
    }


}
