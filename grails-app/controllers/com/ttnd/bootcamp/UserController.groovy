package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.CO.SearchCO
import com.ttnd.bootcamp.CO.TopicSearchCO
import com.ttnd.bootcamp.CO.UserCO
import com.ttnd.bootcamp.VO.PostVO
import com.ttnd.bootcamp.VO.TopicVO
import groovy.util.logging.Slf4j

@Slf4j
class UserController {

//    def userService
//    def myBean
//    def customBeanUsingConstructor
//    @Autowired
//    customBean customBean1

    def topicService
    def subscriptionService

    def index() {
        User user = session.user
        List<Topic> subscribedTopics = user.getSubscribedTopic()
        List<PostVO> readingItems = User.getReadingItems(session.user)
//        SearchCO co=new SearchCO(q: 'aditi.bhatnagar',max: 10, offset: 0)
//         List<ReadingItem> readingItems=User.getUnReadResources(co)
        render view: 'myAccount', model: [
                topics          : subscribedTopics.toList(),
                subscribedTopics: subscribedTopics,
                readingItems    : readingItems
        ]
    }


    def profile(ResourceSearchCO resourceSearchCO){

        User user = User.get(resourceSearchCO.id)

        if(session.user){
            if(!(session.user.admin || session.user.equals(user))){
                resourceSearchCO.visibility = Visibility.PUBLIC
            }
        }else
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<Resource> resources = Resource.search(resourceSearchCO).list()
        List<PostVO> createdPosts = resources?.collect{ Resource.getPostInfo(it.id) }

        render (view: 'profile', model: [createdPosts: createdPosts, user: user.getInfo()])
    }

    def topics(Long id){
log.info "/user/topics called"
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if(session.user)
        {
            if(!(session.user.admin || session.user.equals(User.load(id)))){
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        }
        else
            topicSearchCO.visibility = Visibility.PUBLIC
log.ifo "${topicSearchCO.visibility}"
        List<TopicVO> createdTopics = topicService.search(topicSearchCO)

        render(template:'/topic/list', model: [topics: createdTopics])
    }

    def subscriptions(Long id){

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if(session.user)
        {
            if(!(session.user.admin || session.user.equals(User.load(id)))){
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        }
        else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVO> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template:'/topic/list', model: [topics: subscribedTopics])

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
