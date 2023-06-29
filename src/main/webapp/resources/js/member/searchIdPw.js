
  console.log("main.load");

/*tbody */
//이메일 찾기
const table = document.querySelector("table");
const pwTbody = document.querySelector(".pwTbody");




//비밀번호 찾기


const FindPw_Email = document.getElementById("FindPw_Email");
// FindPw_Email.addEventListener("change", function() {
//   const memberName = document.getElementById("memberName").value;
//   const memberEmail = document.getElementById("memberEmail").value;
  
//   pwTbody.innerHTML = `
//     <tr>
//       <th>이름</th>
//       <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
//     </tr>
//     <tr>
//       <th>이메일</th>
//       <td><input type="text" id="memberEmail" name="memberEmail" style="width:200px;"></td>
//     </tr>
//   `;
// });

const FindPw_Tel = document.getElementById("FindPw_Tel");
// FindPw_Tel.addEventListener("change", function() {
//   const memberName = document.getElementById("memberName").value;
//   const memberTel = document.getElementById("memberTel").value;
  
//   pwTbody.innerHTML = `
//     <tr>
//       <th>이름</th>
//       <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
//     </tr>
//     <tr>
//       <th>전화번호</th>
//       <td><input type="text" id="memberTel" name="memberTel" style="width:200px;"></td>
//     </tr>
//   `;
// });

// $.ajax({
//   url: "searchIdPw",
//   type: "POST",
//   data: {
//     memberName: memberName, // memberName 값을 가져와서 전달
//     memberEmail: memberEmail,
//     memberTel: memberTel,
//   },
//   success: function(response) {
//     if (response === "searchEmailModal") {
//       // Handle logic for searchEmailModal view
//       console.log("searchEmailModal");
//     } else if (response === "searchPwModal") {
//       // Handle logic for searchPwModal view
//       console.log("searchPwModal");
//     } else {
//       // Handle logic for other views or scenarios
//       console.log("Other response: " + response);
//     }
//   },
//   error: function(error) {
//     // Handle error
//     console.log(error);
//   }
// });

// 이메일 찾기 버튼
const searchEmailBtn = document.getElementById("searchEmailBtn");
// 비밀번호 찾기 버튼
const searchPwBtn = document.getElementById("searchPwBtn");

const memberName=document.getElementById("memberName");
const memberTel=document.getElementById("memberTel");


function searchEmailValidate() {
  const memberName = document.getElementById("memberName").value.trim();
  const memberTel = document.getElementById("memberTel").value.trim();

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
      memberName: memberName.value,
      memberTel: memberTel.value,
    },
    success: function (emailResult) {
      if (emailResult !== "") {
        // 이메일을 성공적으로 찾은 경우, 모달 창 띄우기
        openModal(emailResult); // 모달 창에 이메일 값을 전달하여 처리
      } else {
        alert("해당되는 회원정보가 없습니다.");
      }
    },
    error: function (xhr, status, error) {
      // 에러 처리
      console.log("AJAX Error: " + error);
    },
  });
});



searchPwBtn.addEventListener("click", function (event) {

  event.preventDefault(); 

  // AJAX 요청
  $.ajax({
    url: "searchIdPw/pw", // URL 경로 앞에 슬래시(/)를 추가하여 절대 경로로 지정합니다.
    type: "GET",
    data: {
      memberName: memberName.value,
      memberTel: memberTel.value,
    },
    success: function (pwResult) {
      if (pwResult>0) {
        // 이메일을 성공적으로 찾은 경우, 모달 창 띄우기
        openModal();
      } else {
        var message = "${message}"; // 모델에서 전달된 메시지 값 가져오기
        alert(message);
      }
    },
    error: function (xhr, status, error) {
      // 에러 처리
      console.log("AJAX Error: " + error);
     
    },
  });
});



// 모달 창 열기
// function openModal(emailResult) {
//   var modal = document.getElementById("myModal");
//   var emailResultElement = document.getElementById("emailResult");
//   emailResultElement.textContent = emailResult;

//   // 모달 창 표시
//   modal.style.display = "block";

//   // 모달 창 닫기 버튼 클릭 시
//   var closeBtn = document.getElementsByClassName("close")[0];
//   closeBtn.onclick = function () {
//     modal.style.display = "none";
//   };

//   // 모달 외부 영역 클릭 시 닫기
//   window.onclick = function (event) {
//     if (event.target == modal) {
//       modal.style.display = "none";
//     }
//   };
// }

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