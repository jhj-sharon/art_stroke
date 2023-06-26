<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <!-- 상단 배너 -->
 <div id="mainpage-top-banner">
    <span>신규가입혜택</span>
    <span><span style="color:#FCBC41">10% </span>할인 쿠폰</span>
    <span>|</span>
    <span>배송비 <span style="color:#FCBC41; font-weight: 400;"> 무료 </span> 쿠폰
    
    <c:choose>
        <c:when test="${empty sessionScope.loginMember}">
            <!-- 로그인 안하면 쿠폰 받기 버튼 보이지 않음 -->
        </c:when>
        <c:when test="${sessionScope.loginMember.couponOptIn eq 'Y'}">
            <!-- 쿠폰을 받은 멤버는 쿠폰 받기 버튼 보이지 않음 -->
        </c:when>
        <c:otherwise>
            <button type="button" onclick="location.href='${contextPath}/member/insertCoupon'" class="btn btn-primary header-coupon-btn">쿠폰 받기</button>
        </c:otherwise>
    </c:choose>


</span>
    <span>
        
        <span>하루 동안 보지 않기</span>
        <span id="header-hide-banner">&times;</span>
    </span>

 </div>

<!-- 헤더 -->
<header class="header-style">
    <!-- 로고 및 메뉴 -->
    <div class="header-container">
        <div>
            <!-- 로고 -->
            <a href ="${contextPath}/index.jsp">
            <img src="${contextPath}/resources/img/header/logo.png" alt="logo">
            </a>

            <!-- 메뉴 -->
            <ul>
                <li><a href="${contextPath}/board/writer">Artist</a></li>

                <li><a href="${contextPath}/product/productMain2">Shop</a></li>

                    <li>
                        <a href="">Community</a>

                        <ul class="nav-drop-menu">
                            <c:forEach var="boardType" items="${boardTypeList}">
                                <a href="${contextPath}/board/list/${boardType.boardCode}"><li>${boardType.boardName}</li></a>
                            </c:forEach>
                        </ul>
                    </li>

                <li><a href="${contextPath}/myPage/myPageMain">About</a></li>
            </ul>
        </div>

        <!-- 기타메뉴 -->
        <div class="header-myinfo">
            <ul>  
                <li>
                    <button type="button"  id="header-search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
                </li>
                <c:choose>
                    <c:when test="${empty sessionScope.loginMember}">
                        <li><a href="" onclick="alert('로그인이 필요한 서비스입니다.'); return false;"><span><i class="fa-solid fa-heart"></i></span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${contextPath}/myPage/myPageWishList"><span><i class="fa-solid fa-heart"></i></span></a></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${empty sessionScope.loginMember}">
                        <li><a href="" onclick="alert('로그인이 필요한 서비스입니다.'); return false;"><span><i class="fa-solid fa-cart-shopping"></i></span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${contextPath}/product/productCart"><span><i class="fa-solid fa-cart-shopping"></i></span></a></li>
                    </c:otherwise>
                </c:choose>
               
               <!-- 로그인 -->
                <c:choose>
                    <c:when test = "${empty sessionScope.loginMember}">
                        <li><a href="${contextPath}/member/login"><span><i class="fa-solid fa-arrow-right-to-bracket"></i></span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <span><i class="fa-solid fa-user"></i></span>
    
                            <ul id="nav-drop-myinfo">
                                <a href="${contextPath}/myPage/myPageMain"><li>My Page</li></a>
                                <a href="${contextPath}/member/logout" onclick="clearSessionStorage()"><li>Logout</li></a>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>

    <!-- 검색창 -->
    <div class="header-search-top">
        <div class="header-search-form-keyword">
            <input type="text" 
                   class="header-search-input"
                   placeholder="검색어를 입력하세요"
                   onkeydown="searchKeyword(event)">
        </div>
        <button id="header-search-close-btn">&times;</button>

    <!-- 검색 화면  -->
    <div class="header-search-popup">
        <div class="header-search-popup-wrap">
            <div class="header-search-popup-container">
                <div class="header-search-popup-keyword">
                    <div>인기검색어</div>
                    <ul id="header-keyword-list">
                        <!-- <li onclick="searchPopKeyword(event)">드로잉</li> -->
                    </ul>
                </div>

                <form class="header-search-popup-category" name="header-search-popup-category">
                    <div>다양한 작품을 만나보세요</div>
                    <ul>
                        <li onclick="searchCategory('동물')">
                            <img src="${contextPath}/resources/img/header/search-animal.jpeg" alt="">
                            <span>동물</span>
                        </li>
                        <li onclick="searchCategory('그래픽디자인')">
                            <img src="${contextPath}/resources/img/header/search-graphic.jpeg" alt="">
                            <span>그래픽디자인</span>
                        </li>
                        <li onclick="searchCategory('사진')">
                            <img src="${contextPath}/resources/img/header/search-photography.jpeg" alt="">
                            <span>사진</span>
                        </li>
                        <li onclick="searchCategory('패턴')">
                            <img src="${contextPath}/resources/img/header/search-pattern.jpeg" alt="">
                            <span>패턴</span>
                        </li>
                        <li onclick="searchCategory('식물')">
                            <img src="${contextPath}/resources/img/header/search-plant.jpeg" alt="">
                            <span>식물</span>
                        </li>
                        <li onclick="searchCategory('드로잉')">
                            <img src="${contextPath}/resources/img/header/search-drawing.jpeg" alt="">
                            <span>드로잉</span>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
</header>

