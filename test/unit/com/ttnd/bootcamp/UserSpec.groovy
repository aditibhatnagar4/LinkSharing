package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll

@Slf4j
@Stepwise
@TestFor(User)
class UserSpec extends Specification {

    def "Email address of employee should be unique"() {
        setup:
        String email = "aditi@tothenew.com"
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: email,
                password: "password",
                username: "aditi",
                admin: false,
                enabled: true)

        when:
        user.save(flush: true)

        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: email,
                password: "password",
                username: "aditi2",
                admin: false,
                enabled: true)
        newUser.save(flush: true)

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('email') == 1
    }

    def "User name of employee should be unique"() {
        setup:
        String userName = "aditi"
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a2@b.com",
                password: "password",
                username: userName,
                admin: false,
                enabled: true)
        when:
        user.save(flush: true)

        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                username: userName,
                admin: false,
                enabled: true)
        newUser.save(flush: true)

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('username') == 1
    }

    @Unroll("User Validation:Executing #sno")
    void "Test User validations"() {
        setup:
        User user = new User(firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                username: userName,
                admin: admin,
                enabled: active)
        when:
        Boolean result = user.validate()

        then:
        result == valid
        where:
        sno | firstName | lastName    | email     | password  | userName | admin | active | photo        | valid
        1   | ""        | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | "test.bytes" | false
        2   | "Aditi"   | "Bhatnagar" | "aditi"   | "test123" | "aditi"  | false | true   | "test.bytes" | false
        3   | "Aditi"   | "Bhatnagar" | null      | "test123" | "aditi"  | false | true   | "test.bytes" | false
        4   | "Aditi"   | "Bhatnagar" | ""        | "test123" | "aditi"  | false | true   | "test.bytes" | false
        5   | "Aditi"   | "Bhatnagar" | "a@b.com" | null      | "aditi"  | false | true   | "test.bytes" | false
        6   | "Aditi"   | ""          | "a@b.com" | "test123" | "aditi"  | false | true   | "test.bytes" | false
        7   | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | "test.bytes" | true
        8   | "Aditi"   | "Bhatnagar" | "a@b.com" | ""        | "aditi"  | false | true   | "test.bytes" | false
        9   | "Aditi"   | "Bhatnagar" | "a@b.com" | "test"    | "aditi"  | false | true   | "test.bytes" | false
        10  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test1"   | "aditi"  | false | true   | "test.bytes" | true
        11  | null      | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | "test.bytes" | false
        12  | "Aditi"   | null        | "a@b.com" | "test123" | "aditi"  | false | true   | "test.bytes" | false
        13  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | null  | true   | "test.bytes" | true
        14  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | null   | "test.bytes" | true

    }

    @Unroll("Checking full name:Executing #sno")
    def "Check Full Name"() {
        setup:
        User user = new User(firstName: firstName, lastName: lastName)

        expect:
        result == user.getName()

        where:
        sno | firstName | lastName    | result
        1   | ""        | ""          | ""
        2   | "Aditi"   | ""          | ""
        3   | ""        | "Bhatnagar" | ""
        4   | "Aditi"   | "Bhatnagar" | "Aditi Bhatnagar"
        5   | null      | null        | ""
        6   | "Aditi"   | null        | ""
        7   | null      | "Bhatnagar" | ""


    }

    @Unroll("Checking ToString :Executing #sno")
    def "CheckToString"() {
        setup:
        User user = new User(username: userName)

        when:
        result == user.toString()

        then:
        noExceptionThrown()

        where:
        sno | userName | result
        1   | "aditi"  | "aditi"
        2   | null     | null
    }

}
