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


            <img src="${contextPath}/resources/img/eventPage/eventPageImg.jpg" alt="" id="event-img">

            <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/chartjs-plugin-datalabels/2.1.0/chartjs-plugin-datalabels.min.js"></script>
            <div id="eventpage-wrapper">	
                <button id="roulette-btn">START</button>
                <span class="roulette-arrow"><i class="fa-solid fa-location-dot"></i></span>
                <canvas id="roulette"></canvas>
            </div> -->

            <div class="roulette-container">
                <div class="roulette"> 
                    <div class="roulette-item1">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">30%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div class="roulette-item2">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">10%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div class="roulette-item3">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">10%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div class="roulette-item4">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">20%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div class="roulette-item5">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">10%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px">
                    </div>
                    <div class="roulette-item6">
                        <span class="item-text1">할인쿠폰</span>
                        <span class="item-text2">20%</span>
                        <img src="${contextPath}/resources/img/eventPage/couponImg.png" width="80px" class="item-img">
                    </div>
                </div>
                
                
                <button id="roulette-btn">START</button>
                <span id="roulette-pin"><img src="${contextPath}/resources/img/eventPage/pin.png" alt="" width="60px"></span>
            </div>
            

        

    </main>

   

    



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/eventPage.js"></script>
</body>
</html>