console.log("작동되는지 확인");

/* 모두 체크 */
const allCheckBox = document.getElementById("chk_all");
const blindList = document.getElementsByClassName("blind");

allCheckBox.addEventListener("change", function() {
  const isChecked = allCheckBox.checked;

  for (let i = 0; i < blindList.length; i++) {
    blindList[i].checked = isChecked; // 모두 체크박스들의 체크 상태를 allCheckBox의 체크 여부와 동일하게 설정합니다.
  }

  console.log(emailOptInCheckBox.checked)
});

// 모든 체크박스(blindList)가 눌러지면 allCheckBox가 체크됨/해제됨
for (let i = 0; i < blindList.length; i++) {
  blindList[i].addEventListener("change", function () {
    let allChecked = true;
    for (let j = 0; j < blindList.length; j++) {
      if (!blindList[j].checked) {
        allChecked = false;
        break;
      }
    }
    allCheckBox.checked = allChecked;
  });
}


/*이메일 수신 체크 쿼리스트링으로넘기기*/
// const emailOptInCheckBox = document.getElementById("termsService4");
// let emailOptIn = "N"; // 기본값을 "N"으로 설정합니다.

// emailOptInCheckBox.addEventListener("change", function() {
//   const isChecked = emailOptInCheckBox.checked;
//   emailOptIn = isChecked ? "Y" : "N"; // 체크 여부에 따라 "Y" 또는 "N"으로 설정합니다.
//   // sessionStorage.setItem("emailOptIn", emailOptIn);
//   console.log(isChecked);
// });

// const nextBtn = document.getElementById("nextBtn");
// nextBtn.addEventListener("click", function() {
//   const checkboxes = document.getElementsByClassName("neCe");

//   for (let i = 0; i < checkboxes.length; i++) {
//     if (!checkboxes[i].checked) {
//       alert("필수 체크박스에 체크해주세요");
//       return false; // 체크되지 않은 경우 함수를 종료합니다.
//     }
//   }

//   // const emailOptIn = emailOptInCheckBox.checked ? "Y" : "N";

//   // window.location.href = "${contextPath}/member/signUp?emailOptIn=" + emailOptIn;
// });
function termsValidate() {
  const checkboxes = document.getElementsByClassName("neCe");
  let allChecked = true;

  for (let i = 0; i < checkboxes.length; i++) {
    if (!checkboxes[i].checked) {
      allChecked = false;
      break;
    }
  }

  if (allChecked) {
    console.log("회원가입페이지로 넘어감");
    return true;
    // 여기에 validate 함수에 대한 내용을 추가하면 됩니다.
  } else {
    alert("필수 체크박스를 모두 체크해주세요.");
    return false;
  }
}
