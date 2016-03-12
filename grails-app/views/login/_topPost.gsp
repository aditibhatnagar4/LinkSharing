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
                    <strong>${it.createdBy}</strong>
                    <span class="text-muted">@uday 21 jul 2014</span>
                </div>

                <div class="col-xs-4">
                    <g:link controller="topic" action="showTopic">${it.topic}</g:link>
                </div>
            </div>${it.description}
            <br>

            <div class="row"><div class="col-xs-8">
                <i class="fa fa-facebook-official blue"></i>
                <i class="fa fa-tumblr twitter"></i>
                <i class="fa fa-google-plus red"></i>
            </div>

                <div class="col-xs-4"><a href="#">View Post</a></div></div>
        </td>

        </tr>
    </g:each>
    </tbody>
</table>
