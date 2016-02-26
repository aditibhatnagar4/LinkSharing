<%@ page import="com.ttnd.bootcamp.Visibility" %>
<!DOCTYPE HTML>
<html>
<head>
	<asset:stylesheet href="bootstrap-3.3.6-dist/css/bootstrap.css"/>
	<asset:stylesheet href="font-awesome-4.5.0/css/font-awesome.css"/>
	<asset:stylesheet href="myStylesheet.css"/>

<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body class=" margin-top">

<div class="container">


	<nav class="navbar navbar-default">
    	<div class="navbar-header">
      	<a class="navbar-brand text-color" href="#"><ins>LinkSharing</ins></a>
    	</div>  
		<div class="pull-right">

			<form class="form-inline">
              <div class="form-group navbar-search search-box">
                <div class="input-group">
                  <div class="input-group-addon"><div class="glyphicon glyphicon-search font-size-sm"></div></div>
                  <input type="text" class="form-control" id="SearchBox" placeholder="Search">
                  <div class="input-group-addon "><div class="glyphicon glyphicon-remove-sign font-size-sm"></div></div>
                </div>
              </div>
  
            </form>
        </div>
    </nav>

    <div class="row">
		<div class="col-md-7">
			<div class="panel panel-default" >
  				<div class="panel-heading">Recent Shares</div>
  				<div class="panel-body">
				<table class="table">
				    <tbody>
				      <tr class=" spec_table">
					<td><asset:image src="image1.png"/></td>
					<td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh </strong><span class="text-muted">@uday 5min</span></div><div class="col-xs-4"><a href="#">Grails</a></div></div>
Doe fgbueinf grbacu gfbu uifwb gfaub gau cjbjkv jkcbz zcjbjkxzvh cjzc jkcbj cjkksbvjk cjkbzjkvbz cjbj zhjhdjslk vkjfbf vknvkvc vkvnd xv,mnknn vdkxv vcnxkxb xcmnvklxcnv xkcvnkl <br>
					<div class="row"><div class="col-xs-8">
						<i class="fa fa-facebook-official"></i>
						<i class="fa fa-tumblr"></i>
						<i class="fa fa-google-plus"></i>
					</div><div class="col-xs-4"><a href="#">View Post</a></div></div>
					</td>
					
				      </tr>
				      <tr>
					<td><asset:image src="image1.png"/></td>
					<td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh </strong><span class="text-muted">@uday 10min</span></div><div class="col-xs-4"><a href="#">Grails</a></div></div>
Dooley jbsdcn hvhvsa duu ge buu asg b ih dhfhgf afgh vghb vhjasjvbk shjfghkbfj scbjkzd bvdjb chvxjzb sfjbjbdk jbvjj jkdvbjkd jhfgh jksfh sklj ghfl gkjkfj djkghd <br>
						<div class="row"><div class="col-xs-8">
						<i class="fa fa-facebook-official"></i>
						<i class="fa fa-tumblr"></i>
						<i class="fa fa-google-plus"></i>
	 					</div><div class="col-xs-4"><a href="#">View Post</a></div></div>
					</td>
					
				      </tr>
				    </tbody>
				  </table>

				</div>
			</div>

			
			<div class="panel panel-default" >
  				<div class="panel-heading"><div class="row"><div class="col-xs-8">Top Posts</div>
					<div class="col-xs-4">
						<div class="dropdown">
						  <g:select class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" name="dropdownDay" from="${Visibility.values()}"> Today
						  <span class="caret"></span>
						  <ul class="dropdown-menu">
						    <li><a href="#">Today</a></li>
						    <li><a href="#">1 day</a></li>
						    <li><a href="#">1 week</a></li>
						    <li><a href="#">1 year</a></li>
						  </ul></g:select>
						</div>
					</div>
					</div>
				</div>
  				<div class="panel-body">
					<table class="table">
				    <tbody>
				      <tr class=" spec_table">
					<td><asset:image src="image1.png"/></td>
					<td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh </strong><span class="text-muted">@uday 21 jul 2014</span></div><div class="col-xs-4"><a href="#">Grails</a></div></div>
