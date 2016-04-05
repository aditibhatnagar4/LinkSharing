<%@ page import="com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>

<body>
<div class="container">

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-3 image">
                            <ls:userImage id="${user.id}"/>
                        </div>

                        <div class="col-xs-4">
                            ${user.name}
                            <p class="text-muted">@${user.username}</p>

                            <div class="row">
                                <div class="text-muted col-xs-8">Subscription
                                </div>

                                <div class="text-muted col-xs-4">Topics
                                </div></div>

                            <div class="row">
                                <div class="col-xs-8 text-color">50
                                </div>

                                <div class="col-xs-4 text-color"><ls:topicCount userId="${user.id}"/>
                                </div></div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Subscriptions<div class="pull-right"><a href="#">View All</a></div>
                </div>

                <div class="panel-body">
                    <table class="table">
                        <tbody>

                        <g:each in="${subscribedTopics}">
                            <g:if test="${subscribedTopics.indexOf(it) == 0}">
                                <tr class="spec_table" data-topic-id="${it.id}">
                            </g:if>
                            <g:else>
                                <tr data-topic-id="${it.id}">
                            </g:else>

                            <td><ls:userImage id="${it.createdBy.id}"/></td>
                            <td>
                                <g:link controller="topic" action="showTopic"
                                        params="[topicId: it.id]">${it.name}</g:link>

                                <div style="display: none" id="editForm${it.id}" >
                                    <span class="row">
                                        <g:textField name="topicName" id="name${it.id}" value="${it.name}"/>


                                        <g:hiddenField name="topicId" id="topicId${it.id}" value="${it.id}"/>

                                        <span class="row">
                                            <button class="saveTopicNameButton btn-success" topicId="${it.id}">Save</button>


                                            <button class="cancelTopicNameButton btn-danger" topicId="${it.id}">Cancel</button>
                                        </span>
                                    </span>
                                </div>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">${it.createdBy.username}<div>
                                        <ls:showSubscribe topicId="${it.id}"/>
                                    </div>
                                    </div>

                                    <div class="col-xs-5 text-muted"><br>Subscriptions<div
                                            class="text-color"><ls:subscriptionCount topicId="${it.id}"/></div>
                                    </div>

                                    <div class="col-xs-2 text-muted"><br>Post<div class="text-color"><ls:resourceCount
                                            topicId="${it.id}"/></div></div>

                                </div>

                            </td>

                            </tr>



                            <tr class="spec_table " data-topic-id="${it.id}"><td colspan="2">
                                <div class="row">

                                    <div class="dropdown col-xs-5">
                                        <ls:seriousnessDropdown topicId="${it.id}"/>
                                    </div>

                                    <g:if test="${session.user.id == it.createdBy.id || session.user.authorities.any { it.authority == "ROLE_ADMIN" }}">
                                        %{--<sec:ifAnyGranted roles="ROLE_USER">--}%
                                            %{--<g:if test="${session.user.id == it.createdBy.id}">--}%
                                        <div class="dropdown col-xs-4">
                                            <ls:visibilityDropdown topicId="${it.id}"/>
                                        </div>
                                                %{--</g:if>--}%
                                            %{--</sec:ifAnyGranted>--}%
                                    </g:if>

                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                                          data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <g:if test="${user.id == it.createdBy.id}">
                                        %{--or the logged in user is admin--}%
                                        <span class="glyphicon glyphicon-pencil col-xs-1 font-size-md sub-edit-topic"
                                              data-topic-id="${it.id}">

                                        </span>

                                        <ls:showDelete topicId="${it.id}"/>
                                    </g:if>
                                </div>
                            </td></tr>

                        </g:each>

                        </tbody>
                    </table>

                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Trending Topics</div>

                <div class="panel-body">
                    <ls:trendingTopics/>
                </div>
            </div>

        </div>

        <div class="col-md-7">

            <div class="panel panel-default">
                <div class="panel-heading">Inbox</div>

                <div class="panel-body">

                    <g:render template="/resource/show" model="[readingItems: readingItems]"/>

                </div>
            </div>

        </div>
    </div>

</div>

</body>