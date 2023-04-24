$(document).ready(function (){
    let session_val = $("#session_value").text();
    let loginBt = $("#login");

    let new_name;
    let new_id;
    let new_pwd;
    let new_email;
    let new_phoneNum;
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js modify: "+session_val);
        loginBt.text("로그아웃");

        $.get("/user/getUser?id="+session_val, function (data){

            $('[name="name"]').attr("placeholder", data.name);
            $('[name="id"]').attr("placeholder", data.id);
            $('[name="pwd"]').attr("placeholder", data.pwd);
            $('[name="email"]').attr("placeholder", data.email);
            $('[name="phoneNum"]').attr("placeholder", data.phoneNum);

            new_name=data.name;
            new_id=data.id;
            new_pwd=data.pwd;
            new_email=data.email;
            new_phoneNum=data.phoneNum
        })

    } else{
        console.log("session value is null");
        loginBt.text("로그인")
    }

    $('[name="name"]').change(function (){
        new_name = $('[name="name"]').val();
        console.log("이름 변경:"+new_name);
    });
    $('[name="id"]').change(function (){
        new_id = $('[name="id"]').val();
        console.log("ID 변경:"+new_id);
    });
    $('[name="pwd"]').change(function (){
        new_pwd = $('[name="pwd"]').val();
        console.log("pwd 변경:"+new_pwd);
    });
    $('[name="email"]').change(function (){
        new_email = $('[name="email"]').val();
        console.log("이메일 변경:"+new_email);
    });
    $('[name="phoneNum"]').change(function (){
        new_phoneNum = $('[name="phoneNum"]').val();
        console.log("연락처 변경:"+new_phoneNum);
    });

    $("#login").click(function (){
        if (loginBt.text() == "로그아웃"){
            $.get("/test");
            window.location.href = "/main"
        }else{
            window.location.href = "/mypage_login"
        }
    })

    $('#menu_btn').mouseenter(function () {
        $('.menu_li').css('display', 'block');
    })
    $('.top_menu').mouseleave(function () {
        $('.menu_li').css('display', 'none');
    })

    //수정버튼
    $("#modify_btn").click(function (){
        console.log(new_id+","+new_pwd+","+new_name+","+new_email+","+new_phoneNum);

        $.get("/user/updateInfo?user_id="+session_val+"&new_id="+new_id
            +"&name="+new_name+"&pwd="+new_pwd+"&email="+new_email+"&phonenNum="+new_phoneNum);

        window.location.href = "/test";
    })
})