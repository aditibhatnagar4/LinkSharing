package com.ttnd.bootcamp

class DocumentResource extends Resource {

    String filePath
    String contentType
    String filename

    static constraints = {

        filePath blank: false

        contentType(bindable: true, validator: { val, obj ->
            if ((val == null) || (val != Constants.DOCUMENT_CONTENT_TYPE)) {
                return false
            }
        })
    }

    String toString() {
        return filePath
    }

    static transients = ['contentType', 'filename']

    String getFilename(String filePath) {
        int index = filePath.lastIndexOf("/");
        String filename = filePath.substring(index + 1);
        return filename
    }

    void deleteFile() {
        new File(filePath).delete()
    }
}
