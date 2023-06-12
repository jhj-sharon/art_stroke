// 메인 이미지 --------------------------------

let productMainText = document.querySelector("h1");

window.addEventListener('scroll', function(){

    let value = window.scrollY

    if(value>400){
        productMainText.style.animation='disappear 1s  ease-out forwards';
    }else{
        productMainText.style.animation='slide 1s ease-out';
    }
})


//Ajax로 화면 로드시 전체 가져오기
//상품 정보가 담긴 객체 itemObj, 객체 배열 itemList
var itemObj;
var itemList = [];

$(document).ready(function(){
    
    console.log("화면로드 함수 실행중")
   
     $.ajax({
       url: "productMain",
       type: "POST",
       dataType: "JSON",
       success: function (productList) {
        //    console.log("productList::",productList)
   
      //  1. 서버에서 받아온 데이터를 객체로 변환
        for (let i = 0; i < productList.length; i++) {
           let item = productList[i];
           let itemObj = {
             productArtist: item.productArtist,
             productCategory: item.productCategory,
             productContent: item.productContent,
             productId: item.productId,
             productImage : item.productImage,
             productName : item.productName,
             productOption1 : item.productOption1,
             productOption2 : item.productOption2,
             productPrice : item.productPrice,
             productRdate : item.productRdate,
             productType : item.productType,
             sales: item.sales
           };
           itemList.push(itemObj);
         }
         localStorage.setItem("itemList", JSON.stringify(itemList));
         addCard(itemList);
       
       },
       error : function(error){
         console.log("화면 로드 실패")
       }
     });
   
   })


   function addCard() {
    var ul = document.querySelector('.product-list');
    //세션에서 위시리스트 가져오기
    var wishList = JSON.parse(sessionStorage.getItem('wishList'));


 
    // 기존 카드 삭제
    ul.innerHTML = '';
    
    // 새로운 카드 생성
    for(let i = 0; i < itemList.length; i++) {
        const itemObj = itemList[i];
    var li = document.createElement('li');

      // wishList에 포함되어 있는지 여부 확인
      var isWishlisted = wishList.includes(itemObj.productId);

      // 하트 아이콘 클래스 설정
      var heartIconClass = isWishlisted ? "fa-solid fa-heart" : "fa-regular fa-heart";
      var heartIconStyle = isWishlisted ? "color: #ee1b1b;" : "";

    li.innerHTML = `
      <div class="product-item" id="${itemObj.productId}">
        <div class="product-item-img">
           <a href="/stroke/product/productDetail?product_id=${itemObj.productId}">
            <img src="../${itemObj.productImage}" alt="">
          </a>
          <i class="${heartIconClass}" style="${heartIconStyle}"></i>
        </div>
  
        <div class="product-item-info">
          <span>${itemObj.productArtist}</span>
          <span>${itemObj.productName}</span>
          <span>${itemObj.productPrice}원</span>
        </div>
      </div>
    `;

    // 새로운 카드 추가
    ul.appendChild(li);
    }
    //페이지네이션 함수 실행
    setupPagination()

    //하트 등록 : 하트클릭 함수가 ajax 이후 실행되도록 하기위해
    var heartIcons = document.getElementsByClassName('fa-heart');
    for (var i = 0; i < heartIcons.length; i++) {
      heartIcons[i].addEventListener('click', handleHeartClick);
  }
}
  
  
function setupPagination(){
//pagination
const rowsPerPage = 12;
const rows = document.querySelectorAll('.product-list li');
const rowsCount = rows.length;
const pageCount = Math.ceil(rowsCount / rowsPerPage);
const numbers = document.querySelector('#numbers');
const prevPageBtn = document.querySelector('.pagination .fa-arrow-left');
const nextPageBtn = document.querySelector('.pagination .fa-arrow-right');
let pageActiveIdx =0; //현재 페이지 그룹의 번호
let currentPage = 0;  //현재 보고있는 페이제네이션 번호
let maxPageNum =3; //페이지그룹 최대 개수

for (let i = 1; i <= pageCount; i++) {

    numbers.innerHTML += `<li><a href="">${i}</a></li>`;
}

//페이지네이션 클릭
const numbersBtn = numbers.querySelectorAll('a');


//페이지네이션 번호 감추기
for(nb of numbersBtn){
    nb.style.display = 'none';
}


numbersBtn.forEach((item, idx)=>{
    item.addEventListener('click',(e)=>{
        e.preventDefault() // 기본기능 막기

         displayRow(idx)
    })

})

function displayRow(idx) {

    let start = idx * rowsPerPage;
    let end = start + rowsPerPage;
    let rowsArray = [...rows];


    //let rowsArray = Array.from(rows);
    for(ra of rowsArray) {
        //안보이게 하기
        ra.style.display = 'none';
    }

    let newRows =rowsArray.slice(start, end);
    for(nr of newRows) {
        nr.style.display = '';
    }

    //css 조정
    for(nb of numbersBtn){
        nb.classList.remove('active');
    }
    numbersBtn[idx].classList.add('active');

    //console.log("idx::",idx);

}//displayRow

//페이지 랜더링시 페이지네이션 바로 적용
displayRow(0);

//페이지네이션 그룹 표시 함수
function displayPage(num) {
    //페이지네이션 번호 감추기
    for(nb of numbersBtn){
        nb.style.display = 'none';
    }

    let totalPageCount = Math.ceil(pageCount / maxPageNum);
    let pageArr = [...numbersBtn];
    let start =num*maxPageNum;
    let end = start + maxPageNum;
    let pageListArr = pageArr.slice(start, end);

    for(pa of pageListArr) {
        pa.style.display = 'block';
    }

    //첫페이지에 화살표 안보이게하기
    if(pageActiveIdx == 0){
        prevPageBtn.style.display = 'none';
    }else{
        prevPageBtn.style.display = 'block';
    }

    if(pageActiveIdx == totalPageCount-1){
        nextPageBtn.style.display = 'none';
    }else{
        nextPageBtn.style.display = 'block';
    }

}

displayPage(0);

nextPageBtn.addEventListener('click', ()=>{ 
    let nextPageNum = pageActiveIdx*maxPageNum+maxPageNum;
    displayRow(nextPageNum);
    ++pageActiveIdx;
    displayPage(pageActiveIdx);

});

prevPageBtn.addEventListener('click', ()=>{

    let prevPageNum = pageActiveIdx*maxPageNum-maxPageNum;
    displayRow(prevPageNum);
    --pageActiveIdx;
    displayPage(pageActiveIdx);
});

}

