<%@ page import="com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>

<div class="container">

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-3 image">
                            <ls:userImage id="${session.user.id}"/>
                        </div>

                        <div class="col-xs-4">
                            ${session.user.name}
                            <p class="text-muted">@${session.user.userName}</p>

                            <div class="row">
                                <div class="text-muted col-xs-8">Subscription
                                </div>

                                <div class="text-muted col-xs-4">Topics
                                </div></div>

                            <div class="row">
                                <div class="col-xs-8 text-color">50
                                </div>

                                <div class="col-xs-4 text-color"><ls:topicCount userId="${session.user.id}"/>
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

                                <div class="row">
                                    <div class="col-xs-5 text-muted">${it.createdBy.userName}<div>
                                        <ls:showSubscribe topicId="${it.id}"/>
                                    </div>
                                    </div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div
                                            class="text-color"><ls:subscriptionCount topicId="${it.id}"/></div>
                                    </div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color"><ls:resourceCount
                                            topicId="${it.id}"/></div></div>

                                </div>

                            </td>

                            </tr>



                            <tr class="spec_table" data-topic-id="${it.id}"><td colspan="2">
                                <div class="row">

                                    <div class="dropdown col-xs-5">
                                        <ls:seriousnessDropdown topicId="${it.id}"/>
                                    </div>

                                    <g:if test="${session.user.id == it.createdBy.id || session.user.admin == true}">
                                        <div class="dropdown col-xs-4">
                                            <ls:visibilityDropdown topicId="${it.id}"/>
                                        </div>
                                    </g:if>

                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                                          data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <g:if test="${session.user.id == it.createdBy.id || session.user.admin == true}">
                                        <span class="glyphicon glyphicon-pencil col-xs-1 font-size-md"></span>
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


