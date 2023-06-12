<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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
    <div id="contextPathElement" data-context-path="${pageContext.request.contextPath}"></div>

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
                        <button class="button-48" onclick="addCard(itemList)" ><span class="text">ALL</span></button>
					</li>

					<li class="product-type-list">
                        <button class="button-48 best" onclick="sortItemsBySalesDesc()"><span class="text">BEST</span></button> 
					</li>
                    
                    <li class="product-type-list">
                        <button class="button-48 new" onclick="sortItemsByProductRdateDesc()" ><span class="text">NEW</span></button>
					</li>
                    
                    <li class="product-type-list">
                        <button class="button-48 poster" onclick="sortItemsByType('포스터')"><span class="text">POSTER</span></button>
					</li>

                    <li class="product-type-list">
                        <button class="button-48 homeFabric" onclick="sortItemsByType('홈패브릭')" ><span class="text">HOME FABRIC</span></button>
					</li>

                    <li class="product-type-list">
                        <button class="button-48 phoneCase" onclick="sortItemsByType('스마트폰 케이스')" ><span class="text">PHONE CASE</span></button>
					</li>
				</ol>
		</div>
		</div>
  	
  	</section>

    <section class="product-display-wrapper">
        <div class="product-sidebar-left">

            <div class="df-productlist-filter-left" style="margin-top:11px">
                <div id="searchContent" class="xans-element- xans-product xans-product-searchdata   xans-record-">
                
                <input type="hidden" name="keyword" id="ec-product-searchdata-keyword_hidden">
                &nbsp;<ul id="ec-searchdata-area">
                    <li filterlistnum="0" class="xans-element- xans-product xans-product-searchfilterlist active xans-record-">
        <div class="title">
            <span>Category</span>
        </div>
        <div class="filter button">
             <ul class="product-category-filter">
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('드로잉')" id="pdt-ctgr-drawing" >드로잉</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('사진')" id="pdt-ctgr-picture">사진</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('그래픽디자인')" id="pdt-ctgr-graphic">그래픽디자인</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('동물')" id="pdt-ctgr-animal">동물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('식물')" id="pdt-ctgr-plant">식물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="filterItemsByCategory('패턴')" id="pdt-ctgr-pattern">심플/패턴</button></li>
             </ul>   
        </div>
        </li>
                </ul>
       
        
        <hr>

        
        <button id="btnRefresh" onclick="addCard(itemList)"><span class="text">전체해제</span><span><i class="fa-solid fa-arrows-rotate fa-spin fa-lg" style="color: #ffffff;"></i></span></button>
        </div>
        
            </div>
        </div>

        <div class="product-wrap-right">
                <ul class="product-list">
                   <!-- 동적으로 구현 -->
              
                
                </ul>
    

            <div class="pagination" style="height: 100px; margin-bottom: 30px;">
                <i class="fa-solid fa-arrow-left"  style="color: #222222;"></i>
                <ol id="numbers">
                    <!-- <li><a href="">1</a></li>
                    <li><a href="">1</a></li>
                    <li><a href="">1</a></li> -->
                </ol>
                <i class="fa-solid fa-arrow-right" style="color: #222222;"></i>
            </div>
        </div>

        
    </section>
    <span id="uid" data-uid="${sessionScope.memberId}"></span>


    <!-- <c:choose>
        <c:when test = "${empty sessionScope.loginMember}">
            <li>로그인 안됨</li>
        </c:when>
        <c:otherwise>
            <li>
                <span>로그인됨</i></span>
            </li>
        </c:otherwise>
    </c:choose> -->
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