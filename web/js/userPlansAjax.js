

$(document).ready(function () {

    data = {};
    data.tokenID=id_token;
    $.ajax
    ({
        url: '/tokenVerifier',
        data: data,
        type: 'post',
        success: function (result) {

            var email = result.mail;

            $.get("/getUserPlans",{"email":email}, function (data) {
                _.each(data.plans, function (item) {
                    var row = $('<tr>');
                    row.append($('<td>').html("Date ?"));
                    row.append($('<td>').html(item.title));
                    row.append($('<td>').html(item.totalTime));
                    row.append($('<td>'));

                    $('#tablePlans').append(row);
                });

            })
        }
    });
});
