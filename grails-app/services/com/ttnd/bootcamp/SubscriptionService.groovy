package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.TopicSearchCO
import com.ttnd.bootcamp.VO.TopicVO
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    List<TopicVO> search(TopicSearchCO topicSearchCO){

        if(topicSearchCO.id)
        {
            User user = topicSearchCO.getUser()

            return user.getSubscribedTopicsList()
        }
    }
}
