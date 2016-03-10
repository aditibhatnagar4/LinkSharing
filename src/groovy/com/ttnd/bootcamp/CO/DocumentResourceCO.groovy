package com.ttnd.bootcamp.CO

import com.ttnd.bootcamp.Topic
import com.ttnd.bootcamp.User
import org.springframework.web.multipart.MultipartFile

class DocumentResourceCO {
    User createdBy
    Topic topic
    String contentType
    String description
    Integer id
    String name = UUID.randomUUID()
    MultipartFile myFile

    String getContentType() {
        return myFile.contentType
    }
}
