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
                            <g:if test="${recentPosts.indexOf(it) == 0}">
                                <tr class="spec_table">
                            </g:if>
                            <g:else>
                                <tr>
                            </g:else>

                            <td><ls:userImage id="${it.createdBy.id}"/></td>
                            <td><div class="row"><div class="col-xs-8"><strong>${it.createdBy}</strong><span
                                    class="text-muted">${it.createdBy}</span></div>

                                <div class="col-xs-4"><g:link controller="topic"
                                                              action="showTopic">${it.topic}</g:link></div></div>
                                ${it.description}<br>

                                <div class="row"><div class="col-xs-8">
                                    <i class="fa fa-facebook-official blue"></i>
                                    <i class="fa fa-tumblr twitter"></i>
                                    <i class="fa fa-google-plus red"></i>
                                </div>

                                    <div class="col-xs-4"><g:link controller="resource" action="showResource"
                                                                  params="[id: it.id]">View Post</g:link></div></div>
                            </td>

                            </tr>
                        </g:each>
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
                    <ls:topPosts/>
                </div>
            </div>

        </div>

        <div class="col-md-5">

            <div class="panel panel-default">
                <div class="panel-heading">Login</div>

                <div class="panel-body">
                    <g:render template="/user/login"/>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Register</div>

                <div class="panel-body">
                    <g:render template="/user/register"/>

                </div>
            </div>
        </div>
    </div>
</div>

%{--Class Work--}%

%{--<ls:showAdmin admin="true">This is only visible to admin</ls:showAdmin>--}%
%{--<ls:showAdmin admin="false">This will not be visible</ls:showAdmin>--}%
%{--<ls:showUserList/>--}%

