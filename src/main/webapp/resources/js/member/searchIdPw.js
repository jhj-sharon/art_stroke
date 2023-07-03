
  console.log("main.load");

/*tbody */
//이메일 찾기
const table = document.querySelector("table");
const pwTbody = document.querySelector(".pwTbody");




//비밀번호 찾기


const FindPw_Email = document.getElementById("FindPw_Email");
const FindPw_Tel = document.getElementById("FindPw_Tel");


// 이메일 찾기 버튼
const searchEmailBtn = document.getElementById("searchEmailBtn");
// 비밀번호 찾기 버튼
const searchPwBtn = document.getElementById("searchPwBtn");

const memberName1=document.getElementById("memberName1");
const memberTel1=document.getElementById("memberTel1");


function searchEmailValidate() {
  const memberName = document.getElementById("memberName1").value.trim();
  const memberTel = document.getElementById("memberTel1").value.trim();

  if (memberName.length === 0) {
    alert("이름을 입력해주세요");
    return false;
  }

  if (memberTel.length === 0) {
    alert("전화번호를 입력해주세요");
    return false;
  }

  return true; // 이름과 전화번호 모두 입력된 경우에만 페이지 이동을 허용
}


//버튼 email찾기

searchEmailBtn.addEventListener("click", function (event) {

  event.preventDefault(); 
  // AJAX 요청
  $.ajax({
    url: "searchIdPw/email", // URL 경로 앞에 슬래시(/)를 추가하여 절대 경로로 지정합니다.
    type: "GET",
    data: {
      memberName: memberName1.value.trim(),
      memberTel: memberTel1.value.trim()
    },
    success: function (emailResult) {
      if (emailResult !== "") {
        // 이메일을 성공적으로 찾은 경우, 모달 창 띄우기
        openModal(emailResult); // 모달 창에 이메일 값을 전달하여 처리
      } else {
       
        openModal("해당되는 회원이 없습니다."); 
      } 
    },
    error: function (xhr, status, error) {
      // 에러 처리
      console.log("AJAX Error: " + error);
    },
  });
});


//임시비밀번호 보내기

function searchPwValidate() {
  const memberName = document.getElementById("memberName2").value.trim();
  const memberTel = document.getElementById("memberTel2").value.trim();

  if (memberName.length === 0) {
    alert("이름을 입력해주세요");
    return false;
  }

  if (memberTel.length === 0) {
    alert("전화번호를 입력해주세요");
    return false;
  }

  return true; // 이름과 전화번호 모두 입력된 경우에만 페이지 이동을 허용
}



const memberName2 = document.getElementById("memberName2");
const memberTel2 = document.getElementById("memberTel2");

searchPwBtn.addEventListener("click", function(event) {
  event.preventDefault();

  // AJAX 요청
  $.ajax({
    url: "searchIdPw/pw_Tel", // 호출할 서버의 URL 주소를 입력하세요.
    type: "POST", // 요청 메서드(GET, POST 등)를 선택하세요.
    data: {
      memberName: memberName2.value.trim(),
      memberTel: memberTel2.value.trim()
    
    },
    success: function(smsResult) {
      console.log("서버 응답:", smsResult);

      if (smsResult > 0) {
        // 성공적으로 임시 비밀번호를 전송한 경우
       alert("임시비밀번호를 전화번호로 전송하였습니다.")
      } else {
        // 문자 전송에 실패하거나 해당하는 회원이 없는 경우
       alert("해당되는 회원이 없습니다.")
      }

   
   
    },
    error: function(xhr, status, error) {
      // 요청이 실패한 경우 실행되는 함수입니다.
      // 에러 정보는 `xhr`, `status`, `error` 변수에 담겨 있습니다.
      console.log("AJAX 요청 실패:", error);

      // 에러 메시지 출력
      alert("서버 요청 실패");

      // 실패 시 필요한 작업을 수행하세요.
    }
  });
});




