// 이벤트 팝업
// 팝업 일반 닫기 
const eventPopup = document.querySelectorAll(".mainpage-event-popup");
document.getElementById("mainpage-event-popup-close-btn").addEventListener("click",()=>{
    eventPopup[0].style.display = "none";
})  

// 팝업 오늘하루 닫기 
document.getElementById("mainpage-event-popup-nottoday-btn").addEventListener("click",()=>{
    // 배너 닫기 
    eventPopup[0].style.display = "none";

    // hideBanner라는 이름 쿠키 설정(유효기간: 1일) 
    setCookie("hidePopup", "true", 1);
});

// 쿠키 확인하여 팝업 띄우지 않기 
let hidePopup = getCookie("hidePopup");
if(hidePopup === "true"){
    eventPopup[0].style.display = "none";
}


// 이벤트 캐러셀 -------------------------------------------------------
const eventLeftBtn = document.getElementById("mainpage-event-lbtn"); 
const eventRightBtn = document.getElementById("mainpage-event-rbtn"); 
const eventLeftBtn2 = document.getElementById("mainpage-event-lbtn2");
const eventRightBtn2 = document.getElementById("mainpage-event-rbtn2");
const eventContainer = document.querySelector(".mainpage-event-container");


(function addEvent(){
    eventLeftBtn.addEventListener("click",()=>{
        slideContainer.bind(this,0)();
        eventLeftBtn2.style.backgroundColor = "rgb(34,34,34)"
        eventRightBtn2.style.backgroundColor = "rgb(34,34,34,0.3)"
    });
    eventRightBtn.addEventListener("click",()=>{
        slideContainer.bind(this,-1)();
        eventRightBtn2.style.backgroundColor = "rgb(34,34,34)"
        eventLeftBtn2.style.backgroundColor = "rgb(34,34,34,0.3)"
    });

    eventLeftBtn2.addEventListener("click",()=>{
        slideContainer.bind(this,0)();
        eventLeftBtn2.style.backgroundColor = "rgb(34,34,34)"
        eventRightBtn2.style.backgroundColor = "rgb(34,34,34,0.3)"
    });
    eventRightBtn2.addEventListener("click",()=>{
        slideContainer.bind(this,-1)();
        eventRightBtn2.style.backgroundColor = "rgb(34,34,34)"
        eventLeftBtn2.style.backgroundColor = "rgb(34,34,34,0.3)"
    });

})();


function slideContainer(direction){
    eventContainer.style.transitionDuration = '500ms';
    eventContainer.style.transform = `translateX(${direction * 100/2}%)`;
}
// 이벤트 캐러셀 end -------------------------------------------------------


// 베스트 상품 슬라이드 
const bestLeftBtn = document.getElementById("mainpage-best-lbtn");
const bestRightBtn = document.getElementById("mainpage-best-rbtn");
const bestContainer = document.querySelector(".mainpage-best-product-wrap .product-list");

(function addEvent(){
    bestLeftBtn.addEventListener("click", ()=>{
        bestSlider(1);
    });
    bestRightBtn.addEventListener("click", ()=>{
        bestSlider(-1);
    });
})();


function bestSlider(direction){
    const selectBtn = (direction === 1) ? "left" : "right";
    bestContainer.style.transitionDuration = '500ms';
    bestContainer.style.transform = `translateX(${direction * 100/5}%)`;

    setTimeout(() => {
        bestReorganizeEl(selectBtn);
    }, 500); 

}

function bestReorganizeEl(selectedBtn) {
    bestContainer.removeAttribute('style');
    (selectedBtn === 'left') ? bestContainer.insertBefore(bestContainer.lastElementChild, bestContainer.firstElementChild)
                             : bestContainer.appendChild(bestContainer.firstElementChild);
  }





// 베스트 슬라이딩 메뉴 
let bestHighlight = document.querySelector(".mainpage-best-category-highlight");
const bestitems = document.querySelectorAll(".mainpage-best-category-selector-item");

bestitems[0].classList.add("mainpage-best-category-selector-item--active")

function addClass(target){
    target.classList.add("mainpage-best-category-selector-item--active");
}

function selectItem(event){
    const target = event.target;
    const parent = document.querySelector(".mainpage-best-category-menu");
    const targetRect = target.getBoundingClientRect();
    const parentRect = parent.getBoundingClientRect();

    bestitems.forEach(el => {
        el.classList.remove("mainpage-best-category-selector-item--active");
    });
    bestHighlight.style.top = `${targetRect.top - parentRect.top}px`;
    addClass(target);
}   

// 베스트 아이템 메뉴 키워드 선택 및 변수 저장 
let mainSelectedBestCategory = '포스터';
for(let i = 0; i < bestitems.length; i++){
    bestitems[i].addEventListener("click",(e)=> mainBestSelect(e))
}


const mainBestSelect = (e) => {
    if(e.target.innerText === "Poster") {
        mainSelectedBestCategory = '포스터';
    } else if(e.target.innerText === "Home Fabric"){
        mainSelectedBestCategory = '홈패브릭';
    } else{
        mainSelectedBestCategory = '스마트폰 케이스';
    }
    
    $.ajax({
        url: "/stroke/mainBestProduct",
        type: 'GET',
        data: {
            productName : mainSelectedBestCategory
        },
        success: function(response) {
            console.log("성공", response)
        }, 
        error : function(){
            console.log("베스트 조회 에러 발생");
        }
    });

}












// 리뷰 모달 ---------------------------------------------------------
$(function(){
    $(".mainpage-review-img").click(function(){
        $(".mainpage-review-modal-overlay").fadeIn();
        $(".mainpage-review-modal-overlay").css("display", "flex");
    });

    $(".mainpage-review-modal-close").click(function(){
        $(".mainpage-review-modal-overlay").fadeOut();
    });

});



// 리뷰 모달 상품 띄우기 
document.getElementById("mainpage-review-modal-product-btn").addEventListener("click", function(){

    const modalProduct = document.querySelector(".mainpage-review-modal-product");
    modalProduct.style.display = "block";
    modalProduct.style.display = "flex";
    
    setTimeout(() => {
        modalProduct.classList.toggle("show");
    }, 300);

});


