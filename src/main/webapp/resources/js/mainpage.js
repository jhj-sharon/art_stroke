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

// contextPath 가져오기 
let contextPath = document.getElementById("eventContextPath").value;


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




// 베스트 상품 -------------------------------------------------------------
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



// 베스트 사이드 메뉴 ----
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


// 페이지 로딩 시 '포스터' 아이템 띄우기 
// 베스트 아이템 메뉴 키워드 선택 및 변수 저장 
let mainSelectedBestCategory = '포스터';
let bestItemList = [];

$(function(){
    $.ajax({
        url: "/stroke/mainBestProduct",
        type: 'GET',
        data: {
            productName : mainSelectedBestCategory
        },
        success: function(response) {
       
            bestItemList.push(...response);

                const bestProductList = document.querySelector(".product-list");
                let bestProductItem ='';

                // 베스트 상품 카드 
                for(let i = 0; i<bestItemList.length; i++){

                        bestProductItem += `<li class="product-item">
                                            <div class="product-item-img">
                                                <a href="${contextPath}/product/productDetail?product_id=${bestItemList[i].productId}">
                                                    <img src="${bestItemList[i].productImage}" alt="베스트상품 썸네일">
                                                </a>
                                                <span class="main-heart-area" id="${bestItemList[i].productId}" onclick="wishListHandler(event)"></span>
                                            </div>
            
                                            <div class="product-item-info">
                                                <span>${bestItemList[i].productArtist}</span>
                                                <span>${bestItemList[i].productName}</span>
                                                <span>${bestItemList[i].productPrice.toLocaleString()}원</span>
                                            </div>
                                        </li>`
                }
                
            
                bestProductList.innerHTML = bestProductItem;
                
        }, 
        error : function(){
            console.log("포스터 조회 에러 발생");
        }
    });

    findHeart();

    bestItemList = [];
    bestProductItem ='';
    
})


// 클릭 시 카테고리 변경 
for(let i = 0; i < bestitems.length; i++){
    bestitems[i].addEventListener("click",(e)=> {
        mainSelectedBestCategory = e.target.dataset.category;
        
        $.ajax({
            url: "/stroke/mainBestProduct",
            type: 'GET',
            data: {
                productName : mainSelectedBestCategory
            },
            success: function(response) {
           
                bestItemList.push(...response);

                    const bestProductList = document.querySelector(".product-list");
                    let bestProductItem ='';

                    // 베스트 상품 카드 
                    for(let i = 0; i<bestItemList.length; i++){

                            bestProductItem += `<li class="product-item">
                                                <div class="product-item-img">
                                                    <a href="${contextPath}/product/productDetail?product_id=${bestItemList[i].productId}">
                                                        <img src="${bestItemList[i].productImage}" alt="베스트상품 썸네일">
                                                    </a>
                                                    <span class="main-heart-area" id="${bestItemList[i].productId}" onclick="wishListHandler(event)"></span>
                                                </div>
                
                                                <div class="product-item-info">
                                                    <span>${bestItemList[i].productArtist}</span>
                                                    <span>${bestItemList[i].productName}</span>
                                                    <span>${bestItemList[i].productPrice.toLocaleString()}원</span>
                                                </div>
                                            </li>`
                    }
                
                
                    bestProductList.innerHTML = bestProductItem;
                    
           
            }, 
            error : function(){
                console.log("베스트 조회 에러 발생");
            }
        });
        findHeart();

        bestItemList = [];
        bestProductItem ='';
    })
}


