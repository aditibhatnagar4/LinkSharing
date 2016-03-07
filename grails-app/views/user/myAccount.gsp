<%@ page import="com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>
<%@ page import="com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>

<div class="container box">

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-3 image">
                            <asset:image src="image1.png" height="100" width="100"/>
                        </div>

                        <div class="col-xs-4">
                            Uday Pratap Singh
                            <p class="text-muted">@uday</p>

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
                            <g:if test="${subscribedTopics.indexOf(it)==0}">
                                <tr class="spec_table">
                            </g:if>
                            <g:else>
                                <tr>
                            </g:else>

                            <td><asset:image src="image1.png" height="100" width="100"/></td>
                                <td>
                                    <a href="#">${it.name}</a>

                                    <div class="row">
                                        <div class="col-xs-5 text-muted">${it.createdBy.userName}<div><a
                                                href="#">Unsubscribe</a></div>
                                        </div>

                                        <div class="col-xs-5 text-muted">Subscriptions<div
                                                class="text-color"><ls:subscriptionCount topicId="${it.id}"/></div>
                                        </div>

                                        <div class="col-xs-2 text-muted">Post<div class="text-color"><ls:resourceCount topicId="${it.id}"/></div></div>

                                    </div>

                                </td>

                            </tr>



                            <tr class="spec_table"><td colspan="2">
                                <div class="row">

                                    <div class="dropdown col-xs-5">
                                        <g:select name="dropdownSeriousness2"
                                                  from="${com.ttnd.bootcamp.Seriousness.values()}"
                                                  class="btn btn-primary dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Seriousness
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">

                                            </ul></g:select>
                                    </div>

                                    <g:if test="${session.user == it.createdBy || session.user.admin == true}">
                                        <div class="dropdown col-xs-4">
                                            <g:select from="${com.ttnd.bootcamp.Visibility.values()}"
                                                      name="dropdownVisibility"
                                                      class="btn btn-primary dropdown-toggle"
                                                      type="button"
                                                      data-toggle="dropdown">Visibility
                                                <span class="caret"></span>
                                                <ul class="dropdown-menu">

                                                </ul></g:select>
                                        </div>
                                    </g:if>

                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                                          data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <g:if test="${session.user == it.createdBy || session.user.admin == true}">
                                        <i class="fa fa-file-o col-xs-1 font-size-md" data-toggle="modal"
                                           data-target="#shareDocument"></i>

                                        <span class="glyphicon glyphicon-trash col-xs-1 font-size-md"></span>
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

                            <g:render template="/resource/show" model="[readingItems:readingItems]"/>


                </div>
            </div>

        </div>
    </div>

</div>


