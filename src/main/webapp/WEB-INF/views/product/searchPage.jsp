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

        <!-- 검색결과 있음 -->
        <!-- <section class="contents-wrap search-result">
            <article class="searchpage-result-text">
                <span>'검색어'</span>에 대한 결과 (<span>4</span>)
            </article>

            <article class="searhpage-product-wrap">
                <ul class="product-list">
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/big/202305/4cbe20ff9320f120867373dcbe79a306.jpg" alt="">
                        </a>
                            <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                           
                        </div>
        
                        <div class="product-item-info">
                            <span>작가1</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가2</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202305/fd1884123b1e8b4b152298d8d80ffd60.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가3</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202305/436e4ab0f2ef629616d0c84ae9cb4d0b.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가4</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                </ul>
            </article> 
        </section> -->

        <!-- 검색결과 없음 -->
        <section class="contents-wrap search-result-none">

            <article class="searchpage-result-text">
                "<span>검색어</span>"에 대한 검색결과가 없습니다.<br><br> 
                단어의 철자나 띄어쓰기가 정확한지 확인해주시기 바랍니다. 
            </article>

            <article class="searhpage-product-wrap">
                <span style="font-size: 18px; font-weight: 500;">추천상품</span>
                <ul class="product-list">
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/big/202305/4cbe20ff9320f120867373dcbe79a306.jpg" alt="">
                        </a>
                            <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                            
                        </div>
        
                        <div class="product-item-info">
                            <span>작가1</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가2</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202305/fd1884123b1e8b4b152298d8d80ffd60.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가3</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                    <li class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202305/436e4ab0f2ef629616d0c84ae9cb4d0b.jpg" alt="">
                        </a>
                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                        </div>
        
                        <div class="product-item-info">
                            <span>작가4</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </li>
                </ul>
            </article> 
        </section>

    </main>

    <!-- footer -->
    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>


    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/product/searchPage.js"></script>
</body>
</html>