// 키매, 하이퍼펜션 상품 불러오기 
$(function(){

    $.ajax({
        url: "/stroke/mainArtistProdcut",
        type: 'GET',
        success: function(response) {

                const kimmaeContainer = document.getElementById("main-kimmae");
                const hypereContainer = document.getElementById("main-hyperpension");
                

                // 키매, 하이퍼펜션 상품 배열 
                let kimmaeItemArr =[];
                let hyperItemArr =[];

                // 키매, 하이퍼펜션 카드 html 
                let kimmaeItem = '';
                let hyperItem = '';

                // 키매, 하이퍼펜션 아이템 배열 생성
                for(let i = 0; i<response.length; i++){
                   if(response[i].productArtist === "키매(KKIMAE)") {
                    kimmaeItemArr.push(response[i]);
                   } else{
                    hyperItemArr.push(response[i]);
                   }
                };

                for(let i = 0; i<kimmaeItemArr.length; i++){
                    kimmaeItem += `<li class="product-item">
                                                <div class="product-item-img">
                                                    <a href="${contextPath}/product/productDetail?product_id=${kimmaeItemArr[i].productId}">
                                                        <img src="${kimmaeItemArr[i].productImage}" alt="베스트상품 썸네일">
                                                    </a>
                                                    <span class="main-heart-area" id="${kimmaeItemArr[i].productId}" onclick="wishListHandler(event)"></span>
                                                </div>
                
                                                <div class="product-item-info">
                                                    <span style="font-size:18px;">${kimmaeItemArr[i].productArtist}</span>
                                                    <span style="font-size:15px;">${kimmaeItemArr[i].productName}</span>
                                                    <span style="font-size:15px;">${kimmaeItemArr[i].productPrice.toLocaleString()}원</span>
                                                </div>
                                            </li>`
                }

                for(let i = 0; i<hyperItemArr.length; i++){
                    hyperItem += `<li class="product-item">
                                                <div class="product-item-img">
                                                    <a href="${contextPath}/product/productDetail?product_id=${hyperItemArr[i].productId}">
                                                        <img src="${hyperItemArr[i].productImage}" alt="베스트상품 썸네일">
                                                    </a>
                                                    <span class="main-heart-area" id="${hyperItemArr[i].productId}" onclick="wishListHandler(event)"></span>
                                                </div>
                
                                                <div class="product-item-info">
                                                    <span style="font-size:18px;">${hyperItemArr[i].productArtist.substring(0, 5)}</span>
                                                    <span style="font-size:15px;">${hyperItemArr[i].productName.substring(0, 10)}</span>
                                                    <span style="font-size:15px;">${hyperItemArr[i].productPrice.toLocaleString()}원</span>
                                                </div>
                                            </li>`
                }

                kimmaeContainer.innerHTML = kimmaeItem;
                hypereContainer.innerHTML = hyperItem;

        }, 
        error : function(){
            console.log("아티스트 조회 에러 발생");
        }
    });
    findHeart();
})


// 위시리스트 
let mainHeartArea;
const emptyHeart = '<i class="fa-regular fa-heart"></i>';
const redHeart = '<i class="fa-solid fa-heart" style="color: #f42525;"></i>';
let mainWishProductId = [];
let mainLoginMember = document.getElementById("mainLoginMember");

//  위시리스트에 있는 상품 빨간 하트로 나타내기 
const findHeart = () => {

    setTimeout(() => {

        mainHeartArea = document.querySelectorAll(".main-heart-area");

        if(mainLoginMember.value != "null"){
            $.ajax({
                url: "/stroke/mainWishProdcut",
                type: 'GET',
                success: function(response) {
                    mainWishProductId = response.map(obj => obj.productId);

                    for(let i = 0; i < mainHeartArea.length; i++){
                        
                        if(mainWishProductId.includes(parseInt(mainHeartArea[i].id))){
                            mainHeartArea[i].innerHTML = redHeart;
                        } else {
                            mainHeartArea[i].innerHTML = emptyHeart;
                        }
                    }
                }, 
                error : function(){
                    console.log("하트 에러 발생");
                }
            });
    
        } else if(mainLoginMember.value === "null"){
            for(let i = 0; i < mainHeartArea.length; i++){
                mainHeartArea[i].innerHTML = emptyHeart;
            }
        }
    }, 500);
}

let productId;

// 빈 하트를 누르면 INSERT 빨간 하트를 누르면 DELETE + 로그인이 안되어있으면 alret창 
const wishListHandler = (event) =>{ 
    mainHeartArea = document.querySelectorAll(".main-heart-area");
  
    if(mainLoginMember.value != "null"){
        productId = event.target.parentNode.id;

        if(event.target.classList[0] === "fa-solid"){
            $.ajax({
                url: "/stroke/deleteMainWishList",
                type: 'post',
                data:{productId : productId},
                success: function(result) {
                    console.log("삭제 성공")
                    event.target.parentNode.innerHTML = emptyHeart;
                },
                error: function(){
                    console.log("위시리스트 삭제 실패")
                }
            })

        } else {
            $.ajax({
                url: "/stroke/addMainWishList",
                type: 'post',
                data:{productId : productId},
                success: function(result) {
                    console.log("추가 성공")
                    event.target.parentNode.innerHTML = redHeart;
                },
                error: function(){
                    console.log("위시리스트 추가 실패")
                }
            })
        }
    } else{
        alert("로그인이 필요한 기능입니다.");
        event.preventDefault();
        window.location.href = `${contextPath}/member/login`;
    }
}
    



