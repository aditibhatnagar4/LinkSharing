package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import com.ttnd.bootcamp.Visibility
import grails.validation.Validateable

@Validateable
class ResourceSearchCO extends SearchCO {
    Long id
    Long topicId
    Visibility visibility
    Boolean global
    String topicName

    public User getUser(){
        User user = User.get(id)

        return user
    }

}
