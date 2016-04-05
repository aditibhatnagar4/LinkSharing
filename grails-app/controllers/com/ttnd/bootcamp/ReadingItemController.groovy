package com.ttnd.bootcamp

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class ReadingItemController {

    def changeIsRead(Long id, Boolean isRead) {
        log.info "${id} ${isRead}"
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource.id=:id", [isRead: isRead, id: id])) {
            flash.message = "Reading Item Status Changed"
        } else {
            flash.error = "Reading Item Status not Changed"
        }
        redirect(uri: '/')
    }
}
