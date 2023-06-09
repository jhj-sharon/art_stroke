<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 검색 페이지</title>
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productMain.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productSearch.css">
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
                <h1>Seach your favorite</h1>
                <img src="${contextPath}/resources/images/productMain/option5.jpg" alt="">
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
                <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="${contextPath}/product/productDetail">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="${contextPath}/product/productDetail2">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
                          <li>
                    <div class="product-item">
                        <div class="product-item-img">
                        <a href="상세페이지">
                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                        </a>
                            <i class="fa-regular fa-heart"></i>
                        </div>
    
                        <div class="product-item-info">
                            <span>김키매(KimKimme)</span>
                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                            <span>21,900원</span>
                        </div>
                    </div>
                </li>
        
            </ul>
    
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