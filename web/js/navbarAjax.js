/**
 * Created by arthurveys on 20/01/16.
 */

var id_token;
function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
    id_token = googleUser.getAuthResponse().id_token;
    /*var profile = googleUser.getBasicProfile();
     console.log("ID: " + profile.getId()); // Don't send this directly to your server!
     console.log("Name: " + profile.getName());
     console.log("Image URL: " + profile.getImageUrl());
     console.log("Email: " + profile.getEmail());*/
    data = {};
    data.tokenID=id_token;
    $.ajax
    ({
        url: '/tokenVerifier',
        data: data,
        type: 'post',
        success: function(result)
        {
            $('#google-button').hide();
            $('#navbar-list').append("<li><a href=\"ha-training-plans.html\">Welcome "+result.name+"<br/><div id='user-mail'>"+result.mail+"</div></a></li>");
        },
        error: function () {
            alert("Token Invalid");
        }
    });

}