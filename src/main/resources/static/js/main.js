$(document).ready(function (){

    let session_val = $("#session_value").text();
    let loginBt = $("#login");
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js: "+session_val);
        loginBt.text("로그아웃");
    } else{
        console.log("session value is null");
        loginBt.text("로그인");
    }

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

    $('#arr').keydown(function () {
        let dep = $('#dep').val();
        let arr = $('#arr').val();
        if(event.keyCode == 13){
            window.location.href="/schedule";

            localStorage.setItem('dep', dep);
            localStorage.setItem('arr', arr);
        }})

    // let depPlace = $("#dep").val();
    // let arrPlace = $("arr").val();
    // console.log("depP: "+depPlace);
    // console.log("arrP: "+arrPlace);

    //마이페이지, 승차권확인 클릭시 로그인으로 보내주기
    $("#mypage").click(function (){
         if (session_val != ""){
            location.href="/mypage_info";
         } else {
            var result = confirm("마이페이지는 로그인 후 이용해 주시기 바랍니다.");
            if (result == true) {
                window.location.href="/mypage_login";
            }else if (result == false){
                window.location.reload();
                return false;
            }
        }
    })

    $("#ticket").click(function (){
        if (session_val != ""){
            location.href="/ticket";
        } else {
            var result = confirm("마이페이지는 로그인 후 이용해 주시기 바랍니다.");
            if (result == true) {
                window.location.href="/mypage_login";
            }else if (result == false){
                window.location.reload();
                return false;
            }
        }
    })
})