package com.ttnd.bootcamp

class DocumentResource extends Resource {

    String filePath
    String contentType
    String filename
    String uuid

    static constraints = {

        filePath blank: false

        contentType(bindable: true, validator: { val, obj ->
            if ((obj.contentType==null) && (obj.contentType != Constants.DOCUMENT_CONTENT_TYPE)) {
                return false
            }
        })
    }

    String toString() {
        return filePath
    }

    static transients = ['contentType','filename']

    String getFilename(String filePath) {
        int index = filePath.lastIndexOf("/");
        String filename = filePath.substring(index + 1);
        return filename
    }
}
