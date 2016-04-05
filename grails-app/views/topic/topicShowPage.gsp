<%@ page import="com.ttnd.bootcamp.Resource; com.ttnd.bootcamp.User; com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>

<div class="container">
    <div class="row">
        <div class="col-xs-6">
<g:if test="${session.user}">
    <div class="panel panel-default">
        <div class="panel-heading">Topic: "${topic.name}"</div>

        <div class="panel-body">

            <table class="table">
                <tbody>


                        <tr class="spec_table">

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

                </tbody>
            </table>

        </div>
    </div>
</g:if>
<div class="panel panel-default">
    <div class="panel-heading">Users: "${topic.name}"</div>

    <div class="panel-body">

        <g:render template="/user/show"/>

    </div>
</div>

</div>

<div class="col-xs-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-6">
                    Posts: "${topic.name}"
                </div>

                <div class="col-xs-6">
                    <g:form controller="resource" action="search">
                        <div class="input-group">

                            <span class="input-group-btn">
                                <input type="text" id="q" name="q" class="form-control input-group"
                                       placeholder="Search">
                        <g:hiddenField name="topicId" value="${topic.id}" />
                            </span>

                            <span class="input-group-btn">
                                <button class="btn btn-default glyphicon glyphicon-search searchButtons"
                                        type="submit"></button>
                            </span>
                    </g:form>
</div>
                </div>

                </div>
            </div>

            <div class="panel-body">
                <table class="table">
                    <tbody>
                    <g:each in="${readingItems}">

                        <g:if test="${readingItems.indexOf(it) == 0}">
                            <tr class="spec_table">
                        </g:if>
                        <g:else>
                            <tr>
                        </g:else>


                        <td><ls:userImage id="${it.userId}"/></td>
                        <td><div class="row"><div class="col-xs-8"><strong>${it.resource.createdBy.name}</strong><span
                                class="text-muted">@${it.resource.createdBy.username} 5min</span></div>

                            <div class="col-xs-4"><g:link controller="topic" action="showTopic"
                                                          params="[topicId: it.resource.topic.id]">${it.resource.topic.name}</g:link></div>
                        </div>
                            ${it.resource.description}<br>
                            <i class="fa fa-facebook-official glyphicon blue"></i>
                            <i class="fa fa-tumblr twitter"></i>
                            <i class="fa fa-google-plus red"></i>

                            <div class="pull-right">
                                <g:if test="${Resource.checkResourceType(it.resource.id) == "LinkResource"}">
                                    <ls:resourceType resourceId="${it.resource.id}" url="${it.resource.url}"/>
                                </g:if>
                                <g:else>
                                    <ls:resourceType resourceId="${it.resource.id}"/>
                                </g:else>
                                <ls:markAsRead isRead="${it.isRead}" id="${it.resource.id}"/>
                                <g:link controller="resource" action="showResource"
                                        params="[id: it.resource.id]">View Post</g:link></div>
                        </td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>
</div>