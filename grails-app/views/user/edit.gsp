<g:applyLayout name="main"/>

<div class="container">

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-3 image">
                            <ls:userImage id="${session.user.id}"/>
                        </div>

                        <div class="col-xs-4">
                            ${userCo.name}
                            <p class="text-muted">@${userCo.userName}</p>

                            <div class="row">
                                <div class="text-muted col-xs-8">Subscription
                                </div>

                                <div class="text-muted col-xs-4">Topics
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-8 text-color">50
                                </div>

                                <div class="col-xs-4 text-color"><ls:topicCount userId=""/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-7">
                            Trending Topics
                        </div>

                    </div>

                </div>

                <div class="panel-body">
                    <ls:trendingTopics/>
                </div>
            </div>

        </div>

        <div class="col-md-7">

            <div class="panel panel-default">
                <div class="panel-heading">Profile</div>

                <div class="panel-body">
                    <g:uploadForm class="form-horizontal" name="registrationForm" controller="user" action="save">

                        <g:hasErrors bean="${userCo}">
                            <div class="alert alert-danger">
                                Update Failed
                            </div>
                        </g:hasErrors>
                        <g:hiddenField name="id" value="${userCo?.id}"/>
                        <div class="form-group">
                            <label class="col-xs-5">First Name *</label>

                            <div class="col-xs-7">
                                <g:textField class="form-control" name="firstName" value="${userCo?.firstName}"/>
                            </div>
                            <g:hasErrors bean="${userCo}">
                                <div class="alert-danger" role="alert">
                                    <g:renderErrors bean="${userCo}" field="firstName"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5">Last Name *</label>

                            <div class="col-xs-7">
                                <g:textField class="form-control" name="lastName" value="${userCo?.lastName}"/>
                            </div>
                            <g:hasErrors bean="${userCo}">
                                <div class="alert-danger" role="alert">
                                    <g:renderErrors bean="${userCo}" field="lastName"/>
                                </div>
                            </g:hasErrors>
                        </div>


                        <div class="form-group">
                            <label class=" col-xs-5">Username *</label>

                            <div class="col-xs-7">
                                <g:textField class="form-control" name="userName" value="${userCo?.userName}"/>
                            </div>
                            %{--<g:hasErrors bean="${userCo}">--}%
                                %{--<div class="alert-danger" role="alert">--}%
                                    %{--<g:renderErrors bean="${userCo}" field="userName"/>--}%
                                %{--</div>--}%
                            %{--</g:hasErrors>--}%
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5 align-left">Photo</label>

                            <div class="col-xs-3">
                                <g:field type="file" name="file" value="Browse"/>
                            </div>
                            <g:hasErrors bean="${userCo}">
                                <div class="alert-danger" role="alert">
                                    <g:renderErrors bean="${userCo}" field="file"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5"></label>

                            <div class="col-xs-7">
                                <g:submitButton class="form-control btn btn-primary" name="updateButton"
                                                value="Update"/>
                            </div>
                        </div>

                    </g:uploadForm>

                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Change Password</div>

                <div class="panel-body">
                    <g:form class="form-horizontal" controller="user" action="updatePassword">
                        <g:hiddenField name="id" value="${userCo?.id}"/>
                        <div class="form-group">
                            <label class=" col-xs-5">Old Password *</label>

                            <div class="col-xs-7">
                                <g:passwordField class="form-control" name="oldPassword" value="${userCo?.password}"/>
                            </div>

                        </div>


                        <div class="form-group">
                            <label class=" col-xs-5">Password *</label>

                            <div class="col-xs-7">
                                <g:passwordField class="form-control" name="password" />
                            </div>
                            <g:hasErrors bean="${userCo}">
                                <div class="alert-danger" role="alert">
                                    <g:renderErrors bean="${userCo}" field="password"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5">Confirm Password *</label>

                            <div class="col-xs-7">
                                <g:passwordField class="form-control" name="confirmPassword"
                                                 value="${userCo?.confirmPassword}"/>
                            </div>
                            <g:hasErrors bean="${userCo}">
                                <div class="alert-danger" role="alert">
                                    <g:renderErrors bean="${userCo}" field="confirmPassword"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5"></label>

                            <div class="col-xs-7">
                                <g:submitButton class="form-control btn btn-primary" name="updateButton1"
                                                value="Update"/>
                            </div>
                        </div>

                    </g:form>

                </div>
            </div>

        </div>
    </div>

</div>