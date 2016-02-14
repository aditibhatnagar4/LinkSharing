package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    @Unroll("LinkResource Validation:Executing #sno")
    void "Test LinkResource validations"() {
        setup:
        LinkResource linkResource = new LinkResource(description: description,
                createdBy: createdBy,
                topic: topic,
                url: url)

        when:
        Boolean result = linkResource.validate()

        then:
        result == valid

        where:
        sno | createdBy  | description | topic       | url     | valid
        1   | new User() | "desc1"     | new Topic() | "a.com" | true
        2   | new User() | 1           | new Topic() | "a.com" | false
        3   | null       | "desc1"     | new Topic() | "a.com" | false
        4   | new User() | null        | new Topic() | "a.com" | false
        5   | new User() | "desc1"     | null        | "a.com" | false
        6   | new User() | ""          | new Topic() | "a.com" | false
        7   | new User() | "desc1"     | new Topic() | "acom"  | false

    }

}
