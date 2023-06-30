<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
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
<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf-8">
	sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
</script>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<main class="main-style">
		<section class="contents-wrap">
			<h4>| 회원 정보 수정</h4>
			<div style="border: 1px solid lightgrey;" class="myPage-table-wrap">
				<form action="modify" method="POST" id="modifyForm" name="modify-form"
					onsubmit="return modifyValidate()">
					<table>
						<tr>
							<td><label for="memberEmail"><span class="required">*</span>아이디(이메일)</label></td>
							<td><input type="text" id="memberEmail" name="memberEmail"
								placeholder="아이디(이메일)" maxlength="30" autocomplete="off"
								value="${loginMember.memberEmail}" readonly></td>
						</tr>
						<tr>
							<td><label for="memberName"><span class="required">*</span>이름</label></td>
							<td><input type="text" id="memberName" name="memberName"
								maxlength="30" autocomplete="off"
								value="${loginMember.memberName}" readonly></td>
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
								style="font-size: 8px">영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로
									작성해주세요.</span></td>
						</tr>

						<tr>
							<td><label for="memberNickname"><span
									class="required">*</span>닉네임</label></td>
							<td><input type="text" id="memberNickname"
								name="memberNickname" placeholder="닉네임"
								value="${loginMember.memberNick}" maxlength="10"></td>
							<td><span class="modify-message" id="nicknameMessage"
								style="font-size: 8px">영어/숫자/한글 2~10글자 사이로 작성해주세요.</span></td>
						</tr>

						<tr>
							<td><label for="memberTel"><span class="required">*</span>전화번호</label></td>
							<td><input type="text" id="memberTel" name="memberTel"
								value="${loginMember.memberTel}" placeholder="(- 없이 숫자만 입력)"
								maxlength="11"></td>
							<td><span class="modify-message" id="telMessage"
								style="font-size: 8px">전화번호를 입력해주세요.(-제외)</span></td>
						</tr>

						<tr>
							<td rowspan="3"><label for="memberAddress">주소</label></td>
							<td><input type="text" id="sample4_postcode"
								name="updateAddress" placeholder="우편번호" maxlength="6" readonly></td>
							<td><button type="button" class="myPage_AddrBtn"
									onclick="sample4_execDaumPostcode()">검색</button></td>
						</tr>
						<tr>
							<td colspan="2"><input type="text" id="sample4_roadAddress"
								name="updateAddress" placeholder="도로명주소" readonly></td>
						</tr>
						<tr>
							<td colspan="2"><input type="text"
								id="sample4_detailAddress" name="updateAddress"
								placeholder="상세주소"></td>
						</tr>
						<tr>
							<td><label for="check">이메일 수신 여부</label></td>

							<td>
								<input type="radio" id="receive" name="emailOptIn" value="Y" ${loginMember.emailOptIn == 'Y' ? 'checked' : ''}>
								<label for="receive" class="emailradiobtn">수신</label>
								<input type="radio" id="notReceive" name="emailOptIn" value="N" ${loginMember.emailOptIn == 'N' ? 'checked' : ''}>
								<label for="notReceive" class="emailradiobtn">미수신</label>
							</td>
							<td><span class="modify-message" style="font-size: 8px">쇼핑몰에서
									제공하는 유익한 이벤트 소식을 이메일로 받으실 수 있습니다.</span></td>
						</tr>
						<tr>
							<td><label for="memberSNS">SNS</label></td>
							<td colspan="2"><input type="text" id="memberSNS"
								name="memberSns" placeholder="SNS" value="${loginMember.memberSns}"></td>
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
						<form action="secession" method="POST" onsubmit="return secessionValidate()">
							<h4>| 탈퇴 사유</h4>
							<div class="secessionSelect">
								<select name="option1" id="option1">
									<option value="0">상품가격이 비싸다.</option>
									<option value="1">상품종류가 부족하다.</option>
									<option value="2">상품가격에 비해 품질이 떨어진다.</option>
									<option value="3">배송이 느리다.</option>
									<option value="4">상담원 고객응대가 불친절하다.</option>
									<option value="5">쇼핑몰 혜택이 부족하다.</option>
									<option value="6">이용빈도가 낮다.</option>
									<option value="7">개인정보 유출이 걱정된다.</option>
									<option value="8">기타</option>
								</select>
							</div>
							<div class="secessionRule">
								<pre class="secessionRule-div">
art stroke 회원 탈퇴 약관

제 1 조 (목적)
본 약관은 art stroke (이하 '회사')와 회원 사이의 회원 탈퇴 절차 및 권리, 의무를 정하는 것을 목적으로 합니다.

제 2 조 (회원 탈퇴 절차)

회원은 회원 탈퇴를 원할 경우, art stroke 웹사이트 또는 앱 내의 '회원 탈퇴' 페이지를 방문하여 탈퇴 절차를 진행해야 합니다.
회원 탈퇴 절차를 완료하기 위해 회원은 개인 식별 정보를 제공해야 하며, 회사는 이를 확인하여 회원 탈퇴를 처리합니다.
회원 탈퇴는 회사의 승인을 거쳐 완료됩니다. 탈퇴 요청 후 일정 기간 동안 회사의 확인 및 처리를 기다려야 합니다.

제 3 조 (회원 탈퇴의 효과)

회원 탈퇴가 완료되면, 회원은 [art_stroke]에 대한 모든 권한과 서비스 이용 기록이 삭제됩니다.
회원 탈퇴 후에는 재가입을 위해 새로운 회원 가입 절차를 진행해야 합니다.

제 4 조 (회원 탈퇴 시의 유의사항)

회원은 탈퇴 시 본인이 등록한 개인 정보를 삭제해야 하는 의무가 있으며, 회사는 이를 적절히 관리하여야 합니다.
탈퇴 이후에도 회사의 서비스와 관련된 법적 의무 및 분쟁 해결을 위한 목적으로 개인 정보는 보존될 수 있습니다.

제 5 조 (손해배상)

회원이 본 약관에 위배되거나 불법적인 행위로 회원 탈퇴를 요청할 경우, 회사는 이에 대한 책임을 면할 수 있습니다.

제 6 조 (약관의 변경)

회사는 필요한 경우 약관을 변경할 수 있으며, 변경된 약관은 회사 웹사이트나 앱 내의 공지사항을 통해 회원에게 통지됩니다.

제 7 조 (분쟁의 해결)

본 약관에 따른 회원 탈퇴와 관련된 분쟁은 회사와 회원 간의 합의에 의해 원만히 해결되도록 합니다. 
합의가 이루어지지 않을 경우, 관련 법령에 따라 분쟁을 해결합니다.
								</pre>
								
				</div>
							<div class="secessionCheck">
								<input type="checkbox" id="agree" required> 안내사항을 모두 읽었으며 동의합니다.
							</div>
							<div class="secessionBtn-wrap">
								<button class="myPage-btn" id="memberOut">탈퇴하기</button>
								<button type="button" class="myPage-btn" id="close">취소</button>
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
	<!-- 주소 가져오는 js! -->
	<script>
		const addr = "${loginMember.memberAddr}";
		const addrArr = addr.split(",,");
		document.getElementById("sample4_postcode").value = addrArr[0];
		document.getElementById("sample4_roadAddress").value = addrArr[1];
		document.getElementById("sample4_detailAddress").value = addrArr[2];
	</script>
	<script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageModify.js"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>