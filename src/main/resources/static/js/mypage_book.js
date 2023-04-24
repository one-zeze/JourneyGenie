$(document).ready(function (){
    let session_val = $("#session_value").text();
    let loginBt = $("#login");
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js: "+session_val);
        loginBt.text("로그아웃");

        $.get("/user/getReservationList?user_id="+session_val, function (data){
              let tableHistory = document.getElementById('history_body');
              data.forEach(item=>{
                  console.log(item);
                  let innerHtml = "";

                  innerHtml += "<tr>";
                  innerHtml += "<td><div class='selectall'><input type='checkbox' id='selectall' name='selectall'></div></td>";
                  innerHtml += "<td>" + item.info + "</td>";
                  innerHtml += "<td>" + item.depCity + " - " + item.arrCity + "</td>";
                  innerHtml += "<td>" + item.depTime + "</td>";
                  innerHtml += "<td>" + item.arrTime + "</td>";
                  innerHtml += "<td>" + item.charge + '원'+ "</td>";
                  innerHtml += "</tr>";

                  tableHistory.innerHTML += innerHtml;
              })
        });

    } else{
        console.log("session value is null");
        loginBt.text("로그인")
    }

    $("#login").click(function (){
        if (loginBt.text() == "로그아웃"){
            $.get("/test");
            window.location.href = "/main"
        }else{
            window.location.href = "/mypage_login"
        }
    })

    //취소버튼
    $(".cancel").click(function () {

            var checkRows = $("[name=selectall]:checked");
            console.log(checkRows);
            console.log('삭제');

            if ($('input:checkbox[name="selectall"]').is(':checked')) {
                console.log("체크");
                $("#cancel").prop("disabled", false); //활성화
                for (var i = checkRows.length - 1; i > -1; i--) {
                    checkRows.eq(i).closest('tr').remove();
                }
            } else {
                console.log("언 체크");
                alert("취소할 항목을 선택해주세요.")
                $("#cancel").prop("disabled", false); //비활성화
            }
        })
})

    // //테이블 행 개수 세기
    // const table = document.getElementById('history');
    // //tbody
    // const tbody = table.tBodies[0].rows.length;
    // result.innerText += '총 ' + tbody + '건의 이용내역이 있습니다.';
    //
    //
    // //페이지 로드할 때 체크박스 상태 확인, 결제버튼
    // $(".pay").click(function () {
    //
    //     if ($('input:checkbox[name="selectall"]').is(':checked')) {
    //         console.log("체크");
    //         $("#pay").prop("disabled", false); //활성화
    //         $("#pay").css("background-image", "linear-gradient(to right, #6a11cb 0%, #2575fc 100%)");
    //     } else {
    //         console.log("언 체크");
    //         $("#pay").prop("disabled", false); //비활성화
    //         $("#pay").css("background", "gray");
    //     }
    //
    // })
