<!-- Modal3 -->
<div class="modal fade" id="shareLink" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Link</h4>
            </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="LinkResource" action="saveLinkResource">
                <div class="form-group">
                    <label for="url" class=" col-xs-4">Link *</label>

                    <div class="col-xs-8">
                        <g:textField class="form-control" name="url"
                                     placeholder="Link"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class=" col-xs-4">Description *</label>

                    <div class="col-xs-8">
                        <g:field type="textarea"
                                 class="form-control"
                                 name="description"
                                 placeholder="Description"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicName" class=" col-xs-4">Topic *</label>

                    <div class="col-xs-8">
                        <span class="dropdown">
                            <g:select class="btn btn-default dropdown-toggle" type="button"
                                      data-toggle="dropdown" name="topicName" from="${topics}">
                                Topic
                                <span class="caret"></span>
                                <ul class="dropdown-menu">

                                </ul>
                            </g:select>
                        </span>
                    </div>
                </div>
                </div>
                <div class="modal-footer">
                <g:submitButton class="btn btn-primary" name="Share"/>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </g:form>
        </div>
        </div>
    </div>
</div>

