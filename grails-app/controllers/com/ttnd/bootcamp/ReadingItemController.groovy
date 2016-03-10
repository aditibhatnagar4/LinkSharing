package com.ttnd.bootcamp

class ReadingItemController {

    def changeIsRead(Long resourceId, Boolean isRead) {
        ReadingItem readingItem = ReadingItem.get(resourceId)
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: isRead, id: resourceId])) {
            readingItem.refresh()
            render "Success"
        } else {
            render "Error"
        }
    }
}
