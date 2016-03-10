package com.ttnd.bootcamp

class LinkResource extends Resource {

    String url

    static constraints = {
        url url: true
    }

    String toString() {
        return url
    }

    void deleteFile() {
        log.info "Delete file called in Link Resource"

    }
}
