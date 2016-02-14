package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "ResourceRating can be given by a user only one time for a resource"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        Topic topic = new Topic(name: "topic1",
                visibility: Visibility.PUBLIC,
                createdBy: user)
        Resource resource = new LinkResource(description: "description",
                createdBy: user,
                topic: topic,
                url: "abc.com")
        ResourceRating resourceRating = new ResourceRating(score: 3, user: user, resource: resource)

        when:
        resourceRating.save()

        then:
        resourceRating.count() == 1

        when:
        ResourceRating newResourceRating = new ResourceRating(score: 4, user: user, resource: resource)
        newResourceRating.save()

        then:
        resourceRating.count() == 1
        newResourceRating.errors.allErrors.size() == 1
        newResourceRating.errors.getFieldErrorCount('user') == 1
    }


    @Unroll("ResourceRating Validation:Executing #sno")
    void "Test ResourceRating validations"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                userName: "aditi",
                admin: false,
                active: true)
        Topic topic = new Topic(name: "topic1",
                visibility: Visibility.PUBLIC,
                createdBy: user)
        Resource resource = new LinkResource(description: "description",
                createdBy: user,
                topic: topic,
                url: "abc.com")

        ResourceRating resourceRating = new ResourceRating(score: score, resource: resource1, user: user1)

        when:
        Boolean result = resourceRating.validate()

        then:
        result == valid

        where:
        sno | score | resource1 | user1 | valid
        1   | 3     | resource  | user  | true
        2   | 0     | resource  | user  | false
        3   | 1     | resource  | user  | true
        4   | 5     | resource  | user  | true
        5   | 6     | resource  | user  | false
        6   | null  | resource  | user  | false
        7   | 3     | null      | user  | false
        8   | 3     | resource  | null  | false

    }

}
