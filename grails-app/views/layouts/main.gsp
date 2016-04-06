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

<script type='text/javascript'>
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
</script>


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

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Link Sharing</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
                <li>
                    <a href="javascript:void(0)" id="loader" style="display: none">
                        Loading...
                    </a>
                </li>
                <li>
                    <g:form controller="resource" action="search" class="navbar-form navbar-right" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search" name="q">
                            <g:hiddenField name="topicId" value="0" />
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </g:form>
                </li>
                <g:if test="${session.user}">
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#createTopic">
                            <i class="fa fa-comment"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#shareLink">
                            <i class="fa fa-chain"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#shareDocument">
                            <i class="fa fa-file"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#sendInvitation">
                            <i class="fa fa-envelope"></i>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user">&nbsp;${session.user.name}</i>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><g:link controller="user" action="edit">Profile</g:link> </li>
                            <g:if test="${session.user.admin}">
                                <li><g:link controller="user" action="list">User</g:link> </li>
                            </g:if>
                            <li><g:link controller="login" action="logout">Logout</g:link></li>
                        </ul>
                    </li>
                </g:if>
            </ul>
        </div><!--/.nav-collapse -->
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

    <g:layoutBody/>
    </div>

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>
<asset:javascript src="additional-methods.min.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
%{--<asset:javascript src="https://apis.google.com/js/platform.js" async defer/>--}%
<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>
</html>
