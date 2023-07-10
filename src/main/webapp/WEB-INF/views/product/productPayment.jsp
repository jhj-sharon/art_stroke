<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="orderItemsList" value="${map.orderItemsList}" />
<c:set var="couponList" value="${map.couponList}" />
<c:set var="addrList" value="${map.addrList}" />
<c:set var="orderNumber" value="${map.orderNumber}" />



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<link rel="stylesheet" href="../resources/css/product/productPayment.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
	<%--아임포트 라이브러리--%>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

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
					<li class="stage-orderItemsList">01 SHOPPING BAG
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

			<!-- order-wrapper: 전체 페이지  -->
			<div class="order-wrapper">

			<!-- 주문정보 전체 영역 -->
				<div class="order-container">
			<!-- 배송정보 -->
					<div class="delivery-info level1" >
						<div class="delivery-info-header">
							<div class="delivery-left"><span style="font-weight: bold; color: rgb(34,34,34);">| 배송정보</span></div>
							<div class="delivery-middle"></div>
							<div class="delivery-right"><span style="color:red">*</span>표시는 필수 입력사항</div>
						</div>
						<div class="addressInfo_div">
							<div class="addressInfo_button_div"  style="border-bottom: 1px solid lightgrey;">
								<button class="address_btn address_btn_1" onclick="showAdress('1')">기존 배송지</button>
								<button class="address_btn address_btn_2" onclick="showAdress('2')">직접 입력</button>
							</div>
							<div class="addressInfo_input_div_wrap">
								<div class="addressInfo_input_div addressInfo_input_div_1" style="display: block">
									<!-- 사용자 지정 배송지 -->
									<table id="delivery-info-table">
										<colgroup>
											<col width="25%">
											<col width="*">
										</colgroup>
										<tbody>
											<tr>
												<th>주문자 성함</th>
												<td id="name">
													<!-- ${memberInfo.memberName} -->
													${loginMember.memberName}
												</td>
											</tr>
											<tr>
												<th>배송지</th>
												<td>
													집
												</td>
											</tr>
											<tr>
												<th>주소</th>
												<td >
													<!-- ${memberInfo.memberAddr1} ${memberInfo.memberAddr2}<br>${memberInfo.memberAddr3}										 -->
													${loginMember.memberAddr}
												</td>
											</tr>
											<tr>
												<th>전화번호</th>
												<td id="phone">
													<!-- ${memberInfo.memberTel}									 -->
													${loginMember.memberTel}
												</td>
											</tr>
											<tr>
												<th>배송메세지</th>
												<td>
                                                     <select class="select1" name="selec1 delivery-message" id="">
														<option value="1">배송시 요청사항을 선택해 주세요.</option>
														<option value="2">부재시 문 앞에 놓아주세요.</option>
														<option value="3">부재시 경비실에 맡겨주세요</option>
														<option value="4">부재시 전화 또는 문자 주세요</option>
														<option value="5">택배함에 넣어주세요.</option>
														<option value="6">파손위험상품입니다. 배송시 주의해주세요</option>
														<option value="7">배송전에 연락주세요.</option>
													 </select>                          
												</td>
											</tr>
									
										</tbody>
									</table>
									<div class="address_list_btn">
										<button class="address_list">배송지 목록</button>
									</div>
									  <!-- 모달 창 -->
									  <div class="modal">
										<div class="modal-content">
										  <span class="close">&times;</span>
										  <!-- 모달 내용 -->
										  <h4>| 배송 주소록 관리</h4>
											<div class="myPageAddrList">
												<table>
													<thead>
														<tr>
															<th>배송지명</th>
															<th>수령인</th>
															<th>주소</th>
															<th>전화번호</th>
															<th>선택</th>
															
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${addrList}" var="address">
															<tr>
																<td>${address.deliveryName}</td>
																<td>${address.receiverName}</td>
																<td>${address.addr}</td>
																<td>${address.addrTel}</td>
																<td><button data-addr-id="${address.addrId}">선택</button></td>
														</c:forEach>
													</tbody>
												</table>								
												</div>
											</div>
										</div><!--end of Modal-->
										
								</div>
								<div class="addressInfo_input_div addressInfo_input_div_2">
									<!-- 새 주소 입력 -->
									<div class="popup-content">
									<form id="addressForm">
										<div class="popup-table">
											<table>
												<tr>
													<td>배송지명</td>
													<td><input type="text" id="addrName" name="addrName"
														placeholder="배송지명" maxlength="30" autocomplete="off"
														required></td>
												</tr>
												<tr>
													<td>성명</td>
													<td><input type="text" id="receiverName"
														name="receiverName" placeholder="성명" maxlength="30"
														autocomplete="off" required></td>
												</tr>
												<tr>
													<td rowspan="3"><label for="memberAddress" style="font-weight: bold;">주소</label></td>
													<td><input type="text" id="sample4_postcode"
														name="memberAddress" placeholder="우편번호" maxlength="6" readonly>
													<button type="button" class="myPage_AddrBtn"
															onclick="sample4_execDaumPostcode()">검색</button></td>
														
												</tr>
												<tr>
													<td colspan="2"><input type="text"
														id="sample4_roadAddress" name="memberAddress"
														placeholder="도로명주소" readonly></td>
												</tr>
												<tr>
		
													<td colspan="2"><input type="text"
														id="sample4_detailAddress" name="memberAddress"
														placeholder="상세주소"></td>
												</tr>
												<tr>
													<td><label for="memberTel" style="font-weight: bold;"><span 
															class="required"></span>전화번호</label></td>
													<td><input type="text" id="memberTel" name="memberTel"
														placeholder="(- 없이 숫자만 입력)" maxlength="11">
													</td>
													
												</tr>
											</table>
										</div>
										<div class="popupBtn-wrap">
											<button class="myPage-btn" id="Send" type="submit">등록하기</button>
										</div>
									</form>
							</div>
								</div>
							</div>
						</div>
						
						
					</div>
					<!-- 쿠폰 적립금 정보 -->
					<div class="discout-info level1">
						<div class="discount-info-header">
							<div class="delivery-left"><span style="font-weight: bold; color: rgb(34,34,34);">| 쿠폰</span></div>
						</div>
						<div class="discount-table-wrapper">
							<table id="delivery-info-table" style="border-spacing: 0; ">
								<tbody>
									<tr>
										<th>보유쿠폰</th>
										<td>
											<select class="select1 coupon-list" name="coupon" id="">
												<option value="0">보유 쿠폰을 선택하세요.</option>
												<c:forEach var="coupon" items="${map.couponList}">
													<option value="${coupon.couponId}">${coupon.couponName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</tbody>
								
							</table>
						</div>
					</div>

					<!-- 결제정보 아임포트api -->
					<div class="payment-info level1">
						<div class="discount-info-header">
							<div class="delivery-left"><span style="font-weight: bold; color: rgb(34,34,34);">| 결제하기</span></div>
						</div>
						<div class="payment-select">
							<div class="payment-table-wrapper">
								<table id="payment-info-table" style="border-spacing: 0; ">
									<tbody>								
										<tr>
											<th>결제 수단 선택</th>
											<td>
												 <select class="select1 payment-list" id="payment">
													<option value="1">결제 수단을 선택하세요.</option>
													<option value="card">신용카드</option>
													<option value="kakopay">카카오페이</option>
													<option value="vbank">무통장입금</option>								
													<option value="phone">휴대폰 결제</option>								
												 </select>                          
											</td>
										</tr>							
									</tbody>
								</table>
							</div>

						</div>
					</div>





				</div> 
			
			
				<!-- 주문 상품 정보 -->
				<aside class="order-items">
					<div class="item-head level-sm-1">
						<div class="item-head-text">| 주문상품 정보 
							<strong> | 총 <span id="quantity"><c:out value="${orderItemsList.size()}" /></span>개 |</strong>
							<span  id="orderNumberSpan"> 주문번호 : ${orderNumber}</span>
						</div>
					</div>
					
					<div class="items level-sm-1">

						<c:choose>
							<c:when test="${not empty orderItemsList}">
							   <c:forEach var="orderItemsList" items="${orderItemsList}">
								  <div class="payment-item-detail">
									 <div class="payment-item-img">
										<a href="/stroke/product/productDetail?product_id=${orderItemsList.productId}">
										<img src="${contextPath}/${orderItemsList.productImage}" style="width: 200px;" alt="">
										</a>
									 </div>
									 <div class="payment-item-info">
										<div class="payment-item-name" id="${orderItemsList.productId}"><span>${orderItemsList.productName}</span></div>
										<div class="payment-item option"><span>Option : ${orderItemsList.option}</span></div>
										<div class="payment-item unit-price"><span>${orderItemsList.productPrice}원</span></div>
										<div class="payment-item qty"><span>${orderItemsList.quantity}</span> 개</div>
									 </div>
								  </div>
							   </c:forEach>
							</c:when>
						 </c:choose>


					</div>
					<div class="payment-numeric level-sm-1">
						<div class="pay-wrapper">
							<div class="pay-text">총 상품금액</div>
							<div class="pay-figure"><span>0</span>원</div>
						</div>
						<div class="pay-wrapper">
							<div class="pay-text">쿠폰 사용</div>
							<div class="pay-figure">-<span class="discountAmount">0</span>원</div>
						</div>
						<div class="pay-wrapper">
							<div class="pay-text">배송비</div>
							<div class="pay-figure" id="shippingFee"><span >3,000</span>원</div>
						</div>
						<div class="pay-wrapper">
							<div class="pay-text total">총 결제금액</div>
							<div class="pay-figure total stress"><span>0</span>원</div>
						</div>
					</div>

					<div class="agree level-sm-1">
						<div class="level2">
							<input type="checkbox" id="agree-checkbox" style="margin-right: 10px;">
							<p> 주문 내용을 확인했으며, 구매 약관에 동의합니다.</p>
						</div>
					</div>

					<div class="checkout level-sm-1">
						<div class="checkout-btn-warpper">
							<button id="pay-btn">
								CHECKOUT	
							</button>
						</div>
					</div>
				</aside>

			</div>
	
		</section>
	    <%String contextPath = request.getContextPath(); %>
    <input type="hidden" id="eventContextPath" value="<%= contextPath %>" />
	</main>


	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>

    <script>
		var phoneNumber = "${loginMember.memberTel}";
		var formattedPhoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");
		document.getElementById("phone").textContent = formattedPhoneNumber;
	  </script>

	<script src="${contextPath}/resources/js/common/appkey.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/product/productPayment.js"></script>
	<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>