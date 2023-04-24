$(document).ready(function () {
    let session_val = $("#session_value").text();
    let loginBt = $("#login");
    const info = window.localStorage.getItem('info');
    const depT = window.localStorage.getItem('depT');
    const arrT = window.localStorage.getItem('arrT');
    const depP = window.localStorage.getItem('depP');
    const arrP = window.localStorage.getItem('arrP');
    const charge = window.localStorage.getItem('charge');
    console.log(info+","+depT+","+arrT+","+depP+","+arrP+","+charge);
    let total = 0;
    $("#money").text(charge); //요금
    $("#person").change(function (){
        // console.log(this.value);
        // console.log(charge);
        total = parseInt(charge) * parseInt(this.value);
        $("#money").text(total);
    })
    
    // console.log(session_val)
    if (session_val != ""){
        console.log("session check from js: "+session_val);
        loginBt.text("로그아웃");

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

    $('#menu_btn').mouseenter(function () {
        $('.menu_li').css('display', 'block');
    })

    $('.top_menu').mouseleave(function () {
        $('.menu_li').css('display', 'none');
    })

    //결제버튼
    $("#payment-button").click(function (){
        $.get("/user/reservation?user_id="+session_val+"&info="+info+"&depPlace="+depP
        +"&arrPlace="+arrP+"&depTime="+depT+"&arrTime="+arrT+"&charge="+total);

        window.localStorage.clear();
        window.location.href = "/ticket"
    })
})

window.onload = function () {
    let frm = document.querySelector('form');

    for (let i = 2; i <= 10; i++) {
        frm.adult.add(new Option(i, i));
    }

    for (let i = 1; i <= 10; i++) {
        frm.chiled.add(new Option(i, i));
        frm.infant.add(new Option(i, i));
        frm.elder.add(new Option(i, i));
    }

};

function setDisplay() {
    if ($('input:radio[id=chk_card]').is(':checked')) {
        $('#mycard').show();
    } else {
        $('#mycard').hide();
    }

    if ($('input:radio[id=chk_kakao]').is(':checked')) {
        $('#kakao_btn').show();
    } else {
        $('#kakao_btn').hide();
    }

    if ($('input:radio[id=chk_naver]').is(':checked')) {
        $('#naver_btn').show();
    } else {
        $('#naver_btn').hide();
    }
}