package com.ttnd.bootcamp

class UserController {

    def index() {
        render "User Dashboard  "
        render session.user
    }

    def registerUser() {
        User user = new User(userName: "user",
                firstName: "aditi",
                lastName: "bhatnagar",
                email: "user@ttnd.com",
                password: Constants.DEFAULT_PASSWORD,
                confirmPassword: Constants.DEFAULT_PASSWORD,
                isActive: true,
                isAdmin: false)

        if (user.validate()) {
            user.save(flush: true)
            render "${user} saved"
        } else {
            flash.message = "${user} not added--- ${user.errors.allErrors}"
            render "${user.errors.allErrors.collect { message(error: it) }.join(',')}"
        }
    }
}
