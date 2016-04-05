package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def "User should not be able to subscribe to topic multiple times"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                username: "aditi",
                admin: false,
                enabled: true)
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PUBLIC, createdBy: user)
        Subscription subscription = new Subscription(seriousness: Seriousness.CASUAL, user: user, topic: topic)

        when:
        subscription.save(flush: true)

        then:
        subscription.count() == 1

        when:
        Subscription newSubscription = new Subscription(seriousness: Seriousness.CASUAL, user: user, topic: topic)
        newSubscription.save(flush: true)

        then:
        Subscription.count() == 1
        newSubscription.errors.allErrors.size() == 1
        newSubscription.errors.getFieldErrorCount('user') == 1

    }

    @Unroll("Subscription Validation:Executing #sno")
    void "Test Subscription validations"() {
        setup:
        Subscription subscription = new Subscription(seriousness: seriousness, user: user, topic: topic)

        when:
        Boolean result = subscription.validate()

        then:
        result == valid

        where:
        sno | seriousness        | user       | topic       | valid
        1   | Seriousness.CASUAL | new User() | new Topic() | true
        2   | null               | new User() | new Topic() | false
        3   | Seriousness.CASUAL | null       | new Topic() | false
        4   | Seriousness.CASUAL | new User() | null        | false

    }

}
