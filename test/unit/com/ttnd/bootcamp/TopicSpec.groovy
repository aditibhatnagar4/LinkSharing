package com.ttnd.bootcamp

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Topic)
@Mock(Subscription)
class TopicSpec extends Specification {

    def "Topic name should be unique"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                username: "aditi",
                admin: false,
                enabled: true)
        String name = "topic1"
        Topic topic = new Topic(name: name, visibility: Visibility.PUBLIC, createdBy: user)

        when:
        topic.save(flush: true)

        then:
        topic.count() == 1

        when:
        Topic newTopic = new Topic(name: name, visibility: Visibility.PRIVATE, createdBy: user)
        newTopic.save(flush: true)

        then:
        Topic.count() == 1
        newTopic.errors.allErrors.size() == 1
        newTopic.errors.getFieldErrorCount('name') == 1
    }


    @Unroll("Topic Validation:Executing #sno")
    void "Test Topic validations"() {
        setup:
        Topic topic = new Topic(name: name, visibility: visibility, createdBy: createdBy)

        when:
        Boolean result = topic.validate()

        then:
        result == valid

        where:
        sno | name     | visibility        | createdBy  | valid
        1   | "topic1" | Visibility.PUBLIC | new User() | true
        2   | ""       | Visibility.PUBLIC | new User() | false
        3   | null     | Visibility.PUBLIC | new User() | false
        4   | "topic1" | null              | new User() | false
        5   | "topic1" | Visibility.PUBLIC | null       | false

    }

    @Unroll("Checking ToString :Executing #sno")
    def "CheckToString"() {
        setup:
        Topic topic = new Topic(name: name)

        when:
        result == topic.toString()

        then:
        noExceptionThrown()

        where:
        sno | name     | result
        1   | "topic1" | "topic1"
        2   | null     | null
    }

}
