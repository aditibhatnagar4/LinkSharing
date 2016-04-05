// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self


function ajaxSuccess(result) {

    if (result) {
        var jsonResponseDiv = $(".jsonResponse");

        if (result.message) {
            jsonResponseDiv.text(result.message);
            jsonResponseDiv.addClass("alert alert-success");
        }
        else {
            jsonResponseDiv.text(result.error);
            jsonResponseDiv.addClass("alert alert-danger");
        }
        jsonResponseDiv.css({'display': 'block'});

    }

}


function unsubscribe(id) {
    event.preventDefault();
    $.ajax({
            url: '/subscription/deleteSubscription',
            data: {id: id},
            method: 'post',
            success: ajaxSuccess

        }
    );
    $("tr[data-topic-id='" + id + "']").remove();

}


function subscribe(id) {
    event.preventDefault();
    $.ajax({
            url: '/subscription/saveSubscription',
            data: {id: id},
            method: 'post',
            success: ajaxSuccess
        }
    );

   // $("tr[data-topic-id='" + id + "']").after($(".sub-add"));
}


$(document).ready(function () {

    $(".unsubscribe").on('click', function () {
        var topicId = $(this).data('topicid');
        unsubscribe(topicId);

    });

    $(".subscribe").on('click', function () {
        var topicId = $(this).data('topicid');
        subscribe(topicId);
    });


    $(".seriousness").change(function () {
        $.ajax({
            url: "/subscription/updateSubscription",
            data: {topicId: $(this).attr('topicId'), seriousness: $(this).val()},
            success: ajaxSuccess
        });
    });


    $(".visibility").change(function () {
        $.ajax({
            url: "/topic/saveTopicVisibility",
            data: {topicName: $(this).attr('topicName'), visibility: $(this).val()},
            success: ajaxSuccess
        });
    });

    $(".deleteTopic").click(function () {
        $.ajax({
            url: "/topic/delete",
            data: {topicId: $(this).attr('topicId')},
            success: ajaxSuccess
        });
        var id = $(this).attr('topicId')
        $("tr[data-topic-id='" + id + "']").remove();
        $("tr[tt-topic-id='" + id + "']").remove();
    });


    //$(".markAsRead").click(function () {
    //    $.ajax({
    //        url: "/readingItem/changeIsRead",
    //        data: {topicId: $(this).attr('topicId')},
    //        success: ajaxSuccess
    //    });
    //    var id = $(this).attr('topicId')
    //    $("tr[data-topic-id='" + id + "']").remove();
    //    $("tr[tt-topic-id='" + id + "']").remove();
    //});



    $(function () {
        $('#registrationForm').validate({
            rules: {
                'firstName': {
                    required: true
                },
                'lastName': {
                    required: true
                },
                'password': {
                    required: true,
                    minlength: 5
                },
                'confirmPassword': {
                    required: true,
                    confirm: true
                },
                'username': {
                    required: true,
                    remote: {
                        url: "/login/validateUserName",
                        type: "post"
                    }
                },
                'email': {
                    required: true,
                    email: true,
                    remote: {
                        url: "/login/validateEmail",
                        type: "post"
                    }
                }
            },
            messages: {
                'firstName': {
                    required: "First name can't be blank"
                },
                'lastName': {
                    required: "Last name can't be blank"
                },
                'password': {
                    required: "Password can't be blank",
                    minlength: "Password should be at least 5 character long"
                },
                'confirmPassword': {
                    required: "Confirm password can't be blank"
                },
                'email': {
                    required: "Email address can't be blank",
                    remote: "Email address entered is already used"
                },
                'username': {
                    required: "User name can't be blank",
                    remote: "User name entered already exist"
                }
            }
        });

        $.validator.addMethod("confirm", function (val, element) {
            var result = false;
            var password = $('form#registrationForm input[id=password]').val();

            if (password === val) {
                result = true;
            }
            return result;
        }, "Confirm password not matched with password");
    });


    $(".sub-edit-topic").on('click', function () {
        var topicId = $(this).attr('data-topic-id');
        $("#editForm" + topicId).css({'display': 'block'});

    });

    $(".tt-edit-topic").on('click', function () {
        var topicId = $(this).attr('data-topic-id');
        $("#editForm1" + topicId).css({'display': 'block'});

    });


    $(".cancelTopicNameButton").on('click', function () {
        var topicId = $(this).attr('topicId');
        $("#editForm" + topicId).css({'display': 'none'});

    });

    $(".cancelTopicNameButton1").on('click', function () {
        var topicId = $(this).attr('topicId');
        $("#editForm1" + topicId).css({'display': 'none'});

    });


    $(".saveTopicNameButton").click(function () {
        var topicId = $(this).attr('topicId')
        $.ajax({
            url: "/topic/titleUpdate",
            data: {topicId: topicId, name: $("#name" + topicId).val()},
            success: ajaxSuccess
        })
    });

    $(".saveTopicNameButton1").click(function () {
        var topicId = $(this).attr('topicId')
        $.ajax({
            url: "/topic/titleUpdate",
            data: {topicId: topicId, name: $("#name1" + topicId).val()},
            success: ajaxSuccess
        })
    });




});