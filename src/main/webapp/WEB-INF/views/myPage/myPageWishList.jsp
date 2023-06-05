<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="../resources/css/myPage/myPageWishList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">

<title>관심목록</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<main class="main-style">

		<section class="contents-wrap">
			<h4>| 관심상품</h4>
			<div class="myPageWishList-wrap">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" id="wishListSelectAll"></th>
							<th>이미지</th>
							<th>상품</th>
							<th>판매가</th>
							<th>옵션</th>
							<th>선택</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td><img src="../resources/img/aaa.png"
								style="width: 80px; height: 80px"></td>
							<td>삐약이</td>
							<td>90000</td>
							<td><select name="option1" id="option1">
									<option value="S">S</option>
									<option value="M">M</option>
									<option value="L">L</option>
							</select></td>
							<td><button class="myPage-btn">장바구니</button>
								<button class="myPage-btn">삭제</button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="myPageWishList-wrap2">
				<button class="myPage-button">선택 품목 삭제</button>
				<button class="myPage-button">선택 품목 주문</button>
			</div>
		</section>


	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
</body>
</html>