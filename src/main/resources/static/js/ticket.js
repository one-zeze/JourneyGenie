$(document).ready(function (){
    let session_val = $("#session_value").text();
    let loginBt = $("#login");
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js: "+session_val);
        loginBt.text("로그아웃");

        $.get("/user/myReservate?user_id="+session_val, function (data){
            //상단
            $("#dep_title").text(data.depCity);
            $("#dep_time").text(data.depTime);
            $("#arr_title").text(data.arrCity);
            $("#arr_time").text(data.arrTime);
            
            //승차권
            $("#ticket1_infonum").text(data.info);
            $("#ticket1_deptime").text(data.depTime);
            $("#ticket1_arrtime").text(data.arrTime);
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
})