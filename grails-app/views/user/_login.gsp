<div id="login">
<form class="form-horizontal cssform" controller="login" action="${postUrl}" method='POST' id='loginForm'>
    <div class="form-group">
        <label for="username" class=" col-xs-4"><g:message code="springSecurity.login.username.label"/> *</label>

        <div class="col-xs-8">
            <input type="text" class="form-control text_" name="j_username" id="username"/>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class=" col-xs-4"><g:message code="springSecurity.login.password.label"/> *</label>

        <div class="col-xs-8">
            <input type="password" name="j_password" class="form-control text_" id="password"/>
        </div>
    </div>

<p id="remember_me_holder">
    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
    <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
</p>


    <div class="form-group">
        <a href="" class="col-xs-4" data-toggle="modal" data-target="#forgotPassword">Forgot Password</a>

        <div class="col-xs-5">
            <input type="submit" class="form-control btn btn-primary loginButton"
                            id="submit" name="submit" value='${message(code: "springSecurity.login.button")}'/>
        </div>
    </div>

</form>
</div>
%{--<div id='login'>--}%
        %{--<div class='fheader'><g:message code="springSecurity.login.header"/></div>--}%


        %{--<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>--}%
            %{--<p>--}%
                %{--<label for='username'><g:message code="springSecurity.login.username.label"/>:</label>--}%
                %{--<input type='text' class='text_' name='j_username' id='username'/>--}%
            %{--</p>--}%

            %{--<p>--}%
                %{--<label for='password'><g:message code="springSecurity.login.password.label"/>:</label>--}%
                %{--<input type='password' class='text_' name='j_password' id='password'/>--}%
            %{--</p>--}%

            %{--<p id="remember_me_holder">--}%
                %{--<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>--}%
                %{--<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>--}%
            %{--</p>--}%

            %{--<p>--}%
                %{--<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>--}%
            %{--</p>--}%
        %{--</form>--}%
%{--</div>--}%
