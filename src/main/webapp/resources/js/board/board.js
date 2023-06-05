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