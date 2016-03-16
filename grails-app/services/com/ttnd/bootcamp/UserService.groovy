package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.UpdatePasswordCO
import com.ttnd.bootcamp.CO.UpdateProfileCO
import grails.transaction.Transactional

@Transactional
class UserService {
    def emailService

    def updatePassword(UpdatePasswordCO updatePasswordCO) {
        User user = updatePasswordCO.getUser()
        if (user) {
            user.password = updatePasswordCO.password
            user.confirmPassword = updatePasswordCO.password
            return user.save()
        } else {
            return null
        }
    }

    def updateProfile(UpdateProfileCO updateProfileCO) {
        User user = updateProfileCO.getUser()
        if (user) {
            if (!updateProfileCO.file.empty) {
                user.photo = updateProfileCO.file.bytes
            }
            user.firstName = updateProfileCO.firstName
            user.lastName = updateProfileCO.lastName
            user.userName = updateProfileCO.userName
            return user.save()
        } else {
            return null
        }
    }


    def sendUnreadItemsEmail() {
        List<User> userList = getUsersWithUnreadItems()

        userList.each {
            user -> emailService.sendUnreadResourcesEmail(user, getUnreadResourcesForUser(user))
        }
    }

    List<User> getUsersWithUnreadItems() {

        return Resource.usersWithUnreadResources()
    }

    List<Resource> getUnreadResourcesForUser(User user) {

        return user.unreadResources()
    }
}
