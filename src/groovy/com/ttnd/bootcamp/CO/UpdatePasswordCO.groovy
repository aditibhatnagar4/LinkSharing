package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {
    Long id
    String oldPassword
    String password
    String confirmPassword

    static constraints = {
       // importFrom(User, include: ['password','confirmPassword'])
        importFrom(User)
        oldPassword(validator: { val, obj ->
            if (!obj.id && (val == (obj.getUser()?.password))) {
                return false
            }
        })
    }

    User getUser() {
        return User.get(id)
    }
}
