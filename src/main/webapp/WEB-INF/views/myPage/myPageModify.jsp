<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 정보 변경</title>

<link rel="stylesheet"
	href="../resources/css/myPage/myPageModify-style.css">
<link rel="stylesheet" href="../resources/css/style.css">
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
	<main class="main-style">
		<section class="contents-wrap">
			<h4>| 회원 정보 수정</h4>
			<div style="border: 1px solid lightgrey;" class="myPage-table-wrap">
				<form action="modify" method="POST" name="modify-form"
					onsubmit="return modifyValidate()">
					<table>
						<tr>
							<td><label for="memberEmail"><span class="required">*</span>아이디(이메일)</label></td>
							<td><input type="text" id="memberEmail" name="memberEmail"
								placeholder="아이디(이메일)" maxlength="30" autocomplete="off"
								required></td>
						</tr>
						<tr>
							<td><label for="memberName"><span class="required">*</span>이름</label></td>
							<td><input type="text" id="memberName" name="memberName"
								placeholder="이름" maxlength="30" autocomplete="off" required></td>
						</tr>
						<tr>
							<td><label for="memberPw"><span class="required">*</span>비밀번호</label></td>
							<td><input type="password" id="memberPw" name="memberPw"
								placeholder="비밀번호" maxlength="30"></td>
						</tr>
						<tr>
							<td><label for="memberPw2"><span class="required">*</span>비밀번호
									확인</label></td>
							<td><input type="password" id="memberPwConfirm"
								placeholder="비밀번호 확인" maxlength="30"></td>
							<td><span class="modify-message" id="pwMessage"
								style="font-size: 8px">영문 대소문자/숫자/특수문자 중 2가지 이상 조합,
									8자~16자</span></td>
						</tr>

						<tr>
							<td><label for="memberNickname"><span
									class="required">*</span>닉네임</label></td>
							<td><input type="text" id="memberNickname"
								name="memberNickname" placeholder="닉네임" maxlength="10"></td>
							<td><span class="modify-message" id="nicknameMessage"
								style="font-size: 8px">영어/숫자/한글 2~10글자 사이로 작성해주세요.</span></td>
						</tr>

						<tr>
							<td><label for="memberTel"><span class="required">*</span>전화번호</label></td>
							<td><input type="text" id="memberTel" name="memberTel"
								placeholder="(- 없이 숫자만 입력)" maxlength="11"></td>
							<td><span class="modify-message" id="telMessage"
								style="font-size: 8px">전화번호를 입력해주세요.(-제외)</span></td>
						</tr>

						<tr>
							<td rowspan="3"><label for="memberAddress">주소</label></td>
							<td><input type="text" id="sample4_postcode"
								name="memberAddress" placeholder="우편번호" maxlength="6" readonly></td>
							<td><button type="button" class="myPage_AddrBtn"
									onclick="sample4_execDaumPostcode()">검색</button></td>
						</tr>
						<tr>

							<td colspan="2"><input type="text" id="sample4_roadAddress"
								name="memberAddress" placeholder="도로명주소" readonly></td>
						</tr>
						<tr>

							<td colspan="2"><input type="text"
								id="sample4_detailAddress" name="memberAddress"
								placeholder="상세주소"></td>
						</tr>
					</table>
					<div class="myPage-modify-button">
						<button type="submit" class="myPage-button" id="modify-btn">변경하기</button>
						<button type="button" class="myPage-button" id="btn">취소</button>
					</div>
				</form>
			</div>
			<button type="button" class="myPage-button" id="myPage-secession">탈퇴하기</button>
			<div class="background">
				<div class="window">
					<div class="popup">
						<form action="">
							<h4>| 탈퇴 사유</h4>
							<div class="secessionSelect">
								<select name="option1" id="option1">
									<option value="S">-- 선택하세요</option>
									<option value="S">노잼</option>
									<option value="M">안이쁨</option>
									<option value="L">구매의사 없음</option>
								</select>
							</div>
							<div class="secessionRule">
								<ul>
									<li>재가입 불가</li>
									<li>후회해도 소용없음</li>
									<li>진짜 탈퇴할꺼?</li>
								</ul>
							</div>
							<div class="secessionCheck">
								<input type="checkbox"> 안내사항을 모두 읽었으며 동의합니다.
							</div>
							<div class="secessionBtn-wrap">
								<button class="myPage-btn" id="secession">탈퇴하기</button>
								<button class="myPage-btn" id="close">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>

	<script src="${contextPath}/resources/js/myPage/myPageModify.js"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>