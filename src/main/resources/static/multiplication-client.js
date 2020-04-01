function updateMultiplication() {
    $.ajax({
        url:"http://localhost:8080/multiplications/random"
    }).then(function (data) {
        $("#attempt-form").find("input[name='result-attempt']").val("");
        $("#attempt-form").find("input[name='user-alias']").val("");
        // 무작위 문제를 API로 가져와서 추가하기
        $('.multiplication-a').empty().append(data.factorA);
        $('.multiplication-b').empty().append(data.factorB);
    });
}

function updateStats(alias) {
    $.ajax({
        url: "http://localhost:8080/results?alias=" + alias
    }).then(function (data) {
        $('#stats-body').empty();
        data.forEach(function(row){
            $('#stats-body').append('<tr><td>' +
                '<td>' + row.multiplication.factorA + ' x ' + row.multiplication.factorB + '</td>' +
                '<td>' + row.resultAttempt + '</td>' +
                '<td>' + (row.correct == true ? 'YES' : 'NO' ) + '</td></tr>');
        });
    });
}

$(document).ready(function () {
    updateMultiplication();

    $("#attempt-form").submit(function(event){
        // 폼 기본 제출 막기
        event.preventDefault();

        // 페이지에서 값 가져오기
        var a = $('.multiplication-a').text();
        var b = $('.multiplication-b').text();
        var $form = $(this),
            attempt   = $form.find("input[name='result-attempt']").val(),
            userAlias = $form.find("input[name='user-alias']").val();

        var data = {user: {alias: userAlias}, multiplication: {factorA: a, factorB: b}, resultAttempt: attempt};

        // POST를 이용해 데이터 보내기
        $.ajax({
            url: '/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                alert(result.correct);
                if(result.correct) {
                    $('.result-message').empty().append("정답");
                } else {
                    $('.result-message').empty().append("오답");
                }
            }
        });
        updateStats(userAlias);
        updateMultiplication();
    })
})