package com.ttnd.bootcamp.CO

import grails.validation.Validateable

/**
 * Created by aditi on 2/3/16.
 */
@Validateable
class PersonCO {
    String name
    int age

    static constraints={
        age max: 5

    }
}
