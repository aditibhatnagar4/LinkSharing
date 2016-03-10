package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserController)
class UserControllerSpec extends Specification {

    def "CheckRegistration- New user registers for the application"() {
        setup:
        User user = new User(userName: userName,
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                confirmPassword: confirmPassword)
        when:
        controller.registerUser(user.userName,
                user.firstName,
                user.lastName,
                user.email,
                user.password,
                user.confirmPassword)
        user.save(flush: true)

        then:
        User.count()
        response.text == result

        where:
        userName | firstName | lastName    | email                       | password | confirmPassword | result
        "aditi"  | "aditi"   | "bhatnagar" | "aditi.bhatnagar@tothe.com" | "123abc" | "123abc"        | "aditi saved"
    }

    def "CheckUserIndex"() {
        when:
        session.user = user
        controller.index()

        then:
        response.text == result

        where:
        user                        | result
        new User(userName: "aditi") | "User aditi Dashboard"
    }

}
