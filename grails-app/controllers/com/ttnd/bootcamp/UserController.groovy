package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.UserCO
import com.ttnd.bootcamp.VO.PostVO
import com.ttnd.bootcamp.VO.TopicVO
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
class UserController {
    def userService
    def myBean
    def customBeanUsingConstructor

//    @Autowired
//    customBean customBean1


    def index() {
        User user = session.user
        List<Subscription> subscriptions

        List<Topic> subscribedTopics = user.getSubscribedTopic()
       // List<ReadingItem> readingItems = ReadingItem.findAllByUserAndIsRead(user,false)
        List<PostVO> readingItems = User.getReadingItems(session.user)
        render view: 'myAccount', model: [
                topics          : subscribedTopics.toList(),
                subscriptions   : subscriptions,
                subscribedTopics: subscribedTopics,
                readingItems    : readingItems
        ]

        // render "User ${user} Dashboard"

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


    def save(User user) {

        println myBean.name
        println myBean.age
        println customBeanUsingConstructor.name
        println customBeanUsingConstructor.age
//        if (user?.hasErrors()) {
//            render view: 'login', model: [user       : user,
//                                          currentTime: new Date()]
//        } else {
//            user.save()
//            render "form saved"
//        }
    }



}
