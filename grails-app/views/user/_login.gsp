%{--<g:form class="form-horizontal" controller="login" action="loginHandler">--}%
    %{--<div class="form-group">--}%
        %{--<label for="username" class=" col-xs-4">Email/Username *</label>--}%

        %{--<div class="col-xs-8">--}%
            %{--<g:textField class="form-control" name="username"/>--}%
        %{--</div>--}%
    %{--</div>--}%

    %{--<div class="form-group">--}%
        %{--<label for="password" class=" col-xs-4">Password *</label>--}%

        %{--<div class="col-xs-8">--}%
            %{--<g:passwordField name="password" class="form-control"/>--}%
        %{--</div>--}%
    %{--</div>--}%

    %{--<div class="form-group">--}%
        %{--<a href="" class="col-xs-4" data-toggle="modal" data-target="#forgotPassword">Forgot Password</a>--}%

        %{--<div class="col-xs-5">--}%
            %{--<g:submitButton name="loginButton" class="form-control btn btn-primary loginButton"--}%
                            %{--id="loginButton" value="Login"/>--}%
        %{--</div>--}%
    %{--</div>--}%

%{--</g:form>--}%

<div id='login'>
        %{--<div class='fheader'><g:message code="springSecurity.login.header"/></div>--}%


        <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
            <p>
                <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                <input type='text' class='text_' name='j_username' id='username'/>
            </p>

            <p>
                <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                <input type='password' class='text_' name='j_password' id='password'/>
            </p>

            <p id="remember_me_holder">
                <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
            </p>

            <p>
                <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
            </p>
        </form>
</div>
