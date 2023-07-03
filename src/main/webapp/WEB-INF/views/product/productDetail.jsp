<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="product" value="${product}" />

<!DOCTYPE html>
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
            <img class="target" src="${contextPath}/${product.productImage}"  data-zoom="2">
        </div>
        <!-- 상품 정보 영역 -->
        <div class="product-infoArea" >

            <div class="product-headingArea">
                <h2>${product.productName}</h2>
                <span>현재 ${product.sales}명의 고객이 구매했어요!</span>
            </div>
            <div class="product-info-detail">
                <p class="writerName">${product.productArtist}</p>

                <button id="artist-page-btn" onclick="location.href='${contextPath}/board/detailWriter/12'">작가페이지</button>

                <p class="product-content">${product.productContent}</p>
            </div>
            <hr>
            <div class="product-price">
                <table summary="가격테이블">
                    <tr>
                        <td class="td1">판매가</td>
                        <td class="td2"> <fmt:formatNumber value="${product.productPrice}" pattern="#,###원"/></td>
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
                            <select name="option1" id="option1" onchange="addOption()">
                                <option value="">-[필수] 옵션을 선택해 주세요-</option>
                                <c:if test="${not empty product.productOption1}">
                                    <c:set var="options" value="${fn:split(product.productOption1, '/')}"/>    
                                    <c:forEach items="${options}" var="option">
                                        <option value="${option}">${option}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty product.productOption1 and not empty product.productOption2}">
                                    <c:set var="options" value="${fn:split(product.productOption2, '/')}"/>    
                                    <c:forEach items="${options}" var="option">
                                        <option value="${option}">${option}</option>
                                    </c:forEach>
                                </c:if>
                            </select>

                        </td>

                    </tr>
                </table>
            </div>
            <!-- 사용자가 선택한 옵션값 보이기 -->
            <div class="option_wrapper">
                <!-- <div class="option-tr">

                    <div class="option-name">
                        <span></span>
                    </div>
                    <div class="option-qty">
                        <span class="minus">-</span>
                        <span class="num">01</span>
                        <span class="plus">+</span>
                    </div>
                    <div class="goods-price">
                        <span></span>
                        <i class="fa-regular fa-circle-minus" style="color:  #555555;"></i>
                    </div>
                </div> -->
            </div>
            <div class="total_price_wrapper">
                <div class="total_price">
                    <p>Total: <span class="tts"><strong id="sum">0</strong>원</span><span class="totalCount">(총<strong id="count">0</strong>개)</span>
                    </p>
                </div>

            </div>

            <div class="product-detail-btn">
                <c:choose>
                    <c:when test="${empty sessionScope.loginMember}">
                        <a href="#none" class="btn buy" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                             <div class="ac-buy wrap">
                                <span id="btnBuy" class="lang-buy" >바로구매</span>
                            </div> 
                        </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#none" class="btn buyNow" onclick="buyNow()" id="${product.productId}-buyNow">
                            <div class="ac-buy wrap">
                                <span class="lang-buy buyNow">바로구매</span>
                            </div>                             
                            </a> 
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${empty sessionScope.loginMember}">
                            <a href="#none" class="btn basket lang-basket" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                                <div class="ac-basket wrap">
                                    <span class="lang-buy">장바구니</span>
                                </div>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${contextPath}/product/productCart" class="btn basket lang-basket" id="${product.productId}-cart">
                                <div class="ac-basket wrap">
                                    <span class="lang-buy">장바구니</span>
                                </div>
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${empty sessionScope.loginMember}">
                            <a href="#none" class="btn wishlist lang-wishlist" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                                <div class="ac-wishlist wrap">
                                    <span class="lang-buy">관심상품</span>
                                </div>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#none" class="btn wishlist lang-wishlist" id="${product.productId}-wishList">
                                <div class="ac-wishlist wrap">
                                    <span class="lang-buy">관심상품</span>
                                </div>
                            </a>
                        </c:otherwise>
                    </c:choose>
                
            </div>
            
        </div>
       </div>

       <!-- 팝업 창 HTML -->

       <div class="confirmPop-Container">
   
           <div class="confirmPop wishPop"  style="display: none;">
               <div class="wishPop_header">
                       <h3>관심상품담기</h3>
               <a class="close" onclick="$('.wishPop').hide();">X</a>
                   </div>
               <div class="wishPop_content">
                       <p><strong>선택하신 상품</strong>을 <strong>관심상품</strong>에 담았습니다. <br>지금 관심상품을 확인하시겠습니까?</p>
                   </div>
               <div class="wishPop_button">
                       <a href="#none" class="wishbtn ongoing" onclick="$('.wishPop').hide();">쇼핑 계속하기</a>
                       <a href="http://localhost:8080/stroke/myPage/myPageWishList" class="wishbtn wishPage">관심상품 확인</a>
                   </div>
           
           </div>
   
           
   
           <div class="confirmPop cartPop"  style="display: none;">
               <div class="wishPop_header">
                       <h3>장바구니 담기</h3>
               <a class="close" onclick="$('.cartPop').hide();">X</a>
                   </div>
               <div class="wishPop_content">
                       <p><strong>선택하신 상품</strong>을 <strong>장바구니</strong>에 담았습니다. <br>지금 장바구니를 확인하시겠습니까?</p>
                   </div>
               <div class="wishPop_button">
                       <a href="#none" class="wishbtn ongoing" onclick="$('.cartPop').hide();">쇼핑 계속하기</a>
                       <a href="${contextPath}/product/productCart" class="wishbtn wishPage">장바구니 확인</a>
                   </div>
           
           </div>
   
   
       </div>

       <hr id="divider">

       <div class="product-tabArea">

        <ul class="df-prd-tab-items">

            <!-- 상품상세정보 -->
            <li class="prd-tab selected">
                <a href="${contextPath}/product/productDetail?product_id=${product.productId}"><span class="df-prd-tab-item-detail" style="background-color: whitesmoke; font-weight: bold; text-decoration: underline;">상세정보</span></a>
            </li>
            <li> | </li>
            <!-- 상품후기 -->
            <li class="prd-tab df-use-prd-review df-use-on">
                <a href="${contextPath}/product/productDetailReview?product_id=${product.productId}"><span class="df-prd-tab-item-review">REVIEW</span></a>
            </li>
            <li> | </li>
            <!-- 상품문의 -->
            <li class="prd-tab df-use-prd-qna df-use-on">
                <a href="${contextPath}/product/productDetailQnA?product_id=${product.productId}"><span class="df-prd-tab-item-qna">제품Q&A</span></a>
            </li>
        </ul>

       </div>

       <div class="product-contents">
            <div class="mainImagePosition">
                <img alt="악세사리 상품 이미지-S5L34" src="https://tounou.co.kr/web/upload/NNEditor/20221216/f6587abd56b52a050133509c64b94a3c.jpg" style="margin:0 auto; display: block; max-width:100%;min-height: 504px;">
            </div>
            <div class="mainImagePosition">
                <img alt="악세사리 상품 이미지-S5L34" src="https://tounou.co.kr/web/upload/NNEditor/20221216/dc8c1654a6178de9c7a31c1df78806ba.jpg" style="margin:0 auto; display: block; max-width:100%;min-height: 504px;">
            </div>
            <div class="mainImagePosition">
                <img alt="악세사리 상품 이미지-S5L34" src="https://tounou.co.kr/web/upload/NNEditor/20221216/fadec04cdadfee53746d26469e3c4fb9.jpg" style="margin:0 auto; display: block; max-width:100%;min-height: 504px;">
            </div>
            <div style="position:relative;" class="edb-img-tag-w"><img alt="악세사리 -S5L7" src="https://tounou.co.kr/web/upload/NNEditor/20221216/b1405456ab176d997419daf8d468f9fe.jpg" style="margin:0 auto; display: block; max-width:100%;min-height: 57px;"></div>
            <div style="position:relative;" class="edb-img-tag-w">
            <div class="frameOption">
                <span>제품 유형 : 알루미늄 액자</span>
                <span>프레임 전면폭 : 7mm</span>
                <span>프레임 측면폭 : 21mm </span>
                <span>상판 : 1mm 투명 아크릴판</span>
            </div>
            <div class="mainImagePosition">
                <img alt="악세사리 상품 이미지-S5L34" src="https://tounou.co.kr/web/upload/NNEditor/20230427/a9a03aa586909ee2b443ec9d57c94f6c.jpg" style="margin:0 auto; display: block; max-width:100%;min-height: 504px;">
            </div>
        </div>

        <!-- 위로 가기 버튼-->
       <div class="index_top_btn_area">
        <i class="fa-solid fa-square-caret-up"></i>
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