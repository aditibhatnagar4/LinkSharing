package com.ttnd.bootcamp

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
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
