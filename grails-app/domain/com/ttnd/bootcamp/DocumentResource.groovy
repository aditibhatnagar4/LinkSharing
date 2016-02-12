package com.ttnd.bootcamp

class DocumentResource extends Resource {

    String filePath

    static constraints = {

        filePath blank: false
    }
}
