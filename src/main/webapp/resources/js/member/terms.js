function termsValidate(){ 

  const checkbox=document.getElementsByClassName(".blind");

  if(!checkbox.checked){
    return false;

  }

}


const allCheckBox = document.getElementById("chk_all");
const blindList = document.getElementsByClassName("blind");



allCheckBox.addEventListener("click", function() {

  const allCheckBox = document.getElementById("chk_all");
  const blindList = document.getElementsByClassName("blind");

  if (allCheckBox.checked) {
    for (let i = 0; i < blindList.length; i++) {
      blindList[i].checked = true; // 나머지 체크박스들 체크
    }
  } else {
    for (let i = 0; i < blindList.length; i++) {
      blindList[i].checked = false; // 나머지 체크박스들 체크해제
    }
  }

});

