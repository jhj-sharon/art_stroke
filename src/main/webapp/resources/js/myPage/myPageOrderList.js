function openPopup(productName, orderDetailId) {
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";

    document.getElementById("productName").innerText = productName;
    document.getElementById("orderDetailId").value = orderDetailId;
}
  
function closePopup() {
        var popup = document.getElementById("popup");
        popup.style.visibility = "hidden";
        popup.style.opacity = "0";
}

function openPopup2(orderId) {
    var popup = document.getElementById("popup2");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    document.getElementById('hiddenOrderId').value = orderId;
    document.getElementById('orderId').innerText = orderId;
}
  
function closePopup2() {
        var popup = document.getElementById("popup2");
        popup.style.visibility = "hidden";
        popup.style.opacity = "0";
}
  
function reviewStatus(){
    alert("리뷰 작성이 불가 합니다.");
}
function cancelStatus(){
    alert("취소 신청이 불가 합니다.");
}

function openPopup3() {
    const deliveryKey = delivery.deliveryKey;
    var popup = document.getElementById("popup3");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    document.getElementById("deliveryKey").value = deliveryKey;
}
function closePopup3() {
    var popup = document.getElementById("popup3");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

$(document).ready(function() {
    setupPagination();
});
  
  function setupPagination(){
  const rowsPerPage = 5;
  const rows = document.querySelectorAll('#my-table tbody tr');
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