/**
 * Created by Max TOMPOUCE on 19/01/2016.
 */



$('#btn-exercise').click(function() {
    var data={};
    data.token=id_token;
    data.type="exercise";
    data.title=$('#titleDescription').val();
    data.desc=$('#exerciseDescription').val();
    data.mn=$('#ex-time-mn').val();
    data.s=$('#ex-time-s').val();
    data.row=$('#ex-row').val();
    $.ajax
    ({
        url: '/addTraining',
        data: data,
        type: 'post',
        success: function(result)
        {
            var row = $('<tr>');
            row.append($('<td>').html(data.title));
            row.append($('<td>').html(data.desc));
            row.append($('<td>').html(data.mn+':'+data.s));
            row.append($('<td>').html(data.row));
            row.append($('<td> <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'));

            $('#row-exercise').append(row);

            //TODO
            //change of total time
            //$('#totalTimeValue').text()
        }
    });
});



$('#trainingSubmit').click(function() {
    var data={};
    data.type="plan";
    $.ajax()
    ({
        url: '/addTraining',
        data: data,
        type: 'post',
        success: function(result)
        {

        }
    });
});