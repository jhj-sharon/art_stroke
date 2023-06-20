const targetCaerouselBtn = document.querySelectorAll(".carousel-indicators > button");
const nextBtn = document.getElementById("next");
const prevBtn = document.getElementsByClassName("carousel-control-prev");
const targetClass = "active";
const lastBtn =document.getElementById("lastBtn");
for (let i = 0; i < targetCaerouselBtn.length; i++) {
  targetCaerouselBtn[i].addEventListener("click", function() {
    // 현재 클릭된 버튼에 "active" 클래스 추가
    this.classList.add("active");
    
    // 나머지 버튼에서 "active" 클래스 제거
    for (let j = 0; j < targetCaerouselBtn.length; j++) {
      if (j !== i) {
        targetCaerouselBtn[j].classList.remove("active");
      }
    }
  });
}


let targetIndex = -1;
//이거 이름이 반대로 되어있음 참고.
prevBtn[0].addEventListener("click",function(){
    for (let i = 0; i < targetCaerouselBtn.length; i++) {
        if (targetCaerouselBtn[i].classList.contains(targetClass)) {
            if(i==targetCaerouselBtn.length-1){
                targetIndex = 0;
            }else{
            targetIndex = i+1;
            }
            console.log(targetIndex);
            targetCaerouselBtn[targetIndex].classList.add(targetClass);
            break;
        }    
    
    }
    for(let i = 0; i < targetCaerouselBtn.length; i++){
        if(i != targetIndex){
            targetCaerouselBtn[i].classList.remove(targetClass);
        }
    }

});


nextBtn.addEventListener("click", function () {
  for (let i = 0; i < targetCaerouselBtn.length; ++i) {
    if (targetCaerouselBtn[i].classList.contains(targetClass)) {
      if (i == 0) {
        targetIndex = 2;
        lastBtn.classList.add("active");
      } else {
        targetIndex = i - 1;
        targetCaerouselBtn[targetIndex].classList.add("active");
      }
    }
  }

  for (let j = 0; j < targetCaerouselBtn.length; j++) {
    if (j !== targetIndex) {
      targetCaerouselBtn[j].classList.remove("active");
    }
  }
});

const sorting = document.getElementById("lang");

const container = document.createElement('div');
if (sortValue != null) {
  switch (sortValue) {
    case "date":
      sorting.value = "date";
      break;
    case "cnt":
      sorting.value = "cnt";
      break;
    case "good":
      sorting.value = "good";
      break;
  }
}

sorting.addEventListener("change",function(){

  $.ajax({
    url : contextPath + "/board/boardSorting",
    data: {"sort" : this.value,
           "boardCode":boardCode,
           "cp" : cp,
           "key" : key,
           "query": query},
    type: "post",
    success(map){
      const boardContainer = document.getElementById("board-card-detail");
      boardContainer.innerHTML = "";

      for(let board of map.boardList){
        const boardCardElement = document.createElement('div');
        boardCardElement.style.cursor = "pointer";
        boardCardElement.onclick = () => {
                   location.href = '../detail/'+boardCode+"/"+board.boardId+'?cp='+map.pagination.currentPage+sURL;
        };
        boardCardElement.classList.add("board-card-element");

        if(board.boardFile2 != null){
          const boardCardImg = document.createElement('div');
          boardCardImg.classList.add('board-card-img');
          const boardImg = document.createElement('img');
          boardImg.src = board.boardFile2;
          boardImg.classList.add('imgSize');
          boardImg.alt = '...';

          boardCardImg.appendChild(boardImg);
          boardCardElement.appendChild(boardCardImg);
        }else{
          const boardCardImg = document.createElement('div');
          boardCardImg.classList.add('board-card-img');

          const defaultImg = document.createElement('img');
          defaultImg.src = contextPath + "/resources/images/boardImg/board_defaultImg.jpg";
          defaultImg.classList.add('imgSize');
          defaultImg.alt = '...';

          boardCardImg.appendChild(defaultImg);
          boardCardElement.appendChild(boardCardImg);
        }
        const itemSentence = document.createElement('div');
        itemSentence.classList.add('item-sentence');

        const boardTitle = document.createElement('p');
        boardTitle.textContent = board.boardTitle;

        itemSentence.appendChild(boardTitle);
        boardCardElement.appendChild(itemSentence);

        boardContainer.appendChild(boardCardElement);
        

      }
      const paginationContainer = document.getElementById("paginationContainer");
      paginationContainer.innerHTML="";
      const ulPagination = document.createElement('ul');
      ulPagination.classList.add("pagination");

      const firstLi = document.createElement("li");
      const firstLiA = document.createElement("a");
      firstLiA.href = url+1+sURL +"&sort="+map.sort;
      firstLiA.innerText = `<<`;

      firstLi.appendChild(firstLiA);

      const prevLi = document.createElement("li");
      const prevLiA = document.createElement("a");
      prevLiA.href = url+map.pagination.prevPage+sURL +"&sort="+map.sort;
      prevLiA.innerText = `<`;

      prevLi.appendChild(prevLiA);

      ulPagination.appendChild(firstLi);
      ulPagination.appendChild(prevLi);
      
      for(var i = map.pagination.startPage; i<= map.pagination.endPage; i++){
        var listItem = document.createElement('li');
        var anchor = document.createElement('a');
  
        if (i === map.pagination.currentPage) {
        anchor.className = 'current';
        anchor.innerText = i;
        } else {
        anchor.href = url + i + sURL +"&sort="+map.sort;
        anchor.innerText = i;
        }
        listItem.appendChild(anchor);
        ulPagination.appendChild(listItem);
      }

      const lastLi = document.createElement("li");
      const lastLiA = document.createElement("a");
      lastLiA.href = url+map.pagination.nextPage+sURL +"&sort="+map.sort;
      lastLiA.innerText = `>`;

      lastLi.appendChild(lastLiA);

      const trueLastLi = document.createElement("li");
      const trueLastLiA = document.createElement("a");
      trueLastLiA.href = url+map.pagination.maxPage+sURL +"&sort="+map.sort;
      trueLastLiA.innerText = `>>`;

      trueLastLi.appendChild(trueLastLiA);

      ulPagination.appendChild(lastLi);
      ulPagination.appendChild(trueLastLi);
      paginationContainer.appendChild(ulPagination);
    }
  });
        

// <%-- container를 적절한 위치에 추가하는 코드 --%>
// const parentElement = document.querySelector('#board-card-detail');
// parentElement.appendChild(cont ainer);
});
    
