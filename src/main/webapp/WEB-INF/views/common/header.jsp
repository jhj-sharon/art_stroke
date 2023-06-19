<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <!-- 상단 배너 -->
 <div id="mainpage-top-banner">
    <span>신규가입혜택</span>
    <span><span style="color:#FCBC41">10% </span>할인 쿠폰</span>
    <span>|</span>
    <span>배송비 <span style="color:#FCBC41; font-weight: 400;"> 무료 </span> 쿠폰<button type="button" onclick="location.href='${contextPath}/member/insertCoupon'" class="btn btn-primary">받기</button>

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
                <li><a href = "${contextPath}/board/boardBoardDetail">임시</a>
            </ul>
        </div>

        <!-- 기타메뉴 -->
        <div class="header-myinfo">
            <ul>  
                <li>
                    <button type="button"  id="header-search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
                </li>
                <li><a href=""><span><i class="fa-solid fa-heart"></i></span></a></li>
                <li><a href=""><span><i class="fa-solid fa-cart-shopping"></i></span></a></li>
               
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
        <form name="header-search-form" action="">
            <input type="text" 
                   class="header-search-input"
                   placeholder="검색어를 입력하세요">
        </form>
        <button id="header-search-close-btn">&times;</button>

    <!-- 검색 화면  -->
    <div class="header-search-popup">
        <div class="header-search-popup-wrap">
            <div class="header-search-popup-container">
                <div class="header-search-popup-keyword">
                    <div>인기검색어</div>
                    <ul>
                        <li>드로잉</li>
                        <li>폰케이스</li>
                        <li>포스터</li>
                        <li>키매</li>
                        <li>쿠션</li>
                    </ul>
                </div>

                <div class="header-search-popup-category">
                    <div>다양한 작품을 만나보세요</div>
                    <ul>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-animal.jpeg" alt="">
                            <span>동물</span>
                        </li>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-graphic.jpeg" alt="">
                            <span>그래픽디자인</span>
                        </li>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-photography.jpeg" alt="">
                            <span>사진</span>
                        </li>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-pattern.jpeg" alt="">
                            <span>패턴</span>
                        </li>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-plant.jpeg" alt="">
                            <span>식물</span>
                        </li>
                        <li>
                            <img src="${contextPath}/resources/img/header/search-drawing.jpeg" alt="">
                            <span>드로잉</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</header>

