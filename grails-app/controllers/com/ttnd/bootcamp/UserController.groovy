package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.UserCO

class UserController {

    def index() {
        render view: 'myAccount',model: [topics: ["topic1","topic2","topic3"]]
       // render session.user
    }

    def registerUser(UserCO co) {
        User user = new User(userName: params.userName,
                firstName: params.firstName,
                lastName: params.lastName,
                email: params.email,
                password: params.password,
                confirmPassword: params.confirmPassword,
                active: params.active,
                admin: params.admin)

        if (user.validate()) {
            user.save(flush: true)
            render "${user} saved"
        } else {
            flash.message = "${user} not added--- ${user.errors.allErrors}"
            render "${user.errors.allErrors.collect { message(error: it) }.join(',')}"
        }
    }


    def save(User user){
        if(user?.hasErrors()){
            render view: 'login' , model: [user: user, currentTime: new Date()]
        }
        else{
            user.save()
            render "form saved"
        }
    }

}
