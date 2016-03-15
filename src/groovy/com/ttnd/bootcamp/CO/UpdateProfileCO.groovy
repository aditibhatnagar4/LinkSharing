package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import grails.validation.Validateable
import groovy.transform.ToString
import org.springframework.web.multipart.MultipartFile


@ToString
@Validateable
class UpdateProfileCO {
    Long id
    String firstName
    String lastName
    String userName
    MultipartFile file

    static constraints = {
        id(nullable: false, blank: false)
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        userName(nullable: true, blank: true)
        file(nullable: true, blank: true)
    }

    User getUser() {
        return User.get(id)
    }
}
