package com.ttnd.bootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "when user is available in session then user should be forwarded to user index action"() {
        setup:
        controller.session.user = new User()
        when:
        controller.index()
        then:
        response.forwardedUrl == "/user/index"
    }

    void "when user is not available in session then message should be rendered"() {
        when:
        controller.index()
        then:
        response.text == "user is not logged in"

    }


}
