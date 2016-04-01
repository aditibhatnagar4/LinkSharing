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

%{--<div id="fb-root"></div>--}%
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=1582026362087346";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#share_button').click(function (e) {
            e.preventDefault();
            FB.ui(
                    {
                        method: 'feed',
                        name: 'This is the content of the "name" field.',
                        link: 'http://localhost:8080/',
                        picture: 'http://localhost:8080/img.png',
                        caption: 'This is the content of the "caption" field.',
                        description: 'This is the content of the "description" field, below the caption.',
                        message: ''
                    });
        });
    });
</script>

<g:if test="${session.user}">
<g:render template="/documentResource/create" model="[topics: session.user.getSubscribedTopic().toList()]"/>
<g:render template="/linkResource/create" model="[topics: session.user.getSubscribedTopic().toList()]"/>
<g:render template="/topic/create"/>
<g:render template="/topic/email" model="[topics: session.user.getSubscribedTopic().toList()]"/>

</g:if>
%{--<div class="container">--}%

    <nav class="navbar navbar-default">
        <div class="navbar-header navbar-inline bgColor">
            <div class="row">
                <div class="col-xs-4">
                    <a href="/login/index" class="navbar-brand text-color"><ins>LinkSharing</ins></a>
                </div>

                <div class="col-xs-4"><g:form controller="resource" action="search">
                    <div class="input-group navbar-search">

                        <span class="input-group-btn">
                            <input type="text" id="q" name="q" class="form-control input-group"
                                   placeholder="Search">
                    <g:hiddenField name="topicId" value="0" />
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
                        <li><a href="/user/edit">Profile</a></li>
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
    <g:if test="${flash.message}">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${flash.message}
        </div>
    </g:if>
    <g:elseif test="${flash.error}">
        <div class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${flash.error}
        </div>
    </g:elseif>


    <div class = "jsonResponse" style="display: none">

    </div>
</div>
    <g:layoutBody/>

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>
<asset:javascript src="additional-methods.min.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
%{--<asset:javascript src="https://apis.google.com/js/platform.js" async defer/>--}%
<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>
</html>
