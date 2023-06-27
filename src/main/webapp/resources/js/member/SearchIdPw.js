
  console.log("main.load");

  /*tbody */
const table = document.querySelector("table");
const emailTbody = document.querySelector(".emailTbody");
const telTbody=document.querySelector(".telTbody");

const FindEmail_Email = document.getElementById("FindEmail_Email");
FindEmail_Email.addEventListener("change", function() {
  emailTbody.innerHTML = `
    <tr>
      <th>이름</th>
      <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
    </tr>
    <tr>
      <th>이메일</th>
      <td><input type="text" class="memberEmail" name="memberEmail" style="width:200px;"></td>
    </tr>
  `;
});

const FindEmail_Tel = document.getElementById("FindEmail_Tel");
FindEmail_Tel.addEventListener("change", function() {
emailTbody.innerHTML = `
    <tr>
      <th>이름</th>
      <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
    </tr>
    <tr>
      <th>전화번호</th>
      <td><input type="text" class="memberTel" name="memberTel" style="width:200px;"></td>
    </tr>
  `;
});



const FindPw_Email=document.getElementById("FindPw_Email");
 FindPw_Email.addEventListener("change", function() {
  telTbody.innerHTML = `
    <tr>
      <th>이름</th>
      <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
    </tr>
    <tr>
      <th>이메일</th>
      <td><input type="text" class="memberEmail" name="memberEmail" style="width:200px;"></td>
    </tr>
  `;
});

const FindPw_Tel = document.getElementById("FindEmail_Tel");
FindEmail_Tel.addEventListener("change", function() {
telTbody.innerHTML = `
    <tr>
      <th>이름</th>
      <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width:200px;"></td>
    </tr>
    <tr>
      <th>전화번호</th>
      <td><input type="text" class="memberTel" name="memberTel" style="width:200px;"></td>
    </tr>
  `;
});







