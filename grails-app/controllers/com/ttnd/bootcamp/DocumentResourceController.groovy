package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.DocumentResourceCO
import grails.transaction.Transactional

class DocumentResourceController extends ResourceController {

    def index() {}
    //@Transactional
//    def saveDocumentResource(String description, String topicName) {
//        String filepath = new File("${grailsApplication.config.linksharing.documents.folderPath}/${topicName}.pdf")
//
//        response.contentType = "application/pdf"
//        User user = session.user
//        Topic topic = Topic.findByCreatedByAndName(user, topicName)
//        Resource resource = new DocumentResource(filePath: filePath,
//                description: description,
//                topic: topic,
//                createdBy: user,
//                uuid: UUID.randomUUID()
//        )
//
//        if (resource.save(flush: true)) {
//            flash.message = "Resource saved successfully."
//            render flash.message
////            File newFile = new File("/var/www/linksharing/resource.pdf");
////            resource.eachLine { line -> newFile << line }
//
//           // response.setContentType("APPLICATION/OCTET-STREAM")
//            def outputStream = response.getOutputStream()
//            outputStream << resource.filedata
//            outputStream.flush()
//            outputStream.close()
//
//        } else {
//            flash.error = "Topic not saved"
//            render "flash.error $topic.errors.allErrors"
//            // redirect controller: 'user', action: 'index'
//        }
//    }


    def saveDoc(DocumentResourceCO documentResourceCo) {
        String filepath = new File("${grailsApplication.config.linksharing.documents.folderPath}/${documentResourceCo.name}.pdf")


        Topic topic = Topic.findById(documentResourceCo.id)

        DocumentResource doc = new DocumentResource(createdBy: session.user,
                topic: topic,
                description: documentResourceCo.description,
                filepath: filepath,
                contentType: documentResourceCo.contentType)


        if (doc.validate()) {
            println("........${documentResourceCo.myFile}")
            File file = new File(filepath) << documentResourceCo.myFile.bytes
            println("........${file.size()}")
            if (doc.save(flush: true)) {
                flash.message = " document saved ------Success "
                render flash.message
                readingItem(doc)
// render(view: 'save')
            } else {
                log.error(" Could not save document ${doc}")
                flash.message = "Document ${doc.properties} dosent satisfied constraints"
                render flash.message

            }
        } else render " error creating doc"


    }

//    def readingItem(DocumentResource doc) {
//        List<Subscription> subscriptions=Subscription.
//        if (user) {
//            if (session.user.id == doc.createdBy.id) {
//                ReadingItem readingItem = new ReadingItem(isRead: true, user: doc.createdBy, resource: doc)
//            } else {
//                ReadingItem readingItem = new ReadingItem(isRead: false, user: session.user, resource: doc)
//            }
//        }
//    }

    private def readingItem(DocumentResource resource) {
        Topic topic = resource.topic
        List<User> user = Topic.getSubscribedUser(topic.id)
        user.each {
            ReadingItem readingItem
            if (it.id == resource.createdBy.id)
                readingItem = new ReadingItem(resource: resource, user: it, isRead: true)
            else
                readingItem = new ReadingItem(resource: resource, user: it, isRead: false)
            it.addToReadingItems(readingItem)
        }
    }


}
