package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.DocumentResourceCO
import groovy.util.logging.Slf4j

@Slf4j
class DocumentResourceController extends ResourceController {

    def saveDoc(DocumentResourceCO documentResourceCo) {
        String filepath = new File("${grailsApplication.config.grails.linksharing.documents.folderPath}/${documentResourceCo.name}.pdf")

        Topic topic = Topic.get(documentResourceCo.id)
        User user = session.user

        DocumentResource doc = new DocumentResource(createdBy: user,
                topic: topic,
                description: documentResourceCo.description,
                filePath: filepath,
                contentType: documentResourceCo.contentType
        )
        if (doc.validate()) {
            File file = new File(filepath) << documentResourceCo.myFile.bytes
            if (doc.save(flush: true)) {
                flash.message = " document saved ------Success "
                render flash.message
                addToReadingItems(doc)
            } else {
                log.error(" Could not save document ${doc}")
                flash.message = "Document ${doc.properties} dosent satisfied constraints"
                render flash.message
            }
        } else {
            doc.errors.allErrors.each {
                log.error "$it"
            }
            render " error creating doc"
        }
    }

    def download(Long id) {
        Resource resource = DocumentResource.get(id)
        if (resource) {
            Topic topic = resource.topic
            String filename = resource.getFilename(resource.filePath)
            if (topic.canViewedBy(session.user)) {
                File file1 = new File(resource.filePath)
                response.setHeader("Content-disposition", "attachment; filename=" + filename)
                response.contentType = Constants.DOCUMENT_CONTENT_TYPE
                response.contentLength = file1.bytes.length
                response.outputStream << file1.bytes
            } else {
                render "resource is not accessible by logged in user"
            }
        } else
            render "resource not found"

    }

}
