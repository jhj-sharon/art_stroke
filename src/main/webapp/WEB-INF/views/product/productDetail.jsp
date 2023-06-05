<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productDetail.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;
600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>

    <header class="header-style">
        <!-- header -->
       <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
 
     </header>


    <main class="main-style">
        <!-- 상품 헤더 영역 -->
       <div class="product-detailArea" >
        <!-- 상품 썸네일 영역 -->
        <div class="product-imageArea" >
            <img class="target" src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg"  data-zoom="2">
        </div>
        <!-- 상품 정보 영역 -->
        <div class="product-infoArea" >

            <div class="product-headingArea">
                <h2>lazzy weekend 베개 커버</h2>
                <i class="fa-regular fa-heart"></i>
            </div>
            <div class="product-info-detail">
                <p class="writerName">Artist. Wan</p>
                <button id="artist-page-btn" onclick="location.href='#' ">작가페이지</button>
                <p class="product-content">숲 안에 나무 있고, 나무 안에 숲이 있다.</p>
            </div>
            <hr>
            <div class="product-price">
                <table summary="가격테이블">
                    <tr>
                        <td class="td1">판매가</td>
                        <td class="td2">36,900원</td>

                    </tr>
                </table>
                <hr>
            </div>
            <div class="min-order">

                <span >최소주문수량 : 1개</span>
            </div>

            <div class="product-option">
                <table summary="가격테이블">
                    <tr>
                        <td class="td1">필수옵션</td>
                        <td class="td2">
                            <select name="option1" id="option1">
                                <option value="S">S</option>
                                <option value="M">M</option>
                                <option value="L">L</option>
                            </select>

                        </td>

                    </tr>
                </table>
            </div>

                        <!-- 구매 버튼 영역 -->
                        <div class="product-detail-btn">
                            <div class="ac-buy wrap ">
                                <a href="#none" class="btn buy" onclick="product_submit(1, '/exec/front/order/basket/', this)">
                                    <span id="btnBuy" class="lang-buy">바로구매</span>
                                </a>
                            </div>
                            <div class="ac-basket wrap ">
                                <a href="${contextPath}/product/productCart" class="btn basket lang-basket" onclick="addtoCart(); product_submit(2, '/exec/front/order/basket/', this)">
                                    장바구니
                                </a>
                            </div>
                            <div class="ac-wishlist wrap ">
                                <a href="#none"  class="btn wishlist lang-wishlist" onclick="add_wishlist(this, true);">
                                    관심상품
                                </a>
                            </div>
                        </div>
        </div>
       </div>

       <hr id="divider">

       <div class="product-tabArea">

        <ul class="df-prd-tab-items">

            <!-- 상품상세정보 -->
            <li class="prd-tab selected">
                <a href="${contextPath}/product/productDetail"><span class="df-prd-tab-item-detail" style="font-weight: bold;">상세정보</span></a>
            </li>
            <li> | </li>
            <!-- 상품후기 -->
            <li class="prd-tab df-use-prd-review df-use-on">
                <a href="${contextPath}/product/productDetailReview"><span class="df-prd-tab-item-review">REVIEW</span></a>
            </li>
            <li> | </li>
            <!-- 상품문의 -->
            <li class="prd-tab df-use-prd-qna df-use-on">
                <a href="${contextPath}/product/productDetailQnA"><span class="df-prd-tab-item-qna">제품Q&A</span></a>
            </li>
        </ul>

       </div>

       <div class="product-contents" style="border: 2px solid teal;">

       </div>



    </main>

    <footer class="footer-style">
        <!-- footer  -->
	<jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    
    </footer>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/product/productDetail.js"></script>
 
</body>
</body>
</html>