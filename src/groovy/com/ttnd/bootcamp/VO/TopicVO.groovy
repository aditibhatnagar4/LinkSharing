package com.ttnd.bootcamp.VO

import com.ttnd.bootcamp.User
import com.ttnd.bootcamp.Visibility

class TopicVO {
    Long id
    String name
    Visibility visibility
    int count
    User createdBy

    String toString() {
        return "$id , $name , $visibility , $count , $createdBy"
    }
}
