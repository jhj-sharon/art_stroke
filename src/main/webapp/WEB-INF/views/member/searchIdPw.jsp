<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/searchIdPw-style.css">
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

	<main class="searchIdPw-main-style">

		<!-- 여기부터 추가 -->
		<section class="searchIdPw-contents-wrap">

			<tab>
			<ul class="tabnav">
				<li><a href="#tab01">아이디찾기</a></li>
				<li><a href="#tab02">비밀번호찾기</a></li>
			</ul>
			<div class="tabcontent">

				<div id="tab01">


					<h3>*가입하셨던 방법으로 이메일 찾기가 가능합니다.</h3>
					<div class="selectWay_area">
						가입방법 : Tel<input type="radio" id="FindEmail_Tel" name="searchType">
						</p>
					</div>
					<form action="searchIdPw" method="post" onsubmit="return searchEmailValidate()" name="findEmail">
            <div class="searchEmail_area">
              <table>
                <tr>
                  <th>이름</th>
                  <td><input type="text" id="memberName" name="memberName" placeholder="이름을 입력하세요" style="width: 200px;"></td>
                </tr>
                <tr>
                  <th>전화번호</th>
                  <td><input type="text" id="memberTel" name="memberTel" style="width: 200px;" placeholder="전화번호를 입력하세요"></td>
                </tr>
              </table>
            </div>
            <div class="searchEmailBtn_area">
              <button id="searchEmailBtn" type="submit">이메일찾기</button>
            </div>
          </form>
          


				</div>
				<div id="tab02">
					<h3>*가입방법으로 비밀번호 찾기가 가능합니다.</h3>
					<div class="selectWay_area">
						가입방법 : Email<input type="radio" id="FindPw_Email"
							name="searchType"> Tel<input type="radio" id="FindPw_Tel"
							name="searchType">
						</p>
					</div>
					<form action="searchIdPw" method="post" validation="return PwValidate()" name="findPw">
						<div class="searchPw_area">
							<table>
                <tbody class="pwTbody">
								<tr>
									<th>이름</th>
									<td><input type="text" id="memberName" name="memberName"
										placeholder="이름을 입력하세요" style="width: 200px;"></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><input type="text" id="memberTel" name="memberTel"
										style="width: 200px;" placeholder="전화번호를 입력하세요"></td>
								</tr>
              </tbody>
							</table>
						</div>
						<div class="searchEmailBtn_area">
							<button id="searchPwBtn" type="submit">비밀번호찾기</button>
						</div>
					</form>

				</div>
			</tab>
			<!--tab-->

		</section>
	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<!--main.js-->
	<!-- <script src="${contextPath}/resources/js/main.js"></script> -->
	<script src="${contextPath}/resources/js/member/searchIdPw.js"></script>
	<!-- jQuery 라이브러리 추가 -->

	<script>
		$(function() {
			$('.tabcontent > div').hide();
			$('.tabnav a').click(function() {
				$('.tabcontent > div').hide().filter(this.hash).fadeIn();
				$('.tabnav a').removeClass('active');
				$(this).addClass('active');
				return false;
			}).filter(':eq(0)').click();
		});
	</script>


</body>
</html>

