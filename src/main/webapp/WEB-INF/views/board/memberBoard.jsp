<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--가져온 값 변수 선언-->

<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/css/board/board.css">
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <title>artStroke_획을 긋는 장소</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <%-- 검색을 진행한 경우 key, query를 쿼리스트링 형태로 저장한 변수 생성 --%>
    <c:if test="${!empty param.key}">
        <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
    </c:if>
    <header>
        <section class = "board-content-1">
            <div id = "board-title-area">   
                <div>
                    <!--community 소개 영역-->
                    <span class = "mb-title">art stroke.</span>
                    <span class = "mb-sub">Draw the world</span>
                </div>
            </div>
        </section>
    </header>
    <!--  -->
    <!-- <main class = "main-style">-->
        <!--제목과 이미지가 나타나는 구역-->
        <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
        </c:if>

        <section class = "board-content-2">
            <div id = "board-content-2-main">
                <div>
                    <span class = "font-gwang board-title-today">TOPIC</span>
                    <span class = "font-gwang board-title-today" style="font-size:20px; margin-bottom:100px">Feel your mind and Spell it out. Than you will find it</span>

                    <div id="carouselExampleIndicators" class="carousel slide gradient" data-bs-ride="true">



                      <div class="carousel-inner heightfull">
                        <c:choose>
                            <c:when test ="${empty boardList}">
                                <div class="carousel-item active heightfull">
                          
                                    <!-- 게시글 목록 조회 결과가 비어있다면 -->
                                    <div class ="noRecordField">
                                        <span style = "color:white; font-size:50px; margin:50px 0px 50px 0">No record</span>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                
                                <div class="carousel-item active heightfull">
                                    <c:forEach var="board" items="${boardList}" begin="1" end="5">  
                                        <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                            <div>
                                                <c:if test="${!empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/${board.boardFiles}" class="d-block w-100 imgSize" alt="...">
                                                </c:if> 
                                                <c:if test = "${empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                                </c:if>

                                            </div>
                                            <div class="item-sentence">
                                                <span style="font-size:15px;">${board.boardTitle}</span>
                                            </div> 
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class = "carousel-item heightfull">
                                    <c:forEach var="board" items="${boardList}" begin="6" end="10">  
                                        <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                            <div>
                                                <c:if test="${!empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/${board.boardFiles}" class="d-block w-100 imgSize" alt="...">
                                                </c:if> 
                                                <c:if test = "${empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                                </c:if>

                                            </div>
                                            <div class="item-sentence">
                                                <span style="font-size:15px;">${board.boardTitle}</span>
                                            </div> 
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class = "carousel-item heightfull">
                                    <c:forEach var="board" items="${boardList}" begin="11" end="15">  
                                        <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                            <div>
                                                <c:if test="${!empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/${board.boardFiles}" class="d-block w-100 imgSize" alt="...">
                                                </c:if> 
                                                <c:if test = "${empty board.boardFiles}">
                                                    <img class = "imgSize" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                                </c:if>

                                            </div>
                                            <div class="item-sentence">
                                                <span style="font-size:15px;">${board.boardTitle}</span>
                                            </div> 
                                        </div>
                                    </c:forEach>

                                </div>
                            
                            </c:otherwise>
                            </c:choose>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                        </button>
                      <button id = "next" class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                      </button>
                    </div>
                    <div class = "bestList-bottom">
                        <div class="carousel-indicators">
                            <button id = "firstBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-label="Slide 1"></button>
                            <button id = "midBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button id = "lastBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" class="lastBtn" aria-label="Slide 3"></button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <content>
            <!-- <div class = "board-searchPreview">                
                <div class = "board-search-text">
                    <p>검색 결과 :</p>
                </div>
            </div> -->
            
            <span class = "font-gwang board-title-today" style = "margin-top:100px;">The conversing masses</span>
            <span class = "font-gwang board-title-today" style="font-size:20px; margin-bottom:100px;">Feel your mind and Spell it out. Than you will find it</span>
            <div style ="display:flex; justify-content: center;">
                <div style ="display:flex; justify-content: end; width:1420px;">
                    <c:if test = "${!empty loginMember}">
                        <div class = "writeBtn" style ="cursor: pointer;" onclick="location.href='../boardWrite/${boardCode}?type=insert';">Write</div>
                    </c:if>
                </div>
            </div>
            <section class = "board-content-3">
                <div class = "board-card-area">
                    <div class="board-card-area-width">
                        <div>
                            
                            
                            <c:choose>
                                <c:when test ="${empty boardList}">
                                <!-- 게시글 목록 조회 결과가 비어있다면 -->
                                <div class ="noRecordField">
                                    <span style = "color:white; font-size:50px; margin:50px 0px 50px 0">No record</span>
                                </div>
                                </c:when>
                                <c:otherwise>
                                <c:forEach var="board" items="${boardList}">
                                    <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='../detail/${boardCode}/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                        <c:if test="${!empty board.boardFiles}">
                                            <div class = "board-card-img">
                                                <img src="${contextPath}/resources/images/boardImg/${board.boardFiles}" class="imgSize" alt="...">
                                            </div>
                                        </c:if>  
                                        <c:if test ="${empty board.boardFiles}">
                                            <div class = "board-card-img">
                                                <img class = "imgSize" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                            </div>
                                        </c:if>
                                        <div class = "item-sentence">
                                            <p>${board.boardContent}</p>
                                       </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                            </c:choose>
                            
                            
                            
                            
                            <!-- <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div>
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div>
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div> 
                            <div class = "board-card-element" style=" cursor: pointer;" onclick="location.href='*';">
                                <div class = "board-card-img">
                                    <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="imgSize" alt="...">
                                </div> 
                                <div class = "item-sentence">
                                     <p>뭐</p>
                                </div>
                            </div>-->
                        </div>
                    </div>
                </div>
            </section>
        
            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언-->
                <c:set var="url" value="${boardCode}?cp="/> 

                <div>
                    <!-- <ul class="pagination">
                        <li><a href="*">1</a></li>
                        <li><a href="*">2</li>
                        <li><a href="*">3</li>
                        <li><a href="*">4</li>
                        <li><a href="*">5</li>
                        <li><a href="*">&gt;</a></li>
                         끝 페이지로 이동
                        <li><a href="*">&gt;&gt;</a></li>
                    </ul> -->
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
            <div class = "search-area">
                <div class = "search-area-field">
                    <article class="search-area-article">
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                        <form action="#" name="search-form">
            
                            <!-- fieldset: form 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
                             <fieldset>
            
                                <!-- autocomplete="off" : HTML 기본 자동완성 사용 X -->
                                <input type="search" id="query" name="query" 
                                autocomplete="off" placeholder="검색어를 입력해주세요.">
            
                                <!-- 검색 버튼 -->
                                <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>  
                            </fieldset>
                        </form>
                    </article>
                </div>
            </div>
        </content>
        <!-- footer  -->
	
    <!-- </main>  -->
    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    <script src ="${contextPath}/resources/js/board/board.js"></script>
    <script src ="${contextPath}/resources/js/board/boardstrap.js"></script>
</body>
</html>