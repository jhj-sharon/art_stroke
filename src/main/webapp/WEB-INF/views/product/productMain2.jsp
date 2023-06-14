<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="productList" value="${map.productList}" />
<c:set var="wishList" value="${map.wishList}" />

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
                    <button class="button-48" onclick="location.href='${contextPath}/product/productMain2'" ><span class="text">ALL</span></button>
                </li>

                <li class="product-type-list">
                    <button class="button-48 best" onclick="sortItemsBySalesDesc()"><span class="text">BEST</span></button> 
                </li>
                
                <li class="product-type-list">
                    <button class="button-48 new" onclick="sortItemsByProductRdateDesc()" ><span class="text">NEW</span></button>
                </li>
                
                <li class="product-type-list">
                    <button class="button-48 poster"  onclick="redirectToProductType('포스터')"><span class="text">POSTER</span></button>
                </li>

                <li class="product-type-list">
                    <button class="button-48 homeFabric" onclick="redirectToProductType('홈패브릭')" ><span class="text">HOME FABRIC</span></button>
                </li>

                <li class="product-type-list">
                    <button class="button-48 phoneCase" onclick="redirectToProductType('스마트폰 케이스')" ><span class="text">PHONE CASE</span></button>
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
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('드로잉')" id="pdt-ctgr-drawing" >드로잉</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('사진')" id="pdt-ctgr-picture">사진</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('그래픽디자인')" id="pdt-ctgr-graphic">그래픽디자인</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('동물')" id="pdt-ctgr-animal">동물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('식물')" id="pdt-ctgr-plant">식물</button></li>
                <li class=" xans-record-"><button class="product-category-btn" onclick="redirectToProductCategory('심플')" id="pdt-ctgr-pattern">심플/패턴</button></li>
             </ul>   
        </div>
        </li>
                </ul>
       
        
        <hr>

        
        <button id="btnRefresh"><span class="text">전체해제</span><span><i class="fa-solid fa-arrows-rotate fa-spin fa-lg" style="color: #ffffff;"></i></span></button>
        </div>
        
            </div>
        </div>

        <div class="product-wrap-right">
            <div class="product-wrap-list">
                <ul class="product-list">
                    <c:choose>
                        <c:when test="${not empty productList}">
                            <!-- 상품이 있다면 -->
                            <!-- 향상된 for문처럼 사용 -->
                            <c:forEach var="product" items="${productList}">
                                <li>
                                    <div class="product-item">
                                        <div class="product-item-img">
                                            <a href="/stroke/product/productDetail?product_id=${product.productId}">
                                                <img src="${contextPath}/${product.productImage}" alt="">
                                            </a>
                                            <c:choose>
                                                <c:when test="${not empty wishList}">
                                                    <!-- 위시리스트가 있다면 -->
                                                    <c:set var="found" value="false" />
                                                    <c:forEach var="wishItem" items="${wishList}">
                                                        <c:if test="${wishItem.productId == product.productId && found == false}">
                                                            <!-- 위시리스트에 해당 상품이 있는 경우 -->
                                                            <i class="fa-solid fa-heart" id="${product.productId}" style="color: #f42525;"></i>
                                                            <c:set var="found" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${found == false}">
                                                        <!-- 위시리스트에 해당 상품이 없는 경우 -->
                                                        <i class="fa-regular fa-heart" id="${product.productId}"></i>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- 위시리스트가 없다면 -->
                                                    <i class="fa-regular fa-heart" id="${product.productId}"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="product-item-info">
                                            <span>${product.productArtist}</span>
                                            <span>${product.productName}</span>
                                            <span><fmt:formatNumber value="${product.productPrice}" pattern="###,###원" /></span>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <!-- 상품이 없다면 -->
                            <li>상품이 존재하지 않습니다.</li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                
                
                
            </div>
            
    

            <div class="pagination-area">

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
        </div>

        
    </section>

</main>
    <footer class="footer-style">
      <!-- footer  -->
	<jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    
    </footer>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/product/productMain2.js"></script>

</body>
</html>