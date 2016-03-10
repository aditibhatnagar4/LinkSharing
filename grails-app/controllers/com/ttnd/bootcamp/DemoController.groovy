package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.PersonCO

//Class Work
class DemoController {

    def index(int age, String name) {
        render name
        render "<br>"
        render age

        Person p = new Person(name: "aditi", age: 10)
        p.properties = params
    }

    def action() {

        Person p = new Person(name: "aditi", age: 10)
        render p.properties

        p.properties = params
        render p.properties
    }

    def display() {
        List l = params.list("items")
        render l
    }

    def conversions() {
        float age = params.float("age")
        Date dob = params.date("dob", "dd/MMM/yyyy")
        render age
        render dob
        render params.getProperties()
    }

    def errors() {
        render params
        render "<br>"
        def b = new Person(params)
        render b.hasErrors()
        render "<br>"
        if (b.hasErrors()) {
            render "${b.errors.getFieldError('age')}"
            render "<br>"
            if (b.errors.hasFieldErrors("age")) {
                render b.errors.getFieldError("age").rejectedValue
                render "<br>"
            }
        }
        render b.properties
        render "<br>"
    }


    def usingCO(PersonCO pco) {
        render pco.properties
        render "<br>"
        render pco.errors
        render pco.validate()
        render "<br>"
        if (pco.hasErrors()) {
            render pco.errors.getFieldError("age").rejectedValue
        }

    }
}
