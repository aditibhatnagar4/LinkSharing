package com.ttnd.bootcamp

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
                redirect(controller: "login", action: "index")
                return
            } else {
                flash.error = 'Your account is not active'
            }
        } else {
            flash.error = "User not found"
        }
        render flash.error
    }

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }

    def validateEmail(){

        Integer numUser = User.countByemail(params.emailId)
        log.info params.emailId

        Boolean result = numUser ? false : true

        render result
    }

    def validateUserName(){
        Integer numUser = User.countByUserName(params.userName)
        log.info params.userName
        Boolean result = numUser ? false : true

        render result

    }
}
