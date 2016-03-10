<g:uploadForm class="form-horizontal" controller="user" action="registerUser">

    <g:hasErrors bean="${user}">
        <div class="alert alert-danger">
            Registration Failed
        </div>
    </g:hasErrors>

    <div class="form-group">
        <label class="col-xs-5">First Name *</label>

        <div class="col-xs-7">
            <g:textField class="form-control" name="firstName" value="${user?.firstName}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="firstName"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5">Last Name *</label>

        <div class="col-xs-7">
            <g:textField class="form-control" name="lastName" value="${user?.lastName}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="lastName"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5">Email *</label>

        <div class="col-xs-7">
            <g:field type="email" class="form-control" name="email" value="${user?.email}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="email"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5">Username *</label>

        <div class="col-xs-7">
            <g:textField class="form-control" name="userName" value="${user?.userName}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="userName"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5">Password *</label>

        <div class="col-xs-7">
            <g:passwordField class="form-control" name="password" value="${user?.password}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="password"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5">Confirm Password *</label>

        <div class="col-xs-7">
            <g:passwordField class="form-control" name="confirmPassword" value="${user?.confirmPassword}"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="confirmPassword"/>
            </div>
        </g:hasErrors>
    </div>

    <div class="form-group">
        <label class=" col-xs-5 align-left">Photo</label>

        <div class="col-xs-3">
            <g:field type="file" name="file" value="Browse"/>
        </div>
        <g:hasErrors bean="${user}">
            <div class="alert-danger" role="alert">
                <g:renderErrors bean="${user}" field="file"/>
            </div>
        </g:hasErrors>
    </div>


    <div class="form-group">
        <label class=" col-xs-5"></label>

        <div class="col-xs-7">
            <g:submitButton class="form-control btn btn-primary" name="registerButton"
                            value="Register"/>
        </div>
    </div>

</g:uploadForm>