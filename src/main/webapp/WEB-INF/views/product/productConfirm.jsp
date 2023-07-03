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
						<p>주문내역 및 배송에 관한 안내는 마이페이지 <a href="${contextPath}/myPage/myPageOrderList" class="linkMypage">주문조회</a>를 통하여 확인 가능합니다.</p>
					</div>
					<div class="order-detail">
						<div class="info1" id="orderId">주문번호 : <span>${order.orderId}</span></div>
						<div class="info1" id="orderDate">주문일자 : <span>${order.orderDate}</span></div>
						<div class="info1" id="orderPaymethod">주문수단 : <span>${order.paymethod}</span></div>
						<div class="info1" id="order-pricep">최종결제금액 : <span>${order.totalPrice}</span>원</div>
					</div>
				</div>
			</div>

		</section>
		<section class="confirm-info-wrapper">
			

	
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