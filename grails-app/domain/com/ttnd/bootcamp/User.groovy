package com.ttnd.bootcamp

class User {

    Date dateCreated
    Date lastUpdated

    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin
    Boolean active
    String confirmPassword


    static constraints = {
        email email: true, unique: true, blank: false
        password blank: false, minSize: 5
        firstName blank: false
        lastName blank: false
        photo nullable: true
        admin nullable: true
        active nullable: true
        userName unique: true
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (!obj.id && (obj.password != val || !val)) {
                return false
            }
        })

    }


    static hasMany = [
            topics         : Topic,
            subscriptions  : Subscription,
            readingItems   : ReadingItem,
            resources      : Resource,
            resourceRatings: ResourceRating
    ]


    String getName() {

        if (firstName && lastName) {
            return "$firstName $lastName"
        } else return ""

    }
    static transients = ['name', 'confirmPassword']

    static mapping = {
        photo(sqlType: 'longblob')
        sort id: "desc"
    }

    String toString() {
        return userName
    }


}
