package com.ttnd.bootcamp

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([Subscription,Topic])
class SubscriptionControllerSpec extends Specification {

    def "Delete Subscription"(){
        Long id=1
        Topic topic=new Topic(id: id)
        User user=new User()
        Subscription subscription=new Subscription(user: user,topic: topic)
        int count=Subscription.count()

        when:
        controller.deleteSubscription(id)
        then:
        count-Subscription.count()==0

    }

    def "Save subscription"(){
        Long id=1
        Topic topic=new Topic(id: id)
        User user=session.user
        Subscription subscription=new Subscription(user: user,topic: topic)

        when:
        controller.saveSubscription(id)
        then:
        Subscription.count==1

    }

}
