package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }


    def "Email address of employee should be unique"() {
        setup:
        String email = "aditi@tothenew.com"
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: email,
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)

        when:
        user.save()

        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: email,
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        newUser.save()

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
                email: "a@b.com",
                password: "password",
                userName: userName,
                admin: false,
                active: true)
        when:
        user.save()

        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: userName,
                admin: false,
                active: true)
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('userName') == 1
    }

    @Unroll("User Validation:Executing #sno")
    void "Test User validations"() {
        setup:
        User user = new User(firstName: firstName,
                             lastName: lastName,
                             email: email,
                             password: password,
                             userName: userName,
                             admin: admin,
                             active: active)
        when:
        Boolean result = user.validate()

        then:
        result == valid
        where:
        sno | firstName | lastName    | email     | password  | userName | admin | active | valid
        1   | ""        | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | false
        2   | "Aditi"   | "Bhatnagar" | "aditi"   | "test123" | "aditi"  | false | true   | false
        3   | "Aditi"   | "Bhatnagar" | null      | "test123" | "aditi"  | false | true   | false
        4   | "Aditi"   | "Bhatnagar" | ""        | "test123" | "aditi"  | false | true   | false
        5   | "Aditi"   | "Bhatnagar" | "a@b.com" | null      | "aditi"  | false | true   | false
        6   | "Aditi"   | ""          | "a@b.com" | "test123" | "aditi"  | false | true   | false
        7   | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | true
        8   | "Aditi"   | "Bhatnagar" | "a@b.com" | ""        | "aditi"  | false | true   | false
        9   | "Aditi"   | "Bhatnagar" | "a@b.com" | "test"    | "aditi"  | false | true   | false
        10  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test1"   | "aditi"  | false | true   | true
        11  | null      | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | true   | false
        12  | "Aditi"   | null        | "a@b.com" | "test123" | "aditi"  | false | true   | false
        13  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | null  | true   | true
        14  | "Aditi"   | "Bhatnagar" | "a@b.com" | "test123" | "aditi"  | false | null   | true

    }

}
