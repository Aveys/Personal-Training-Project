/**
 * Created by arthurveys on 21/01/16.
 */
$(document).ready(function () {
    var searchTerms = QueryString.q;
    $.get("/search",{"search":searchTerms}, function (data) {
        console.log("Searchresult :"+JSON.stringify(data));
        _.each(data.trainings, function (item) {
            console.log('item training'+JSON.stringify(item));
            content = JSON.parse(item.content);
            $('.training-items').append(generatePEHTML("training",item.key,content.propertyMap.title,content.propertyMap.totalLength));
        });
        _.each(data.exercises, function (item) {
            console.log('item exercises'+JSON.stringify(item));
            content = JSON.parse(item.content);
            $('.exercise-items').append(generatePEHTML("exercise",item.key,content.propertyMap.title,content.propertyMap.length));
        });
    });
    $.get("/rss", function (data) {
        _.each(data.rss, function (item) {
            $('.rss-items').append(generateRSSHTML(item.title,item.description,item.link));
        });

    })
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

function generatePEHTML(type,id,title,length){
    return '<div class="'+type+'-item"><div class=" col-md-6 col-sm-6 col-xs-6"><a href="ha-result-detail-screen.html?keyid='+id+'" class="btn btn-link">'+title+'</a></div><div class=" col-md-6 col-sm-6 col-xs-6"> <label class="btn"> <span class="glyphicon glyphicon-time"></span> '+length+' min. </label></div></div>';
}
function generateRSSHTML(title,content,url){
    return '<div class="rss-item"><h4>'+title+'</h4><p>'+content+'</p><p><a href="'+url+'">'+url+'</a></p></div>';
}