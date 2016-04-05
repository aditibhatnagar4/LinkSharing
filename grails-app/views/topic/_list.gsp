<%@ page import="com.ttnd.bootcamp.User; com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>



<table class="table">
    <tbody>

    <g:each in="${topics}" var="topic">
        <g:if test="${topics.indexOf(topic) == 0}">
            <tr class="spec_table" tt-topic-id="${topic.id}">
        </g:if>
        <g:else>
            <tr tt-topic-id="${topic.id}">
        </g:else>

        <td><ls:userImage id="${topic.createdBy.id}"/></td>
        <td>

            <div class="row">

                <div class="form-group col-xs-4">

                    <g:link controller="topic" action="showTopic" params="[topicId: topic.id]">
                        ${topic.name}
                    </g:link>

                    <div style="display: none" id="editForm${topic.id}" >
                        <span class="row">
                            <g:textField name="topicName" id="name${topic.id}" value="${topic.name}"/>


                            <g:hiddenField name="topicId" id="topicId${topic.id}" value="${topic.id}"/>

                            <span class="row">
                                <button class="saveTopicNameButton btn-primary" topicId="${topic.id}">Save</button>


                                <button class="cancelTopicNameButton btn-primary" topicId="${topic.id}">Cancel</button>
                            </span>
                        </span>
                    </div>


                </div>

            </div>


            <div class="row">
                <div class="col-xs-5 text-muted">${topic.createdBy.username}
                    <div>
                        <ls:showSubscribe topicId="${topic.id}"/>
                    </div>
                </div>

                <div class="col-xs-5 text-muted">Subscriptions<div
                        class="text-color"><ls:subscriptionCount topicId="${topic.id}"/></div>
                </div>

                <div class="col-xs-2 text-muted">Post<div class="text-color"><ls:resourceCount
                        topicId="${topic.id}"/></div></div>

            </div>

        </td>

        </tr>



        <tr class="spec_table" tt-topic-id="${topic.id}"><td colspan="2">
            <div class="row">

                <div class="dropdown col-xs-5">
                    <ls:seriousnessDropdown topicId="${topic.id}"/>
                </div>
                <g:if test="${session.user.id == topic.createdBy.id || session.user.authorities.any { it.authority == "ROLE_ADMIN" }}">
                    <div class="dropdown col-xs-4">
                        <ls:visibilityDropdown topicId="${topic.id}"/>
                    </div>
                </g:if>

                <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                      data-toggle="modal"
                      data-target="#sendInvitation"></span>
                <g:if test="${session.user.id == topic.createdBy.id || session.user.authorities.any { it.authority == "ROLE_ADMIN" }}">

                    <span class="glyphicon glyphicon-pencil col-xs-1 font-size-md edit-topic"
                          data-topic-id="${topic.id}">

                    </span>

                    <ls:showDelete topicId="${topic.id}"/>
                </g:if>
            </div>
        </td></tr>

    </g:each>

    </tbody>
</table>
