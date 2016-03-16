<g:form class="form-horizontal" controller="login" action="loginHandler">
    <div class="form-group">
        <label for="userName" class=" col-xs-4">Email/Username *</label>

        <div class="col-xs-8">
            <g:textField class="form-control" name="userName"/>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class=" col-xs-4">Password *</label>

        <div class="col-xs-8">
            <g:passwordField name="password" class="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <a href="" class="col-xs-4" data-toggle="modal" data-target="#forgotPassword">Forgot Password</a>

        <div class="col-xs-5">
            <g:submitButton name="loginButton" class="form-control btn btn-primary loginButton"
                            id="loginButton" value="Login"/>
        </div>
    </div>

</g:form>
