$(document).ready(function (){

    //지역검색
    $("#search_btn1").click(function (){

        //새로운 지역 검색시 기존값 비우기
        for (let i=0; i<10; i++){
            $(".station"+i).text("")
        }

        //input param값 확인
        let query = $(".search_word1").val();
        console.log("query : "+query);

        //h2내용 변경
        $(".stationH2").text(query);

        //기차역조회 service 호출
        $.get("/api/search_ts?cityName="+query, function (data){
            // console.log("data:"+data);

            for(let i in data){
                let str = data[i];
                $(".station"+i).text(str)
            }
        });
    });

    //기차역 선택시
    $(".search_body1 button").click(function (){

        //선택한 기차역 이름 log확인
        let station_name = $(this).text();

        let dep = window.opener.document.getElementById("dep1").value;
        let arr = window.opener.document.getElementById("arr1").value;

        if (dep == "출발지") {
            window.opener.document.getElementById("dep1").value = station_name;
            window.close();
        }else if (arr == "도착지") {
            window.opener.document.getElementById("arr1").value = station_name;
            // console.log("dep:"+dep+", arr:"+arr);
            window.close();
        }
    });

    //지역검색(ex터미널)
    $("#search_btn2").click(function (){

        //새로운 지역 검색시 기존값 비우기
        for (let i=0; i<10; i++){
            $(".terminal_btn"+i).text("")
        };

        //input param값 확인
        let queryT = $(".search_word2").val();

        //h2내용 변경
        $(".terminalH2").text(queryT);

        //ex터미널역 조회 service 호출
        $.get("/api/search_exb?cityNameT="+queryT, function(body){

            for(let i = 0; i < body.length; i++){
                    let str = body[i];
                    $(".terminal_btn"+i).text(str);
            };
        });
    });
    $(".search_body2 button").click(function (){

         //선택한 ex터미널역 이름 log확인
         let exB_Terminal_name = $(this).text();

         let dep = window.opener.document.getElementById("dep1").value;
         let arr = window.opener.document.getElementById("arr1").value;

         if (dep == "출발지") {
             window.opener.document.getElementById("dep1").value = exB_Terminal_name;
             window.close();
         } else if (arr == "도착지") {
             window.opener.document.getElementById("arr1").value = exB_Terminal_name;
            window.close();
         }
     })

     //지역검색(ic터미널)
     $("#search_btn3").click(function (){

         //새로운 지역 검색시 기존값 비우기
         for (let i=0; i<20; i++){
             $(".icTerminal_btn"+i).text("")
         };

         //input param값 확인
         let queryIcT = $(".search_word3").val();

         //h2내용 변경
         $(".icTerminalH2").text(queryIcT);

         //ic터미널역 조회 service 호출
         $.get("/api/search_icb?cityNameIc="+queryIcT, function(data){

             for(let i = 0; i < 30; i++){
                 let str = data[i];
                 $(".icTerminal_btn"+i).text(str);
             };
         });
     });
     $(".search_body3 button").click(function (){

          //선택한 ic터미널역 이름 log확인
          let icB_Terminal_name = $(this).text();

          let dep = window.opener.document.getElementById("dep1").value;
          let arr = window.opener.document.getElementById("arr1").value;

          if (dep == "출발지") {
              window.opener.document.getElementById("dep1").value = icB_Terminal_name;
              window.close();
          } else if (arr == "도착지") {
              window.opener.document.getElementById("arr1").value = icB_Terminal_name;
              window.close();
          }
     })


})

window.onload = function(){
    const isValue1 = localStorage.getItem('selectType1');

    if(isValue1 == "기차"){
        $(".station").show();
        $(".terminal").hide();
        $(".icTerminal").hide();
    }else if(isValue1 == "시외버스"){
        $(".station").hide();
        $(".terminal").hide();
        $(".icTerminal").show();
    }else {
        $(".station").hide();
        $(".terminal").show();
        $(".icTerminal").hide();
    }
}