<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %><!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="product" value="${product}" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lazyyy weekend 미니 쿠션</title>
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productDetail.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/product/productDetailReview.css">
    
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
              <a href="${contextPath}/product/productDetail?product_id=${product.productId}"><span class="df-prd-tab-item-detail">상세정보</span></a>
          </li>
          <li> | </li>
          <!-- 상품후기 -->
          <li class="prd-tab df-use-prd-review df-use-on">
              <a href="${contextPath}/product/productDetailReview?product_id=${product.productId}"><span class="df-prd-tab-item-review" style="background-color: whitesmoke; font-weight: bold; text-decoration: underline;">REVIEW</span></a>
          </li>
          <li> | </li>
          <!-- 상품문의 -->
          <li class="prd-tab df-use-prd-qna df-use-on">
              <a href="${contextPath}/product/productDetailQnA?product_id=${product.productId}"><span class="df-prd-tab-item-qna">제품Q&A</span></a>
          </li>
      </ul>

       </div>

       <div class="product-contents" >

       </div>

       <hr style="margin-bottom: 20px;">

       <div class="product-review-wrapper">

        <div class="product-review-detail" tabindex="0">

            <div class="rivewScore">
              <div class="review-text">
                상품만족도
              </div>
              <div class="reviewScore-rating">
                <i class="fa-solid fa-star" style="color: #ffec1a;"></i>
                <span>4.9</span>
                <span> / 5</span>
              </div>
            </div>

            <div class="countReview">
                <div class="review-text">
                리뷰 개수
                </div>
                <span>58</span>
                <span>개</span>
            </div>
            <div class="writeReview">
                <div class="review-text" style="font-size: 12px;">
                    리뷰는 'Mypage'에서 작성가능합니다.
                </div>
                <button class="review-write-btn" onclick="location.href='${contextPath}/myPage/myPageMain'">Mypage</button>
              </div>
            </div>

            <ul class="review-list">
                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 방안이나 복도에 전시해두면 공간 분위기가 확 살아납니다.
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                    <div id="modal">
                        
                        
                        <div class="review-modal-container">
                            <div>
                                <button id="close-modal">x</button>
                            </div>
                            <div>
                                <div class="review-move" >
                                    <i class="fa-solid fa-chevron-left fa-xl" style="color: #ffffff;"></i>
                                </div>
                                <div class="image-modal">
                                    <img src="${contextPath}/resources/img/thumbnail/thumbnail_boy.jpg">
                                </div>
                                <div class="content-modal">
                                  <div class="modal-product-detail">
                                    <div class="modal-arrange">
                                        <div class="modal-small-img">
                                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_boy.jpg" alt="">
                                        </div>
                                        <div class="modal-sm-info">
                                            <div class="modal-sm-name">청년의 시선 포스터</div>
                                            <div class="modal-writer-nick">작가이름</div>
                                            </div>
                                    </div>
                                    
                                    <hr>
                                        <div class="modal-star">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                                <path fill="none" d="M0 0H14V20H0z"></path>
                                                <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                              </svg>
                                              <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                                <path fill="none" d="M0 0H14V20H0z"></path>
                                                <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                              </svg>
                                              <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                                <path fill="none" d="M0 0H14V20H0z"></path>
                                                <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                              </svg>
                                              <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                                <path fill="none" d="M0 0H14V20H0z"></path>
                                                <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                              </svg>
                                              <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                                <path fill="none" d="M0 0H14V20H0z"></path>
                                                <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                              </svg>
                                        </div>
                                    <div class="modal-sm-content">
                                        <p>
                                        좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 방안이나 복도에 전시해두면 공간 분위기가 확 살아납니다.
                                        </p>
                                        <div class="review_item_username">
                                            <span class="review_item_username_1">NAME </span>
                                          </div>
                                          <div class="review_item_date_option">
                                            05.15
                                          </div>

                                    </div>
                                  </div>
                                 </div>
                                 <div class="review-move" >
                                    <i class="fa-solid fa-chevron-right fa-xl" style="color: #ffffff;"></i>
                                </div>
                            </div>
                            
                         </div>
                        
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_boy.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <i class="la la-times"></i>
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>

                <li>
                    <div class="product-review-card">
                        <div class="reivew_item_img">
                            <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg">
                        </div>
                        <div class="review_item_info_container">
                            <div class="review_item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                                  <svg xmlns="http://www.w3.org/2000/svg" class="review_info_star_size" viewBox="0 0 14 20">
                                    <path fill="none" d="M0 0H14V20H0z"></path>
                                    <path fill="#ffc72e" d="M7.294 11.03L2.967 13.3l.827-4.81-3.5-3.409 4.837-.7L7.294 0l2.163 4.379 4.837.7-3.5 3.409.826 4.812z" transform="translate(-.294 3.349)"></path>
                                  </svg>
                            </div>
                            <div class="review-detail-info">
                            <div class="review_text">
                                좋습니다 배송이좀느려요 디자인이 좋아서 가족들것도 주문했습니다. 주기적으로 구문하려구요
                              </div>
                              <div class="review_item_username">
                                <span class="review_item_username_1">NAME </span>
                              </div>
                              <div class="review_item_date_option">
                                05.15
                              </div>
                            </div>
                        </div>       
                    </div>
                </li>
            </ul>
        
           



            </div>
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
    <script src="${contextPath}/resources/js/product/productDetailReview.js"></script>
</body>
</html>