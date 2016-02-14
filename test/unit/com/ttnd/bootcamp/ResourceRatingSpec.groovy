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
        resourceRating.save(flush: true)

        then:
        resourceRating.count() == 1

        when:
        ResourceRating newResourceRating = new ResourceRating(score: 4, user: user, resource: resource)
        newResourceRating.save(flush: true)

        then:
        resourceRating.count() == 1
        newResourceRating.errors.allErrors.size() == 1
        newResourceRating.errors.getFieldErrorCount('user') == 1
    }


    @Unroll("ResourceRating Validation:Executing #sno")
    void "Test ResourceRating validations"() {
        setup:
        ResourceRating resourceRating = new ResourceRating(score: score, resource: resource, user: user)

        when:
        Boolean result = resourceRating.validate()

        then:
        result == valid

        where:
        sno | score | resource           | user       | valid
        1   | 3     | new LinkResource() | new User() | true
        2   | 0     | new LinkResource() | new User() | false
        3   | 1     | new LinkResource() | new User() | true
        4   | 5     | new LinkResource() | new User() | true
        5   | 6     | new LinkResource() | new User() | false
        6   | null  | new LinkResource() | new User() | false
        7   | 3     | null               | new User() | false
        8   | 3     | new LinkResource() | null       | false

    }

}
