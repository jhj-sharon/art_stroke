<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/member/signUp-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
  <header class="header-style">
       <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
  </header>

  <main class="signUp-main-style">

      <!-- 여기부터 추가 은영 -->
      <section class="signUp-contents-wrap">
       
            
  <form action="signUp" method="post" onsubmit="return signUpValidate()">
  <h1>회원가입</h1>
  <div class="sns_signUp_area">
    <button class="round">
      <img src="${contextPath}/resources/img/member/naver_logo.png" width="44px";>
      </button>
      <button class="round">
        <img src="${contextPath}/resources/img/member/kakao_logo.png" width="44px";>
        </button>
        <button class="round">
          <img src="${contextPath}/resources/img/member/facebook_logo.png" width="44px";>
          </button>
          <button class="round">
            <img src="${contextPath}/resources/img/member/google_logo.png" width="44px";>
						</button>
  </div>





  <div class="general_signUp_area"><!--general,checkagree-->
  <label>이메일</label>
  
  <div>
    <input type="text" id="memberEmail" name="memberEmail">
    <button>인증번호 보내기</button>
  </div>
  <span>이메일형식이 일치하지 않습니다.</span>
  <label>인증번호</label>
  <div>
    <input type="text" id="cNumber" name="cNumber">
    <button>인증확인</button>
  </div>
  <label>비밀번호</label>
  <div>
  <input type="password" id="memberPw" name="memberPw">
</div>
<span>비밀번호 형식이 맞지 않습니다.</span>
<label>비밀번호 확인</label>
<div>
  <input type="password" id="memberPwContfirm" name="memberPwConfirm">
</div>
<span>비밀번호가 일치하지 않습니다.</span>
<label>이름</label>
<div>
  <input type="text" id="memberName" name="memberName">
</div>
<label>닉네임</label>
<div>
  <input type="text" id="memberNick" name="memberNick">
</div>
<span>닉네임 형식에 맞지 않습니다.</span>
<label>주소</label>
<div>
  <input type="text" id="zoneCode" name="memberAddr"
  placeholder="우편번호" maxlength="6" class="testtest">
  <button>우편번호</button>
</div>
<div>
  <input type="text" id="roadAddr"  name="memberAddr" placeholder="도로명주소">
</div>
<div>
  <input type="text" id="detailAddr" name="memberAddress" placeholder="상세주소">
</div>
<label>통신사</label>
<div>
  <select id="telecom" name="telecom" class="telecom" value="통신사선택">
    <option value="SKT">SKT</option>
    <option value="KT">KT</option>
    <option value="LGT">LGT</option>
    <option value="SKT알뜰">SKT알뜰</option>
    <option value="KT알뜰">KT알뜰</option>
    <option value="LGT알뜰">LGT알뜰</option>
    </select>
</div>
<label>전화번호</label>
<div>
  <select id="mobile1" name="memberTel" class="memberTel">
    <option value="010">010</option>
    <option value="011">011</option>
    <option value="016">016</option>
    <option value="017">017</option>
    <option value="018">018</option>
    <option value="019">019</option>
    </select>-<input type="text" class="memberTel" name="memberTel"  maxlength="4">-<input type="text"  class="memberTel" name="memberTel"  maxlength="4">
</div>














  <div class="signUpBtn_area">
    <button id="signUpBtn">회원가입</button>
    </div>
</form>
         
         

          </section>

  </main>

  <footer class="footer-style">
        <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
  </footer>
     <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   	<!--main.js-->
	<script src="${contextPath}/resources/js/main.js"></script>
    <!-- signUp.js 연결 -->
    <script src="${contextPath}/resources/js/member/signUp.js"></script>
   <!---->
   <!-- <script type="text/javascript"> 
    var bDisplay = true; function doDisplay(){ 	
        var con = document.getElemen("conent"); 	
        if(con.style.display=='none'){ 		
            con.style.display = 'block'; 	
        }else{ 		
            con.style.display = 'none'; 	
        } 
    }  -->
</script> 
</body>
</html>