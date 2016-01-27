/**
 * Created by arthurveys on 22/01/16.
 */
$(document).ready(function () {
    var keyID = QueryString.keyid;
    $.get("/plan",{"keyID":keyID}, function (data) {
        _.each(data, function (item) {
            if(item.key.kind=="Exercise"){
                $('#table-exercise').append(generateRowExercice(item.propertyMap.title,item.propertyMap.description,item.propertyMap.length));
            }
        });

    });
});
var QueryString = function () {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();

var generateRowExercice = function (title, desc, time) {
    var text;
    return text = '<tr><td class=" col-md-12 col-sm-12 col-xs-12"><div class="row"><div class=" col-md-3 col-sm-12 col-xs-12 "><h3>' + title + '</h3></div><div class=" col-md-1 col-sm-2 col-xs-2 "><p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span> '+time+'</p> </div> </div> <div class="row"> <div class=" col-md-1 col-sm-0 col-xs-0 " ></div> <div class=" col-md-6 col-sm-12 col-xs-12 "> <p>'+desc+'</p> </div> <div class=" col-md-3 col-sm-12 col-xs-12 "> <div class=" col-md-12 col-sm-12 col-xs-12 "> <div id="flipcountdownbox1"> </div> </div> <div class=" col-md-12 col-sm-12 col-xs-12 centered"> <div class="btn-group"> <button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button> <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button> </div><button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button></div></div></div><div class=" col-md-2 ol-sm-5 col-xs-12 text-center"><button type="submit" class="btn btn-success btn-lg"> <span class="glyphicon glyphicon-ok"></span></button><button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-fast-forward"></span></button></div></td></tr>';
};