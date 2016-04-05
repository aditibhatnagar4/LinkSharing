package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import grails.validation.Validateable

@Validateable
class UserCO {

    String email
    String username
    String firstName
    String lastName
    def photo
    boolean enabled
    String confirmPassword
    String password

    static constraints = {
        importFrom(User)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (obj.password != val || !val) {
                return false
            }
        })

    }

}
