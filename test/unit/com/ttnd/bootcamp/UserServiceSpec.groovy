package com.ttnd.bootcamp

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User])
class UserServiceSpec extends Specification {

    @ConfineMetaClassChanges(ReadingItem)
    def "Test job wala method"() {
        setup:
        def mockedEmailService = Mock(EmailService)
        service.emailService = mockedEmailService
        service.metaClass.getUsersWithUnreadItems = { ->
            [new User()]

        }
        service.metaClass.getUnreadResources={ User user ->
            [new LinkResource()]
        }

        when:
        service.sendUnreadItemsEmail()
        then:
        1*mockedEmailService.sendUnreadResourcesEmail()
    }

}
