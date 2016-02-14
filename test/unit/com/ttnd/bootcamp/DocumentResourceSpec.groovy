package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    @Unroll("DocumentResource Validation:Executing #sno")
    void "Test DocumentResource validations"() {
        setup:
        DocumentResource documentResource = new DocumentResource(description: description,
                createdBy: createdBy,
                topic: topic,
                filePath: filePath)

        when:
        Boolean result = documentResource.validate()

        then:
        result == valid

        where:
        sno | createdBy  | description | topic       | filePath | valid
        1   | new User() | "desc1"     | new Topic() | "a/com/" | true
        2   | new User() | 1           | new Topic() | "a/com/" | false
        3   | null       | "desc1"     | new Topic() | "a/com/" | false
        4   | new User() | null        | new Topic() | "a/com/" | false
        5   | new User() | "desc1"     | null        | "a/com/" | false
        6   | new User() | ""          | new Topic() | "a/com/" | false
        7   | new User() | "desc1"     | new Topic() | ""       | false

    }

}
