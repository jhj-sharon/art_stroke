<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/login-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
	

</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>

	<main class="login-main-style">

		<!-- 여기부터 추가 -->
		<section class="login-contents-wrap">

			<form action="login" method="post" name="login"
				onsubmit="return loginValidate()">
				
				<h1>로그인</h1>
				<fieldset id="id-pw_area">
					<input type="text" name="memberEmail" id="inputEmail"
						placeholder="아이디(이메일)">
					<input type="password"
						name="memberPw" id="inputPw" placeholder="비밀번호">
				</fieldset>
				<div class="check_area">
					<p><input class="form-check-input" type="checkbox" id="saveId"
						name="saveId"> <label class="form-check-label"
						for="saveId"> 아이디저장</p>
						<p></label> <a href="${contextPath}/member/searchIdPw">아이디/비밀번호찾기</a></p>
				</div>
				<div class="Btn_area">
					<button id="loginBtn">로그인</button>
				</div>
			
				<div class="snsLogin_area" >
					<h3>SNS 계정으로 로그인하기</h3>
					<div class="kakaoLoginDiv" >
						<button class="kakaoBtn" type="button" onclick="kakaoLogin();"><svg width="18" height="17" viewBox="0 0 18 17" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M9 0C4.0294 0 0 3.09858 0 6.92081C0 9.39196 1.68456 11.5603 4.21858 12.7847C4.08072 13.2484 3.33268 15.7676 3.30291 15.9656C3.30291 15.9656 3.285 16.1144 3.38374 16.1711C3.48248 16.2277 3.59862 16.1837 3.59862 16.1837C3.88177 16.1452 6.88214 14.0897 7.40137 13.7327C7.92017 13.8044 8.45446 13.8416 9 13.8416C13.9706 13.8416 18 10.7431 18 6.92081C18 3.09858 13.9706 0 9 0Z" fill="black"></path></svg><p>빠른시작</p></button>
						<button class="round">
            <img src="${contextPath}/resources/img/member/naver_logo.png" width="44px";>
						</button>
							<button class="round">
							<img src="${contextPath}/resources/img/member/facebook_logo.png" width="44px";>
						</button>

							<button class="round">
							<img src="${contextPath}/resources/img/member/google_logo.png" width="44px";>
						</button>
					
				</div>
				</div>

				<div class="ment_area"><img src="${contextPath}/resources/img/member/signUpbenefit2.png" style="width:400px"></div>
				<div class="sign_area">
					<button type="button" onclick="location.href='${contextPath}/member/terms'" id="signUp-btn">회원가입</button>

				</div>


				
			</form>
		</section>

	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<!-- jQuery 라이브러리 추가 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>

	<!-- login.js 연결 -->
	<script src="${contextPath}/resources/js/member/login.js"></script>
	<!--main.js-->
	<script src="${contextPath}/resources/js/main.js"></script>
	<!--카카오로그인-->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init(''); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
</script>
<div id="fb-root"></div>
<!--페이스북 로그인-->
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v17.0" nonce="sgpndPzx"></script>
</body>
</html>