console.log("js load");

// 메인 이미지 

let productMainText = document.querySelector("h1");

window.addEventListener('scroll', function(){

    let value = window.scrollY

    if(value>400){
        productMainText.style.animation='disappear 1s  ease-out forwards';
    }else{
        productMainText.style.animation='slide 1s ease-out';
    }
})



///Ajax로 화면 로드시 전체 가져오기
//상품 정보가 담긴 객체 itemObj, 객체 배열 itemList
var itemObj;
var itemList = [];

$(document).ready(function(){
    
    console.log("화면로드 함수 실행중")
   
     $.ajax({
       url: "prouctMain",
       type: "GET",
       dataType: "JSON",
       success: function (productList) {
           console.log("productList::",productList)
   
        // 1. 서버에서 받아온 데이터를 객체로 변환
        for (let i = 0; i < productList.length; i++) {
           let item = productList[i];
           let itemObj = {
             id: item.wishlist_idx,
             rest_id: item.rest_id,
             name: item.rest_name,
             address: item.rest_Addr,
             category : item.rest_category,
             sns : item.rest_sns,
             tel : item.rest_tel,
             time : item.rest_time,
             img : item.rest_img,
             contents : item.rest_contents
   
           };
           itemList.push(itemObj);
         }
         console.log("itemList::"+itemList)
      addCard(itemList);
       
       },
       error : function(error){
         console.log("화면 로드 실패")
       }
     });
   
   })


   function addCard() {
    var ul = document.querySelector('.product-list');
    
    // 기존 카드 삭제
    ul.innerHTML = '';
    
    // 새로운 카드 생성
    var li = document.createElement('li');
    li.innerHTML = `
      <div class="product-item">
        <div class="product-item-img">
          <a href="${contextPath}/product/productDetail">
            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
          </a>
          <i class="fa-regular fa-heart"></i>
        </div>
  
        <div class="product-item-info">
          <span>김키매(KimKimme)</span>
          <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
          <span>21,900원</span>
        </div>
      </div>
    `;
    
    // 새로운 카드 추가
    ul.appendChild(li);
  }
  
  // addCard 함수 호출
  addCard();
  