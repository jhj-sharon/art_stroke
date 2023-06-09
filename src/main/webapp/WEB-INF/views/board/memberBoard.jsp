<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--가져온 값 변수 선언-->

<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />
<c:set var = "boardBestList" value = "${map.boardBestList}"/>
<c:set var = "sort" value = "${map.sort}"/>
<c:set var="size" value="0" />

<c:forEach items="${boardBestList}" var="item">
  <c:set var="size" value="${size + 1}" />
</c:forEach>
<script>
    let boardType = "${boardTYPE}";
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/css/board/board.css">
    
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    
    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <title>art Stroke</title>
</head>



<body>
    <!-- header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>


    <%-- 검색을 진행한 경우 key, query를 쿼리스트링 형태로 저장한 변수 생성 --%>
    <c:if test="${!empty param.key}">
        <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
    </c:if>


    <!--community 소개 영역-->
    <c:choose>
    <c:when test="${boardCode==1}">
    <section class = "board-content-1">
        
        <img src="${contextPath}/resources/img/board/memberBoardImg.jpg" alt="멤버게시판 이미지">  
        <div id = "board-title-area"> 
            <span>Board For Members</span>
            <span>The Conversing masses</span>
            <span>: Feel your mind and spell it out. Then, you will find it.</span>
        </div>
        
    </section>
    </c:when>
    <c:when test = "${boardCode == 2}">
    <section class = "board-content-1">
        
        <img src="${contextPath}/resources/img/board/artistBoardImg.jpg" alt="작가게시판 이미지">  
        <div id = "board-title-area"> 
            <span>Board For Artists</span>
            <span>The Conversing masses</span>
            <span>: Feel your mind and spell it out. Then, you will find it.</span>
        </div>
        
    </section>
    </c:when>
    </c:choose>

    <main class = "main-style">
        <c:choose>
            <c:when test="${boardCode == 2}">

            </c:when>
            <c:otherwise>
                <c:if test = "${size>0}">
                <section class = "board-content-2">
                    <div id = "board-content-2-main">
                        <div>
                            <span class = "font-gwang board-title-today">Trending Posts</span>
                            <div id="carouselExampleIndicators" class="carousel slide gradient" data-bs-ride="true">
        
        
        
                              <div class="carousel-inner heightfull">
                                <c:choose>
                                    <c:when test ="${empty boardList}">
                                        <div class="carousel-item active heightfull">
                                  
                                            <!-- 게시글 목록 조회 결과가 비어있다면 -->
                                            <div class ="noRecordField">
                                                <span style = "color:white; font-size:50px; margin:50px 0px 50px 0">작성된 게시글이 없습니다.</span>
                                            </div>
                                        </div>
                                    </c:when>


                                    <c:otherwise>
                                        <!-- 오늘 작성한 게시글 -->
                                        <div class="carousel-item active heightfull">
                                            <c:forEach var="board" items="${boardBestList}" begin="0" end="4">  
                                                <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${boardCode}/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                                    <div>
                                                        <c:if test="${!empty board.boardFile2}">
                                                            <img class = "imgSize" src="${board.boardFile2}" class="d-block w-100 imgSize" alt="...">
                                                        </c:if> 
                                                        <c:if test = "${empty board.boardFile2}">
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
                                            <c:forEach var="board" items="${boardBestList}" begin="5" end="9">  
                                                <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${boardCode}/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                                    <div>
                                                        <c:if test="${!empty board.boardFile2}">
                                                            <img class = "imgSize" src="${board.boardFile2}" class="d-block w-100 imgSize" alt="...">
                                                        </c:if> 
                                                        <c:if test = "${empty board.boardFile2}">
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
                                            <c:forEach var="board" items="${boardBestList}" begin="10" end="14">  
                                                <div class = "bestList-item" style=" cursor: pointer;" onclick="location.href='../detail/${boardCode}/${board.boardId}?cp=${pagination.currentPage}${sURL}';">
                                                    <div>
                                                        <c:if test="${!empty board.boardFile2}">
                                                            <img class = "imgSize" src="${board.boardFile2}" class="d-block w-100 imgSize" alt="...">
                                                        </c:if> 
                                                        <c:if test = "${empty board.boardFile2}">
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


                                <c:if test = "${size>0}">
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <i class="fa-solid fa-chevron-right"></i>
                                </button>
                                <button id = "next" class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <i class="fa-solid fa-chevron-left"></i>
                                </button>
                            </c:if>
                            </div>


                            <div class = "bestList-bottom">
                                <div class="carousel-indicators">
                                    <c:if test = "${size>0}">
                                        <button id = "firstBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-label="Slide 1"></button>
                                    </c:if>
                                    <c:if test = "${size>5}">
                                        <button id = "midBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                    </c:if>
                                    <c:if test = "${size>10}">
                                       <button id = "lastBtn" style = "background-color:gray;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" class="lastBtn" aria-label="Slide 3"></button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </c:if>
            </c:otherwise>
        </c:choose>
        <!— 캐러셀 끝 —>


        <!-- 셀렉트버튼, 글쓰기 버튼  -->
        <section class="board-button-area">

            <!-- 셀렉트 버튼 -->
            <div class="board-select-area">
                <select name="languages" id="lang" style ="display:flex; align-items: bottom;">
                    <option value="date">최신순</option>
                    <option value="cnt">조회순</option>
                    <option value="good">좋아요 순</option>
                </select>
            </div>

            <!-- 글쓰기 버튼 -->
            <c:if test = "${!empty loginMember}">
                <c:choose>
                    <c:when test = "${boardCode ==2}">
                        <c:if test = "${loginMember.auth == 1}">
                            <div style="flex-basis:50%; display:flex; justify-content:end; align-items: end;">
                                <div class = "writeBtn" style ="cursor: pointer;" onclick="location.href='../boardWrite/${boardCode}?type=insert';">Write</div>
                            </div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <div class="board-writeBtn-area">
                            <div class = "writeBtn" style ="cursor: pointer;" onclick="location.href='../boardWrite/${boardCode}?type=insert';">Write</div>
                        </div>
                    </c:otherwise> 
                </c:choose>
                
            </c:if> 
                    
        </section>


            <!-- 게시글 -->
            <section class = "board-content-3" id = "board-card-detail">
                <!-- <div class = "board-card-area">
                    <div class="board-card-area-width">
                        <div id = "board-card-detail"> -->
                            
                            
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
                                        <c:if test="${!empty board.boardFile2}">
                                            <div class = "board-card-img">
                                                <img src="${board.boardFile2}" class="imgSize" alt="...">
                                            </div>
                                        </c:if>  
                                        <c:if test ="${empty board.boardFile2}">
                                            <div class = "board-card-img">
                                                <img class = "imgSize" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                            </div>
                                        </c:if>
                                        <div class = "item-sentence">
                                            <p>${board.boardTitle}</p>
                                       </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                            </c:choose>
                            
                            
                            
                            
                            
                        <!-- </div>
                    </div>
                </div> -->
            </section>
        
            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언-->
                <c:set var="url" value="${boardCode}?cp="/> 

                <div id = "paginationContainer">
                    
                    <ul class="pagination">
                        <c:if test = "${pagination.listCount>0}">
                        <!-- 첫 페이지로 이동 -->
                        <li><a href="${url}1${sURL}&sort=${param.sort}"><i class="fa-solid fa-angles-left"></i></a></li>
    
                        <!-- 이전 목록 마지막 번호로 이동 -->
                        <li><a href="${url}${pagination.prevPage}${sURL}&sort=${param.sort}"><i class="fa-solid fa-angle-left"></i></a></li>
                    </c:if>
                        <!-- 범위가 정해진 일반 for문 사용 -->
                        <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
    
                            <c:choose>
                                <c:when test="${i == pagination.currentPage}">
                                    <li><a class="current">${i}</a></li>
                                </c:when>
    
                                <c:otherwise>
                                    <li><a href="${url}${i}${sURL}&sort=${param.sort}">${i}</a></li>        
                                </c:otherwise>
                            </c:choose>
    
                        </c:forEach>
                        <c:if test = "${pagination.listCount>0}">
                        <!-- 다음 목록 시작 번호로 이동 -->
                        <li><a href="${url}${pagination.nextPage}${sURL}&sort=${param.sort}"><i class="fa-solid fa-chevron-right"></i></a></li>
    
                        <!-- 끝 페이지로 이동 -->
                        <li><a href="${url}${pagination.maxPage}${sURL}&sort=${param.sort}"><i class="fa-solid fa-angles-right"></i></a></li>
                    </c:if>
                    </ul>
                </div>
            </div>
            <div class = "search-area">
                <div class = "search-area-field">
                    <article class="search-area-article">
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                        <form action="${contextPath}/board/searchBoard/${url}${pagination.currentPage}${sURL}" name="search-form">
            
                            <!-- fieldset: form 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
                             <fieldset>
                                <select name="key" id="searchKey" style ="display:flex; align-items: bottom;">
                                    <option value="searchTitle">제목</option>
                                    <option value="writer">작가 명</option>
                                    <option value="boardInner">내용</option>
                                </select>
                                <!-- autocomplete="off" : HTML 기본 자동완성 사용 X -->
                                <input type="search" id="query" name="query" autocomplete="off">
                                <!-- 검색 버튼 -->
                                <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>  
                            </fieldset>
                        </form>
                    </article>
                </div>
            </div>


    </main> 


	<script>
    const boardCode = "${boardCode}";
    var sURL = "${sURL}";
    var cp = ${pagination.currentPage};
    var url = "${boardCode}?cp=";
    const contextPath = "${contextPath}";
    
    const sortValue = "${param.sort}"
        var query = "${param.query}";
        var key = "${param.key}";
    
    </script>



    


    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>


    <!-- footer -->
    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    
    <!-- js -->
    <script src ="${contextPath}/resources/js/board/board.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src ="${contextPath}/resources/js/board/boardstrap.js"></script>
    
</body>
</html>

