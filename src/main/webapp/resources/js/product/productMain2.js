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
// 메인이미지 End--------------------------------
function showLoginAlert() {
  alert("로그인이 필요합니다.");
  // 원하는 추가 동작을 여기에 작성하세요
}



//Ajax로 화면 로드시 전체 가져오기------------------------------
//상품 정보가 담긴 객체 itemObj, 객체 배열 itemList
var itemObj;
var itemList = [];

$(document).ready(function(){
    
   
   
     $.ajax({
       url: "productMain",
       type: "POST",
       dataType: "JSON",
       success: function (productList) {
   
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
             productRdate : item.productRDate,
             productType : item.productType,
             sales: item.sales
           };
           itemList.push(itemObj);
         }
         localStorage.setItem("itemList", JSON.stringify(itemList));

       
       },
       error : function(error){
         
       }
     });
   
   })


 
//-----------LoadProduct End----------------------------------------

//url 쿼리스트링을 이용한 필터링 디자인 변경----------------------
window.onload = function() {
  // 현재 URL의 쿼리스트링 파싱
  const urlParams = new URLSearchParams(window.location.search);

  // productType 쿼리스트링 값 가져오기
  const productType = urlParams.get('productType');

  // productCategory 쿼리스트링 값 가져오기 (중복된 값 배열로 가져오기)
  const productCategories = urlParams.getAll('productCategory');

  // 모든 버튼 요소 선택
  const buttons = document.querySelectorAll('.button-48, .product-category-btn');

  // 버튼에 대한 반복문
  buttons.forEach(button => {
    // productType에 따른 스타일 변경
    if (button.getAttribute('onclick') === `redirectToProductType('${productType}')`) {
      button.style.fontWeight = 'bold';
      button.style.backgroundColor = 'whitesmoke';
    }

    // productCategory에 따른 스타일 변경
    productCategories.forEach(category => {
      if (button.getAttribute('onclick') === `redirectToProductCategory('${category}')`) {
        button.style.fontWeight = 'bold';
        button.style.backgroundColor = 'whitesmoke';
      }
    });
  });
};

//----------------------------------------------------------------

//-----------pagination----------------------------------------
$(document).ready(function() {
  setupPagination();
});

