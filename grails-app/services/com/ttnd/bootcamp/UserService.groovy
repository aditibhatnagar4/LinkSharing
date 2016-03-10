package com.ttnd.bootcamp

import grails.transaction.Transactional

//Class Work
@Transactional
class UserService {

    def serviceMethod(User user) {
        /*if (user?.hasErrors()) {
            user.errors.allErrors
        } else {
            user.save()
        }*/
        user.save()
    }


}
