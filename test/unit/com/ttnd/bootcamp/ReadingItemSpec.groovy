package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "ReadingItem resource should be unique per user"() {
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
        ReadingItem readingItem= new ReadingItem(isRead: false,user: user,resource: resource)


        when:
        readingItem.save()

        then:
        readingItem.count() == 1

        when:
        ReadingItem newReadingItem = new ReadingItem(isRead: true,user: user,resource: resource)
        newReadingItem.save()

        then:
        ReadingItem.count() == 1
        newReadingItem.errors.allErrors.size() == 1
        newReadingItem.errors.getFieldErrorCount('user') == 1
    }

    @Unroll("ReadingItem Validation:Executing #sno")
    void "Test ReadingItem validations"() {
        setup:
        ReadingItem readingItem = new ReadingItem(isRead: isRead,user: user,resource: resource)

        when:
        Boolean result = readingItem.validate()

        then:
        result == valid

        where:
        sno | user | isRead | resource | valid
        1   | user | false  | resource | true
        2   | null | false  | resource | false
        3   | user | null   | resource | false
        4   | user | false  | null     | false
        5   | user | true   | resource | true


    }

}
