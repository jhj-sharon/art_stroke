<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>art storke</title>
    
    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    
    <!-- css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/searchPage.css">

    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>

<body>

    <!-- header -->
    <jsp:include page ="/WEB-INF/views/common/header.jsp"/>

    <main class="main-style">
        <section class="contents-wrap search-result">
            <article class="searchpage-result-text">
                <!-- 결과 문구 출력 -->
            </article>

            <article class="searhpage-product-wrap">
                <ul class="product-list">
                    <!-- 결과 상품 출력 -->
                </ul>
            </article> 
        </section> 

    </main>

      <!-- context Path 전달 -->
      <%String contextPath = request.getContextPath(); %>
      <input type="hidden" id="searchContextPath" value="<%= contextPath %>" />

       <!-- loginMember session값 전달 -->
       <% Object searchLoginMember = session.getAttribute("loginMember");%>
       <input type="hidden" id="searchLoginMember" value="<%= searchLoginMember %>" />


    <!-- footer -->
    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>


    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/product/searchPage.js"></script>
</body>
</html>



