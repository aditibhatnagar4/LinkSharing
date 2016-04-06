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
        <td><div class="row"><div class="col-xs-8"><strong>${it.firstName} ${it.lastName}</strong><span
                class="text-muted"><br>${it.userName} <br>5 min</span></div>

            <div class="col-xs-4"><g:link controller="topic" action="showTopic"
                                          params="[topicId: it.topicId]">${it.topicName}</g:link></div></div>
           <br> ${it.description}<br><br>
            %{--<i class="fa fa-facebook-official glyphicon blue" data-href="http://127.0.0.1/resource/showResource/${it.resourceId}"></i>--}%

            %{--<div class="fb-share-button" data-href="http://127.0.0.1/resource/showResource/${it.resourceId}" data-layout="button_count"></div>--}%
            %{--<div class="fb-share-button" data-layout="button_count"></div>--}%

            %{--<i class="fa fa-tumblr twitter"></i>--}%
            <div class="fb-share-button" data-href="${request.getRequestURL().toString()}" data-layout="button_count"></div>
            <a class="twitter-share-button"
               href="https://twitter.com/intent/tweet?text=${it.description}&url=http://localhost:8080/resource/showResource/${it.resourceId}&hashtags=${it.topicName}">
                <i class="fa fa-twitter twitter"></i></a>
            %{--<i class="fa fa-google-plus red"></i>--}%
            %{--<g:plus action="share"></g:plus>--}%
            <a href="https://plus.google.com/share?url=http://127.0.0.1/resource/showResource/${it.resourceId}"
               onclick="window.open(this.href,
                       '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');
               return false;"><img
                    src="https://www.gstatic.com/images/icons/gplus-16.png" alt="Share on Google+"/></a>


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





