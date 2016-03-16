package com.ttnd.bootcamp

import grails.converters.JSON

class LoginController {


    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else {
            List<Resource> resources = Resource.getTopPosts()
            List<Resource> recentPosts = Topic.getRecentPosts()
            render view: 'homePage', model: [resources  : resources,
                                             recentPosts: recentPosts]
        }
    }

    def loginHandler(String userName, String password) {
        User user = User.findByUserNameAndPassword(userName, password)
        if (user) {
            if (user.active) {
                session.user = user
                flash.message="Login success."


                //return
            } else {
                flash.error = 'Your account is not active'

            }
        } else {
            flash.error = "Could not find user with the specified username and password."


           // render ([flash.error] as JSON)
        }

        redirect(controller: "login", action: "index")
    }

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }

    def validateEmail(){

        Integer numUser = User.countByemail(params.email)

        Boolean result = numUser ? false : true

        render result
    }

    def validateUserName(){
        Integer numUser = User.countByUserName(params.userName)
        Boolean result = numUser ? false : true

        render result

    }
}
