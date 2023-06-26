console.log("작동?");


const allCheckBox = document.getElementById("chk_all");
const agreeCheckList = document.getElementsByClassName("agreeCheck");

allCheckBox.addEventListener("change", function() {
  const isChecked = allCheckBox.checked;

  for (let i = 0; i < agreeCheckList.length; i++) {
    agreeCheckList[i].checked = isChecked;
  }

  console.log(allCheckBox.checked);
});

for (let i = 0; i < agreeCheckList.length; i++) {
  agreeCheckList[i].addEventListener("change", function() {
    let allChecked = true;

    for (let j = 0; j < agreeCheckList.length; j++) {
      if (!agreeCheckList[j].checked) {
        allChecked = false;
        break;
      }
    }

    allCheckBox.checked = allChecked;
  });
}



const snsSignUp_Btn = document.getElementById("snsSignUp_Btn");
snsSignUp_Btn.addEventListener("click", function() {
  const checkboxes = document.getElementsByClassName("neCe");

  for (let i = 0; i < checkboxes.length; i++) {
    if (!checkboxes[i].checked) {
      alert("필수 체크박스에 체크해주세요");
      return false; // 체크되지 않은 경우 함수를 종료합니다.
    }
  }

 
});
 function sns_signUpValidate(){
 
 return true;
 
 }


