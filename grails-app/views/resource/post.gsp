<g:applyLayout name="main"/>
<div class="container">
    <div class="row">
        <div class="col-md-7">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-2">
                            <ls:userImage id="${post.userId}"/>
                        </div>

                        <div class="col-xs-9">
                            <div class="row">
                                <div class="col-xs-7">
                                    <strong>${post.userFirstName}</strong><br>

                                    <div class="text-muted">
                                        @${post.userName} 5min
                                    </div></div>
                                <span class="col-xs-5">
                                    <a href="#">${post.userName}</a>

                                    <div class="text-muted">
                                        ${post.postDate}
                                    </div>

                                    <g:select class="dropdown" name="rating" from="[1, 2, 3, 4, 5]">
                                        <button class="btn btn-primary dropdown-toggle" type="button"
                                                data-toggle="dropdown">Rating
                                            <span class="caret"></span></button>

                                        <div class="dropdown-menu">

                                        </div>
                                    </g:select>

                                </span>
                            </div>
                        </div>
                    </div>

                    <br>
                    ${post.description}<br>

                    <div class="row">
                        <i class="fa fa-facebook-official glyphicon blue"></i>
                        <i class="fa fa-tumblr twitter"></i>
                        <i class="fa fa-google-plus red"></i>

                        <div class="pull-right">
                            <ls:canDeleteResource resourceId="${post.resourceId}"/>
                            <ls:canEdit/>
                            <ls:resourceType resourceId="${post.resourceId}" url="${post.url}"
                                             filePath="${post.filePath}"/>
                        </div>
                    </div>

                </div>
            </div>

        </div>


        <div class="col-md-5">

            <div class="panel panel-default">
                <div class="panel-heading">Trending Topics</div>

                <div class="panel-body">
                    <ls:trendingTopics/>
                </div>
            </div>

        </div>

    </div>

</div>
