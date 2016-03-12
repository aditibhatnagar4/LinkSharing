<%@ page import="com.ttnd.bootcamp.User; com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<table class="table">
    <tbody>

    <g:each in="${trendingTopics}" var="topic">
        <g:if test="${trendingTopics.indexOf(topic) == 0}">
            <tr class="spec_table">
        </g:if>
        <g:else>
            <tr>
        </g:else>

        <td><ls:userImage id="${topic.createdBy.id}"/></td>
        <td>

            <div class="row">

                <div class="form-group col-xs-4">

                    <g:textField class="form-control" placeholder="${topic.name}"
                                 name="text"/>
                </div>

                <div class="">
                    <ls:showSave topicId="${topic.id}"/>
                </div>
            </div>


            <div class="row">
                <div class="col-xs-5 text-muted">${topic.createdBy.userName}<div><ls:showSubscribe
                        topicId="${topic.id}"/></div>
                </div>

                <div class="col-xs-5 text-muted">Subscriptions<div
                        class="text-color"><ls:subscriptionCount topicId="${topic.id}"/></div>
                </div>

                <div class="col-xs-2 text-muted">Post<div class="text-color"><ls:resourceCount
                        topicId="${topic.id}"/></div></div>

            </div>

        </td>

        </tr>



        <tr class="spec_table"><td colspan="2">
            <div class="row">

                <div class="dropdown col-xs-5">
                    <ls:seriousnessDropdown/>
                </div>
                <g:if test="${session.user.id == topic.createdBy.id || session.user.admin == true}">
                    <div class="dropdown col-xs-4">
                        <ls:visibilityDropdown/>
                    </div>
                </g:if>

                <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                      data-toggle="modal"
                      data-target="#sendInvitation"></span>
                <g:if test="${session.user.id == topic.createdBy.id || session.user.admin == true}">
                    <span class="glyphicon glyphicon-pencil col-xs-1 font-size-md"></span>
                    <ls:showDelete topicId="${topic.id}"/>
                </g:if>
            </div>
        </td></tr>

    </g:each>

    </tbody>
</table>
