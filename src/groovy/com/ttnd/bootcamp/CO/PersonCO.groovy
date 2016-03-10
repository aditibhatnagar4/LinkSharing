package com.ttnd.bootcamp.CO

import grails.validation.Validateable

//Class Work
@Validateable
class PersonCO {
    String name
    int age

    static constraints = {
        age max: 5

    }
}
