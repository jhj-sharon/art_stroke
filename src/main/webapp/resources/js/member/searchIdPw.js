
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
