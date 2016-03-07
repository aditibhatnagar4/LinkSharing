package com.ttnd.bootcamp

/**
 * Created by aditi on 7/3/16.
 */
class customBean {
    String name
    int age

    customBean(String name, int age) {
        this.name = name
        this.age = age
        println "parameterized constructor called"
    }

    customBean() {
        println "default constructor called"
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
        println "setName called ${this.name}"
    }

    int getAge() {
        return age
    }

    void setAge(int age) {
        this.age = age
        println "setAge called ${this.age}"
    }
}
