<%@ page import="com.ttnd.bootcamp.Visibility" %>
<g:applyLayout name="main"/>


    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <div class="panel panel-default">
                    <div class="panel-heading">Recent Shares</div>

                    <div class="panel-body">
                        <table class="table">
                            <tbody>
                            <g:each in="${recentPosts}">
                            <g:if test="${recentPosts.indexOf(it)==0}">
                                <tr class="spec_table">
                            </g:if>
                                    <g:else>
                                    <tr>
                                        </g:else>

                                <td><asset:image src="image1.png"/></td>
                                <td><div class="row"><div class="col-xs-8"><strong>${it.createdBy}</strong><span
                                        class="text-muted">${it.createdBy}</span></div>

                                    <div class="col-xs-4"><a href="" controller="topic" action="showTopic">${it.topic}</a></div></div>
                                    ${it.description}<br>

                                    <div class="row"><div class="col-xs-8">
                                        <i class="fa fa-facebook-official"></i>
                                        <i class="fa fa-tumblr"></i>
                                        <i class="fa fa-google-plus"></i>
                                    </div>

                                        <div class="col-xs-4"><g:link controller="resource" action="showResource" params="[id:it.id]">View Post</g:link></div></div>
                                </td>

                            </tr>
                                </g:each>
                            %{--<tr>--}%
                                %{--<td><asset:image src="image1.png"/></td>--}%
                                %{--<td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh</strong><span--}%
                                        %{--class="text-muted">@uday 10min</span></div>--}%

                                    %{--<div class="col-xs-4"><a href="#">Grails</a></div></div>--}%
                                    %{--Dooley jbsdcn hvhvsa duu ge buu asg b ih dhfhgf afgh vghb vhjasjvbk shjfghkbfj scbjkzd bvdjb chvxjzb sfjbjbdk jbvjj jkdvbjkd jhfgh jksfh sklj ghfl gkjkfj djkghd <br>--}%

                                    %{--<div class="row"><div class="col-xs-8">--}%
                                        %{--<i class="fa fa-facebook-official"></i>--}%
                                        %{--<i class="fa fa-tumblr"></i>--}%
                                        %{--<i class="fa fa-google-plus"></i>--}%
                                    %{--</div>--}%

                                        %{--<div class="col-xs-4"><a href="#">View Post</a></div></div>--}%
                                %{--</td>--}%

                            %{--</tr>--}%
                            </tbody>
                        </table>

                    </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading"><div class="row"><div class="col-xs-8">Top Posts</div>

                        <div class="col-xs-4">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button"
                                        data-toggle="dropdown">Today
                                    <span class="caret"></span>
                                </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Today</a></li>
                                        <li><a href="#">1 day</a></li>
                                        <li><a href="#">1 week</a></li>
                                        <li><a href="#">1 year</a></li>
                                    </ul>
                            </div>
                        </div>
                    </div>
                    </div>

                    <div class="panel-body">
                        %{--<g:render template="/login/topPost"/>--}%
                        <ls:topPosts/>
                    </div>
                </div>

            </div>

            <div class="col-md-5">

                <div class="panel panel-default">
                    <div class="panel-heading">Login</div>

                    <div class="panel-body">

                    %{--<g:hasErrors bean="{user}">--}%
                    %{--<div class="alert alert-danger">--}%
                    %{--<g:eachError>--}%
                    %{--<g:message error=" $     it"></g:message>--}%
                    %{--</g:eachError>--}%
                    %{--</div>--}%

                    %{--</g:hasErrors>--}%

                        <g:render template="/user/login"/>
                                            </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading">Register</div>

                    <div class="panel-body">
                        <g:form class="form-horizontal">
                            <div class="form-group">
                                <label class=" col-xs-5">First Name *</label>

                                <div class="col-xs-7">
                                    <g:textField class="form-control" name="firstName"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5">Last Name *</label>

                                <div class="col-xs-7">
                                    <g:textField class="form-control" name="lastName"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5">Email *</label>

                                <div class="col-xs-7">
                                    <g:field type="email" class="form-control" name="inputEmail1"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5">Username *</label>

                                <div class="col-xs-7">
                                    <g:textField class="form-control" name="inputUser"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5">Password *</label>

                                <div class="col-xs-7">
                                    <g:passwordField class="form-control" name="inputPassword1"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5">Confirm Password *</label>

                                <div class="col-xs-7">
                                    <g:passwordField class="form-control" name="confirmPassword"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5 align-left">Photo</label>

                                <div class="col-xs-4">
                                    <g:textField class="form-control" name="inputPhoto"/>
                                </div>

                                <div class="col-xs-3">
                                    <g:submitButton class="form-control btn btn-primary" name="browseButton"
                                                    value="Browse"/>
                                </div>

                            </div>

                            <div class="form-group">
                                <label class=" col-xs-5"></label>

                                <div class="col-xs-7">
                                    <g:submitButton class="form-control btn btn-primary" name="registerButton"
                                                    value="Register"/>
                                </div>
                            </div>

                        </g:form>
                    %{--<ls:showAdmin admin="true">This is only visible to admin</ls:showAdmin>--}%
                    %{--<ls:showAdmin admin="false">This will not be visible</ls:showAdmin>--}%
                    %{--<ls:showUserList/>--}%

                    </div>
                </div>
            </div>
        </div>
    </div>

