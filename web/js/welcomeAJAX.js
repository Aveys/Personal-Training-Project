/**
 * Created by arthurveys on 19/01/16.
 */

$(document).ready(function () {
    $.get("/welcomeText", function (data) {
        $("#welcomeContent").text(data.message);
    });


});