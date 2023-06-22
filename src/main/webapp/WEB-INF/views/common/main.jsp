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
<link rel="stylesheet" href="${contextPath}/resources/css/mainpage.css">
<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
	
</head>
<body>

	<!-- header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />


   
	 <!-- 이벤트 팝업 -->
	 <section class="mainpage-event-popup">
	
	    <img src="${contextPath}/resources/img/mainpage/popupEventImg.jpg" alt="">
	
	    <!-- 팝업 링크 -->
	    <a href="${contextPath}/event/rouletteEvent">룰렛 돌리러 가기</a>
	
	
	    <!-- 팝업 닫기 -->
	    <div class="mainpage-event-popup-close-area">
	        <div id="mainpage-event-popup-close-btn">
	            닫기
	        </div>
	        <div id="mainpage-event-popup-nottoday-btn">
	            오늘하루안보기
	        </div>
	    </div>
	</section><!-- 이벤트 팝업 끝 -->         

    <div class="mainpage-img-area">
        <img src="${contextPath}/resources/img/mainpage/mainImg.gif" alt="" width="100%">
        <div>
            <span>enjoy art everywhere</span>
            <span>art Stroke.</span>
        </div>
    </div>

    <main class="main-style">

        <!-- 이벤트 -->
        <section class="contents-wrap">
            <span class="mainpage-content-title">EVENT</span>
            <div class="mainpage-event-window">
                <ul class="mainpage-event-container">
                    <li class="mainpage-event-cell">
                        <a href=""><img src="${contextPath}/resources/img/mainpage/eventSample1.jpg" alt="" width="300px"></a>
                        <a href=""><img src="${contextPath}/resources/img/mainpage/eventSample2.jpg" alt="" width="300px"></a>
                    </li>
                    <li class="mainpage-event-cell">
                        <a href=""><img src="${contextPath}/resources/img/mainpage/eventSample3.jpg" alt="" width="300px"></a>
                        <a href=""><img src="${contextPath}/resources/img/mainpage/eventSample4.jpg" alt="" width="300px"></a>
                    </li>
                </ul> 
                
                <div class="mainpage-event-btn1">
                    <button id="mainpage-event-lbtn"><i class="fa-solid fa-chevron-left"></i></button>
                    <button id="mainpage-event-rbtn"><i class="fa-solid fa-chevron-right"></i></button>
                </div>

                <div class="mainpage-event-btn2">
                    <button id="mainpage-event-lbtn2"></button>
                    <button id="mainpage-event-rbtn2"></button>
                </div>
            </div>
    
        </section>
        

        <!-- 제품 -->
        <section class="contents-wrap">


            <!-- 베스트 -->
            <article style="margin-top: 100px;">
                <span class="mainpage-content-title">BEST</span>
                <div class="mainpage-best-container">

                    <div class="mainpage-best-category-wrap">
                        <div class="mainpage-best-category-menu" self="size-x1" layout="row center-left">
                            <div class="mainpage-best-category-highlight"></div>
                            <div class="mainpage-best-category-selector-item" onclick="selectItem(event)" data-category="포스터">Poster</div>
                            <div class="mainpage-best-category-selector-item" onclick="selectItem(event)" data-category="홈패브릭">Home Fabric</div>
                            <div class="mainpage-best-category-selector-item" onclick="selectItem(event)" data-category="스마트폰 케이스">Phone Case</div>
                        </div>
                      </div>

                    <div class="mainpage-best-product-wrap">
                        <ul class="product-list">
                            <!-- 베스트 상품 item -->
                        </ul>

                        <div>
                            <button id="mainpage-best-lbtn"><i class="fa-solid fa-chevron-left"></i></button>
                            <button id="mainpage-best-rbtn"><i class="fa-solid fa-chevron-right"></i></button>
                        </div>
                    </div>
                </div>
            </article>
        </section><!-- 상품 끝 -->


        <!-- 아티스트 소개 -->
        <section class="contents-wrap">

            <!-- hot artist -->
            <article>
                <div class="mainpage-artist-container">
                    <span class="mainpage-content-title">HOT ARTIST</span>
                    <div>
                        <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">

                        <div class="mainpage-artist-container-info">
                            <span>
                                <span>KIMMAE</span>
                                <span>illustrator</span>
                                <a href="#"><i class="fa-solid fa-house"></i></a>
                            
                                <div>
                                    외계에 살고 있던 키캐스. <br><br>
    
                                    혼자 살기에 부족할 것 없었던 핑크빛의 행성이지만, <br>
                                    어쩐지 고독한 자기의 고향을 떠나기로 결심한다. <br><br>
                                    
                                    또 다른 행성으로 여행을 떠나며 겪는 이야기를 기록하는 그림일기. <br>
                                </div>
                            </span>

                            

                            <div class="mainpage-artist-product">
                                <ul class="product-list" id="main-kimmae">
                                   <!-- 키매 상품 아이템 -->
                                </ul>
                            </div>
                        </div> <!-- mainpage-artsit-container-info end -->
                    </div>
                </div> <!-- mainpage-artist-container end-->
            </article> <!-- mainpage-artist-wrap end  -->

             <!-- hot artist -->
             <article style="margin-top: 50px;">
                <div class="mainpage-artist-container">
                    <div>
                        <div class="mainpage-artist-container-info2" >
                            <span>
                                <a href="#"><i class="fa-solid fa-house"></i></a>
                                <span>illustrator</span>
                                <span>HYPER PENSION</span>
                            
                                <div>
                                    하이퍼펜션이 만드는 모든 것들은<br><br>
                                    창문 틈으로 오래된 노래와 노란 조명이 흘러 나오는<br><br>
                                    흥겨운 펜션같은 분위기를 지향합니다.
                                </div>
                            </span>
                            <div class="mainpage-artist-product">
                                <ul class="product-list" id="main-hyperpension">
                                    <!-- 하이퍼펜션 상품 아이템 -->
                                </ul>
                            </div>
                        </div> <!-- mainpage-artsit-container-info end -->
                        <img src="${contextPath}/resources/img/mainpage/hyperpension.jpeg" alt="">
                    </div>
                </div> <!-- mainpage-artist-container end-->
            </article> <!-- mainpage-artist-wrap end  -->
        </section> <!-- 아티스트 소개 끝 -->



        <!-- 베스트 리뷰 -->
        <section class="contents-wrap">
            <span class="mainpage-content-title">BEST REVIEW</span>

            <article class="mainpage-review-container">
                
                <div class="mainpage-review-item"><!-- 리뷰 아이템 -->
                    <div class="mainpage-review-img">
                        <img src="https://storybook-cdn.sweetoffer.co.kr/c001/tounou/img/1678163850392-304218.webp">
                    </div>

                    <div>
                        <div class="mainpage-review-item-profile">
                            <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">
                            <span>닉네임1</span>
                        </div>
                        <div class="mainpage-review-item-content">
                            나리는 꽃가루에 눈이 따끔해
                            눈물이 고여도 꾹 참을래
                            내 마음 한켠 비밀스런 오르골에 넣어두고서
                            영원히 되감을 순간이니까 
                        </div>
                        <span>
                            #작가이름 &nbsp; #홈패브릭
                        </span>
                    </div>
                </div><!-- 리뷰 아이템  end-->
                <div class="mainpage-review-item"><!-- 리뷰 아이템 -->
                    <div>
                        <img src="https://storybook-cdn.sweetoffer.co.kr/c001/tounou/img/1675733623823-469470.webp?w=640&q=80">
                    </div>

                    <div>
                        <div class="mainpage-review-item-profile">
                            <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">
                            <span>닉네임2</span>
                        </div>
                        <div class="mainpage-review-item-content">
                            우리 둘의 마지막 페이지를 잘 부탁해
                            어느 작별이 이보다 완벽할까
                            Love me only till this spring
                        </div>
                        <span>
                            #작가이름 &nbsp; #포스터
                        </span>
                    </div>
                </div><!-- 리뷰 아이템  end-->
                <div class="mainpage-review-item"><!-- 리뷰 아이템 -->
                    <div>
                        <img src="https://storybook-cdn.sweetoffer.co.kr/c001/tounou/img/1677478105873-764494.webp?w=640&q=80">
                    </div>

                    <div>
                        <div class="mainpage-review-item-profile">
                            <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">
                            <span>닉네임3</span>
                        </div>
                        <div class="mainpage-review-item-content">
                            오 라일락 꽃이 지는 날 goodbye
                            이런 결말이 어울려
                            안녕 꽃잎 같은 안녕
                            하얀 우리 봄날의 climax
                            아 얼마나 기쁜 일이야
                        </div>
                        <span>
                            #작가이름 &nbsp; #포스터
                        </span>
                    </div>
                </div><!-- 리뷰 아이템  end-->
                <div class="mainpage-review-item"><!-- 리뷰 아이템 -->
                    <div>
                        <img src="https://storybook-cdn.sweetoffer.co.kr/c001/tounou/img/1678757607152-762714.webp?w=640&q=80">
                    </div>

                    <div>
                        <div class="mainpage-review-item-profile">
                            <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">
                            <span>닉네임4</span>
                        </div>
                        <div class="mainpage-review-item-content">
                            기분이 달아 콧노래 부르네 (랄라)
                            입꼬리는 살짝 올린 채
                            어쩜 이렇게 하늘은 더 바람은 또 완벽한 건지
                            오늘따라 내 모습 맘에 들어
                        </div>
                        <span>
                            #작가이름 &nbsp; #홈패브릭
                        </span>
                    </div>
                </div><!-- 리뷰 아이템  end-->
            </article>

             <!-- 리뷰 모달 -->
            <div class="mainpage-review-modal-overlay">
                <div class="mainpage-review-modal-window">
                    <div class="mainpage-review-modal-container">
                        <div class="mainpage-review-modal-close"><button>&times;</button></div>
                        <div class="mainpage-review-modal-img">
                            <img src="https://storybook-cdn.sweetoffer.co.kr/c001/tounou/img/1677478105873-764494.webp?w=640&q=80">
                            <button id="mainpage-review-modal-product-btn"><i class="fa-solid fa-plus"></i></button>
                            
                            <a href="">
                                <div class="mainpage-review-modal-product">
                                    <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="이벤트모달상품">
                                    <div>
                                        <span style="font-weight: 500;">어쩌구저쩌구 핸드폰케이스</span>
                                        <span>하이프펜션</span>
                                        <span>19,900원</span>
                                    </div>
                                </div>
                            </a>
                       
                        </div>
                        <div class="mainpage-review-modal-contents">
                            <div class="mainpage-review-modal-profile">
                                <img src="${contextPath}/resources/img/mainpage/kimmae.jpeg" alt="">
                                <span>닉네임4</span>
                            </div>
                            <div>
                                기분이 달아 콧노래 부르네 (랄라)
                                입꼬리는 살짝 올린 채
                                어쩜 이렇게 하늘은 더 바람은 또 완벽한 건지
                                오늘따라 내 모습 맘에 들어
                            </div>
                            <div>#작가이름 &nbsp; #홈패브릭</div>
                        </div>
                    </div>
                </div>
                <div class="mainpage-review-modal-btn">
                    <button><i class="fa-solid fa-chevron-left"></i></button>
                    <button><i class="fa-solid fa-chevron-right"></i></button> 
                </div>
            </div>
            
        </section>
        
    </main>


    <!-- context Path 전달 -->
    <%String contextPath = request.getContextPath(); %>
    <input type="hidden" id="eventContextPath" value="<%= contextPath %>" />


    <!-- loginMember session값 전달 -->
    <% Object mainLoginMember = session.getAttribute("loginMember");%>
    <input type="hidden" id="mainLoginMember" value="<%= mainLoginMember %>" />


	<!-- footer  -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/mainpage.js"></script>
</body>
</html>