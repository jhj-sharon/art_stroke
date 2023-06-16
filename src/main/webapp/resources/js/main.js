// 상단 배너 오늘 하루 안보이기
// 1. 쿠키 설정
function setCookie(name, value, days) {

    // 쿠키의 만료 정보를 담을 변수 
    let expires = "";

    // days(쿠키의 유효기간) 변수가 존재하는 경우 expires에 쿠키 만료 날짜 및 시간 정보 설정 
    if(days) {
        let date = new Date();

        // 일(day) -> 밀리초(ms) 변환: days(일수) * 시간 * 분 * 초 * 밀리초 
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));

        // 날짜를 UTC 형식으로 변환
        expires = "; expires=" + date.toUTCString();
    }

    // 쿠키에 설정된 이름 값, 만료날짜 및 경로 정보 조합하여 설정 
    document.cookie = name + "=" + value + expires + "; path=/";
}

// 2. 쿠키 값 가져오기 
function getCookie(name) {

    // 찾고자하는 쿠키 이름과 일치하는지 확인하는 데 사용할 변수 설정 
    let nameEQ = name + "=";

    // 현재 페이지의 모든 쿠키 문자열을 가져와 split 사용하여 개별적 쿠키로 분할하여 ca 배열에 넣음
    let ca = document.cookie.split(";");

    // 분할된 쿠키 배열인 ca를 순환하여 현재 쿠키 문자열을 c변수에 할당 
    for(let i = 0; i < ca.length; i++){
        let c = ca[i];

        // c의 첫 번째 문자가 공백인 경우 공백 첫번째 문자를 제외한 나머지 문자열 가져옴
        while(c.charAt(0) == " ") c = c.substring(1, c.length);
        
        // 현재 쿠키 문자열이 nameEQ와 일치하는지 확인, 일치한다면 해당 쿠키의 값 인덱스를 찾아 그 인덱스의 
        // 쿠키의 값 부분만 추출하여 반환 
        if(c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }

    // 쿠키를 찾지 못한 경우 null 반환
    return null;
}


// 상단배너, 이벤트팝업 닫기
const mainBanner = document.getElementById("mainpage-top-banner");


// 배너 닫기 
document.getElementById("header-hide-banner").addEventListener("click",()=>{
    // 배너 닫기 
    mainBanner.style.height="0px"
    mainBanner.style.overflow = "hidden";

    // hideBanner라는 이름 쿠키 설정(유효기간: 1일) 
    setCookie("hideBanner", "true", 1);

    setSearchHeight();
});



// 페이지 로드 시 쿠키 확인하여 요소 숨기기 
let hideBanner = getCookie("hideBanner");

if(hideBanner === "true"){
    mainBanner.style.height="0px"
    mainBanner.style.overflow = "hidden";
}

// 상단배너 end -------------------------------------------------------




// 검색----------------------------------------------------------------

// 검색 박스 위치 조정 
let topBanner = document.getElementById("mainpage-top-banner");
let topBannerHeight;

const setSearchHeight = function(){

    if(topBanner.style.height === "0px") {
        topBannerHeight = 0;
    } else{
        topBannerHeight = topBanner.clientHeight;
    }

    document.querySelector(".header-search-top").style.top = topBannerHeight + "px";
}


window.addEventListener("scroll", function(){

    let scrollTop = $(window).scrollTop();
    
    if (scrollTop == 0) {
        setSearchHeight();
        document.querySelector(".header-search-top").style.top = topBannerHeight + "px";

    } else {
        document.querySelector(".header-search-top").style.top = 0 + "px";
    }
    

});

setSearchHeight();

// 검색창 열기 
$(function(){
    $("#header-search-btn").click(function(){
        $(".header-search-top").fadeIn();
        $(".header-search-input").toggleClass("expend");
        $(".header-search-popup").fadeIn();
        $(".header-search-popup-container").toggleClass("show");
    });

    $("#header-search-close-btn").click(function(){
        $(".header-search-top").fadeOut();
        $(".header-search-input").toggleClass("expend");
        $(".header-search-popup").fadeOut();
        $(".header-search-popup-container").toggleClass("show");
    });

});

// 검색 end -----------------------------------------------------------





//로그아웃시 세션 제거--------------------------------------------------------
function clearSessionStorage() {
    sessionStorage.clear();
  }

//세션 제거--------------------------------------------------------

