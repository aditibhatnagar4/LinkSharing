<table class="table">
    <tbody>
    <g:each in="${subscribedUsers}">
        <g:if test="${subscribedUsers.indexOf(it)==0}">
            <tr class="spec_table">
        </g:if>
        <g:else>
            <tr><hr>
        </g:else>


        <div class="row">
    <div class="col-xs-3 image">
        <asset:image src="image1.png" height="100" width="100"/>
    </div>

    <div class="col-xs-4">
        ${it.firstName}
        <p class="text-muted">@${it.userName}</p>

        <div class="row">
            <div class="text-muted col-xs-8">Subscription
            </div>

            <div class="text-muted col-xs-4">Topics
            </div></div>

        <div class="row">
            <div class="col-xs-8 text-color">50
            </div>

            <div class="col-xs-4 text-color">30
            </div></div>
    </div>
</div>

</tr>
</g:each>
    </tbody>
</table>