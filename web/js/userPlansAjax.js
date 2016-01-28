

$(document).ready(function () {
    setTimeout(function () {
        data = {};
        data.tokenID=id_token;
        $.ajax
        ({
            url: '/tokenVerifier',
            data: data,
            type: 'post',
            success: function (result) {

                var email = result.mail;

                $.get("/userPlans",{"email":email}, function (data) {
                    console.log(data);
                    _.each(data.plans, function (item) {
                        var row = $('<tr>');
                        row.append($('<td>').html(item.propertyMap.date));
                        row.append($('<td>').html(item.propertyMap.titlePlan));
                        row.append($('<td>').html(item.propertyMap.titleExo));
                        row.append($('<td>').html(item.propertyMap.status));
                        row.append($('<td>'));

                        $('#tablePlans > tbody').append(row);
                    });

                })
            }
        });
    },3000);

});
