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
<g:if test="${session.user}">
<g:render template="/documentResource/create" model="[topics: session.user.getSubscribedTopic().toList()]"/>
<g:render template="/linkResource/create" model="[topics: session.user.getSubscribedTopic().toList()]"/>
<g:render template="/topic/create"/>
<g:render template="/topic/email" model="[topics: session.user.getSubscribedTopic().toList()]"/>
<g:render template="/user/forgotPassword"/>
</g:if>
%{--<div class="container">--}%

    <nav class="navbar navbar-default">
        <div class="navbar-header navbar-inline">
            <div class="row">
                <div class="col-xs-4">
                    <a href="#" class="navbar-brand text-color"><ins>LinkSharing</ins></a>
                </div>

                <div class="col-xs-4"><g:form controller="resource" action="search">
                    <div class="input-group navbar-search">

                        <span class="input-group-btn">
                            <input type="text" id="q" name="q" class="form-control input-group"
                                   placeholder="Search">
                        </span>

                        <span class="input-group-btn">
                            <button class="btn btn-default glyphicon glyphicon-search searchButtons"
                                    type="submit"></button>
                        </span></g:form>
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
                        <li><g:link controller = "user" action = "list">
                            Users
                        </g:link></li>
                    </g:if>
                    <li><a href="/login/logout">Logout</a></li>
                    </ul>
                </span>
                </g:if>
            </div>
            </div>
        </div>

    </nav>
<div class="container">
<div class = "jsonResponse" style = "display:none"></div>
</div>
    <g:layoutBody/>

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>
<asset:javascript src="additional-methods.min.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
</body>
</html>
