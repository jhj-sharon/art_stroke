<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="../resources/css/myPage/myPageReviewList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<title>내 리뷰 모음</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<main class="main-style">

		<section class="contents-wrap">
			<h4>| 내 리뷰 모음</h4>
			<div class="myPageReviewList-wrap">
				<table>
					<thead>
						<tr>
							<th>상품명</th>
							<th>이미지</th>
							<th>리뷰내용</th>
							<th>작성일</th>
							<th>평점</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>삐약이</td>
							<td><img src="../resources/img/aaa.png" width=100px; height="100px">  </td>
							<td>너무너무너무 완벽합니다.</td>
							<td>2023.05.21</td>
							<td>5.0</td>
						</tr>
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