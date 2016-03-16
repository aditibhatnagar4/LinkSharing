package com.ttnd.bootcamp

import com.ttnd.bootcamp.CO.TopicSearchCO
import com.ttnd.bootcamp.VO.TopicVO
import grails.transaction.Transactional

@Transactional
class TopicService {

    List<TopicVO> search(TopicSearchCO topicSearchCO) {
        List<TopicVO> createdTopicsList = []

        if (topicSearchCO.id) {

            List<Topic> topicList = Topic.createCriteria().list(max: topicSearchCO.max) {
                eq('createdBy.id', topicSearchCO.id)

                if (topicSearchCO.visibility)
                    eq('visibility', topicSearchCO.visibility)
            }

            topicList.each {
                topic ->
                    createdTopicsList.add(new TopicVO(id: topic.id,
                            name: topic.name,
                            visibility: topic.visibility,
                            createdBy: topic.createdBy))
            }
            return createdTopicsList

        }
    }

}