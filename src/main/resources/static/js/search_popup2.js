$(document).ready(function (){

    //지역검색
    $("#popup2_search_btn1").click(function (){

        //새로운 지역 검색시 기존값 비우기
        for (let i=0; i<10; i++){
            $(".popup2_station_btn"+i).text("")
        }

        //input param값 확인
        let query = $(".popup2_search_word1").val();
        console.log("query : "+query)

        //h2내용 변경
        $(".popup2_stationH2").text(query);

        //기차역조회 service 호출
        $.get("/api/search_ts?cityName="+query, function (data){
            console.log("data:"+data);

            for(let i in data){
                let str = data[i];
                $(".popup2_station_btn"+i).text(str)
            }
        });
    })
    //기차역 선택시
    $(".popup2_search_body1 button").click(function (){
        console.log("clicked")
        //선택한 기차역 이름 log확인
        let station_name = $(this).text();

        let dep = window.opener.document.getElementById("dep2").value;
        let arr = window.opener.document.getElementById("arr2").value;

        if (dep == "") {
            window.opener.document.getElementById("dep2").value = station_name;
            window.close();
        }else if (arr == "") {
            window.opener.document.getElementById("arr2").value = station_name;
            window.close();
        }
    })

    //지역검색(ex터미널)
    $("#popup2_search_btn2").click(function (){

        //새로운 지역 검색시 기존값 비우기
        for (let i=0; i<10; i++){
            $(".popup2_terminal_btn"+i).text("")
        };

        //input param값 확인
        let queryT = $(".popup2_search_word2").val();

        //h2내용 변경
        $(".popup2_terminalH2").text(queryT);

        //터미널역 조회 service 호출
        $.get("/api/search_exb?cityNameT="+queryT, function(body){

            for(let i = 0; i < body.length; i++){
                    let str = body[i];
                    $(".popup2_terminal_btn"+i).text(str);
            };
        });
    });
    $(".popup2_search_body2 button").click(function (){

         //선택한 터미널역 이름 log확인
         let exB_Terminal_name = $(this).text();

         let dep = window.opener.document.getElementById("dep2").value;
         let arr = window.opener.document.getElementById("arr2").value;

         if (dep == "") {
             window.opener.document.getElementById("dep2").value = exB_Terminal_name;
             window.close();
         } else if (arr == "") {
             window.opener.document.getElementById("arr2").value = exB_Terminal_name;
             window.close();
         }
     })

     //지역검색(ic터미널)
     $("#popup2_search_btn3").click(function (){

          //새로운 지역 검색시 기존값 비우기
          for (let i=0; i<20; i++){
              $(".popup2_icTerminal_btn"+i).text("")
          };

          //input param값 확인
          let queryIcT = $(".popup2_search_word3").val();

          //h2내용 변경
          $(".popup2_icTerminalH2").text(queryIcT);

          //ic터미널역 조회 service 호출
          $.get("/api/search_icb?cityNameIc="+queryIcT, function(data){

              for(let i = 0; i < 20; i++){
                  let str = data[i];
                  $(".popup2_icTerminal_btn"+i).text(str);
              };
          });
     });
     $(".popup2_search_body3 button").click(function (){

          //선택한 ic터미널역 이름 log확인
          let icB_Terminal_name = $(this).text();

          let dep = window.opener.document.getElementById("dep2").value;
          let arr = window.opener.document.getElementById("arr2").value;

          if (dep == "") {
               window.opener.document.getElementById("dep2").value = icB_Terminal_name;
               window.close();
          } else if (arr == "") {
               window.opener.document.getElementById("arr2").value = icB_Terminal_name;
               window.close();
          }
     })
})

window.onload = function(){
    const isValue2 = localStorage.getItem('selectType2');

    if(isValue2 == "기차"){
            $(".popup2_station").show();
            $(".popup2_terminal").hide();
            $(".popup2_icTerminal").hide();
        }else if(isValue2 == "시외버스"){
            $(".popup2_station").hide();
            $(".popup2_terminal").hide();
            $(".popup2_icTerminal").show();
        }else {
            $(".popup2_station").hide();
            $(".popup2_terminal").show();
            $(".popup2_icTerminal").hide();
        }

}