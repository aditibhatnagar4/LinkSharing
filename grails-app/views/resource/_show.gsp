<table class="table">
                        <tbody>
<g:each in="${readingItems}">


    <g:if test="${readingItems.indexOf(it)==0}">
        <tr class="spec_table">
    </g:if>
    <g:else>
        <tr>
    </g:else>


    <td><asset:image src="image1.png" height="100" width="100"/></td>
            <td><div class="row"><div class="col-xs-8"><strong>${it.userFirstName}</strong><span
                    class="text-muted">${it.userName} 5min</span></div>

                <div class="col-xs-4"><a href="#" >${it.topicName}</a></div></div>
                ${it.description}<br>

                <div class="row">
                    <i class="fa fa-facebook-official glyphicon"></i>
                    <i class="fa fa-tumblr"></i>
                    <i class="fa fa-google-plus"></i>

                    <div class="pull-right">
                        <a href="#"><ins>Download&nbsp;</ins></a>
                        <a href="#"><ins>View Full Site&nbsp;</ins></a>
                        <a href="#"><ins><ls:markAsRead isRead="${it.isRead}"/>&nbsp;</ins></a>

                        <g:link controller="resource" action="showResource" params="[id:it.resourceId]">${it.resourceId}View Post</g:link></div></div>
            </td>

        </tr>
</g:each>
                        </tbody>
</table>





