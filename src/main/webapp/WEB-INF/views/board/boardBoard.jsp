<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 
   <!-- <c:forEach var="adminType" items="${adminTypeList}">
       <c:if test="${adminCode == adminType.adminCode}">
           <c:set var="adminName" value="${adminType.adminName}" />
       </c:if>
   </c:forEach> -->
   
   
   <c:set var="pagination" value="${map.pagination}" />
   <c:set var="alarmList" value="${map.alarmList}" />
   <c:if test="${!empty param.key}">
    <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
</c:if>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <title>art_stroke 알립니다</title>
        
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
        <link href="${contextPath}/resources/css/board/boardBoard.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/board/boardBoard-main.css" rel="stylesheet" />
        <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet"> 
        
        
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>

<body class="sb-nav-fixed">

  <!-- 제목 -->
  <jsp:include page="/WEB-INF/views/common/header.jsp" />




  <div id="layoutSidenav">

  <div id="layoutSidenav_content">
    <main>
    
       <c:if test="${!empty param.key}">
            <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
        </c:if>
        `
        
        <div class="container-fluid px-4">

          <div class="admin-container"> 
          
            <div class="admin-main-header">
              <h2>공지사항</h2>
            </div>
  
  
  
        <div class="admin-product-list">    
         <div class="admin-main-nav">
            <div>
          
        </div>
           
         
            
        <div>
            <form action="${contextPath}/board/boardBoard?cp=${pagination.currentPage}${sURL}" method="get" id="boardSearch" onsubmit="return searchValidate()">

                <select name="key" id="search-key"  name="admin-main-nav-input"   placeholder="검색">
                    <option value="searchTitle">제목</option>
                    <option value="boardInner">내용</option>
                </select>

                <input type="text" name="query"  id="search-query" class="admin-main-nav-input" placeholder="검색어를 입력해주세요.">

               <button class="admin-btn">검색</button>
            </form>
        </div>
            </div>

           

          <div class="admin-main"> 
          
            <!-- 
            <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
            </c:if>
             -->
              <table class="admin-main-table" >
                    <thead>
                        <tr >
                            <th style="width:10%">번호</th>
                          
                            <th style="width:50%">제목</th>
                            <th style="width:10%">작성자</th>
                            <th style="width:20%">작성일</th>
                            <th style="width:10%">조회수</th>
                        </tr>
                      </thead>
                    <tbody>
                     <c:choose>
                               <c:when test="${empty alarmList}">
                                   <!-- 게시글 목록 조회 결과가 비어있다면 -->
                                   <tr>
                                       <th colspan="6">게시글이 존재하지 않습니다.</th>
                                   </tr>
                               </c:when>
   
                               <c:otherwise>
                                   <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
   
                                   <!-- 향상된 for문처럼 사용 -->
                                   <c:forEach var="alarm" items="${alarmList}" varStatus = "status">
                                       <tr onclick="location.href ='${contextPath}/board/boardBoardDetail/${alarm.alarmId}?cp=${pagination.currentPage}'" style = "cursor:pointer;">
                                           <td>${alarm.alarmId}</td>
                                         
                                         <!--     
                                           <td> 
                                               <c:if test="${!empty product.productImage}">
                                                   <img class="list-thumbnail" src="${contextPath}${product.productImage}">
                                               </c:if>  
   
                                               <a href="../detail/${boardCode}/${board.boardNo}?cp=${pagination.currentPage}${sURL}">${product.product}</a>              
                                             
                                           </td>
                                            
                                            -->
                                            
                                           <td>${alarm.alarmTitle}</td>
                                           <td>운영자</td>
                                           <td>${alarm.alarmDt}</td>
                                           <td>${alarm.alarmCnt}</td>
                                       </tr>
                                   </c:forEach>
   
                               </c:otherwise>
                         </c:choose>
                    </tbody>
                </table>
              </div> 
              
              
            <!-- 아직 구현 안함 -->
            


            
             <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언-->
                <c:set var="url" value="${adminCode}?cp="/> 

                <div>
                    
                    <ul class="pagination">
                        <!-- 첫 페이지로 이동 -->
                        <li><a href="${url}1${sURL}"><i class="fa-solid fa-angles-left"></i></a></li>
    
                        <!-- 이전 목록 마지막 번호로 이동 -->
                        <li><a href="${url}${pagination.prevPage}${sURL}"><i class="fa-solid fa-angle-left"></i></a></li>
    
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
                        <li><a class = "pagination-li" href="${url}${pagination.nextPage}${sURL}"><i class="fa-solid fa-chevron-right"></i></a></li>
    
                        <!-- 끝 페이지로 이동 -->
                        <li><a href="${url}${pagination.maxPage}${sURL}"><i class="fa-solid fa-angles-right"></i></a></li>
    
                    </ul>
                </div>
            </div>
              
              
              
              
          </div>
          
          <div class="admin-main-footer">
            <c:if test = "${loginMember.auth ==2}">
            <a href="${contextPath}/board/alarmWrite?type=insert"><button class="admin-btn">등록</button></a>
            <button class="admin-btn">삭제</button></c:if>
          </div>
        
        </div>
      </div>
     
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</div>
</div>
<c:if test="${ !empty message }">
    <script>
        alert("${message}");
        // EL 작성 시 scope를 지정하지 않으면
        // page -> request -> session -> application 순서로 검색하여
        // 일치하는 속성이 있으면 출력
    </script>
</c:if> 
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
</body>
</html>

 