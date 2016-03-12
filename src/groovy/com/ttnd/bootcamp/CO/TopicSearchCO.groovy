package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.User
import com.ttnd.bootcamp.Visibility

/**
 * Created by aditi on 11/3/16.
 */
class TopicSearchCO {
    Long id
    Visibility visibility

    public User getUser(){
        User user = User.get(id)

        return user
    }
}
