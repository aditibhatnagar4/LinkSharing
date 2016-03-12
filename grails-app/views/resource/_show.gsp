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
        <td><div class="row"><div class="col-xs-8"><strong>${it.userFirstName}</strong><span
                class="text-muted">${it.userName} 5min</span></div>

            <div class="col-xs-4"><g:link controller="topic" action="index">${it.topicName}</g:link></div></div>
            ${it.description}<br>
            <i class="fa fa-facebook-official glyphicon blue"></i>
            <i class="fa fa-tumblr twitter"></i>
            <i class="fa fa-google-plus red"></i>

            <div class="pull-right">
                <ls:resourceType resourceId="${it.resourceId}" url="${it.url}"/>
                <a href="#"><ls:markAsRead isRead="${it.isRead}"/>&nbsp;</a>

                <g:link controller="resource" action="showResource"
                        params="[id: it.resourceId]">View Post</g:link></div>
        </td>

        </tr>
    </g:each>
    </tbody>
</table>





