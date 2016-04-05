<g:applyLayout name="main"/>
<div class="container">
    <div class="row">
        <div class="col-xs-6">
            <g:if test="${session.user}">
                <div class="panel panel-default">
                    <div class="panel-heading">Trending Topics</div>

                    <div class="panel-body">
                        <ls:trendingTopics/>

                    </div>
                </div>
            </g:if>

            <div class="panel panel-default">
                <div class="panel-heading">Top Posts</div>

                <div class="panel-body">

                    <ls:topPosts/>

                </div>
            </div>

        </div>

        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-heading">Search for "${q}"</div>

                <table class="table">
                    <tbody>
                    <g:each in="${topicPosts}">

                        <g:if test="${topicPosts.indexOf(it) == 0}">
                            <tr class="spec_table">
                        </g:if>
                        <g:else>
                            <tr>
                        </g:else>


                        <td><ls:userImage id="${it.userId}"/></td>
                        <td><div class="row"><div class="col-xs-8"><strong>${it.firstName}</strong><span
                                class="text-muted">@${it.userName} 5min</span></div>

                            <div class="col-xs-4"><g:link controller="topic" action="showTopic"
                                                          params="[topicId: it.topicId]">${it.topicName}</g:link></div>
                        </div>
                            ${it.description}<br>
                            <i class="fa fa-facebook-official glyphicon blue"></i>
                            <i class="fa fa-tumblr twitter"></i>
                            <i class="fa fa-google-plus red"></i>

                            <div class="pull-right">
                                <ls:resourceType resourceId="${it.resourceId}" url="${it.url}"/>
                                <ls:markAsRead isRead="${it.isRead}" id="${it.resourceId}"/>
                                <g:link controller="resource" action="showResource"
                                        params="[id: it.resourceId]">View Post</g:link></div>
                        </td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

                <div class="panel-body">
                </div>
            </div>
        </div>
    </div>
</div>