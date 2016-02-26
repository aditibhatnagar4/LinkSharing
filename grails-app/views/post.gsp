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
                        <input type="text" class="form-control" id="SearchBox" placeholder="Search">
                        <div class="input-group-addon">
                            <div class="glyphicon glyphicon-remove-sign font-size-sm"></div>
                        </div>
                    </div>
                </div>


                <div class="col-xs-2 navbar-option">
                    <span class="glyphicon glyphicon-link font-size-md" data-toggle="modal"
                          data-target="#shareLink"></span>
                    <i class="fa fa-file-o font-size-md" data-toggle="modal" data-target="#shareDocument"></i></div>
                <div class="col-xs-2 navbar-glyphicon">
                    <span class="glyphicon glyphicon-user font-size-md"></span>

		  		<span class="dropdown">
						  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"> Uday
                              <span class="caret"></span></button>
						  <ul class="dropdown-menu">
                              <li><a href="#">Profile</a></li>
                              <li><a href="#">Logout</a></li>
                          </ul>
                    </span></div>
            </div>
        </div>


        <!-- Modal3 -->
        <div class="modal fade" id="shareLink" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Share Link</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputLink" class=" col-xs-4">Link *</label>

                                <div class="col-xs-8">
                                    <input type="text" class="form-control" id="inputLink" placeholder="Link">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputDescription" class=" col-xs-4">Description *</label>

                                <div class="col-xs-8">
                                    <textarea class="form-control" id="inputDescription"
                                              placeholder="Description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTopic" class=" col-xs-4">Topic *</label>

                                <div class="col-xs-8">
							<span class="dropdown" id="inputTopic">
						  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"> Topic
                              <span class="caret"></span></button>
						  <ul class="dropdown-menu">
                              <li><a href="#">Topic1</a></li>
                              <li><a href="#">Topic2</a></li>
                              <li><a href="#">Topic3</a></li>
                              <li><a href="#">Topic4</a></li>
                              <li><a href="#">Topic5</a></li>
                          </ul>
                    </span>
                                </div>
                            </div>

                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

            </div>
        </div>


        <!-- Modal4 -->
        <div class="modal fade" id="shareDocument" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Share Document</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputDocument" class=" col-xs-4">Document *</label>

                                <div class="col-xs-5">
                                    <input type="text" class="form-control" id="inputDocument" placeholder="Document">
                                </div>
                                <div class="col-xs-3">
                                    <button type="button" class="btn btn-primary">Browse</button>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="inputDescription" class=" col-xs-4">Description *</label>

                                <div class="col-xs-8">
                                    <textarea class="form-control" id="inputDescription1"
                                              placeholder="Description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTopic" class=" col-xs-4">Topic *</label>

                                <div class="col-xs-8">
							<span class="dropdown">
						  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"> Topic
                              <span class="caret"></span></button>
						  <ul class="dropdown-menu">
                              <li><a href="#">Topic1</a></li>
                              <li><a href="#">Topic2</a></li>
                              <li><a href="#">Topic3</a></li>
                              <li><a href="#">Topic4</a></li>
                              <li><a href="#">Topic5</a></li>
                          </ul>
                    </span>
                                </div>
                            </div>

                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

            </div>
        </div>


    </nav>


    <div class="row">

        <div class="col-md-7">

            <div class="panel panel-default">

                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-2">
                            <asset:image src="image1.png"/>
                        </div>
                        <div class="col-xs-9">
                            <div class="row">
                                <div class="col-xs-7">
                                    <strong>Uday Pratap Singh </strong><br>
                                    <div class="text-muted">
                                        @uday 5min
                                    </div></div>
                                    <span class="col-xs-5">
                                        <a href="#">Grails</a>

                                    <div class="text-muted">
                                        2:45 PM 22 Feb 2014
                                    </div>

                                    <div class="dropdown">
                                        <button class="btn btn-primary dropdown-toggle" type="button"
                                                data-toggle="dropdown"> Rating
                                            <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">ONE</a></li>
                                            <li><a href="#">TWO</a></li>
                                            <li><a href="#">THREE</a></li>
                                            <li><a href="#">FOUR</a></li>
                                            <li><a href="#">FIVE</a></li>
                                        </ul>
                                    </div>

                                    </span>
                                </div>
                            </div>
                        </div>

<br>
            Doe fgbueinf grbacu gfbu uifwb gfaub gau cjbjkv jkcbz zcjbjkxzvh cjzc jkcbj cjkksbvjk cjkbzjkvbz
            cjbj zhjhdjslk vkjfbf vknvkvc vkvnd xv,mnknn vdkxv vcnxkxb xcmnvklxcnv xkcvnkl <br>
            <div class="row">
                <i class="fa fa-facebook-official glyphicon"></i>
                <i class="fa fa-tumblr"></i>
                <i class="fa fa-google-plus"></i>
                <div class="pull-right">
                    <a href="#">
                        <ins> Delete&nbsp;</ins>
                    </a>
                    <a href="#">
                        <ins> Edit&nbsp;</ins>
                    </a>
                    <a href="#">
                        <ins> Download&nbsp;</ins>
                    </a>

                    <a href="#">
                        <ins>View Full Site</ins>
                    </a>
                </div>
            </div>

        </div>
    </div>


</div>


<div class="col-md-5">


    <div class="panel panel-default">
        <div class="panel-heading">Trending Topics</div>
        <div class="panel-body">
            <table class="table">
                <tbody>
                <tr class=" spec_table">
                    <td><asset:image src="image1.png"/></td>
                    <td>
                        <div class="row">
                        <div class="col-xs-8">
                            <strong>Uday Pratap Singh</strong>

                        </div>
                        <div class="col-xs-4">
                            <a href="#">Grails</a>
                        </div></div>
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


                    </td>

                </tr>
                <tr>
                    <td><asset:image src="image1.png"/></td>
                    <td>
                        <div class="row">
                            <div class="col-xs-8">
                                <strong>Uday Pratap Singh</strong>

                            </div>
                            <div class="col-xs-4">
                              <a href="#">Grails</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-5 text-muted">@uday

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
                                        data-toggle="dropdown"> Serious
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Serious</a></li>
                                    <li><a href="#">Option2</a></li>
                                </ul>
                            </div>
                            <div class="dropdown col-xs-5">
                                <button class="btn btn-primary dropdown-toggle" type="button"
                                        data-toggle="dropdown"> Visibility
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Public</a></li>
                                    <li><a href="#">Private</a></li>
                                </ul>
                            </div>

                                <a href="#"><ins>Edit</ins></a>
                            <a href="#"><ins>Delete</ins></a>
                        </div>
                    </td>

                </tr>
                </tbody>
            </table>

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
