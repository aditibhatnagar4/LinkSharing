<div class="modal fade" id="editResource" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Resource Description</h4>
            </div>

        <div class="modal-body">
            <g:form name="resourceDescriptionEditForm" class="form-horizontal" controller="resource"
                    action="save" params="[id: post.resourceId]">
<div class="row">
                <div class="col-xs-4">
                    <label for="description" class="form-control-label">Description</label>
                    </div>
                <div class="col-xs-8">
                <g:textArea name="description" id="description"
                                    value="${post.description}" cols="30"/>
                    </div>
    </div>

                </div>
                <div class="modal-footer">
            <g:submitButton class="btn btn-primary" name="submit" value="Save"/>
            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button></g:form>
            </div>


        </div>

    </div>
</div>

