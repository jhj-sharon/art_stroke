<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %><!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="product" value="${product}" />


<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:forEach var="boardType" items="${boardTypeList}">
    <c:if test="${boardCode == boardType.boardCode}">
        <c:set var="boardName" value="${boardType.boardName}" />
    </c:if>
</c:forEach>


<c:set var="pagination" value="${map.pagination}" />
<c:set var="productQnAList" value="${map.productQnAList}" />


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lazyyy weekend 미니 쿠션</title>
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productDetail.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productDetailQnA.css">
    
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
                <button id="artist-page-btn" onclick="location.href='#' ">작가페이지</button>
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
                            <select name="option1" id="option1">
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

            <div class="product-detail-btn">
                <div class="ac-buy wrap">
                    <c:choose>
                        <c:when test="${empty sessionScope.loginMember}">
                            <a href="#none" class="btn buy" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                                <span id="btnBuy" class="lang-buy">바로구매</span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#none" class="btn buy">
                                <span id="btnBuy" class="lang-buy">바로구매</span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="ac-basket wrap">
                    <c:choose>
                        <c:when test="${empty sessionScope.loginMember}">
                            <a href="#none" class="btn basket lang-basket" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                                장바구니
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${contextPath}/product/productCart" class="btn basket lang-basket">
                                장바구니
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="ac-wishlist wrap">
                    <c:choose>
                        <c:when test="${empty sessionScope.loginMember}">
                            <a href="#none" class="btn wishlist lang-wishlist" onclick="alert('로그인이 필요한 서비스입니다.'); return false;">
                                관심상품
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="#none" class="btn wishlist lang-wishlist">
                                관심상품
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
        </div>
       </div>

       <hr id="divider">

       <div class="product-tabArea">

        <ul class="df-prd-tab-items">

            <!-- 상품상세정보 -->
            <li class="prd-tab selected">
                <a href="${contextPath}/product/productDetail?product_id=${product.productId}"><span class="df-prd-tab-item-detail" style="font-weight: bold;">상세정보</span></a>
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

       <div class="product-contents" style="border: 2px solid teal;">

       </div>

       <hr style="margin-bottom: 20px;">

       <div class="product-qna-wrapper" >

        <div class="product-qna-head">
            <div class="product-qna-title">
                Q & A
            </div>
            <div class="product-qna-subtext">
                상품에 대해 질문하세요
            </div>
        </div>

        <div class="product-qna-tableArea">
            <table id="qna-table">
                <thead>

                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>

                </thead>
               
                <tbody>
                <tr>
                    <td>10</td>
                    <td><i class="fa-solid fa-lock fa-sm" style="color: #b7b9bd;"></i> Art_Stroke에 문의합니다.</td>
                    <td>비***</td>
                    <td>2023-05-18</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td><i class="fa-solid fa-lock fa-sm" style="color: #b7b9bd;"></i> Art_Stroke에 문의합니다.</td>
                    <td>비***</td>
                    <td>2023-05-18</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td><i class="fa-solid fa-lock fa-sm" style="color: #b7b9bd;"></i> Art_Stroke에 문의합니다.</td>
                    <td>비***</td>
                    <td>2023-05-18</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td><i class="fa-solid fa-lock fa-sm" style="color: #b7b9bd;"></i> Art_Stroke에 문의합니다.</td>
                    <td>비***</td>
                    <td>2023-05-18</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td><i class="fa-solid fa-lock fa-sm" style="color: #b7b9bd;"></i> Art_Stroke에 문의합니다.</td>
                    <td>비***</td>
                    <td>2023-05-18</td>
                </tr>
            </tbody>

            </table>
        </div>
        <div class="product-qna-btn">
            <c:if test="${!empty loginMember}">
            <button id="qna-btn" onclick="location.href='${contextPath}/product/productDetailQnA/productQnAWrite'">문의남기기</button>
            </c:if>
        </div>

        <div class="pagination-area">

            <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
            <c:set var="url" value="${boardCode}?cp="/>

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
       



    </main>

    <footer class="footer-style">
        <!-- footer  -->
	<jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    
    </footer>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/product/productDetail.js"></script>
    <script src="${contextPath}/resources/js/product/productDetailQnA.js"></script>
 
</body>
</body>
</html>