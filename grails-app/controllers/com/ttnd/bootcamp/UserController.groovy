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
import grails.plugin.springsecurity.annotation.Secured
import groovy.util.logging.Slf4j
import com.ttnd.bootcamp.Utility.Util

@Slf4j
@Secured(['permitAll'])
class UserController {

    def topicService
    def subscriptionService
    def emailService
    def messageSource
    def assetResourceLocator
    def userService
    def springSecurityService

    def index() {
        log.info "in /user/index"

        if (springSecurityService.isLoggedIn()) {
            log.info "user logged in "
//            log.info "${session.user.id}"
            User user = session.user = User.read(springSecurityService.currentUserId as Long)
            springSecurityService.reauthenticate(user.email)
            List<Topic> subscribedTopics = user.getSubscribedTopic()
            List<PostVO> readingItems = User.getReadingItems(session.user)

//            log.info "${session.user.findAll{it.isAdmin()}}"

            render(view: '/user/myAccount', model: [
                    topics: subscribedTopics.toList(),
                    subscribedTopics: subscribedTopics,
                    readingItems: readingItems,
                    user: user
            ])
        } else {
            log.info "user not logged in"
            redirect(controller: 'login', action: 'index')
        }

        //  User user = session.user
//        List<Topic> subscribedTopics = user.getSubscribedTopic()
//        List<PostVO> readingItems = User.getReadingItems(session.user)
////         List<ReadingItem> readingItems=User.getUnReadResources(co)
//        render view: 'myAccount', model: [
//                topics          : subscribedTopics.toList(),
//                subscribedTopics: subscribedTopics,
//                readingItems    : readingItems
//        ]
    }


    def profile(ResourceSearchCO resourceSearchCO) {

        User user = resourceSearchCO.getUser()

        if (session.user) {

            if (!(session.user.findAll { it.isAdmin() } || session.user.equals(user))) {
                resourceSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<Resource> resources = Resource.search(resourceSearchCO).list()
        List<PostVO> createdPosts = resources?.collect { Resource.getPost(it.id) }

        render(view: 'profile', model: [createdPosts: createdPosts, user: user])
    }

    def topics(Long id) {

        topicService.method()

        log.info "/user/topics called"
        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (session.user) {
            if (!(session.user.authorities.any {
                it.authority == 'ROLE_ADMIN'
            } || session.user.equals(User.load(id)))) {
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
            if (!(session.user.authorities.any {
                it.authority == 'ROLE_ADMIN'
            } || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVO> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: subscribedTopics])

    }


    def registerUser(UserCO co) {
        String password = co.password
        co.password = springSecurityService.encodePassword(co.password)
        User user = co.properties
        user.enabled = true
        Role role = Role.findByAuthority("ROLE_USER")
        UserRole normalUserRole = new UserRole(user: user, role: role)
        if (params.file) {
            if (!params.file.empty) {
                user.photo = params.file.bytes
            }
        }
        co.password = password
        if (!co.validate()) {
            flash.error = "Registration unsuccessful."
            redirect uri: '/login/auth'
            return

        }

        if (user.validate() && normalUserRole.validate()) {
            user.save(flush: true)
            normalUserRole.save(flush: true)
            flash.message = "You have registered successfully."
            springSecurityService.reauthenticate(user.email)
            redirect uri: '/user/index'
        } else {
            flash.error = "Registration unsuccessful."
            redirect uri: '/login/auth'
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
            if (user.enabled) {
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
                } else {
                    flash.error = "Mail could not be sent."
                }
            } else {
                flash.error = "The user account corresponding to the entered email address is inactive."
            }
        } else {
            flash.error = "The email id doesn't belong to a registered user."
        }
        redirect(uri: "/login/auth")
    }

    @Secured(['ROLE_ADMIN'])
    def list(UserSearchCO userSearchCO) {

        if (session.user) {
            if (session.user.authorities.any { it.authority == "ROLE_ADMIN" }) {

                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max,
                        sort: userSearchCO.sort,
                        order: userSearchCO.order,
                        offset: userSearchCO.offset).findAll { !(it.isAdmin()) }

                List<UserVO> usersList = users?.collect {
                    user ->
                        new UserVO(userId: user.id,
                                userName: user.username,
                                emailId: user.email,
                                firstName: user.firstName,
                                lastName: user.lastName,
                                active: user.enabled)
                }
                if (!request.xhr) {

                    render(view: "/user/list", model: [usersList: usersList, userCount: User.search(userSearchCO).count()])
                } else {
                    render(template: "/user/list", model: [usersList: usersList, userCount: User.search(userSearchCO).count()])
                }
            } //else
            //redirect(controller: "login", action: "index")
        } //else
        // redirect(controller: "login", action: "index")

    }

    @Secured(['ROLE_ADMIN'])
    def toggleActive(Long id) {
        if (session.user) {

            if (session.user.authorities.any { it.authority == 'ROLE_ADMIN' }) {

                User user = User.get(id)

                if (user) {
                    if (user.authorities.any { it.authority == 'ROLE_ADMIN' }) {
                        flash.error = "Admin enabled status cannot be changed."
                    } else
                        user.enabled = !(user.enabled)

                    if (user.save(flush: true)) {
                        flash.message = "User enabled status changed"

                    } else {
                        flash.error = "User enabled status could not be changed"
                    }
                } else
                    flash.error = "User could not be found."

                redirect(controller: "user", action: "list")
            } else
                redirect(controller: "login", action: "index")
        } else {
            redirect(controller: "login", action: "index")
        }
        // redirect(uri: '/')
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
                flash.error = "CO has errors ${updateProfileCO.errors.allErrors}"

                render(view: 'edit', model: [userDetails: session.user.getUserDetails(), userCo: session.user])
            } else {
                User user = userService.updateProfile(updateProfileCO)
                if (user) {
                    session.user = user
                    flash.message = "Profile Updated"

                    render(view: 'edit', model: [userDetails: user.getUserDetails(), userCo: user])
                } else {
                    flash.error = "Profile not Updated"

                    render(view: 'edit', model: [userDetails: session.user.getUserDetails(), userCo: session.user])
                }
            }
        }

    }

    def updatePassword(UpdatePasswordCO updatePasswordCO) {

        if (session.user) {
            if (updatePasswordCO.hasErrors()) {
                flash.error = "Password not Updated 1"

                render(view: 'edit', model: [userDetails: session.user.getUserDetails(), userCo: session.user])
            } else {
                User user = userService.updatePassword(updatePasswordCO)
                if (user) {
                    session.user = user
                    flash.message = "Password Updated"
                    render(view: 'edit', model: [userDetails: user.getUserDetails(),
                                                 userCo     : user])
                } else {
                    flash.error = "Password not Updated"
                    render(view: 'edit', model: [userDetails: session.user.getUserDetails(),
                                                 userCo     : session.user])
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
