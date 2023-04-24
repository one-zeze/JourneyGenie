$(document).ready(function (){
    //session
    let session_val = $("#session_value").text();
    let loginBt = $("#login");

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
    
        //마이페이지, 승차권확인, 예매하기 클릭시 로그인으로 보내주기
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

    $("#pay").click(function (){
        if (session_val != ""){
            location.href="/pay";
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

//main -> schedule localStorage
window.onload = function () {
    const savedDepValue = localStorage.getItem('dep');
    const savedArrValue = localStorage.getItem('arr');

    if (savedDepValue == null) {
        $('#dep1').val("출발지");
    } else {
        $('#dep1').val(savedDepValue);
    }

    if (savedArrValue == null) {
        $('#arr1').val("도착지");
    } else {
        $('#arr1').val(savedArrValue);
    }
    searchInfo("dep1", "arr1", "train_test");

    localStorage.removeItem('dep');
    localStorage.removeItem('arr');
}
//기차 api호출
function searchInfo(tag1, tag2, bodyTag){
        let dep = document.getElementById(tag1).value;
        let arr = document.getElementById(tag2).value;

    if (dep != "출발지" || arr != "도착지") {
        $.get("/api/train/train_info?depNode=" + dep + "&arrNode=" + arr, function (data) {
            if (data && data.response && data.response.body && data.response.body.items) { // 응답 데이터 확인
                let items = data.response.body.items.item;
                let tag = document.getElementById(bodyTag);
                let innerHtml = "";

                items.forEach(item => {
                    innerHtml += "<tr tabindex=\"-1\" name=\"info\">";
                    innerHtml += "<td>" + item.traingradename + "</td>";
                    innerHtml += "<td>" + item.depplandtime.substr(8, 2) + ":" + item.depplandtime.substr(10, 2) + "</td>";
                    innerHtml += "<td>" + item.arrplandtime.substr(8, 2) + ":" + item.arrplandtime.substr(10, 2) + "</td>";
                    innerHtml += "<td>" + item.adultcharge + "원</td>";
                    innerHtml += "<td>" + item.trainno + "</td>";
                    innerHtml += "</tr>";
                });

                tag.innerHTML = innerHtml;
            } else {
                let tag = document.getElementById(bodyTag);
                tag.innerHTML = "<td colspan=\"5\">기차 조회 정보가 없습니다.</td>";
            }
        });
    }
}
//고속버스 api호출
function searchInfo2(tag1, tag2, bodyTag) {
    let dep = document.getElementById(tag1).value;
    let arr = document.getElementById(tag2).value;

    if (dep != "출발지" || arr != "도착지") {
        $.get("/api/EB/Expbus_Info?depT=" + dep + "&arrT=" + arr, function (data) {
            if (data && data.response && data.response.body && data.response.body.items) {
                let items = data.response.body.items.item;
                let tag = document.getElementById(bodyTag);
                let innerHtml = "";

                items.forEach(item => {
                    innerHtml += "<tr tabindex=\"-1\">"
                    innerHtml += "<td>" + item.gradeNm + "</td>"
                    innerHtml += "<td>" + item.depPlandTime.substr(8, 2) + ":" + item.depPlandTime.substr(10, 2) + "</td>"
                    innerHtml += "<td>" + item.arrPlandTime.substr(8, 2) + ":" + item.arrPlandTime.substr(10, 2) + "</td>"
                    innerHtml += "<td>" + item.charge + "원</td>"
                    innerHtml += "</tr>"
                });
                tag.innerHTML = innerHtml;
            } else {
                let tag = document.getElementById(bodyTag);
                tag.innerHTML = "<td colspan=\"5\">고속버스 조회 정보가 없습니다.</td>";
            }
        });
    }
}
//시외버스 api호출
function searchInfo3(tag1, tag2, bodyTag) {
    let dep = document.getElementById(tag1).value;
    let arr = document.getElementById(tag2).value;

    if (dep != "출발지" || arr != "도착지") {
        $.get("/api/bus_info/accdep?depT=" + dep + "&arrT=" + arr, function (data) {
            if (data && data.response && data.response.body && data.response.body.items) {
                let items = data.response.body.items.item;
                let tag = document.getElementById(bodyTag);
                let innerHtml = "";

                items.forEach(item => {
                    innerHtml += "<tr tabindex=\"-1\">"
                    innerHtml += "<td>" + item.gradeNm + "</td>"
                    innerHtml += "<td>" + item.depPlandTime.substr(8, 2) + ":" + item.depPlandTime.substr(10, 2) + "</td>"
                    innerHtml += "<td>" + item.arrPlandTime.substr(8, 2) + ":" + item.arrPlandTime.substr(10, 2) + "</td>"
                    innerHtml += "<td>" + item.charge + "원</td>"
                    innerHtml += "</tr>"
                });
                tag.innerHTML = innerHtml;
            } else {
                let tag = document.getElementById(bodyTag);
                tag.innerHTML = "<td colspan=\"5\">고속버스 조회 정보가 없습니다.</td>";
            }
        });
    }
}

//왼쪽 시간표
function search() {
    var url = "/search_popup";
    var name = "search station or terminal";
    var option = "width = 508, height = 505, top = 50, left = 50, location = no"

    const select1 = document.querySelector(".choice1");
    const currentValue1 = select1.options[select1.selectedIndex].value;//기차, 시외버스, 고속버스
    localStorage.setItem('selectType1', currentValue1);

    var popup = window.open(url, name, option);
    popup.onbeforeunload = function (){
        let arr = document.getElementById("arr1").value;
        if (arr!="도착지"){
            if (currentValue1=="기차"){
                searchInfo("dep1", "arr1","train_test");
            }else if (currentValue1=="고속버스"){
                searchInfo2("dep1", "arr1","bus_test");
            }else if (currentValue1=="시외버스"){
                searchInfo3("dep1", "arr1","bus_test");
            }
        }
    }
}
//오른쪽 시간표
function search2() {
    var url = "/search_popup2";
    var name = "search station or terminal";
    var option = "width = 508, height = 505, top = 50, left = 50, location = no"

    const select2 = document.querySelector(".choice2");
    const currentValue2 = select2.options[select2.selectedIndex].value;
    localStorage.setItem('selectType2', currentValue2);

    var popup = window.open(url, name, option);
    popup.onbeforeunload = function (){
        let arr = document.getElementById("arr2").value;
        if (arr!="도착지"){
            if (currentValue2=="기차"){
                searchInfo("dep2", "arr2","train_test2");
            }else if (currentValue2=="고속버스"){
                searchInfo2("dep2", "arr2","bus_test2");
            }else if (currentValue2=="시외버스"){
                searchInfo3("dep2", "arr2","bus_test2");
            }

        }
    }
}

//선택한 기차정보 출력
$(document).on("click", ".train_table1 tr", function (){
    var str = ""
    // 현재 클릭된 Row(<tr>)
    var tr = $(this);
    var td = tr.children();

    // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
    // td.each(function(i){
    //     tdArr.push(td.eq(i).text());
    // });
    // console.log("배열에 담긴 값 : "+tdArr);

    // td.eq(index)를 통해 값을 가져올 수도 있다.
    var train = td.eq(0).text();
    var dep = td.eq(1).text();
    var arr = td.eq(2).text();
    var charge = td.eq(3).text();
    var num = td.eq(4).text();

    str +=	" * 클릭된 Row의 td값 : " +
        ", 열차 : " + train +
        ", 출발 : " + dep +
        ", 도착 : " + arr +
        ", 운임료 : " + charge +
        ", 번호 : " + num;
    console.log(str)

    let info = train+"-"+num;
    let depP = document.getElementById("dep1").value;
    let arrP = document.getElementById("arr1").value;

    window.localStorage.setItem('info', info);
    window.localStorage.setItem('depT', dep);
    window.localStorage.setItem('arrT', arr);
    window.localStorage.setItem('depP', depP);
    window.localStorage.setItem('arrP', arrP);
    window.localStorage.setItem('charge', charge);

})
$(document).on("click", ".train_table2 tr", function (){
    var str = ""
    var tr = $(this);
    var td = tr.children();

    var train = td.eq(0).text();
    var dep = td.eq(1).text();
    var arr = td.eq(2).text();
    var charge = td.eq(3).text();
    var num = td.eq(4).text();

    str +=	" * 클릭된 Row의 td값 : " +
        ", 열차 : " + train +
        ", 출발 : " + dep +
        ", 도착 : " + arr +
        ", 운임료 : " + charge +
        ", 번호 : " + num;
    console.log(str)
})

//선택한 버스정보 출력(시외/고속)
$(document).on("click", ".bus_table1 tr", function (){
    var str = ""
    var tr = $(this);
    var td = tr.children();

    // td.eq(index)를 통해 값을 가져올 수도 있다.
    var grade = td.eq(0).text();
    var dep = td.eq(1).text();
    var arr = td.eq(2).text();
    var charge = td.eq(3).text();

    str +=	" * 클릭된 Row의 td값 : " +
        ", 등급 : " + grade +
        ", 출발 : " + dep +
        ", 도착 : " + arr +
        ", 요금 : " + charge;
    console.log(str)

    let info = grade+"버스";
    let depP = document.getElementById("dep1").value;
    let arrP = document.getElementById("arr1").value;

    window.localStorage.setItem('info', info);
    window.localStorage.setItem('depT', dep);
    window.localStorage.setItem('arrT', arr);
    window.localStorage.setItem('depP', depP);
    window.localStorage.setItem('arrP', arrP);
    window.localStorage.setItem('charge', charge);
})
$(document).on("click", ".bus_table2 tr", function (){
    var str = ""
    var tr = $(this);
    var td = tr.children();

    // td.eq(index)를 통해 값을 가져올 수도 있다.
    var grade = td.eq(0).text();
    var dep = td.eq(1).text();
    var arr = td.eq(2).text();
    var charge = td.eq(3).text();

    str +=	" * 클릭된 Row의 td값 : " +
        ", 등급 : " + grade +
        ", 출발 : " + dep +
        ", 도착 : " + arr +
        ", 요금 : " + charge;
    console.log(str)
})
