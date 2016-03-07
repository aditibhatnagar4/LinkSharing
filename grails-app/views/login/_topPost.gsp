<table class="table">
    <tbody>
    <g:each in="${topPosts}">
        <g:if test="${topPosts.indexOf(it)==0}">
            <tr class="spec_table">
        </g:if>
        <g:else>
            <tr>
        </g:else>
        <td><asset:image src="image1.png"/></td>
        <td>
            <div class="row">
                <div class="col-xs-8">
                    <strong>${it.createdBy}</strong>
                    <span class="text-muted">@uday 21 jul 2014</span>
                </div>

                <div class="col-xs-4">
                    <a href="" controller="topic" action="showTopic">${it.topic}</a>
                </div>
            </div>${it.description}
            <br>

            <div class="row"><div class="col-xs-8">
                <i class="fa fa-facebook-official"></i>
                <i class="fa fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
            </div>

                <div class="col-xs-4"><a href="#">View Post</a></div></div>
        </td>

        </tr>
    </g:each>
    </tbody>
</table>
