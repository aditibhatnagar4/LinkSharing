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
        LinkResource linkResource = new LinkResource(description: description,
                createdBy: createdBy,
                topic: topic1,
                url: url)

        when:
        Boolean result = linkResource.validate()

        then:
        result == valid

        where:
        sno | createdBy | description | topic1 | url     | valid
        1   | user      | "desc1"     | topic  | "a.com" | true
        2   | user      | 1           | topic  | "a.com" | false
        3   | null      | "desc1"     | topic  | "a.com" | false
        4   | user      | null        | topic  | "a.com" | false
        5   | user      | "desc1"     | null   | "a.com" | false
        6   | user      | ""          | topic  | "a.com" | false
        7   | user      | "desc1"     | topic  | "acom"  | false

    }

}
