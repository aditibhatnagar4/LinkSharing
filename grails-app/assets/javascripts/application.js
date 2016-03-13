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

        if (!result.success) {
            jsonResponseDiv.text(result.message);
            jsonResponseDiv.addClass("alert alert-success");
        }
        else {
            jsonResponseDiv.text(result.error);
            jsonResponseDiv.addClass("alert alert-danger");
        }
        //document.location.reload();
        jsonResponseDiv.css({'display': 'block'})

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
            url: "/topic/saveTopic",
            data: {topicName: $(this).attr('topicName'), visibility: $(this).val()},
            success: ajaxSuccess
        });
    });

    $(".markReadStatus").click(function () {
        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: $(this).attr('resourceId'), isRead: $(this).attr('isRead')},
            success: location.reload()
        });
    });

    $("#saveTopicButton").click(function () {
        $.ajax({
            url: "/topic/saveTopic",
            data: {topicName: $('#topicName').val(), visibility: $('#visibility').val()},
            success: location.reload()
        });
    });


    $("#clearSearchPostBox").click(function () {
        $("#searchPostBox").val("")
    })

    $("#findSearchPostBox").click(function () {
        topicId = $(this).attr('topicId')

        $.ajax({
            url: "/resource/searchResource",
            data: {q: $('#searchPostBox').val(), topicId: topicId},
            success: function (result) {
                alert(result)
                $("#topicPosts").html(result)
            }
        });

    })

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
                'userName': {
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
                'emailID': {
                    required: "Email address can't be blank",
                    remote: "Email address entered is already used"
                },
                'userName': {
                    required: "User name can't be blank",
                    remote: "User name entered already exist"
                }
            }
        });

        jQuery.validator.addMethod("confirm", function (value, element) {
            var result = false;
            var password = $('form#registrationForm input[id=password]').val();

            if (password === value) {
                result = true;
            }
            return result;
        }, "Confirm password not matched with password");
    });


});
