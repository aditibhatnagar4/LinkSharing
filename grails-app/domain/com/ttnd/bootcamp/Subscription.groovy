package com.ttnd.bootcamp

class Subscription extends BaseDomain {

    Seriousness seriousness

    static constraints = {

        user(unique:'topic')

    }

    static belongsTo=[
            user: User,
            topic:Topic
    ]
}
