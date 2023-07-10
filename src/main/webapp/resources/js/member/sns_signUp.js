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

  if (memberTel.value.trim() === "") {
    alert("전화번호를 입력해주세요.");
    return false;
    }
    
   
   

 
});




const neCe=document.getElementsByClassName("neCe");

 function sns_signUpValidate(){

  if (AllNeCeChecked() && memberTel.value.trim() !== "") {
    return true;
  }

  return false;
}

function AllNeCeChecked() {
  const neCe = document.getElementsByClassName("neCe");

  for (let i = 0; i < neCe.length; i++) {
    if (!neCe[i].checked) {
      return false;
    }
  }

  return true;
}
const memberTel = document.getElementById("memberTel");
memberTel.addEventListener("input", function(event) {
  // 전화번호 정규식에 맞는 문자열만 입력할 수 있게
  const regExp = /^[0-9]+$/;

  const inputValue = event.target.value;

  if (regExp.test(inputValue)) {
    // 입력된 값이 정규식에 맞는 경우
    memberTel.value = inputValue; // 그대로 입력됨
  } else {
    // 입력된 값이 정규식에 맞지 않는 경우
    memberTel.value = ""; // 입력창을 비워줌
  }
});
