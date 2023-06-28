<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="order" value="${order}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<link rel="stylesheet" href="../resources/css/product/productConfirm.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">

<title>주문완료</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	
	<main class="main-style">

		
		<section class="cart-contents-wrap">
			<div class="cart-header">
				<ol class="product-stage">
					<li class="stage-cart">01 SHOPPING BAG
						<i class="fa-solid fa-chevron-right" style="color: #888;"></i>
					</li>
					<li class="stage-order">02 ORDER
						<i class="fa-solid fa-chevron-right" style="color: #888;"></i>
					</li>
					<li class="stage-confirm">03 ORDER CONFIRMED
	
					</li>
				</ol>
			</div>
	
			<hr style="border-bottom: 1px solid lightgrey; margin-bottom: 40px;">

		<section class="confirm-message-wrapper">
			<div class="confirm-container">
				<div class="confirm-img">
					<img src="${contextPath}/resources/img/productMain/greycheck.png" style="width: 180px;">
				</div>
				<div class="confirm-message">
					<div class="message-header">
						<h1>고객님의 주문이 완료되었습니다.</h1>
						<p>주문내역 및 배송에 관한 안내는 마이페이지 <a>주문조회</a>를 통하여 확인 가능합니다.</p>
					</div>
					<div class="order-detail">
						<div class="info1" id="orderId">주문번호 : <span>${order.orderId}</span></div>
						<div class="info1" id="orderDate">주문일자 : <span>${order.orderDate}</span></div>
						<div class="info1" id="orderPaymethod">주문수단 : <span>${order.paymethod}</span></div>
					</div>
				</div>
			</div>

		</section>
		<section class="confirm-info-wrapper">
			<h2>| 결제정보</h2>
			<div class="order-table-container">
			<table id="order-table">
				<tr>
					<td>최종결제금액</td>
					<td id="order-price"><span>${order.totalPrice}</span>원</td>
				</tr>
				<tr>
					<td>결제수단</td>
					<td class="order-detail">
						<c:choose>
						  <c:when test="${order.paymethod eq 'vbank'}">
							<!-- vbank에 대한 특정 내용 -->
							<div class="detail-css">무통장 입금</div>
							<div class="detail-css">입금자: 전현정</div>
							<div class="detail-css">국민은행 753502-01-415364((주)코드스트로크)</div>
							
						  </c:when>
						  <c:when test="${order.paymethod eq 'kakaopay'}">
							<!-- kakaopay에 대한 특정 내용 -->
							<div class="detail-css">카카오페이 결제</div>
							<div class="detail-css">결제자: 전현정</div>
						
						  </c:when>
						  <c:when test="${order.paymethod eq 'card'}">
							<!-- card에 대한 특정 내용 -->
							<div class="detail-css">신용카드 결제</div>
							<div class="detail-css">결제자: 전현정</div>
							
						  </c:when>
						  <c:otherwise>
							  <div class="detail-css">결제수단을 선택하지 않았습니다.</div>
						</c:otherwise>
						</c:choose>
					  </td>
				</tr>
			</table>
		</div>

	
			<div class="confirm-btn-container">
				<button id="continue-btn">
					CONTINUE SHOPPING	
				</button>
			</div>
		</section>
	
	</main>


	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>

	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/product/productConfirm.js"></script>
</body>
</html>