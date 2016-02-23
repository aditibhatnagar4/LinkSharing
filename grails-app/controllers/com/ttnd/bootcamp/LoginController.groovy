package com.ttnd.bootcamp

class LoginController {


    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else {
            render "Failed to login"
        }
    }

    def loginHandler(String userName, String password) {
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.user = user
                redirect(controller: "login", action: "index")
            } else {
                flash.error = 'Your account is not active'

            }
        } else {
            flash.error = "User not found"
            render flash.error
        }
    }

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }
}
