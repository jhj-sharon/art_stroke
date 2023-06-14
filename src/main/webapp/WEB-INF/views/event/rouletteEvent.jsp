<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>art stroke</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/eventPage.css">
	<script src="https://kit.fontawesome.com/069a8eb008.js"
		crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    <body>

    <!-- header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

    <main>

			<!-- 이벤트화면 이미지 -->
            <img src="${contextPath}/resources/img/eventPage/eventPageImg.jpg" alt="" id="event-img">

			<!-- 룰렛 -->
             <div class="roulette-container">
                <div class="roulette"> 
                    <div id="roulette-item0" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">7%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div id="roulette-item1" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">5%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div id="roulette-item2" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">3%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div id="roulette-item3" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">5%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div id="roulette-item4" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">3%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div id="roulette-item5" class="roulette-item">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">3%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px" class="item-img">
                    </div>
                </div>
                
                <!-- 전달할 쿠폰 정보 -->
                <form action="eventRoulette" id="event-roulette-form" method="POST" name="eventRoulette">
                    <input type="hidden" id="event-coupon-rate" name="eventRoulette">
                </form>
                <button id="roulette-btn" type="button" form="event-roulette-form">START</button>

                <span id="roulette-pin"><img src="${contextPath}/resources/img/eventPage/pin.png" alt="" width="60px"></span>
            </div> 
            
            <!-- loginMember session값 전달 -->
            <% Object eventLoginMember = session.getAttribute("loginMember");%>
            <input type="hidden" id="eventLoginMember" value="<%= eventLoginMember %>" />
           

            
            <!--  당첨 결과 모달  -->
            <div class="eventpage-modal-overlay"></div>
            
    </main>

   

    



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/eventPage.js"></script>
</body>
</html>