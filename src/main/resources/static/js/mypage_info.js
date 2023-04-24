$(document).ready(function (){
    let session_val = $("#session_value").text();
    let loginBt = $("#login");
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js: "+session_val);
        loginBt.text("로그아웃");

        $.get("/user/getUser?id="+session_val, function (data){

            $("#user_name").text(data.name)
            $("#user_id").text(data.id)
            $("#user_pwd").text(data.pwd)
            $("#user_email").text(data.email)
            $("#user_phone").text(data.phoneNum)
        })

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

    $("#tab2").click(function (){
        $.get("/user/delUser?user_id="+session_val);
        $.get("/test");
        window.location.href = "/main";
    })
})