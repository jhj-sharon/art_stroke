<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="myOrderInfo" value="${myOrderInfo}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="../resources/css/myPage/myPageOrderList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
<title>주문상세페이지</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<main class="main-style">

		<section class="contents-wrap">
			<h4>| 주문 상세 정보</h4>
			<div class="myPageDescription">문자로 전송된 운송장 번호를 사용하여 아래 배송조회를 이용할 수 있습니다.</div>
			<div class="myPageOrderList-wrap">
				<table id="my-table">
					<thead>
						<tr>
							<th></th>
							<th>주문일자</th>
							<th>주문번호</th>
							<th>상품이미지</th>
							<th>상품정보</th>
							<th>배송정보</th>
							<th>리뷰</th>
							<th>구매취소</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${empty myOrderInfo}">
								<tr>
									<td colspan="6" rowspan="6"><div class="noItemWrap">
											<p class="noitem">주문상품이 없습니다.</p>
										</div></td>
								</tr>

							</c:when>
							<c:otherwise>
								<c:forEach items="${myOrderInfo}" var="myOrderInfo">
									<tr>
										<td></td>
										<td>${myOrderInfo.orderDate}</td>
										<td>${myOrderInfo.orderId}</td>
										<td><img src="../${myOrderInfo.productImage}" width=80px;
											height="80px"></td>
										<td><a
										href="/stroke/product/productDetail?product_id=${myOrderInfo.productId}">${myOrderInfo.productName}<br>${myOrderInfo.optionInfo},
											${myOrderInfo.quantity}개
										</a></td>
										<td>${myOrderInfo.orderStatus}</td>
										<c:choose>
											<c:when test="${myOrderInfo.orderStatus eq '취소신청' || myOrderInfo.orderStatus eq '취소승인'}">
												<td><button class="orderlistPageBtn2" onclick="reviewStatus()">리뷰</button></td>
											</c:when>
											<c:when test="${myOrderInfo.reviewStatus eq 'N'}">
												<td><button class="orderlistPageBtn" onclick="openPopup('${myOrderInfo.productName}', '${myOrderInfo.orderDetailId}')">리뷰</button></td>
											</c:when>
											<c:otherwise>
												<td><button class="orderlistPageBtn2" onclick="reviewStatus()">리뷰</button></td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${myOrderInfo.orderStatus eq '결제완료'}">
												<td><button class="orderlistPageBtn"
													onclick="openPopup2('${myOrderInfo.orderId}')">구매취소</button></td>
											</c:when>
											<c:otherwise>
												<td><button class="orderlistPageBtn2" onclick="cancelStatus()">구매취소</button></td>
											</c:otherwise>
										</c:choose>
										
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div id="popup" class="popup-overlay">
				<div class="popup-content">
					<h4>| 리뷰페이지</h4>
					<form action="reviewInsert" enctype="multipart/form-data" method="POST">
						<div class="popup-table">
							<table>
								<tr>
									<th>상품명</th>
									<td id="productName"></td>
								</tr>
								<!-- 여기서는  session에 등록된 로그인 계정의 닉네임. -->
								<tr>
									<th>만족도</th>
									<td><fieldset required>
											<input type="radio" name="reviewStar" value=5 id="rate1"><label
												for="rate1">★</label> 
												<input type="radio" name="reviewStar"
												value = 4 id="rate2"><label for="rate2">★</label>
												<input type="radio" name="reviewStar" value=3 id="rate3"><label
												for="rate3">★</label>
												<input type="radio" name="reviewStar"
												value=2 id="rate4"><label for="rate4">★</label>
												<input type="radio" name="reviewStar" value= 1 id="rate5"><label
												for="rate5">★</label>
										</fieldset></td>
								</tr>
								<tr>
									<th>사진 첨부</th>
									<td><input type="file" name="reviewImg" id="input-image"
										accept="image/*" required></td>
								</tr>
								<tr>
									<th>리뷰작성</th>
									<td><textarea id="reviewContent" class="reviewContent"
											name="reviewContent" required></textarea></td>
								</tr>
							</table>
						</div>
						
						<input type="hidden" id="orderDetailId" name="orderDetailId" value="">
						<div class="popupBtn-wrap">
							<button class="myPage-btn" id="Send" type="submit">등록하기</button>
							<button class="myPage-btn" type="button" onclick="closePopup()">취소</button>
						</div>
					</form>
				</div>
			</div>
			<div id="popup2" class="popup-overlay2">
				<div class="popup-content2">
					<h4>| 상품취소</h4>
					<p>*해당 주문번호에 해당하는 상품들 전부 취소됩니다.</p>
					<form action="cancelOrder">
						<div class="popup-table2">
							<table>
								<tr>
									<th>주문번호</th>
									<td id="orderId"></td>
								</tr>
								<tr>
									<th>취소사유</th>
									<td><textarea id="cancelReason" class="cancelReason"
											name="cancelReason"></textarea></td>
								</tr>
							</table>
						</div>
						<input type="hidden" id="hiddenOrderId" name="orderId" value="">
						<div class="popupBtn-wrap">
							<button class="myPage-btn" id="Send" type="submit">등록하기</button>
							<button class="myPage-btn" type="button" onclick="closePopup2()">취소</button>
						</div>
					</form>
				</div>
			</div>
			<div class="orderEnd-wrap">
				<div class="pagination-area1"></div>
				<div class="pagination-area">
					<div class="pagination" style="height:20px; margin-top:20px;">
						<i class="fa-solid fa-arrow-left"  style="color: #222222;"></i>
						<ol id="numbers">
						</ol>
						<i class="fa-solid fa-arrow-right" style="color: #222222;"></i>
					</div>
				</div>
				<div class="myPageOrderList-wrap2">
					<button class="myPage-button" onclick="openPopup3()">배송 조회</button>
				</div>
			</div>
			
			

			<div id="popup3" class="popup-overlay3">
				<div class="popup-content3">
				    <div class="myPage-popupTag">
					  <h4>| 배송 조회</h4>
					  <div class="close" onclick="closePopup3()">&times;</div>
				    </div>
					<div class="orderForm">
						<form action="http://info.sweettracker.co.kr/tracking/3" method="post">
							<input type="hidden" id="deliveryKey" class="form-control" id="t_key" name="t_key" value="">
							<input type="hidden" class="form-control" name="t_code" id="t_code" value="04">
							<div class="orderFormImg">
								<img class="orderImg"src="${contextPath}/resources/img/cj.png" alt="배송이미지">
							</div>
							<div class="orderCheckNum"for="t_invoice"><span class="redStar">*</span>운송장 번호를 입력 해주세요</div>
							<input type="text" class="form-control" name="t_invoice" id="t_invoice" placeholder="운송장 번호">
							<button type="submit" class="orderlistPageBtn">조회하기</button>
						</form>
					</div>
				</div>
			</div>
		</section>


	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>

	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/common/appkey.js"></script>
	<script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageOrderList.js"></script>
</body>
</html>