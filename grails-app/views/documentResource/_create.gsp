
<!-- Modal4 -->
<div class="modal fade" id="shareDocument" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>

        <div class="modal-body">
            <g:uploadForm class="form-horizontal" controller="DocumentResource" action="saveDoc">
                <div class="form-group">
                    <label for="myFile" class=" col-xs-4">Document *</label>

                    <div class="col-xs-5">
                        <g:field type="file" name="myFile"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class=" col-xs-4">Description *</label>

                    <div class="col-xs-8">
                        <g:field type="texarea" class="form-control" name="description"
                                 placeholder="Description"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="id" class=" col-xs-4">Topic *</label>

                    <div class="col-xs-8">
                        <span class="dropdown">
                            <g:select name="id" from="${topics}"
                                      class="btn btn-default dropdown-toggle" type="button"
                                      data-toggle="dropdown"
                                      optionKey="id">Topic
                                <span class="caret"></span>
                                <ul class="dropdown-menu">
                                </ul></g:select>
                        </span>
                    </div>
                </div>



                </div>

                <div class="modal-footer">
                <g:submitButton class="btn btn-primary"
                                name="Share"/>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </g:uploadForm>
        </div>
        </div>

    </div>
</div>