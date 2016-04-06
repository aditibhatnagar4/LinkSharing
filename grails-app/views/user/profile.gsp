<%@ page import="com.ttnd.bootcamp.User; com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-5">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-xs-3 image">
                            <ls:userImage id="${id}"/>
                        </div>

                        <div class="col-xs-4">
                            <% User user = User.get(id) %>
                            ${user.name}
                            <g:hiddenField name="userId" value="${params.id}"/>
                            <p class="text-muted">${user.username}</p>

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
                <div class="panel-heading">Topics</div>

                <div class="panel-body">

                    <div id="createdTopics">
                        <g:render template="/topic/list"/>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Subscriptions</div>

                <div class="panel-body" id="subscribedTopics" name="subscribedTopics">
                    <g:render template="/topic/list"/>
                </div>
            </div>

        </div>

        <div class="col-xs-7">
            <div class="panel panel-default">
                <div class="panel-heading">Posts</div>

                <div class="panel-body">
                    <g:render template="/resource/show" model="[readingItems: readingItems, id: params.id]"/>

                </div>
            </div>
        </div>
    </div>
</div>

<g:javascript>
    $(document).ready(function () {

        $.ajax({
            url: "/user/topics",
            data: {id: $("#userId").val()},
            success: function (result) {
                $("#createdTopics").html(result)
            }

        });

        $.ajax({
            url: "/user/subscriptions",
            data: {id: $("#userId").val()},
            success: function (result) {
                $("#subscribedTopics").html(result)
            }

        });
    });
</g:javascript>
</body>