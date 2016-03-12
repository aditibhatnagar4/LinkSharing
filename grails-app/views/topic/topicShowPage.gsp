<%@ page import="com.ttnd.bootcamp.User; com.ttnd.bootcamp.Topic; com.ttnd.bootcamp.VO.TopicVO; com.ttnd.bootcamp.Subscription; com.ttnd.bootcamp.Visibility; com.ttnd.bootcamp.Seriousness" %>

<g:applyLayout name="main"/>

<div class="container">
    <div class="row">
        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-heading">Topic: "Grails"</div>

                <div class="panel-body">

                    <table class="table">
                        <tbody>

                        <tr class="spec_table">
                            <td><ls:userImage id="${session.user.id}"/></td>
                            <td>
                                <a href="#">Grails</a>

                                <div class="row">
                                    <div class="col-xs-5 text-muted">@uday<div><a
                                            href="#">Unsubscribe</a></div>
                                    </div>

                                    <div class="col-xs-5 text-muted">Subscriptions<div
                                            class="text-color">50</div>
                                    </div>

                                    <div class="col-xs-2 text-muted">Post<div class="text-color">30</div></div>

                                </div>

                            </td>

                        </tr>



                        <tr class="spec_table"><td colspan="2">
                            <div class="row">

                                <div class="dropdown col-xs-5">
                                    <g:select name="dropdownSeriousness2"
                                              from="${com.ttnd.bootcamp.Seriousness.values()}"
                                              class="btn btn-primary dropdown-toggle" type="button"
                                              data-toggle="dropdown">Seriousness
                                        <span class="caret"></span>
                                        <ul class="dropdown-menu">

                                        </ul></g:select>
                                </div>
                                <span class="glyphicon glyphicon-envelope col-xs-1 font-size-md"
                                      data-toggle="modal"
                                      data-target="#sendInvitation"></span>
                            </div>
                        </td></tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Users: "Grails"</div>

                <div class="panel-body">

                    <g:render template="/user/show"/>

                </div>
            </div>

        </div>

        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-heading">Posts: "Grails"</div>

                <div class="panel-body">
                    <ls:readingItems/>

                </div>
            </div>
        </div>
    </div>
</div>