function setupPagination(){
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
//-----------pagination End----------------------------------------



//-------------------wishList----------------------------


$(document).ready(function() {
  
   // 서버에서 wishList 받아오기
   $.ajax({
    url: "wishList", 
    method: "GET", 
    dataType: "JSON", 
    success: function(response) {

      if (response === 0) {
        //console.log("로그인 필요");
      } else {
        //console.log("위시리스트::",response);

        sessionStorage.setItem('wishList', JSON.stringify(response));
      }
    },
    error: function(xhr, status, error) {
   
      //console.error('위시 리스트 오류 발생:', error);
    }
  });
});
//-------------------wishList End----------------------------




//하트클릭 이벤트(위시리스트 등록, 삭제)--------------------------------------------------------

// 하트 아이콘을 클릭했을 때 이벤트 리스너 등록
// 하트 아이콘을 클릭했을 때 이벤트 리스너 등록
$(document).on('click', '.fa-heart', function(event) {
  var productId = event.target.id;
  var heartIcon = event.target;

  //console.log("productId::", productId);

  // AJAX 요청
  $.ajax({
    url: "wishlist", // 서버의 엔드포인트 URL
    method: "POST", // 요청 메소드 (GET, POST 등)
    data: { productId: productId }, // 전송할 데이터
    success: function(response) {
      // 성공적으로 요청이 처리되었을 때 실행할 코드
      if (response === 1) {
        // 위시리스트 등록에 성공한 경우
        //console.log('위시리스트에 추가되었습니다.');
        alert('위시리스트 등록에 성공했습니다.');

        // 하트 아이콘 변경
        $(heartIcon).removeClass('fa-regular fa-heart').addClass('fa-solid fa-heart').css('color', '#fb0e0e');

        // 상품 ID를 session storage에 누적하여 저장하기.
        var savedWishlist = sessionStorage.getItem('wishList');
        if (savedWishlist) {
          savedWishlist = JSON.parse(savedWishlist);
          savedWishlist.push(productId);
        } else {
          savedWishlist = [productId];
        }
        sessionStorage.setItem('wishList', JSON.stringify(savedWishlist));

      } else if (response === 0) {
        // 위시리스트 등록에 실패한 경우
        //console.error('위시리스트 등록에 실패했습니다.');
        alert('위시리스트 등록에 실패했습니다.');

        // 실패 처리 방법을 선택하여 구현할 수 있습니다.
      } else if (response === -1) {
        // 로그인이 필요한 경우
        //console.error('로그인이 필요합니다.');
        alert('로그인이 필요합니다.');

        // 로그인 페이지로 리다이렉트 또는 오류 처리 등을 수행할 수 있습니다.

      } else {
        //console.error('위시리스트에서 삭제.');
        alert('위시리스트에서 삭제했습니다.');
        $(heartIcon).removeClass('fa-solid fa-heart').addClass('fa-regular fa-heart').css('color', '');

        // 상품 ID를 session storage에서 제거하기.
        var savedWishlist = sessionStorage.getItem('wishList');
        if (savedWishlist) {
          savedWishlist = JSON.parse(savedWishlist);
          var index = savedWishlist.indexOf(productId);
          if (index !== -1) {
            savedWishlist.splice(index, 1);
          }
          sessionStorage.setItem('wishList', JSON.stringify(savedWishlist));
        }
      }
    },
    error: function(xhr, status, error) {
      // 요청 처리 중에 오류가 발생했을 때 실행할 코드
      //console.error('위시 리스트 오류 발생:', error);
      // 오류 처리 방법을 선택하여 구현할 수 있습니다.
    }
  });
});

//하트클릭 이벤트(위시리스트 등록, 삭제) End--------------------------------------------------------

let bestButton = document.querySelector(".best");
let newButton = document.querySelector(".new");
let posterButton = document.querySelector(".poster");
let homeFabricButton = document.querySelector(".homeFabric");
let phoneCaseButton = document.querySelector(".phoneCase");

//Filtering: Best Item---------------------------------------------------
function sortItemsBySalesDesc() {
  // 로컬 스토리지에서 아이템 목록을 가져옴
  let itemList = JSON.parse(localStorage.getItem("itemList"));

  // sales를 기준으로 내림차순으로 정렬
  let sortedList = itemList.sort(function(a, b) {
    return b.sales - a.sales;
  });

  // 정렬된 아이템 목록을 filteredList에 저장
  let filteredList = sortedList;

  // 정렬된 아이템 목록을 카드에 추가
  addCard(filteredList);

    //best 버튼의 배경색 변경
    bestButton.style.backgroundColor = "#f5f5f5";
    bestButton.style.fontWeight = "600";

    newButton.style="";
    posterButton.style="";
    posterButton.style="";
    homeFabricButton.style="";
    phoneCaseButton.style="";
}
//Best Item Ends--------------------------------------------------------

//Filtering: New Item---------------------------------------------------
function sortItemsByProductRdateDesc() {
  // 로컬 스토리지에서 아이템 목록을 가져옴
  let itemList = JSON.parse(localStorage.getItem("itemList"));

  // productRdate를 기준으로 내림차순으로 정렬
  let sortedList = itemList.sort(function(a, b) {
    let dateA = new Date(a.productRdate);
    let dateB = new Date(b.productRdate);
    return dateB - dateA;
  });

  let filteredList = sortedList;
  addCard(filteredList);


}
//New Item Ends--------------------------------------------------------

//Filtering : Posters---------------------------------------------------

function sortItemsByType(parameter) {

  //console.log(parameter);

  // 로컬 스토리지에서 아이템 목록을 가져옴
  let itemList = JSON.parse(localStorage.getItem("itemList"));

  // 파라미터를 productType에 포함하고 있는 아이템 필터링
  let filteredList = itemList.filter(item => item.productType === parameter);


  addCard(filteredList);



}
//Posters End--------------------------------------------------------

//Category--------------------------------------------------------
function redirectToProductType(productType) {
  // 현재 URL 가져오기
  var currentUrl = window.location.href;

  // 기존 쿼리스트링 제거
  var newUrl = currentUrl.split('?')[0];

  // 현재 URL의 파라미터 값들을 가져와 배열로 저장
  var urlParams = new URLSearchParams(window.location.search);

  // 기존 쿼리스트링이 있는 경우 현재 파라미터를 새로운 URL에 추가
  if (urlParams.has('productType')) {
      newUrl += '?productType=' + encodeURIComponent(productType);
  } else {
      // 기존 쿼리스트링이 없는 경우 '?' 추가
      newUrl += '?' + 'productType=' + encodeURIComponent(productType);
  }

  // 페이지 이동
  window.location.href = newUrl;
}
//Category End--------------------------------------------------------

function redirectToProductType(productType) {
  // 현재 URL 가져오기
  var currentUrl = window.location.href;

  // 기존 쿼리 스트링이 있는지 확인하여 물음표 또는 앰퍼샌드(&) 추가
  var separator = currentUrl.includes('?') ? currentUrl.split('?')[0] + '?' : '?';
  //console.log(separator);

  // 새로운 URL 생성
  var newUrl = separator + 'productType=' + encodeURIComponent(productType);

  // 페이지 이동
  window.location.href = newUrl;
}

function redirectToProductCategory(productCategory) {
  // 현재 URL 가져오기
  var currentUrl = window.location.href;

  // 기존 쿼리 스트링이 있는지 확인하여 물음표 또는 앰퍼샌드(&) 추가
  var separator = currentUrl.includes('?') ? '&' : '?';

  // 새로운 URL 생성
  var newUrl = currentUrl + separator + 'productCategory=' + encodeURIComponent(productCategory);

  // 페이지 이동
  window.location.href = newUrl;
}