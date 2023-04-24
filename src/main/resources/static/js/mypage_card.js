$(document).ready(function () {
    //session
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
    // Initialize Swiper
    var swiper = new Swiper(".mySwiper", {
        slidesPerView: 2,
        centeredSlides: true,
        spaceBetween: 30,
        touchRatio: 0,
        // loop: true,
        pagination: {
            el: ".swiper-pagination",
            // type: "fraction",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });
    swiper.on('transitionEnd', function () {
        console.log('now index :::', swiper.realIndex);
    });

    $("#delete_btn").hide();
    $("#card_img1").hover(function(){
        $(".btn1").show();
    },function() {
        $(".btn1").hide();
    });

    $("#delete_btn").hide();
    $("#card_img2").hover(function(){
        $(".btn2").show();
    },function() {
        $(".btn2").hide();
    });

    $("#delete_btn").hide();
    $("#card_img3").hover(function(){
        $(".btn3").show();
    },function() {
        $(".btn3").hide();
    });

    // $("#card_img2").hover(function(){
    //     $(".btn2").show();
    // });

    // $("#card_img3").hover(function(){
    //     $(".btn3").show();
    // });

    $(".btn1").click(function name() {
        $("#card_img1").remove();

        if ($(".swiper-pagination-bullet").length) {
            $(".swiper-pagination-bullet").last().remove();
        }

    });
    $(".btn2").click(function name() {

        $("#card_img2").remove();

        if ($(".swiper-pagination-bullet").length) {
            $(".swiper-pagination-bullet").last().remove();
        }
        swiper.slideTo(swiper.realIndex-1);

    });

    $(".btn3").click(function name() {

        $("#card_img3").remove();

        if ($(".swiper-pagination-bullet").length) {
            $(".swiper-pagination-bullet").last().remove();
        }

        swiper.slideTo(swiper.realIndex-1);

    });
    $('#menu_btn').mouseenter(function () {
        $('.menu_li').css('display', 'block');
    })

    $('.top_menu').mouseleave(function () {
        $('.menu_li').css('display', 'none');
    })

});

// function ChangeButtonText(event,) {
//     var slides = event.slides;
//     var currentSlideIndex = event.activeIndex;
//     var prevSlideIndex = event.activeIndex - 1;
//     var nextSlideIndex = event.activeIndex + 1;
//     // console.log(`다음 슬라이드 : ${prevSlideIndex}`);
//     // console.log(`현재 슬라이드 : ${currentSlideIndex}`);
//     // console.log(`다음 슬라이드 : ${nextSlideIndex}`);
// }