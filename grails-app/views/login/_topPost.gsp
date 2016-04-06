<table class="table">
    <tbody>
    <g:each in="${topPosts}">
        <g:if test="${topPosts.indexOf(it) == 0}">
            <tr class="spec_table">
        </g:if>
        <g:else>
            <tr>
        </g:else>
        <td><ls:userImage id="${it.createdBy.id}"/></td>
        <td>
            <div class="row">
                <div class="col-xs-8">
                    <strong>${it.createdBy.name}</strong>
                    <span class="text-muted"><br>${it.createdBy.username} <br>21 jul 2014</span>
                </div>

                <div class="col-xs-4">
                    <g:link controller="topic" action="showTopic" params="[topicId: it.topic.id]">${it.topic}</g:link>
                </div>
            </div><br>${it.description}<br>
            <br>

            <div class="row"><div class="col-xs-8">
                <i class="fa fa-facebook-official blue"></i>
                <i class="fa fa-tumblr twitter"></i>
                <i class="fa fa-google-plus red"></i>
            </div>

                <div class="col-xs-4"><g:link controller="resource" action="showResource"
                                              params="[id: it.id]">View Post</g:link></div></div>
        </td>

        </tr>
    </g:each>
    </tbody>
</table>
