/**
 * Created by Max TOMPOUCE on 19/01/2016.
 */

/*$(document).ready(function() {
    $.get("/getExs", function (data) {
        $.each(data, function() {
            var row = $('<tr>');
            row.append($('<td>').html(data.title));
            row.append($('<td>').html(data.desc));
            row.append($('<td>').html(data.mn+':'+data.s));
            row.append($('<td>').html(data.row));
            row.append($('<td> <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'));

            $('#row-exercise').append(row);
        })
    })
});*/


//AJOUT UN EXERCICE
$('#btn-exercise').click(function() {
    var data={};
    //data.token=id_token;
    data.title=$('#exTitle').val();
    data.desc=$('#exDesc').val();
    data.mn=$('#ex-time-mn').val();
    data.s=$('#ex-time-s').val();
    data.row=$('#ex-row').val();

    var row = $('<tr class="valueRow">');
    row.append($('<td class="valueTitle">').html(data.title));
    row.append($('<td class="valueDesc">').html(data.desc));
    row.append($('<td class="valueLength">').html(data.mn+':'+data.s));
    row.append($('<td class="valueLoop">').html(data.row));
    row.append($('<td> <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'));

    $('#row-exercise').append(row);

    //TODO
    //change of total time
    var total = $('#totalTimeValue').text();
    var split=total.split(":");
    var hour = split[0];
    var minute = +split[1] + +data.mn;
    var sec = +split[2] + +data.s;
    if (sec>=60) {
        sec-=60;
        minute++;
    }
    if (minute>=60) {
        minute-=60;
        hour++;
    }
    if (minute<10) {
        minute="0"+minute;
    }
    if (sec<10) {
        sec="0"+sec;
    }
    $('#totalTimeValue').text(hour+":"+minute+":"+sec);

});


//AJOUT D'UN PLAN
$('#trainingSubmit').click(function() {
    var data={};
    data.title=$('#planTitle').val();
    data.desc=$('#planDesc').val();
    data.domain=$('#domain').val();
    console.log($('#totalTimeValue').text());
    data.exercises=[];

    $('.valueRow').each(function(i, obj) {
        var exercise = {};
        exercise.title = $(this).find(".valueTitle").html();
        exercise.desc = $(this).find(".valueDesc").html();
        exercise.length = $(this).find(".valueLength").html();
        exercise.row = $(this).find(".valueLoop").html();
        console.log("Ajout ex :" + exercise);
        data.exercises.push(exercise);
    });

    $.ajax
    ({
        url: '/addTraining',
        data: {"PARAM":JSON.stringify(data)},
        type: 'post',
        success: function()
        {
            alert('Training Plan added !');
        }
    });
});