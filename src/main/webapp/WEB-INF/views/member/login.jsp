<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/login-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">

<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

	
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

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
					<button id="loginBtn">로그인하기</button>
				</div>
			
				<div class="snsLogin_area" >
					<h3>SNS 계정으로 로그인/회원가입</h3>
					<div class="kakaoLoginDiv" >
						<button class="kakaoBtn" id="kakao_id_login" onclick="redirectToKakao()" type="button">
						<svg width="18" height="17" viewBox="0 0 18 17" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M9 0C4.0294 0 0 3.09858 0 6.92081C0 9.39196 1.68456 11.5603 4.21858 12.7847C4.08072 13.2484 3.33268 15.7676 3.30291 15.9656C3.30291 15.9656 3.285 16.1144 3.38374 16.1711C3.48248 16.2277 3.59862 16.1837 3.59862 16.1837C3.88177 16.1452 6.88214 14.0897 7.40137 13.7327C7.92017 13.8044 8.45446 13.8416 9 13.8416C13.9706 13.8416 18 10.7431 18 6.92081C18 3.09858 13.9706 0 9 0Z" fill="black"></path></svg><p>빠른시작</p>
						</button>
						<button class="round" id="naver_id_login" type="button" onclick="redirectToNaver()">
            <img src="${contextPath}/resources/img/member/naver_logo.png" width="50px"; >
						</button>


							<button class="round"  id="google_id_login" type="button" onclick="window.location.href='${contextPath}/member/getGoogleAuthUrl'">
								<img src="${contextPath}/resources/img/member/google_logo.png" width="50px" style="border: 1px solid gray; border-radius: 50%;">

						</button>
					
				</div>
				</div>

				<div class="ment_area">
					<h3>신규 회원가입 혜택</h3>
					<div class="ment-area-img">
						<img src="${contextPath}/resources/img/member/freeDeliveryCupon.png">
						<img src="${contextPath}/resources/img/member/tenDiscountCupon.png">
					</div>
				</div>
				
				
				<div class="sign_area">
					<button type="button" onclick="location.href='${contextPath}/member/terms'" id="signUp-btn">일반 회원가입</button>

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
		
		<!--앱키(보안)-->
		<script src="${contextPath}/resources/js/common/appkey.js"></script>

	<!-- login.js 연결 -->
	<script src="${contextPath}/resources/js/member/login.js"></script>
	<!--main.js-->
	<script src="${contextPath}/resources/js/main.js"></script>
	<!--카카오로그인-->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- 카카오 스크립트 -->

<!-- 네이버로그인-->
<!--<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>  -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
 <script>
function redirectToNaver() {
    location.href = "${naverAuthUrl}";
}

function redirectToKakao() {
    location.href = "${kakaoAuthUrl}";
}


</script>
</body>
</html>