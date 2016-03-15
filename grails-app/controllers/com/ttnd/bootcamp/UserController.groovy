package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.ResourceSearchCO
import com.ttnd.bootcamp.CO.TopicSearchCO
import com.ttnd.bootcamp.CO.UpdatePasswordCO
import com.ttnd.bootcamp.CO.UpdateProfileCO
import com.ttnd.bootcamp.CO.UserCO
import com.ttnd.bootcamp.CO.UserSearchCO
import com.ttnd.bootcamp.DTO.EmailDTO
import com.ttnd.bootcamp.VO.PostVO
import com.ttnd.bootcamp.VO.TopicVO
import com.ttnd.bootcamp.VO.UserVO
import groovy.util.logging.Slf4j
import com.ttnd.bootcamp.Utility.Util

@Slf4j
class UserController {

    def topicService
    def subscriptionService
    def emailService
    def messageSource
    def assetResourceLocator
    def userService

    def index() {
        User user = session.user
        List<Topic> subscribedTopics = user.getSubscribedTopic()
        List<PostVO> readingItems = User.getReadingItems(session.user)
//         List<ReadingItem> readingItems=User.getUnReadResources(co)
        render view: 'myAccount', model: [
                topics          : subscribedTopics.toList(),
                subscribedTopics: subscribedTopics,
                readingItems    : readingItems
        ]
    }


    def profile(ResourceSearchCO resourceSearchCO) {

        User user = User.get(resourceSearchCO.id)

        if (session.user) {
            if (!(session.user.admin || session.user.equals(user))) {
                resourceSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<Resource> resources = Resource.search(resourceSearchCO).list()
        List<PostVO> createdPosts = resources?.collect { Resource.getPost(it.id) }

        render(view: 'profile', model: [createdPosts: createdPosts, user: user.getInfo()])
    }

    def topics(Long id) {
        log.info "/user/topics called"
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (session.user) {
            if (!(session.user.admin || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC
        log.info "${topicSearchCO.visibility}"
        List<TopicVO> createdTopics = topicService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: createdTopics])
    }

    def subscriptions(Long id) {

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (session.user) {
            if (!(session.user.admin || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVO> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: subscribedTopics])

    }


    def registerUser(UserCO co) {
        User user = co.properties
        user.active = true
        user.admin = false
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

//    def save(User user) {
//        if (user?.hasErrors()) {
//            render view: 'login', model: [user       : user,
//                                          currentTime: new Date()]
//        } else {
//            user.save()
//            render "form saved"
//        }
//    }

    def image(Long id) {
        User user = User.get(id)
        if (user?.photo) {
            byte[] img = user.photo
            response.outputStream.write(img)
        } else {
            response.outputStream << assetResourceLocator.findAssetForURI('image1.png').getInputStream()
        }
        response.outputStream.flush()
    }

    def forgotPassword(String emailID) {

        User user = User.findByEmail(emailID)

        if (user) {
            if (user.active) {
                String to = emailID
                String subject = "Forgot password request"
                String newPassword = Util.randomPassword

                EmailDTO emailDTO = new EmailDTO(to: to,
                        subject: subject,
                        model: [newPassword: newPassword])

                user.password = newPassword

                if (user.save(flush: true)) {

                    emailService.sendMail(emailDTO)
                    flash.message = "Mail sent with new password."
                    render flash.message
                } else {
                    flash.error = "Mail could not be sent."
                    render flash.error
                }
            } else {
                flash.error = "The user account corresponding to the entered email address is inactive."
                render flash.error
            }
        } else {
            flash.error = "The email id doesn't belong to a registered user."
            render flash.error
        }

        // redirect(controller: "login", action: "index")
    }

    def list(UserSearchCO userSearchCO) {

        if (session.user) {
            if (session.user.admin) {

                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max,
                        sort: userSearchCO.sort,
                        order: userSearchCO.order)

                List<UserVO> usersList = users?.collect {
                    user ->
                        new UserVO(userId: user.id,
                                userName: user.userName,
                                emailId: user.email,
                                firstName: user.firstName,
                                lastName: user.lastName,
                                active: user.active)
                }

                render(view: "/user/list", model: [usersList: usersList])
            } else
                redirect(controller: "login", action: "index")
        } else
            redirect(controller: "login", action: "index")

    }

    def toggleActive(Long id) {
        if (session.user) {

            if (session.user.admin) {

                User user = User.get(id)

                if (user) {
                    if (user.admin) {
                        flash.error = "Admin active status cannot be changed."
                    } else
                        user.active = !(user.active)

                    if (user.save(flush: true)) {
                        flash.message = "User active status changed"
                    } else
                        flash.error = "User active status could not be changed"
                } else
                    flash.error = "User not found."

                redirect(controller: "user", action: "list")
            } else
                redirect(controller: "login", action: "index")
        } else {
            redirect(controller: "login", action: "index")
        }
    }

    def display() {
        User user = User.get(params.id)
        List<PostVO> readingItems = User.getReadingItems(user)
        render view: "/user/profile", model: [readingItems: readingItems, id: params.id]
    }

    def save(UpdateProfileCO updateProfileCO) {
        if (session.user) {
            updateProfileCO.file = params.file
          //  log.info params.file
            if (updateProfileCO.hasErrors()) {
                flash.error="Co has errors"
                render "${updateProfileCO.errors.allErrors}"
                //render(view: 'edit', model: [userDetails: session.user.getUserDetails(), userCo: session.user])
            } else {
                User user = userService.updateProfile(updateProfileCO)
                if (user) {
                    session.user = user
                    flash.message = "Profile Updated"
                    render flash.message
                   // render(view: 'edit', model: [userDetails: user.getUserDetails(), userCo: user])
                } else {
                    flash.error = "Profile not Updated"
                    render flash.error
                   // render(view: 'edit', model: [userDetails: session.user.getUserDetails(), userCo: session.user])
                }
            }
        }
    }

    def updatePassword(UpdatePasswordCO updatePasswordCO) {
        if (session.user) {
            if (updatePasswordCO.hasErrors()) {
                flash.error = "Password not Updated 1"
                render "${updatePasswordCO.errors.allErrors}"

               // render(view: 'edit', model: [userDetails: session.user.getUserDetails(),
                  //                           userCo: session.user])
            } else {
                User user = userService.updatePassword(updatePasswordCO)
                if (user) {
                    session.user = user
                    flash.message = "Password Updated"
                    render flash.message
                   // render(view: 'edit', model: [userDetails: user.getUserDetails(),
                     //                            userCo: user])
                } else {
                    flash.error = "Password not Updated"
                    render flash.error
                    //render(view: 'edit', model: [userDetails: session.user.getUserDetails(),
                          //                       userCo: session.user])
                }
            }
        }
    }

    def edit() {
        User user = session.user
        if (user) {
            render(view: 'edit', model: [userDetails: user.getUserDetails(), userCo: user])
        }
    }



}
