package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    def "ReadingItem resource should be unique per user"() {
        setup:
        User user = new User(firstName: "Aditi",
                lastName: "Bhatnagar",
                email: "a@b.com",
                password: "password",
                username: "aditi",
                admin: false,
                enabled: true)
        Topic topic = new Topic(name: "topic1",
                visibility: Visibility.PUBLIC,
                createdBy: user)
        Resource resource = new LinkResource(description: "description",
                createdBy: user,
                topic: topic,
                url: "abc.com")
        ReadingItem readingItem = new ReadingItem(isRead: false, user: user, resource: resource)


        when:
        readingItem.save(flush: true)

        then:
        readingItem.count() == 1

        when:
        ReadingItem newReadingItem = new ReadingItem(isRead: true, user: user, resource: resource)
        newReadingItem.save(flush: true)

        then:
        ReadingItem.count() == 1
        newReadingItem.errors.allErrors.size() == 1
        newReadingItem.errors.getFieldErrorCount('user') == 1
    }

    @Unroll("ReadingItem Validation:Executing #sno")
    void "Test ReadingItem validations"() {
        setup:
        ReadingItem readingItem = new ReadingItem(isRead: isRead, user: user, resource: resource)

        when:
        Boolean result = readingItem.validate()

        then:
        result == valid

        where:
        sno | user       | isRead | resource           | valid
        1   | new User() | false  | new LinkResource() | true
        2   | null       | false  | new LinkResource() | false
        3   | new User() | null   | new LinkResource() | false
        4   | new User() | false  | null               | false
        5   | new User() | true   | new LinkResource() | true


    }

}
