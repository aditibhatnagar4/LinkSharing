<%@ page import="com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>
<!DOCTYPE HTML>
<html>
<head>
    <asset:stylesheet href="bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <asset:stylesheet href="font-awesome-4.5.0/css/font-awesome.css"/>
    <asset:stylesheet href="myStylesheet.css"/>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<body>

<div class="container box">

    <nav class="navbar navbar-default ">
        <div class="navbar-header navbar-inline">
            <div class="row"><div class="col-xs-4">
                <a href="#" class="navbar-brand text-color"><ins>LinkSharing</ins></a>
            </div>

                <div class="col-xs-4">
                    <div class="input-group navbar-search">
                        <span class="input-group-addon"><div class="glyphicon glyphicon-search font-size-sm"></div>
                        </span>
                        <input type="text" class="form-control" id="SearchBox" placeholder="Search">
                        <span class="input-group-addon"><div class="glyphicon glyphicon-remove-sign font-size-sm"></div>
                        </span>
                    </div>
                </div>


                <div class="col-xs-2 navbar-option">
                    <i class="fa fa-comment font-size-lg" data-toggle="modal" data-target="#createTopic"></i>
                    <span class="glyphicon glyphicon-envelope font-size-md" data-toggle="modal"
                          data-target="#sendInvitation"></span>
                    <span class="glyphicon glyphicon-link font-size-md" data-toggle="modal"
                          data-target="#shareLink"></span>
                    <i class="fa fa-file-o font-size-md" data-toggle="modal" data-target="#shareDocument"></i></div>

                <div class="col-xs-2 navbar-glyphicon">
                    <span class="glyphicon glyphicon-user font-size-md"></span>

                    <span class="dropdown" id="navMenuDropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Uday
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Users</a></li>
                            <li><a href="#">Topics</a></li>
                            <li><a href="#">Posts</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </span>
                </div>
            </div>
        </div>


        <!-- Modal1 -->
        <div class="modal fade" id="createTopic" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Create Topic</h4>
                    </div>

                    <div class="modal-body">
                        <g:form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputName" class=" col-xs-4">Name *</label>

                                <div class="col-xs-8">
                                    <g:textField class="form-control" name="inputName" placeholder="Name"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputVisibility" class=" col-xs-4">Visibility *</label>

                                <div class="col-xs-8">
                                <span class="dropdown" id="inputVisibility">
                                    <g:select name="dropdownVisibility" from="${Visibility.values()}"
                                              class="btn btn-default dropdown-toggle" type="button"
                                              data-toggle="dropdown">Visibility Mode
                                        <span class="caret"></span>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Public</a></li>
                                            <li><a href="#">Private</a></li>

                                        </ul>
                                        </span></g:select>
                                </div>
                            </div>

                        </g:form>

                    </div>

                    <div class="modal-footer">
                        <g:submitButton class="btn btn-primary" name="submitButton1">Save</g:submitButton>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

            </div>
        </div>



        <!-- Modal2 -->
        <div class="modal fade" id="sendInvitation" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Send Invitation</h4>
                    </div>

                    <div class="modal-body">
                        <g:form class="form-horizontal" name="invitationForm">
                            <div class="form-group">
                                <label for="inputEmail" class=" col-xs-4">Email *</label>

                                <div class="col-xs-8">
                                    <g:field type="email" class="form-control"
                                             id="inputEmail"
                                             name="inputEmail"
                                             placeholder="Email"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputTopic" class=" col-xs-4">Topic *</label>

                                <div class="col-xs-8">
                                    <span class="dropdown" id="inputTopic">
                                        <g:select class="btn btn-default dropdown-toggle" type="button"
                                                  data-toggle="dropdown"
                                                  from="${topics}" name="dropdownTopic">Topic
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Topic1</a></li>
                                                <li><a href="#">Topic2</a></li>
                                                <li><a href="#">Topic3</a></li>
                                                <li><a href="#">Topic4</a></li>
                                                <li><a href="#">Topic5</a></li>
                                            </ul></g:select>
                                    </span>
                                </div>
                            </div>

                        </g:form>

                    </div>

                    <div class="modal-footer">
                        <g:submitButton name="submitButton2" class="btn btn-primary">Invite</g:submitButton>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

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
                        <g:form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputLink" class=" col-xs-4">Link *</label>

                                <div class="col-xs-8">
                                    <g:textField class="form-control" name="inputLink" placeholder="Link"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputDescription" class=" col-xs-4">Description *</label>

                                <div class="col-xs-8">
                                    <g:field type="textarea"
                                             class="form-control"
                                             name="inputDescription"
                                             placeholder="Description"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputTopic" class=" col-xs-4">Topic *</label>

                                <div class="col-xs-8">
                                    <span class="dropdown">
                                        <g:select class="btn btn-default dropdown-toggle" type="button"
                                                  data-toggle="dropdown" name="dropdownTopic1" from="${topics}">
                                            Topic
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Topic1</a></li>
                                                <li><a href="#">Topic2</a></li>
                                                <li><a href="#">Topic3</a></li>
                                                <li><a href="#">Topic4</a></li>
                                                <li><a href="#">Topic5</a></li>
                                            </ul>
                                        </g:select>
                                    </span>
                                </div>
                            </div>

                        </g:form>

                    </div>

                    <div class="modal-footer">
                        <g:submitButton class="btn btn-primary" name="submitButton1">Share</g:submitButton>
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
                        <g:form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputDocument" class=" col-xs-4">Document *</label>

                                <div class="col-xs-5">
                                    <g:textField class="form-control" name="inputDocument"
                                                 placeholder="Document"></g:textField></div>

                                <div class="col-xs-3"><button type="file" class="btn btn-primary">Browse</button>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="inputDescription" class=" col-xs-4">Description *</label>

                                <div class="col-xs-8">
                                    <g:field type="texarea" class="form-control" name="inputDescription1"
                                             placeholder="Description"></g:field>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputTopic" class=" col-xs-4">Topic *</label>

                                <div class="col-xs-8">
                                    <span class="dropdown">
                                        <g:select name="dropdownTopics2" from="${topics}"
                                                  class="btn btn-default dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Topic
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Topic1</a></li>
                                                <li><a href="#">Topic2</a></li>
                                                <li><a href="#">Topic3</a></li>
                                                <li><a href="#">Topic4</a></li>
                                                <li><a href="#">Topic5</a></li>
                                            </ul></g:select>
                                    </span>
                                </div>
                            </div>

                        </g:form>

                    </div>

                    <div class="modal-footer">
                        <g:submitButton type="submit" class="btn btn-primary"
                                        name="submitButton2">Share</g:submitButton>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>

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
                                </div></div>

                            <div class="row">
                                <div class="col-xs-8 text-color">50
                                </div>

                                <div class="col-xs-4 text-color">30
                                </div></div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Subscriptions<div class="pull-right"><a href="#">View All</a></div></div>

                <div class="panel-body">
                    <table class="table">
                        <tbody>
                        <tr class=" spec_table">
                            <td><asset:image src="image1.png"/></td>
                            <td>
                                <a href="#">Grails</a>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday<div><a href="#">Unsubscribe</a></div></div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div class="text-color">50</div></div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color">30</div></div>

                                </div>

                                <div class="row">
                                    <div class="dropdown col-xs-4">
                                        <g:select name="dropdownSeriousness2"
                                                  from="${Seriousness.values()}"
                                                  class="btn btn-primary dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Serious
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Serious</a></li>
                                                <li><a href="#">Option2</a></li>
                                            </ul></g:select>
                                    </div>

                                    <div class="dropdown col-xs-4">
                                        <g:select from="${Visibility.values()}"
                                                  name="dropdownVisibility" class="btn btn-primary dropdown-toggle"
                                                  type="button"
                                                  data-toggle="dropdown">Visibility
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Public</a></li>
                                                <li><a href="#">Private</a></li>
                                            </ul></g:select>
                                    </div>
                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md" data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <i class="fa fa-file-o col-xs-1 font-size-md" data-toggle="modal"
                                       data-target="#shareDocument"></i>
                                    <span class="glyphicon glyphicon-trash col-xs-1 font-size-md"></span>

                                </div>

                            </td>

                        </tr>
                        <tr>
                            <td><asset:image src="image1.png"/></td>
                            <td>
                                <a href="#">Grails</a>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday<div><a href="#">Unsubscribe</a></div></div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div class="text-color">50</div></div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color">30</div></div>

                                </div>

                                <div class="row">
                                    <div class="dropdown col-xs-4">
                                        <g:select name="dropdownSeriousness"
                                                  from="${Seriousness.values()}"
                                                  class="btn btn-primary dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Serious
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Serious</a></li>
                                                <li><a href="#">Option2</a></li>
                                            </ul></g:select>
                                    </div>

                                    <div class="dropdown col-xs-4">
                                        <g:select name="dropdownVisibility"
                                                  from="${Visibility.values()}"
                                                  class="btn btn-primary dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Visibility
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Public</a></li>
                                                <li><a href="#">Private</a></li>
                                            </ul></g:select>
                                    </div>
                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md" data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <i class="fa fa-file-o col-xs-1 font-size-md" data-toggle="modal"
                                       data-target="#shareDocument"></i>
                                    <span class="glyphicon glyphicon-trash col-xs-1 font-size-md"></span>

                                </div>
                            </td>

                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">Trending Topics</div>

                <div class="panel-body">
                    <table class="table">
                        <tbody>
                        <tr class=" spec_table">
                            <td><asset:image src="image1.png"/></td>
                            <td>
                                <a href="#">Grails</a>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday<div><a href="#">Unsubscribe</a></div></div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div class="text-color">50</div></div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color">30</div></div>

                                </div>

                            </td>

                        </tr>
                        <tr>
                            <td><asset:image src="image1.png"/></td>
                            <td>
                                <div class="row">
                                    <div class="col-xs-5"><div class="form-group">

                                        <input type="text" class="form-control" placeholder=Grails>
                                    </div></div>

                                    <div class="col-xs-3"><g:submitButton name="submitButton4"
                                                                          class="btn btn-primary">Save</g:submitButton>
                                    </div>

                                    <div class="col-xs-4"><button type="button" class="btn btn-primary">Cancel</button>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday<div><a href="#">Unsubscribe</a></div></div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div class="text-color">50</div></div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color">30</div></div>

                                </div>

                                <div class="row">
                                    <div class="dropdown col-xs-4">
                                        <g:select name="dropdownSeriousness3"
                                                  from="${Seriousness.values()}"
                                                  class="btn btn-primary dropdown-toggle" type="button"
                                                  data-toggle="dropdown">Serious
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Serious</a></li>
                                                <li><a href="#">Option2</a></li>
                                            </ul></g:select>
                                    </div>

                                    <div class="dropdown col-xs-4">
                                        <g:select from="${Visibility.values()}"
                                                  name="dropdownVisibility" class="btn btn-primary dropdown-toggle"
                                                  type="button"
                                                  data-toggle="dropdown">Visibility
                                            <span class="caret"></span>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Public</a></li>
                                                <li><a href="#">Private</a></li>
                                            </ul></g:select>
                                    </div>
                                    <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md" data-toggle="modal"
                                          data-target="#sendInvitation"></span>
                                    <i class="fa fa-file-o col-xs-1 font-size-md" data-toggle="modal"
                                       data-target="#shareDocument"></i>
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
                <div class="panel-heading">Inbox</div>

                <div class="panel-body">
                    <table class="table">
                        <tbody>
                        <tr class=" spec_table">
                            <td><asset:image src="image1.png"/></td>
                            <td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh</strong><span
                                    class="text-muted">@uday 5min</span></div>

                                <div class="col-xs-4"><a href="#">Grails</a></div></div>
                                Doe fgbueinf grbacu gfbu uifwb gfaub gau cjbjkv jkcbz zcjbjkxzvh cjzc jkcbj cjkksbvjk cjkbzjkvbz cjbj zhjhdjslk vkjfbf vknvkvc vkvnd xv,mnknn vdkxv vcnxkxb xcmnvklxcnv xkcvnkl <br>

                                <div class="row">
                                    <i class="fa fa-facebook-official glyphicon"></i>
                                    <i class="fa fa-tumblr"></i>
                                    <i class="fa fa-google-plus"></i>

                                    <div class="pull-right">
                                        <a href="#"><ins>Download&nbsp;</ins></a>
                                        <a href="#"><ins>View Full Site&nbsp;</ins></a>
                                        <a href="#"><ins>Mark as read&nbsp;</ins></a>

                                        <a href="#"><ins>View Post</ins></a></div></div>
                            </td>

                        </tr>
                        <tr>
                            <td><asset:image src="image1.png"/></td>
                            <td><div class="row"><div class="col-xs-8"><strong>Uday Pratap Singh</strong><span
                                    class="text-muted">@uday 10min</span></div>

                                <div class="col-xs-4"><a href="#">Grails</a></div></div>
                                Dooley jbsdcn hvhvsa duu ge buu asg b ih dhfhgf afgh vghb vhjasjvbk shjfghkbfj scbjkzd bvdjb chvxjzb sfjbjbdk jbvjj jkdvbjkd jhfgh jksfh sklj ghfl gkjkfj djkghd <br>

                                <div class="row">
                                    <i class="fa fa-facebook-official glyphicon"></i>
                                    <i class="fa fa-tumblr"></i>
                                    <i class="fa fa-google-plus"></i>

                                    <div class="pull-right"><a href="#"><ins>Download&nbsp;</ins></a>
                                        <a href="#"><ins>View Full Site&nbsp;</ins></a>
                                        <a href="#"><ins>Mark as read&nbsp;</ins></a>
                                        <a href="#"><ins>View Post</ins></a></div>
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
<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap-3.3.6-dist/js/bootstrap.min.js"/>

</body>
</html>
