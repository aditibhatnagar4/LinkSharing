package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.UserCO
import com.ttnd.bootcamp.VO.PostVO
import groovy.util.logging.Slf4j

@Slf4j
class UserController {

//    def userService
//    def myBean
//    def customBeanUsingConstructor
//    @Autowired
//    customBean customBean1

    def index() {
        User user = session.user
        List<Topic> subscribedTopics = user.getSubscribedTopic()
        List<PostVO> readingItems = User.getReadingItems(session.user)
       // List<ReadingItem> readingItems=User.getUnReadResources(q: 'aditi.bhatnagar',max: 10, offset: 0)
        render view: 'myAccount', model: [
                topics          : subscribedTopics.toList(),
                subscribedTopics: subscribedTopics,
                readingItems    : readingItems
        ]
    }

    def registerUser(UserCO co) {
        User user = co.properties
        user.active = true
        if (!params.file.empty) {
            user.photo = params.file.bytes
        }
        if (user.validate()) {
            user.save(flush: true)
            redirect(uri: '/login/loginHandler', params: [userName: co.userName, password: co.password])

        } else {
            render view: '/login/homePage', model: [user: user]
        }
    }

    def save(User user) {
//        println myBean.name
//        println myBean.age
//        println customBeanUsingConstructor.name
//        println customBeanUsingConstructor.age
        if (user?.hasErrors()) {
            render view: 'login', model: [user       : user,
                                          currentTime: new Date()]
        } else {
            user.save()
            render "form saved"
        }
    }
    def assetResourceLocator

    def image(Long id) {
        User user = User.findById(id)
        if (user?.photo) {
            byte[] img = user.photo
            response.outputStream.write(img)
        } else {
            response.outputStream << assetResourceLocator.findAssetForURI('image1.png').getInputStream()
        }
        response.outputStream.flush()
    }
}
