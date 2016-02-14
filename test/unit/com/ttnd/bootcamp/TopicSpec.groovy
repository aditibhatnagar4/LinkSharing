package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "Topic name should be unique"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        String name = "topic1"
        Topic topic = new Topic(name: name, visibility: Visibility.PUBLIC, createdBy: user)

        when:
        topic.save()

        then:
        topic.count() == 1

        when:
        Topic newTopic = new Topic(name: name, visibility: Visibility.PRIVATE, createdBy: user)
        newTopic.save()

        then:
        Topic.count() == 1
        newTopic.errors.allErrors.size() == 1
        newTopic.errors.getFieldErrorCount('name') == 1
    }


    @Unroll("Topic Validation:Executing #sno")
    void "Test Topic validations"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        Topic topic = new Topic(name: name, visibility: visibility)
        when:
        Boolean result = topic.validate()

        then:
        result == valid
        where:
        sno | name     | visibility        | valid
        1   | "topic1" | Visibility.PUBLIC | true
        2   | ""       | Visibility.PUBLIC | false
        3   | null     | Visibility.PUBLIC | false
        4   | "topic1" | null              | false
        5   | "topic1" | Visibility.PUBLIC | false

    }

}
