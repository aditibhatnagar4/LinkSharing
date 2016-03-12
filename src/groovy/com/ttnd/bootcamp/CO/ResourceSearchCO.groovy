package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import com.ttnd.bootcamp.Visibility

class ResourceSearchCO extends SearchCO {
    Long id
    Long topicId
    Visibility visibility

    public User getUser(){
        User user = User.get(id)

        return user
    }

}