Doe fgbueinf grbacu gfbu uifwb gfaub gau cjbjkv jkcbz zcjbjkxzvh cjzc jkcbj cjkksbvjk cjkbzjkvbz cjbj zhjhdjslk vkjfbf vknvkvc vkvnd xv,mnknn vdkxv vcnxkxb xcmnvklxcnv xkcvnkl <br>
					<div class="row"><div class="col-xs-8">
						<i class="fa fa-facebook-official"></i>
						<i class="fa fa-tumblr"></i>
						<i class="fa fa-google-plus"></i>
					</div><div class="col-xs-4"><a href="#">View Post</a></div></div>
					</td>
					
				      </tr>
				     </tbody>
				  </table>

				</div>
			</div>


			

		</div>
	
		<div class="col-md-5">

			<div class="panel panel-default">
  				<div class="panel-heading">Login</div>
  				<div class="panel-body">

					<g:hasErrors bean="{user}">
						<div class="alert alert-danger">
							<g:eachError>
								<g:message error="$it"></g:message>
							</g:eachError>
						</div>

					</g:hasErrors>

					<g:form class="form-horizontal">
        					<div class="form-group">
           						<label for="inputEmail" class=" col-xs-4">Email/Username *</label>

            						<div class="col-xs-8">
                					<g:field type="email" class="form-control" name="inputEmail"></g:field>
            						</div>
        					</div>
						<div class="form-group">
						    <label for="inputPassword" class=" col-xs-4">Password *</label>

						    <div class="col-xs-8">
							<g:passwordField name="inputPassword" class="form-control" id="inputPassword" ></g:passwordField>
						    </div>
						</div>
						<div class="form-group">
           						<a href="#" class=" col-xs-4">Forgot Password</a>

            						<div class="col-xs-5" >
                					<g:submitButton name="loginButton" class="form-control btn btn-primary" id="loginButton" value="Login"></g:submitButton>
            						</div>
        					</div>

					</g:form>
				</div>
			</div>



			<div class="panel panel-default">
  				<div class="panel-heading">Register</div>
  				<div class="panel-body">
					<g:form class="form-horizontal">
        					<div class="form-group">
            						<label class=" col-xs-5">First Name *</label>

            						<div class="col-xs-7">
                						<g:textField class="form-control" name="firstName" ></g:textField>
            						</div>
        					</div>
        					<div class="form-group">
            						<label class=" col-xs-5">Last Name *</label>

            						<div class="col-xs-7">
                						<g:textField class="form-control" name="lastName" ></g:textField>
            						</div>
        					</div>
						<div class="form-group">
						    <label class=" col-xs-5">Email *</label>

						    <div class="col-xs-7">
							<g:field type="email" class="form-control" name="inputEmail1"></g:field>
						    </div>
						</div>
						<div class="form-group">
						    <label class=" col-xs-5">Username *</label>

						    <div class="col-xs-7">
							<g:textField class="form-control" name="inputUser" ></g:textField>
						    </div>
						</div>
						<div class="form-group">
						    <label class=" col-xs-5">Password *</label>

						    <div class="col-xs-7">
							<g:passwordField class="form-control" name="inputPassword1" ></g:passwordField>
						    </div>
						</div>
						<div class="form-group">
						    <label class=" col-xs-5">Confirm Password *</label>

						    <div class="col-xs-7">
							<g:passwordField class="form-control" name="confirmPassword" ></g:passwordField>
						    </div>
						</div>
						<div class="form-group">
						    <label class=" col-xs-5 align-left">Photo </label>

						    <div class="col-xs-4">
							<g:textField class="form-control" name="inputPhoto" />
						    </div>
						    <div class="col-xs-3">
							<g:submitButton class="form-control btn btn-primary" name="browseButton" value="Browse"></g:submitButton>
						    </div>

						</div>
						<div class="form-group">
						    <label class=" col-xs-5"></label>

						    <div class="col-xs-7">
							<g:submitButton class="form-control btn btn-primary" name="registerButton" value="Register"></g:submitButton>
						    </div>
						</div>


					</g:form>
					<ls:showAdmin admin="true">This is only visible to admin</ls:showAdmin>
					<ls:showAdmin admin="false">This will not be visible</ls:showAdmin>
					<ls:showUserList/>


				</div>
			</div>
		</div>
	</div>
</div>

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>

</body>
</html>
