package com.ttnd.bootcamp.DTO

import grails.validation.Validateable

@Validateable
class EmailDTO{
    String to
    String subject
    String view
    String content
    Map model
}