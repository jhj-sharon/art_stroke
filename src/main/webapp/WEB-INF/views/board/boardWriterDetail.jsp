<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardWriterDetail.css">
    <title>artstroke 작가명</title>
</head>
<body>
    
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <main class="main-style">

        <!--작가 상세페이지의 설명부분 대표 섬네일과 -->
        <section class="contents-wrap">
            <div class = "boardWriterDetail-WriterProfile-Area">
                <div class = "boardWriterDetail-WriterProfile-img-Area">
                    <div class = "boardWriter-circle">
                        <img class = "boardWriterDetail-img-content" src = "${contextPath}/resources/images/boardImg/boardWriterDefault.jpg">
                    </div>
                </div>
                <div class = "boardWriterDetail-WriterProfile-text-Area">
                    <div class ="heightfull boardWriterDetail-profile">
                        <div class = "bwd-t">
                            <span class = "boardWriterDetail-content-Writer">작가명</span>
                            <span class = "boardWriterDetail-content-follow font-nano-sub">팔로우</span>
                            <span class = "boardWriterDetail-content-letter font-nano-sub">쪽지보내기</span>
                        </div>
                        
                        <div class = "boardWriterDetail-content-sub">작가소개dd\asdsfasfddsafsdafdsasdfasdfsdafsafdasdfasdfasdfasfsafaff;</div>
                        <button class = "boardWriterDetail-goSns">Go SNS</button>
                    </div>
                </div>
            </div>
            
        </section>

        <section class="contents-wrap">
            <div class = "boardWriterDetail-area">
                <div class = "heightfull">
                    <div class ="boardWriterDetail-direction">
                        <span class = "boardWriterDetail-title">Writer's product</span>
                        <span class = "boardWriterDetail-sub">Choose products And Enhance the quality of your life</span>
                    </div>
                    <!--제품 리스트가 들어갈 영역-->
                    <div class = "boardWriterDetail-content-area">
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="contents-wrap">
            <div class = "boardWriterDetail-area">
                <div class = "heightfull">
                    <div class ="boardWriterDetail-direction">
                        
                        <span class = "boardWriterDetail-title">Writer's product</span>
                        <span class = "boardWriterDetail-sub">Choose products And Enhance the quality of your life</span>
                    </div>
                    <!--제품 리스트가 들어갈 영역-->
                    <div class = "boardWriterDetail-content-area">
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                        <div class = "boardWriterDetail-content-element">
                            <div class = "boardWriterDetail-product-area">
                                <div class = "imgFlex"><img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg"></div>
                                <div class = "contextFlex">
                                    <span class = "BWD-pTitle">제목</span>
                                    <span class = "BWD-pContext">작품 가격</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>