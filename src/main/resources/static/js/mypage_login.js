$(document).ready(function (){
    //마이페이지, 승차권 확인 클릭시 로그인으로 보내주기
    $("#mypage").click(function (){
        var result = confirm("마이페이지는 로그인 후 이용해 주시기 바랍니다.");
        if (result == true) {
            location.href="/mypage_login";
        }else if (result == false){
            return false;
        }
    })

    $("#ticket").click(function (){
        var result = confirm("승차권 확인은 로그인 후 이용해 주시기 바랍니다.");
        if (result == true) {
            location.href="/mypage_login";
        }else if (result == false){
            return false;
        }
    })

    // 로그인
    $("#log_button").click(function (){

        let id_text = $('[name="id"]').val();
        let pwd_text = $('[name="password"]').val();
        
        $.get("/user/login"+"?id="+id_text+"&pwd="+pwd_text, function (data){
            if (data == "등록되지 않은 ID입니다."){
                alert("등록되지 않은 ID입니다.")
            }else if (data == "비밀번호가 틀렸습니다."){
                alert("비밀번호가 틀렸습니다.")
            }else{
                window.location.href = "/main";
            }
        })
        // $.get(url, function (data){
        //     console.log(data);
        // })
        // window.location.href = url;
        // var result = $.get("/login?id="+id_text+"&pwd="+pwd_text);
        // console.log(result);
        // $.get("/login?id="+id_text+"&pwd="+pwd_text);

    })

})