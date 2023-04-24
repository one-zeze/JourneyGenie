$(document).ready(function (){
    //마이페이지, 승차권확인 클릭시 로그인으로 보내주기
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

    //회원가입
    $("#join_button").click(function (){

        let name = $('[name="name"]').val();
        let phoneNum = $('[name="tel"]').val();
        let id = $('[name="id"]').val();
        let pwd = $('[name="password"]').val();
        let preEmail = $('[name="email"]').val();
        let postEmail = "@"+$('[name="auto"]').val();
        let email = preEmail+postEmail;
        console.log(name+","+phoneNum+","+id+","+pwd+","+preEmail+","+postEmail);

        $.get("/user/regist?name="+name+"&phone="+phoneNum+"&id="+id+"&pwd="+pwd+"&email="+email, function (data){
            console.log(data);

            if (data == "이미 등록된 ID입니다."){
                alert(data)
            }else if (data == "이미 등록된 Email입니다."){
                alert(data)
            }else{
                alert(data)
                window.location.href = "/mypage_login";
            }
        });

    })
})
