<%--
  Created by IntelliJ IDEA.
  User: aditi
  Date: 26/2/16
  Time: 12:20 PM
--%>

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

<g:if test="${flash.error}">
    ${flash.error}
</g:if>
<div class="container">

    <nav class="navbar navbar-default box" >
        <div class="navbar-header navbar-inline">
            <div class="row">
                <div class="col-xs-4">
                    <a href="#" class="navbar-brand text-color"><ins>LinkSharing</ins></a>
                </div>

                <div class="col-xs-4">
                    <div class="input-group navbar-search">
                        <span class="input-group-addon"><div class="glyphicon glyphicon-search font-size-sm"></div>
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
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Uday
            <span class="caret"></span>
        </button>
    <ul class="dropdown-menu">
        <li><a href="#">Profile</a></li>
        <g:if test="${session.user.admin == true}">
            <li><a href="#">Users</a></li>
            <li><a href="#">Topics</a></li>
            <li><a href="#">Posts</a></li>
            </g:if>
            <li><a href="#">Logout</a></li>
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
                    <g:form class="form-horizontal" name="invitationForm">
                        <div class="form-group">
                            <label for="inputEmail" class=" col-xs-4">Email *</label>

                            <div class="col-xs-8">
                                <g:field type="email" class="form-control"
                                         id="inputEmail"
                                         name="inputEmail"
                                         placeholder="Email"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputTopic" class=" col-xs-4">Topic *</label>

                            <div class="col-xs-8">
                                <span class="dropdown" id="inputTopic">
                                    <g:select class="btn btn-default dropdown-toggle" type="button"
                                              data-toggle="dropdown"
                                              from="${topics}" name="dropdownTopic">Topic
                                        <span class="caret"></span>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Topic1</a></li>
                                            <li><a href="#">Topic2</a></li>
                                            <li><a href="#">Topic3</a></li>
                                            <li><a href="#">Topic4</a></li>
                                            <li><a href="#">Topic5</a></li>
                                        </ul></g:select>
                                </span>
                            </div>
                        </div>

                    </g:form>

                </div>

                <div class="modal-footer">
                    <g:submitButton name="submitButton2" class="btn btn-primary">Invite</g:submitButton>
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
                    <g:form class="form-horizontal">
                        <div class="form-group">
                            <label for="inputDocument" class=" col-xs-4">Document *</label>

                            <div class="col-xs-5">
                                <g:textField class="form-control" name="inputDocument"
                                             placeholder="Document"></g:textField></div>

                            <div class="col-xs-3"><button type="file" class="btn btn-primary">Browse</button>
                            </div>

                        </div>

                        <div class="form-group">
                            <label for="inputDescription1" class=" col-xs-4">Description *</label>

                            <div class="col-xs-8">
                                <g:field type="texarea" class="form-control" name="inputDescription1"
                                         placeholder="Description"></g:field>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputTopic" class=" col-xs-4">Topic *</label>

                            <div class="col-xs-8">
                                <span class="dropdown">
                                    <g:select name="dropdownTopics2" from="${topics}"
                                              class="btn btn-default dropdown-toggle" type="button"
                                              data-toggle="dropdown">Topic
                                        <span class="caret"></span>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Topic1</a></li>
                                            <li><a href="#">Topic2</a></li>
                                            <li><a href="#">Topic3</a></li>
                                            <li><a href="#">Topic4</a></li>
                                            <li><a href="#">Topic5</a></li>
                                        </ul></g:select>
                                </span>
                            </div>
                        </div>

                    </g:form>

                </div>

                <div class="modal-footer">
                    <g:submitButton type="submit" class="btn btn-primary"
                                    name="Share">Share</g:submitButton>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>

        </div>
    </div>

    </nav>



    <g:layoutBody/>
    </div>

    <asset:javascript src="application.js"/>
    <asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>

    </body>
    </html>
