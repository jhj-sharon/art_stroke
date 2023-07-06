<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${contextPath}/resources/css/member/signUp-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <header class="header-style">
       <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
  </header>

  <main class="signUp-main-style">

    <!-- 여기부터 추가 은영 -->
    <section class="signUp-contents-wrap">
     
          
 <form action="signUp" method="post" onsubmit="return signUpValidate()">
 
 <div class="title_area">
  
    <h1>회원가입</h1>
    
 </div>
 
 
 
 
 
 <div class="general_signUp_area"><!--general,checkagree-->
  <div class="input_area">
 
  <input type="hidden" name="emailOptIn" value="<%= request.getParameter("emailOptIn") %>"/>
 <div class="area">
 <span class="fas fa-regular fa-envelope"></span><p style="color:red">*</p>
 <input type="text" id="memberEmail" name="memberEmail" placeholder="이메일">
  <button type="button" id="sendBtn">전송</button>
 </div>
 <span id="emailMessage"><p></p></span>
 <div class="area">
   <span class="fas fa-solid fa-key"></span><p style="color:red">*</p>
 <input type="text" id="cNumber" name="cNumber" placeholder="인증번호">
  <button type="button" id="cBtn">인증확인</button>
 </div>
 <span id="cMessage"><p></p></span>
 <div class="area">
 <span class="fas fa-solid fa-lock"></span><p style="color:red">*</p>
 <input type="password" id="memberPw" name="memberPw" placeholder="비밀번호">
 <span class="fas fa-eye fa-lg"></span>
 </div>
 <span id="pwMessage"><p>영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.</p></span>
 
 <div class="area">
   <span class="fas fa-solid fa-lock"></span><p style="color:red">*</p>
 <input type="password" id="memberPwConfirm" name="memberPwConfirm" placeholder="비밀번호 확인">
 <span class="fas fa-eye fa-lg"></span>
 </div>
 <span id="pwConfirmMessage"><p></p></span>
 <div class="area"><p style="color:red">*</p>
   <input type="text" id="memberNick" name="memberNick" placeholder="닉네임">
   </div>
   <span id="nicknameMessage"><p>영어(대문자 또는 소문자), 숫자, 또는 한글 문자 2~10글자로 이루어져야 합니다.</p></span>
 <div class="area"><p style="color:red">*</p>
 <input type="text" id="memberName" name="memberName" placeholder="이름">
 </div>
 <div class="area">
   <input type="text" id="memberSns" name="memberSns"placeholder="https://를 포함하여 SNS작성">
   </div>
 
 <div class="area"><p style="color:red">*</p> 
   <span class="fa-sharp fa-solid fa-location-dot"></span>
 <input type="text" id="sample6_postcode" name="memberAddr"
 placeholder="우편번호 버튼을 눌러주세요" readonly>
 <button onclick="sample6_execDaumPostcode()" type="button" id="postCodeBtn">우편번호</button>
 </div>
 <div class="area">
 <input type="text" id="sample6_address"  name="memberAddr" placeholder="도로명주소" readonly>
 </div>
 <div class="area">
 <input type="text" id="sample6_detailAddress" name="memberAddr" placeholder="상세주소">
 </div>
<!--  
 <label for="telecom">통신사</label>
 
 <select id="telecom" name="telecom" class="telecom" value="통신사선택">
  <option value="" disabled selected>통신사</option>
  <option value="SKT">SKT</option>
  <option value="KT">KT</option>
  <option value="LGT">LGT</option>
  <option value="SKT알뜰">SKT알뜰</option>
  <option value="KT알뜰">KT알뜰</option>
  <option value="LGT알뜰">LGT알뜰</option>
  </select> -->
 
 
 <div class="area">
   <span class="fa-solid fa-phone"></span><p style="color:red">*</p>
 <input type="text" id="memberTel" name="memberTel" maxlength="12" placeholder="전화번호를 '-' 를 제외하고 입력">
 <button type="button" id="sendSmsBtn">전송</button>
 </div>
 <span id="telMessage"><p>전화번호를 입력해주세요.(- 제외,12자이내)</p></span>
 <div class="area">
   <span class="fas fa-solid fa-key"></span><p style="color:red">*</p>
 <input type="text" id="smsCNumber" name="smsCNumber" placeholder="문자 인증번호">
  <button type="button" id="smsCBtn">인증확인</button>
 </div>
 <span id="smsCMessage"><p></p></span>
 
 <div class="signUpBtn_area">
 <button id="signUpBtn" type="submit">회원가입</button>
 </div>
 </div><!--inputArea-->
 
 
 </div>
 
 
 
 
 
 
 
 
 
 
 
 </form>
       
       
 
        </section>
 
 </main>

  <footer class="footer-style">
        <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
  </footer>
  <!--폰트어썸-->
  <script src="https://kit.fontawesome.com/b4175ce804.js" crossorigin="anonymous"></script>
     <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   	<!--main.js-->
	<script src="${contextPath}/resources/js/main.js"></script>
    <!-- signUp.js 연결 -->
    <script src="${contextPath}/resources/js/member/signUp.js"></script>
   
</script> 

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
             

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }


                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

</script>
<script>
  //input창누르면 주소팝업
const memberAddr1 = document.getElementById("sample6_postcode");
  memberAddr1.addEventListener("click", function() {
    sample6_execDaumPostcode();
  });
</script>

</body>
</html>