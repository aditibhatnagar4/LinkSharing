<g:applyLayout name="main"/>
<div class="container">
<div class="row">
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading">Trending Topics</div>

            <div class="panel-body">

                %{--<g:render template="/topic/show"/>--}%
                <ls:trendingTopics/>

            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">Top Posts</div>

            <div class="panel-body">

                <ls:topPosts/>


            </div>
        </div>

    </div>
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading">Search for "test"</div>

            <div class="panel-body">

                %{--<g:render template=""/>--}%


            </div>
        </div>
    </div>
</div>
</div>