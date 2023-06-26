<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="myCoupon" value="${myCoupon}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${contextPath}/resources/css/myPage/myPageCouponList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
	<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<title>쿠폰 목록</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>

	<main class="main-style">
		<section class="contents-wrap">
				<h4>| 내 쿠폰 목록</h4>
			<div class="myPageCoupon-wrap">
				<table>
					<thead>
						<tr>
							<th>쿠폰번호</th>
							<th>쿠폰이름</th>
							<th>쿠폰정보</th>
							<th>쿠폰기간</th>
							<th>구매금액</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty myCoupon}">
								<tr>
									<td colspan="6" rowspan="6"><div class="noItemWrap"><p class="noitem">최근 본 상품이 없습니다.</p></div></td>
								</tr>
								
							</c:when>
							<c:otherwise>
								<c:forEach items="${myCoupon}" var="myCoupon">
									<tr>
										<td>${myCoupon.couponId}</td>
										<td>${myCoupon.couponInfo}</td>
										<td>${myCoupon.couponName}</td>
										<td>${myCoupon.issuanceDate} ~ ${myCoupon.expirationDate}</td>
										<td>2만원 이상</td>
										<td></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</section>

	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	
	<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>