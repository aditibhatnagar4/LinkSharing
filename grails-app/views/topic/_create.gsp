<%@ page import="com.ttnd.bootcamp.Visibility" %>
<!-- Modal1 -->
<div class="modal fade" id="createTopic" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="topic" action="saveTopic">
                <div class="form-group">
                    <label for="topicName" class=" col-xs-4">Name *</label>

                    <div class="col-xs-8">
                        <g:textField class="form-control" name="topicName"
                                     placeholder="Name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputVisibility" class=" col-xs-4">Visibility *</label>

                    <div class="col-xs-8">
                    <span class="dropdown" id="inputVisibility">
                        <g:select name="visibility" from="${Visibility.values()}"
                                  class="btn btn-default dropdown-toggle" type="button"
                                  data-toggle="dropdown">Visibility Mode
                            <span class="caret"></span>
                            <ul class="dropdown-menu">

                            </ul>
                            </span></g:select>
                    </div>
                </div>



                </div>

                <div class="modal-footer">
                <g:submitButton class="btn btn-primary" name="Save">Save</g:submitButton>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button></g:form>
        </div>
        </div>

    </div>
</div>
