/**
 * Created by arthurveys on 22/01/16.
 */
$(document).ready(function () {
    var keyID = QueryString.keyid;
    var i = 0;
    $.get("/plan", {"keyID": keyID}, function (data) {
        _.each(data, function (item) {
            console.log("item : " + JSON.stringify(item));
            if (item.key.kind == "Exercise") {
                $('#table-exercise').append(generateRowExercice(item.propertyMap.title, item.propertyMap.description, item.propertyMap.length, i));
                i++;
            }
            else
                $('#plan-title').text(item.propertyMap.title);
        });
        $(".validate-exercise-success").click(function (e) {
            e.preventDefault();
            var button = e.target;
            var s =$(button).attr('class');
            _.each(s.split(' '), function (item) {
                if(stringStartsWith(item,"count")){
                    var data = {};
                    var count = parseInt(item.split('-')[1]);
                    count = count+1;
                    $(".count-"+count).removeClass('disabled');
                    data.date = moment().format("DD/MM/YYYY");
                    data.titlePlan = $("#plan-title").text();
                    data.mail = $("#user-mail").text();
                    data.titleExo = $('h3[class="'+item+'"]').text();
                    data.status="Success";
                    $.ajax
                    ({
                        url: '/userPlans',
                        data: data,
                        type: 'post',
                        success: function(result)
                        {
                            $("."+item).prop('disabled', true);
                        },
                        error: function () {
                            alert("Something went wrong");
                        }
                    });
                }
            });
        });
        $(".validate-exercise-failure").click(function (e) {
            e.preventDefault();
            var button = e.target;
            var s =$(button).attr('class');
            _.each(s.split(' '), function (item) {
                if(stringStartsWith(item,"count")){
                    var data = {};
                    var count = parseInt(item.split('-')[1]);
                    count = count+1;
                    $(".count-"+count).removeClass('disabled');
                    data.date = moment().format("DD/MM/YYYY");
                    data.titlePlan = $("#plan-title").text();
                    data.mail = $("#user-mail").text();
                    data.titleExo = $('h3[class="'+item+'"]').text();
                    data.status="Failure";
                    $.ajax
                    ({
                        url: '/userPlans',
                        data: data,
                        type: 'post',
                        success: function(result)
                        {
                            $("."+item).prop('disabled', true);
                        },
                        error: function () {
                            alert("Something went wrong");
                        }
                    });
                }
            });
        });

    });
});
var QueryString = function () {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();

var generateRowExercice = function (title, desc, time, count) {
    var text;
    if (count == 0)
        return text = '<tr><td class=" col-md-12 col-sm-12 col-xs-12"><div class="row"><div class=" col-md-3 col-sm-12 col-xs-12 "><h3 class="count-' + count + '">' + title + '</h3></div><div class=" col-md-1 col-sm-2 col-xs-2 "><p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span> ' + time + '</p> </div> </div> <div class="row"> <div class=" col-md-1 col-sm-0 col-xs-0 " ></div> <div class=" col-md-6 col-sm-12 col-xs-12 "> <p>' + desc + '</p> </div> <div class=" col-md-3 col-sm-12 col-xs-12 "> <div class=" col-md-12 col-sm-12 col-xs-12 "> <div id="flipcountdownbox1"> </div> </div> <div class=" col-md-12 col-sm-12 col-xs-12 centered"> <div class="btn-group"> <button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button> <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button> </div><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button></div></div></div><div class=" col-md-2 ol-sm-5 col-xs-12 text-center"><button  class="count-' + count + ' btn btn-success btn-lg validate-exercise-success"> <span class="glyphicon glyphicon-ok"></span></button><button type="submit" class="count-' + count + ' btn btn-danger btn-sm validate-exercise-failure"> <span class="glyphicon glyphicon-fast-forward"></span></button></div></td></tr>';
    else
        return text = '<tr><td class=" col-md-12 col-sm-12 col-xs-12"><div class="row"><div class=" col-md-3 col-sm-12 col-xs-12 "><h3 class="count-' + count + '">' + title + '</h3></div><div class=" col-md-1 col-sm-2 col-xs-2 "><p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span> ' + time + '</p> </div> </div> <div class="row"> <div class=" col-md-1 col-sm-0 col-xs-0 " ></div> <div class=" col-md-6 col-sm-12 col-xs-12 "> <p>' + desc + '</p> </div> <div class=" col-md-3 col-sm-12 col-xs-12 "> <div class=" col-md-12 col-sm-12 col-xs-12 "> <div id="flipcountdownbox1"> </div> </div> <div class=" col-md-12 col-sm-12 col-xs-12 centered"> <div class="btn-group"> <button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button> <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button> </div><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button></div></div></div><div class=" col-md-2 ol-sm-5 col-xs-12 text-center"><button  class="count-' + count + ' btn btn-success btn-lg validate-exercise-success disabled"> <span class="glyphicon glyphicon-ok"></span></button><button type="submit" class="count-' + count + ' btn btn-danger btn-sm validate-exercise-failure disabled"> <span class="glyphicon glyphicon-fast-forward"></span></button></div></td></tr>';
};

var stringStartsWith = function (string, prefix) {
    return string.slice(0, prefix.length) == prefix;
}