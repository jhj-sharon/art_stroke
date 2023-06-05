<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/member/searchIdPw-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
  <header class="header-style">
       <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
  </header>

  <main class="searchIdPw-main-style">

      <!-- 여기부터 추가 -->
      <section class="searchIdPw-contents-wrap">



       </section>
    </main>
    
    <footer class="footer-style">
       <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    </footer>
    	<!--main.js-->
	<script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/member/terms.js"></script>
    	<!-- jQuery 라이브러리 추가 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
 
    </body>
    </html>
    
    