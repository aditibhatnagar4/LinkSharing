package com.ttnd.bootcamp

import grails.converters.JSON

class ReadingItemController {

    def changeIsRead(Long id, Boolean isRead) {
        User user = session.user
        log.info "${id} ${isRead}"
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource.id=:id", [isRead: isRead, id: id])) {
            flash.message = "Reading Item Status Changed"
        } else {
            flash.error = "Reading Item Status not Changed"
        }
        redirect(uri: '/')
    }
}