//모달 열고 닫기(EMail찾기)
function openModal(emailResult) {
  var modal = document.querySelector(".modal");
  var emailResultElement = document.getElementById("emailResult");
  emailResultElement.textContent = emailResult;

  modal.classList.remove("hidden");

  // 확인 버튼 클릭 시 모달 창 닫기
  var closeBtn = modal.querySelector(".closeBtn");
  closeBtn.addEventListener("click", function () {
    closeModal();
  });

  // 모달 외부 영역 클릭 시 모달 창 닫기
  var bg = modal.querySelector(".bg");
  bg.addEventListener("click", function () {
    closeModal();
  });
}

// 모달 창 닫기
function closeModal() {
  var modal = document.querySelector(".modal");
  modal.classList.add("hidden");
}



//이메일로 비밀번호찾기
//원래있던 jsp없애고 새로운 폼 hidden이었다가 나타나기.
//비밀번호찾기에서 이메일로찾기,핸드폰(sms)로 찾기
const findPwEmailRadio = document.getElementById("FindPw_Email");
const findPwTelRadio = document.getElementById("FindPw_Tel");
const findPwEmailForm = document.forms["FindPw_EmailForm"];
const findPwTelForm = document.forms["FindPw_TelForm"];

findPwEmailRadio.addEventListener("change", function() {
  findPwEmailForm.style.display = "block";
  findPwTelForm.style.display = "none";
});

findPwTelRadio.addEventListener("change", function() {
  findPwEmailForm.style.display = "none";
  findPwTelForm.style.display = "block";
});



//이메일보내기

const memberName3=document.getElementById("memberName3");
const memberEmail3=document.getElementById("memberEmail3");
const searchPw_EmailBtn=document.getElementById("searchPw_EmailBtn");
searchPw_EmailBtn.addEventListener("click", function(event) {
  event.preventDefault();

  // AJAX 요청
  $.ajax({
    url: "searchIdPw/pw_email", // 호출할 서버의 URL 주소를 입력하세요.
    type: "POST", // 요청 메서드(GET, POST 등)를 선택하세요.
    data: {
      memberName: memberName3.value.trim(),
      memberEmail: memberEmail3.value.trim()
    
    },
    success: function(result) {
      if (result > 0) {
        // 성공적으로 임시 비밀번호를 전송한 경우
       alert("임시비밀번호를 이메일로 전송하였습니다.")
      } else {
        // 문자 전송에 실패하거나 해당하는 회원이 없는 경우
       alert("해당되는 회원이 없습니다.")
      }

   
   
    },
    error: function(xhr, status, error) {
      // 요청이 실패한 경우 실행되는 함수입니다.
      // 에러 정보는 `xhr`, `status`, `error` 변수에 담겨 있습니다.
      console.log("AJAX 요청 실패:", error);

      // 에러 메시지 출력
      alert("서버 요청 실패");

      // 실패 시 필요한 작업을 수행하세요.
    }
  });
});

//전화번호는 숫자만 입력할수있게

// memberTel1 입력 필드에 대한 이벤트 리스너
memberTel1.addEventListener("input", function(event) {
  const regExp = /^[0-9]+$/;
  const inputValue = event.target.value;

  if (regExp.test(inputValue)) {
    // 입력된 값이 정규식에 맞는 경우
    memberTel1.value = inputValue; // 그대로 입력됨
  } else {
    // 입력된 값이 정규식에 맞지 않는 경우
    memberTel1.value = ""; // 입력창을 비워줌
  }
});

// memberTel2 입력 필드에 대한 이벤트 리스너
memberTel2.addEventListener("input", function(event) {
  const regExp = /^[0-9]+$/;
  const inputValue = event.target.value;

  if (regExp.test(inputValue)) {
    // 입력된 값이 정규식에 맞는 경우
    memberTel2.value = inputValue; // 그대로 입력됨
  } else {
    // 입력된 값이 정규식에 맞지 않는 경우
    memberTel2.value = ""; // 입력창을 비워줌
  }
});
