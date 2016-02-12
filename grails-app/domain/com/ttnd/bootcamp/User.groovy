package com.ttnd.bootcamp

class User {

    String email
    String userName
    String password
    String firstName
    String lastName
    byte photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]


    static constraints = {
        email email: true, unique: true, blank: false
        password blank: false, minSize: 5
        firstName blank: false
        lastName blank: false
        photo nullable: true
        admin nullable: true
        active nullable: true

    }


    String getName() {
        return "${firstName} ${lastName}"

    }
    static transients = ['name']

    static mapping = { photo(sqlType: 'longblob') }

}
