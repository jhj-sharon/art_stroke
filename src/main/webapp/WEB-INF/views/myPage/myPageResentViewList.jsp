<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="recentProduct" value="${recentProduct}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="../resources/css/myPage/myPageResentView.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
<title>최근 본 상품</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<main class="main-style">

		<section class="contents-wrap">
			<h4>| 최근 본 상품</h4>
			<div class="myPageDescription">최근 24시간 이내에 본 상품만 표시됩니다.</div>
			<div class="myPageWishList-wrap">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" id="restViewSelectAll"></th>
							<th>이미지</th>
							<th>상품</th>
							<th>판매가</th>
							<th>옵션</th>
							<th>선택</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${empty recentProduct}">
								<tr>
									<td colspan="6" rowspan="6"><div class="noItemWrap"><p class="noitem">최근 본 상품이 없습니다.</p></div></td>
								</tr>
								
							</c:when>
							<c:otherwise>
								<c:forEach items="${recentProduct}" var="recentProduct">
									<tr>
										<td><input type="checkbox" class="checkList"
											id="${recentProduct.productId}"></td>
											<td><img
												src="${contextPath}/${recentProduct.productImage}"
												alt="Product Image" style="width: 80px; height: 80px" onclick="location.href ='${contextPath}/product/productDetail?product_id=${recentProduct.productId}'"></td>
										<td><a
											href="/stroke/product/productDetail?product_id=${recentProduct.productId}">${recentProduct.productName}</a></td>
										<td class="productPrice">${recentProduct.productPrice}</td>
										<td><select name="option1" id="option1" class="optionWidth">
												<c:choose>
													<c:when test="${recentProduct.productOption1 == null}">
														<c:set var="options"
															value="${fn:split(recentProduct.productOption2, '/')}" />
														<c:forEach items="${options}" var="option">
															<option value="${option}">${option}</option>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:set var="options"
															value="${fn:split(recentProduct.productOption1, '/')}" />
														<c:forEach items="${options}" var="option">
															<option value="${option}">${option}</option>
														</c:forEach>
													</c:otherwise>
												</c:choose>
										</select></td>
										<td>
											<button class="myPage-btn" id="cart-btn">장바구니</button>
											<button class="myPage-btn" id="delete-btn">삭제</button>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="myPageWishList-wrap2">
				<button class="myPage-button" id="check-delete-btn">선택 품목 삭제</button>
			</div>
		</section>


	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageResentViewList.js"></script>
	<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>