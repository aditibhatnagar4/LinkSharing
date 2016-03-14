package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {
    Long id
    String oldPassword
    String password

    static constraints = {
        importFrom(User, include: ['password'])
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
