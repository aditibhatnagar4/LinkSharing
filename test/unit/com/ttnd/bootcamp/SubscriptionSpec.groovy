package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "User should not be able to subscribe to topic multiple times"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PUBLIC, createdBy: user)
        Subscription subscription = new Subscription(seriousness: Seriousness.CASUAL, user: user, topic: topic)

        when:
        subscription.save()

        then:
        subscription.count() == 1

        when:
        Subscription newSubscription = new Subscription(seriousness: Seriousness.CASUAL, user: user, topic: topic)
        newSubscription.save()

        then:
        Subscription.count() == 1
        newSubscription.errors.allErrors.size() == 1
        newSubscription.errors.getFieldErrorCount('user') == 1
    }


    @Unroll("Subscription Validation:Executing #sno")
    void "Test Subscription validations"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PUBLIC, createdBy: user)
        Subscription subscription = new Subscription(seriousness: seriousness, user: user1, topic: topic1)

        when:
        Boolean result = subscription.validate()

        then:
        result == valid

        where:
        sno | seriousness        | user1 | topic1 | valid
        1   | Seriousness.CASUAL | user  | topic  | true
        2   | null               | user  | topic  | false
        3   | Seriousness.CASUAL | null  | topic  | false
        4   | Seriousness.CASUAL | user  | null   | false

    }

}
