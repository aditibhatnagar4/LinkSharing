package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

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
        sno | createdBy  | description | topic       | url                      | valid
        1   | new User() | "desc1"     | new Topic() | "https://www.google.com" | true
        2   | new User() | 1           | new Topic() | "a.com"                  | false
        3   | null       | "desc1"     | new Topic() | "a.com"                  | false
        4   | new User() | null        | new Topic() | "a.com"                  | false
        5   | new User() | "desc1"     | null        | "a.com"                  | false
        6   | new User() | ""          | new Topic() | "a.com"                  | false
        7   | new User() | "desc1"     | new Topic() | "acom"                   | false

    }

    @Unroll("Checking ToString :Executing #sno")
    def "CheckToString"() {
        setup:
        Resource resource = new LinkResource(url: url)

        when:
        result == resource.toString()

        then:
        noExceptionThrown()

        where:
        sno | url     | result
        1   | "a.com" | "a.com"
        2   | null    | null
    }
}
