console.log("Product main.js is loading...");

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
    
    // 기존 카드 삭제
    ul.innerHTML = '';
    
    // 새로운 카드 생성
    for(let i = 0; i < itemList.length; i++) {
        const itemObj = itemList[i];
    var li = document.createElement('li');
    li.innerHTML = `
      <div class="product-item" id="${itemObj.productId}">
        <div class="product-item-img">
          <a href="/product/productDetail">
            <img src="../${itemObj.productImage}" alt="">
          </a>
          <i class="fa-regular fa-heart"></i>
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
    setupPagination()
    // 업데이트된 카드로 다시 선택
//   const updatedRows = document.querySelectorAll('.product-list li');
//   const updatedRowsCount = updatedRows.length;
//   const updatedPageCount = Math.ceil(updatedRowsCount / rowsPerPage);
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
console.log("numbersBtn::",numbersBtn)

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
console.log(rows);
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