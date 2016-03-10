<!DOCTYPE HTML>
<html>
<head>
    <asset:stylesheet href="bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <asset:stylesheet href="font-awesome-4.5.0/css/font-awesome.css"/>
    <asset:stylesheet href="myStylesheet.css"/>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<body>

<div class="container margin-top">

    <nav class="navbar navbar-default ">
        <div class="navbar-header navbar-inline navbar-width">
            <div class="row">
                <div class="col-xs-4">
                    <a href="#" class="navbar-brand text-color">
                        <ins>LinkSharing</ins>
                    </a>
                </div>

                <div class="col-xs-4">
                    <div class="input-group navbar-search">
                        <div class="input-group-addon">
                            <div class="glyphicon glyphicon-search font-size-sm"></div>
                        </div>
                        <input type="text" class="form-control" id="SearchBox3" placeholder="Search">

                        <div class="input-group-addon">
                            <div class="glyphicon glyphicon-remove-sign font-size-sm"></div>
                        </div>
                    </div>
                </div>


                <div class="col-xs-2 navbar-option">

                    <span class="glyphicon glyphicon-user font-size-md"></span>

                    <span class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Uday
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </span></div>
            </div>
        </div>

    </nav>


    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-3 image">
                            <asset:image src="image1.png"/>
                        </div>

                        <div class="col-xs-4">
                            Uday Pratap Singh
                            <p class="text-muted">@uday</p>

                            <div class="row">
                                <div class="text-muted col-xs-8">Subscription
                                </div>

                                <div class="text-muted col-xs-4">Topics
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-8 text-color">50
                                </div>

                                <div class="col-xs-4 text-color">30
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

                        <div class="col-xs-5">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <div class="glyphicon glyphicon-search font-size-sm"></div>
                                </div>
                                <input type="text" class="form-control" id="SearchBox2" placeholder="Search">

                                <div class="input-group-addon">
                                    <div class="glyphicon glyphicon-remove-sign font-size-sm"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="panel-body">
                    <table class="table">
                        <tbody>
                        <tr class=" spec_table">
                            <td><asset:image src="image1.png"/></td>
                            <td>
                                <div class="row">
                                    <div class="col-xs-5">
                                        <div class="form-group">

                                            <input type="text" class="form-control" placeholder=Grails>
                                        </div>
                                    </div>

                                    <div class="col-xs-3">
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>

                                    <div class="col-xs-4">
                                        <button type="button" class="btn btn-primary">Cancel</button>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday
                                        <div><a href="#">Unsubscribe</a></div>
                                    </div>

                                    <div class="col-xs-5 text-muted">Subscriptions
                                        <div class="text-color">50</div>
                                    </div>

                                    <div class="col-xs-2 text-muted">Post
                                        <div class="text-color">30</div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="dropdown col-xs-4">
                                        <button class="btn btn-primary dropdown-toggle" type="button"
                                                data-toggle="dropdown">Serious
                                            <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Serious</a></li>
                                            <li><a href="#">Option2</a></li>
                                        </ul>
                                    </div>

                                    <div class="dropdown col-xs-4">
                                        <button class="btn btn-primary dropdown-toggle" type="button"
                                                data-toggle="dropdown">Visibility
                                            <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Public</a></li>
                                            <li><a href="#">Private</a></li>
                                        </ul>
                                    </div>
                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md" data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <span class="glyphicon glyphicon-pencil col-xs-1 font-size-md"></span>
                                    <span class="glyphicon glyphicon-trash col-xs-1 font-size-md"></span>

                                </div>
                            </td>

                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>

        <div class="col-md-7">

            <div class="panel panel-default">
                <div class="panel-heading">Profile</div>

                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class=" col-xs-5">First Name *</label>

                            <div class="col-xs-7">
                                <input type="text" class="form-control" id="firstName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5">Last Name *</label>

                            <div class="col-xs-7">
                                <input type="text" class="form-control" id="lastName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5">Username *</label>

                            <div class="col-xs-7">
                                <input type="text" class="form-control" id="inputUser">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5 align-left">Photo</label>

                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="inputPhoto">
                            </div>

                            <div class="col-xs-3">
                                <input type="button" class="form-control btn btn-primary" id="browseButton"
                                       value="Browse">
                            </div>

                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5"></label>

                            <div class="col-xs-7">
                                <input type="submit" class="form-control btn btn-primary" id="updateButton1"
                                       value="Update">
                            </div>
                        </div>

                    </form>

                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Register</div>

                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class=" col-xs-5">Password *</label>

                            <div class="col-xs-7">
                                <input type="password" class="form-control" id="inputPassword1">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5">Confirm Password *</label>

                            <div class="col-xs-7">
                                <input type="password" class="form-control" id="confirmPassword">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class=" col-xs-5"></label>

                            <div class="col-xs-7">
                                <input type="submit" class="form-control btn btn-primary" id="updateButton"
                                       value="Update">
                            </div>
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js">
</script>

</body>
</html>
