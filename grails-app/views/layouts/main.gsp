<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ttnd.bootcamp.Visibility" %>
<!DOCTYPE HTML>
<html>
<head>
    <asset:stylesheet href="bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <asset:stylesheet href="font-awesome-4.5.0/css/font-awesome.css"/>
    <asset:stylesheet href="myStylesheet.css"/>
    <meta name="layout" content="page"/>
    <title><g:layoutTitle default="LinkSharing"/></title>
    <g:layoutHead/>
</head>

<body>
<g:render template="/documentResource/create"/>
<g:render template="/linkResource/create"/>
<g:render template="/topic/create"/>
<g:render template="/topic/email"/>
<g:render template="/user/forgotPassword"/>

%{--<div class="container">--}%

    <nav class="navbar navbar-default">
        <div class="navbar-header navbar-inline">
            <div class="row">
                <div class="col-xs-4">
                    <a href="#" class="navbar-brand text-color"><ins>LinkSharing</ins></a>
                </div>

                <div class="col-xs-4">
                    <div class="input-group navbar-search">
                        <span class="input-group-addon"><a href="/resource/searchResource"><div class="glyphicon glyphicon-search font-size-sm"></div></a>
                        </span>
                        <input type="text" class="form-control" id="SearchBox" placeholder="Search">
                        <span class="input-group-addon"><div
                                class="glyphicon glyphicon-remove-sign font-size-sm"></div>
                        </span>
                    </div>
                </div>

                <g:if test="${session.user}">
                    <div class="col-xs-2 navbar-option">
                        <i class="fa fa-comment font-size-lg" data-toggle="modal" data-target="#createTopic"></i>
                        <span class="glyphicon glyphicon-envelope font-size-md" data-toggle="modal"
                              data-target="#sendInvitation"></span>
                        <span class="glyphicon glyphicon-link font-size-md" data-toggle="modal"
                              data-target="#shareLink"></span>
                        <i class="fa fa-file-o font-size-md" data-toggle="modal" data-target="#shareDocument"></i></div>

                    <div class="col-xs-2 navbar-glyphicon">
                        <span class="glyphicon glyphicon-user font-size-md"></span>

                    <span class="dropdown" id="navMenuDropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">My Account
                            <span class="caret"></span>
                        </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                    <g:if test="${session.user.admin == true}">
                        <li><a href="#">Users</a></li>
                    </g:if>
                    <li><a href="/login/logout">Logout</a></li>
                    </ul>
                </span>
                </g:if>
            </div>
            </div>
        </div>





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

    </nav>

<div class = "jsonResponse" style = "display:none"></div>

    <g:layoutBody/>
%{--</div>--}%

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>
<asset:javascript src="additional-methods.min.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
</body>
</html>
