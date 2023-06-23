<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<section class="bg-light">
<div class="container py-4">
<div class="row align-items-center justify-content-between">

<div>
<h1 class="text-dark text-center">환영합니다</h1>
<p class="text-center">
<span id="name"></span>님의 로그인 성공<br> 이메일 주소는 <strong id="email"></strong>입니다
</p>
</div>
<div class="d-grid gap-2">
<button type="button">시작하기</button> 
</div>

</div>
</div>
</section>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
	var name = "${name}";
	var email = "${email}";
	$("#name").html(name);
	$("#email").html(email);
});
</script>

</body>
</html>