// 리뷰 ---------------------------------------------------------
let mainReviewArr = [];

// 리뷰 가져오기 
$(function(){
    $.ajax({
        url: "/stroke/getMainReview",
        type: 'GET',
        success: function(response) {
        
            console.log("리뷰:" ,response)

            // 리뷰 arr에 저장 
            mainReviewArr = response.map(item => ({
                memberId: item.member.memberId,
                memberNick: item.member.memberNick,
                memberImg: item.member.profileImage,
                productId: item.product.productId,
                productName: item.product.productName,
                productPrice: item.product.productPrice,
                productArtist: item.product.productArtist,
                productImg: item.product.productImage,
                productCategory: item.product.productCategory,
                reviewId: item.reviewId,
                reviewContent: item.reviewContent,
                reviewImg: item.reviewImg,
              }));


              // 리뷰 띄우기 
              let reviewContainer = document.querySelector(".mainpage-review-container");

              for(let i = 0; i < mainReviewArr.length; i++){
                reviewContainer.innerHTML += `<div class="mainpage-review-item" id="${mainReviewArr[i].reviewId}">
                                                <div class="mainpage-review-img">
                                                    <img src="${contextPath}/${mainReviewArr[i].reviewImg}" alt="리뷰사진">
                                                </div>

                                                <div>
                                                    <div class="mainpage-review-item-profile">
                                                        <img src="${contextPath}/${mainReviewArr[i].memberImg}" alt="멤버프로필">
                                                        <span>${mainReviewArr[i].memberNick}</span>
                                                    </div>
                                                    <div class="mainpage-review-item-content">
                                                        ${mainReviewArr[i].reviewContent}
                                                    </div>
                                                    <span>
                                                        #${mainReviewArr[i].productArtist} &nbsp; #${mainReviewArr[i].productCategory}
                                                    </span>
                                                </div>
                                            </div>`;
              
              
              // 모달 띄우기 
              let reviewWindow = document.querySelector(".mainpage-review-modal-window");
              reviewWindow.innerHTML += `<div class="mainpage-review-modal-container">
                                            <div class="mainpage-review-modal-close"><button>&times;</button></div>
                                            
                                            <div class="mainpage-review-modal-img">
                                                <img src="${contextPath}/${mainReviewArr[i].reviewImg}">
                                                <button id="mainpage-review-modal-product-btn"><i class="fa-solid fa-plus"></i></button>
                                                
                                                <a href="">
                                                    <div class="mainpage-review-modal-product">
                                                        <img src="${mainReviewArr[i].productImg}" alt="이벤트모달상품">
                                                        <div>
                                                            <span style="font-weight: 500;">${mainReviewArr[i].productName}</span>
                                                            <span>${mainReviewArr[i].productArtist}</span>
                                                            <span>${mainReviewArr[i].productPrice}</span>
                                                        </div>
                                                    </div>
                                                </a>
                                            
                                            </div>
                                            <div class="mainpage-review-modal-contents">
                                                <div class="mainpage-review-modal-profile">
                                                    <img src="${contextPath}/${mainReviewArr[i].memberImg}" alt="프로필이미지">
                                                    <span>${mainReviewArr[i].memberNick}</span>
                                                </div>
                                                <div>
                                                    ${mainReviewArr[i].reviewContent}
                                                </div>
                                                <div>#${mainReviewArr[i].productArtist} &nbsp; #${mainReviewArr[i].productCategory}</div>
                                            </div>
                                        </div>`
              
              
              }

        }, 
        error : function(){
            console.log("리뷰 불러오기 에러 발생");
        }
    });

// 상품 정보 가져오기 


});




// 리뷰 모달 
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


