package com.ttnd.bootcamp

class ResourceController {

    def index() {}

    def deleteResource(Long id) {
        Resource resource = Resource.load(id)
        if (resource) {
            resource.delete(flush: true)
            render "Resource Deleted"
        } else {
            render "Resource not deleted--- ${resource.errors.allErrors.collect { message(error: it) }.join(',')}"
        }
    }


}
