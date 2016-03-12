<!-- Modal2 -->
<div class="modal fade" id="sendInvitation" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

        <div class="modal-body">
            <g:form class="form-horizontal" name="invitationForm" controller = "topic" action = "invite">
                <div class="form-group">
                    <label for="emailID" class=" col-xs-4">Email *</label>

                    <div class="col-xs-8">
                        <g:field type="email" class="form-control"
                                 id="emailID"
                                 name="emailID"
                                 placeholder="Email"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="topic" class=" col-xs-4">Topic *</label>

                    <div class="col-xs-8">
                    <span class="dropdown">
                        <g:select class="btn btn-default dropdown-toggle" type="button"
                                  data-toggle="dropdown"
                                  from="${topics}" name="topic"
                                  optionKey="id">Topic
                            <span class="caret"></span>
                            <ul class="dropdown-menu">
                            </ul></span>
                        </g:select>

                    </div>
                </div>



                </div>

                <div class="modal-footer">
                <g:submitButton name="Invite" class="btn btn-primary"/></g:form>
            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
        </div>

        </div>

    </div>
</div>