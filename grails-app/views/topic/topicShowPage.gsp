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
                    <td><ls:userImage id="${session.user.id}"/></td>
                    <td>
                        <a href="#">${topic.name}</a>

                        <div class="row">
                            <div class="col-xs-5 text-muted">@${session.user.userName}<div>
                                <ls:showSubscribe topicId="${topic.id}"/>
                            </div>
                            </div>

                            <div class="col-xs-5 text-muted">Subscriptions<div
                                    class="text-color"><ls:subscriptionCount topicId="${topic.id}"/></div>
                            </div>

                            <div class="col-xs-2 text-muted">Post<div class="text-color">
                                <ls:resourceCount topicId="${topic.id}"/>
                            </div></div>

                        </div>

                    </td>

                </tr>



                <tr class="spec_table"><td colspan="2">
                    <div class="row">

                        <div class="dropdown col-xs-5">
                            <ls:seriousnessDropdown topicId="${topic.id}"/>
                        </div>
                        <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                              data-toggle="modal"
                              data-target="#sendInvitation"></span>
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
                                class="text-muted">@${it.resource.createdBy.userName} 5min</span></div>

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