//-------------------wishList----------------------------
$(document).ready(function() {
  
   // 서버에서 wishList 받아오기
   $.ajax({
    url: "wishList", 
    method: "GET", 
    dataType: "JSON", 
    success: function(response) {
      // 성공적으로 요청이 처리되었을 때 실행할 코드
      console.log("위시리스트::",response);

     //위시리스트:: (14) [59, 47, 70, 64, 50, 56, 1, 21, 24, 46, 71, 69, 91, 90]
     sessionStorage.setItem('wishList', JSON.stringify(response));

    },
    error: function(xhr, status, error) {
      // 요청 처리 중에 오류가 발생했을 때 실행할 코드
      console.error('위시 리스트 오류 발생:', error);
      // 오류 처리 방법을 선택하여 구현할 수 있습니다.
    }
  });
});
















function handleHeartClick(event) {
  var productId = event.target.parentNode.parentNode.id;
  var heartIcon = event.target;

  console.log("productId::",productId);
  console.log("heartIcon::",heartIcon);

  // AJAX 요청
  $.ajax({
    url: "wishlist", // 서버의 엔드포인트 URL
    method: "POST", // 요청 메소드 (GET, POST 등)
    data: { productId: productId }, // 전송할 데이터
    success: function(response) {
      // 성공적으로 요청이 처리되었을 때 실행할 코드
      if (response === 1) {
        // 위시리스트 등록에 성공한 경우
        console.log('위시리스트에 추가되었습니다.');
        alert('위시리스트 등록에 성공했습니다.');
        
        // 하트 아이콘 변경
        $(heartIcon).removeClass('fa-regular fa-heart').addClass('fa-solid fa-heart').css('color', '#fb0e0e');
                // 상품 ID를 session storage에 누적하여 저장하기.
                var savedWishlist = sessionStorage.getItem('wishList');
                if (savedWishlist) {
                  savedWishlist += ',' + productId;
                } else {
                  savedWishlist = productId.toString();
                }
                sessionStorage.setItem('wishList', savedWishlist);
        // 원하는 추가적인 동작을 수행할 수 있습니다.
      } else if (response === 0) {
        // 위시리스트 등록에 실패한 경우
        console.error('위시리스트 등록에 실패했습니다.');
        alert('위시리스트 등록에 실패했습니다.');
        
        // 실패 처리 방법을 선택하여 구현할 수 있습니다.
      } else if (response === -1) {
        // 로그인이 필요한 경우
        console.error('로그인이 필요합니다.');
        alert('로그인이 필요합니다.');
        
        // 로그인 페이지로 리다이렉트 또는 오류 처리 등을 수행할 수 있습니다.
      } else{
        console.error('이미 추가된 상품입니다.');
        alert('이미 추가된 상품입니다.');
      }
    },
    error: function(xhr, status, error) {
      // 요청 처리 중에 오류가 발생했을 때 실행할 코드
      console.error('위시 리스트 오류 발생:', error);
      // 오류 처리 방법을 선택하여 구현할 수 있습니다.
    }
  });
}




//${isProductInWishlist(itemObj.productId) ? '<i class="fa-solid fa-heart"></i>' : '<i class="fa-regular fa-heart"></i>'}


