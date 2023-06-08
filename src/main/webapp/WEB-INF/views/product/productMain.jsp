<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="productList" value="${map.productList}" />
<c:set var="pagination" value="${map.pagination}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ArtStroke_Art Boutique</title>
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productMain.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    
</head>
</head>
<body>
    <header class="header-style">
           	
 	<!-- header -->
 	 <jsp:include page ="/WEB-INF/views/common/header.jsp"/>

    </header>
    <main class="main-style">
  	<section class="product-head-wrapper">
  	
  		<div class="product-header-path">
				<ol>
					<li>홈 ></li>
					<li class=""><a href="/category/art-shop/108/">Art boutique</a></li>
				</ol>
		</div>
        <div class="product-type-display ">
            <section class="productMain-img">
                <h1>Art Boutique</h1>
                <img src="${contextPath}/resources/images/productMain/option2.jpg" alt="">
            </section>
		<p><span>Art Boutique</span></p>
		
		<div class="product-type">
				<ol>
		
					<li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">ALL</span></button>
					</li>

					<li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">BEST</span></button> 
					</li>
                    
                    <li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">NEW</span></button>
					</li>
                    
                    <li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">POSTER</span></button>
					</li>

                    <li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">HOME FABRIC</span></button>
					</li>

                    <li class="product-type-list">
                        <button class="button-48" id="pdt-ctgr-"><span class="text">PHONE CASE</span></button>
					</li>
				</ol>
		</div>
		</div>
  	
  	</section>

    <section class="product-display-wrapper">
        <div class="product-sidebar-left">

            <div class="df-productlist-filter-left" style="margin-top:11px">
                <div id="searchContent" class="xans-element- xans-product xans-product-searchdata   xans-record-">
                    <form class="searchCondition" id="ec-product-searchdata-form" name="" method="get">
                <input type="hidden" name="keyword" id="ec-product-searchdata-keyword_hidden">
                &nbsp;<ul id="ec-searchdata-area">
                    <li filterlistnum="0" class="xans-element- xans-product xans-product-searchfilterlist active xans-record-">
        <div class="title">
            <span>Category</span>
        </div>
        <div class="filter button">
             <ul class="product-category-filter">
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-drawing">드로잉</button></li>
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-picture">사진</button></li>
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-graphic">그래픽디자인</button></li>
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-animal">동물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-plant">식물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" id="pdt-ctgr-pattern">심플/패턴</button></li>
                
        </div>
        </li>
                </ul>
        </form>
        
        <hr>

        
        <button id="btnRefresh"><span class="text">전체해제</span><span><i class="fa-solid fa-arrows-rotate fa-spin fa-lg" style="color: #ffffff;"></i></span></button>
        </div>
        
            </div>
        </div>

        <div class="product-wrap-right">
            <ul class="product-list">

                
            </ul>
    

            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="list?cp="/>
    
                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
    
                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>
    
                    <!-- 범위가 정해진 일반 for문 사용 -->
                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
    
                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current">${i}</a></li>
                            </c:when>
    
                            <c:otherwise>
                                <li><a href="${url}${i}${sURL}">${i}</a></li>        
                            </c:otherwise>
                        </c:choose>
    
                    </c:forEach>
                    
                    <!-- 다음 목록 시작 번호로 이동 -->
                    <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
    
                    <!-- 끝 페이지로 이동 -->
                    <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
    
                </ul>
            </div>
        </div>

        
    </section>

</main>
    <footer class="footer-style">
      <!-- footer  -->
	<jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    
    </footer>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/product/productMain.js"></script>

</body>
</html>