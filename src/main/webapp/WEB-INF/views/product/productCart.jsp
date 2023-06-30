<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cartList" value="${cartList}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<link rel="stylesheet" href="../resources/css/product/productCart.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">

<title>주문하기</title>
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
	
			<hr style="border-bottom: 1px solid lightgrey; margin-bottom: 20px;">
	
			<!-- 주문 목록 -->
			<div class="cart-item-wrapper" >
				<div class="cart-table-container" >
					<table id="cart-item-table">
						<thead>
							<th>선택</th>
							<th>상품정보</th>
							<th>수량</th>
							<th>주문금액</th>
							<th>배송비</th>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty cartList}">
								<!-- 상품이 있다면 -->
								<!-- 향상된 for문처럼 사용 -->
								<c:forEach var="cart" items="${cartList}" varStatus="status">
									<tr style="justify-content: center;" id="${cart.cartId}">
										<td> <input type="checkbox"></td>
										<td>
											<div class="cart-item-detail">
												<div class="cart-item-img">
													<a href="/stroke/product/productDetail?product_id=${cart.productId}">
													<img src="${contextPath}/${cart.productImage}" style="width: 80px;" alt="">
													</a>
													
												</div>
												<div class="cart-item-info">
													<div class="cart-item-name">${cart.productName}</div>
													<div class="cart-item-option">Option : ${cart.cartOption}</div>
													<div class="cart-item-unit-price">${cart.productPrice}원</div>	
												</div>
											</div>
										</td>
										<td>
											<div class="cart-item-qty">
												<div class="qty-container">
													<button class="qtybtn minus-btn" type="button">-</button>
													<input class="qty" autocapitalize="none" type="text" inputmode="numeric" value="${cart.quantity}">
													<button class="qtybtn plus-btn" type="button">+</button>
												</div>
											</div>
										</td>
										<td>
											<div><span class="cart-item-price">${cart.productPrice}</span>원</div>
										</td>
										<c:if test="${status.first}">
											<td rowspan="${cartList.size()}">3,000원 <br>
												<span class="freeshipment">5만원 이상 <br> 무료배송</span>
											</td>
										</c:if>
									</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
									<!-- 상품이 없다면 -->
									<td colspan="5">장바구니에 상품이 존재하지 않습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>

						</tbody>
							
					</table>
				</div>
				
			</div>
			
			<hr style="border-bottom: 1px solid lightgrey; margin-bottom: 20px;">
	
			<div class="cart-btn-wrapper" style="margin-bottom:50px">
				<button id="delete-btn">
					선택상품삭제
				</button>

				<button id="selectOrder-btn">
					선택상품주문
				</button>
			</div>
			
			<!-- 가격 영역 -->
		<div class="cart-price-wrapper">
		<div class="cart-price-container">
			<div class="cart-price-info">
				<div class="unit-price als"><span>총 주문금액</span></div>
				<div class="delivery-fee als"><span>총 배송비</span></div>
				<div class="total-price als"><span>총 결제금액</span></div>
			</div>
			<div class="cart-price-numeric">
				<div class="unit-price-numeric">
					<span class="cart-text">
						<strong class="cart-numeric itemSum">0</strong>원</span>
				</div>
				<div class="delivery-fee-numeric">
					<i class="fa-solid fa-circle-plus icon-1 fa-lg"></i>
					<span class="cart-text">
						<strong class="cart-numeric deleverySum">0</strong>원</span>
				</div>
				<div class="total-price-numeric">
					<i class="fa-solid fa-equals fa-lg icon-2" ></i>
					<span class="cart-text">
						<strong class="cart-numeric totalSum">0</strong>원</span>
				</div>
			</div>
		</div>
	    </div>

		<div class="cart-check-wrapper">

			<div class="cart-checkout-container">
				<div class="cart-btn continue">
					<a href="${contextPath}/product/productMain2">CONTINUE SHOPPING</a>			
				</div>

				<c:choose>
					<c:when test="${not empty cartList}">
						<div class="cart-btn checkout">
							<a href="#none" onclick="orderSelectedItems()">CHECKOUT</a>				
						</div>
					</c:when>
					<c:otherwise>
						<div class="cart-btn checkout">
							<a href="#" onclick="noProduct()">CHECKOUT</a>				
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
		</section>
	
	
	</main>


	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/product/productCart.js"></script>
</body>
</html>