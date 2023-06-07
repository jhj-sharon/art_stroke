<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../resources/css/myPage/myPageAddrList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<title>배송지 관리</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>

	<main class="main-style">
		<section class="contents-wrap">
			<h4>| 배송 주소록 관리</h4>
			<p>자주 쓰는 배송지를 등록 관리하실 수 있습니다.</p>
			<div class="myPageAddrList">
				<table>
					<thead>
						<tr>
							<th>주소록</th>
							<th>배송지명</th>
							<th>수령인</th>
							<th>주소</th>
							<th>삭제</th>
						</tr>
					</thead>

					<tbody>

					</tbody>
				</table>
			</div>
			<div class="myPageAddrwrap">
				<h4>배송 주소록 유의사항</h4>
				<button class="myPage-button" onclick="openPopup()">배송지 등록</button>
				<div id="popup" class="popup-overlay">
					<div class="popup-content">
								<h4>| 주소등록</h4>
						<form action="addrReg" method="post">
								<div class="popup-table">
									<table>
										<tr>
											<td>배송지명</td>
											<td><input type="text" id="addrName" name="addrName"
												placeholder="배송지 명" maxlength="30" autocomplete="off"
												required></td>
										</tr>
										<tr>
											<td>성명</td>
											<td><input type="text" id="receiverName"
												name="receiverName" placeholder="성명" maxlength="30"
												autocomplete="off" required></td>
										</tr>
										<tr>
											<td rowspan="3"><label for="memberAddress">주소</label></td>
											<td><input type="text" id="sample4_postcode"
												name="postcode" placeholder="우편번호" maxlength="6" readonly>
											<button type="button" class="myPage_AddrBtn"
													onclick="sample4_execDaumPostcode()">검색</button></td>
												
										</tr>
										<tr>
											<td colspan="2"><input type="text"
												id="sample4_roadAddress" name="roadAddress"
												placeholder="도로명주소" readonly></td>
										</tr>
										<tr>

											<td colspan="2"><input type="text"
												id="sample4_detailAddress" name="detailAddress"
												placeholder="상세주소"></td>
										</tr>
										<tr>
											<td><label for="memberTel"><span
													class="required"></span>전화번호</label></td>
											<td><input type="text" id="memberTel" name="memberTel"
												placeholder="(- 없이 숫자만 입력)" maxlength="11"></td>
											<td><span class="modify-message" id="telMessage"
												style="font-size: 8px">전화번호를 입력해주세요.(-제외)</span></td>
										</tr>
									</table>
								</div>
								<div class="popupBtn-wrap">
									<button class="myPage-btn" id="Send">등록하기</button>
									<button class="myPage-btn" onclick="closePopup()">취소</button>
								</div>
							</form>
					</div>
				</div>
			</div>
			<div>
				<ul>
					<li>배송 주소록은 최대 10개까지 등록할 수 있으며, 별도로 등록하지 않을 경우 최근 배송 주소록 기준으로
						자동 업데이트 됩니다.</li>
					<li>자동 업데이트를 원하지 않을 경우 주소록 고정 선택을 선택하시면 선택된 주소록은 업데이트 대상에서
						제외됩니다.</li>
					<li>기본 배송지는 1개만 저장됩니다. 다른 배송지를 기본 배송지로 설정하시면 기본 배송지가 변경됩니다.</li>
				</ul>
			</div>

		</section>
	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageAddrList.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
</body>
</html>