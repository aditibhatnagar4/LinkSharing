package com.ttnd.bootcamp

class User extends BaseDomain{

    String email
    String userName
    String password
    String firstName
    String lastName
    Byte[] photo
    Boolean admin
    Boolean active

    static constraints = {
        email email: true, unique: true, blank: false
        password blank: false, minSize: 5
        firstName blank: false
        lastName blank: false
        photo nullable: true
        admin nullable: true
        active nullable: true
        userName unique: true

    }


    static hasMany = [
            topics         : Topic,
            subscriptions  : Subscription,
            readingItems   : ReadingItem,
            resources      : Resource,
            resourceRatings: ResourceRating
    ]


    String getName() {
        return "${firstName} ${lastName}"

    }
    static transients = ['name']

    static mapping = { photo(sqlType: 'longblob') }


}
