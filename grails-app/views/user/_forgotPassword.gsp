<!-- Modal1 -->
<div class="modal fade" id="forgotPassword" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Forgot Password</h4>
            </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="user" action="forgotPassword">
                <div class="form-group">
                    <label for="emailID" class=" col-xs-4">Email Id *</label>

                    <div class="col-xs-8">
                        <g:textField class="form-control" name="emailID"
                                     placeholder="Email"/>
                    </div>
                </div>
                </div>

                <div class="modal-footer">
                <g:submitButton class="btn btn-primary" name="Send"/>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button></g:form>
        </div>
        </div>

    </div>
</div>


