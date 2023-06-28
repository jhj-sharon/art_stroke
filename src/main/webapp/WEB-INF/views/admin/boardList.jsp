<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<c:forEach var="adminType" items="${adminTypeList}">
	    <c:if test="${adminCode == adminType.adminCode}">
	        <c:set var="adminName" value="${adminType.adminName}" />
	    </c:if>
	</c:forEach>
	
	
	<c:set var="pagination" value="${map.pagination}" />
	<c:set var="boardList" value="${map.boardList}" />
		<c:set var="member" value="${map.memberList}" />
	


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>${adminName }</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-chat.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-data-table.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>

<body class="sb-nav-fixed">

  <!-- 제목 -->
  <jsp:include page="/WEB-INF/views/common/adminHeader.jsp" />




  <div id="layoutSidenav">
  <jsp:include page="/WEB-INF/views/common/adminSideMenu.jsp" />

  <div id="layoutSidenav_content">
    <main>
   		
   		<c:if test="${!empty param.key}">
            <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
        </c:if>
        
   
        
        <div class="container-fluid px-4">
            <div class="admin-container"> 
                <div class="admin-main-header">
                    <h2  >
                        <a href="${contextPath}/admin/board/5" class="main-title">
                          게시판 목록
                        </a>
                      </h2>
                  </div>
      
                <div class="admin-main-nav">
                  <div>
                   <input type="radio" name="displayOption3" id="allButton3" value="all3" onchange="boardApply()" checked>전체
                    <input type="radio" name="displayOption3" id="normalButton3" value="normal3" onchange="boardApply()">일반
                    <input type="radio" name="displayOption3" id="withdrawnButton3" value="withdrawn3" onchange="boardApply()">작가
                </div>
                  <div>
                    <form action="${adminCode}" method="get" id="boardListSearch" onsubmit="return searchValidate()">
        
                        <select name="key" id="search-key">
             
                    <option value="tc">제목+내용</option>
                    <option value="w">작성자</option>
                </select>
        			
                        <input type="text" name="query"  id="search-query" class="admin-main-nav-input" placeholder="검색어를 입력해주세요.">
        
                       <button class="admin-btn">검색</button>
                    </form>
                </div>
      
                </div>
      
                <div class="admin-main"> 
                
                <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
           		</c:if>
            
                
              <table class="admin-main-table" id="boardListTable">
                    <thead>
                        <tr>
                           <th colspan="2">번호</th>
                           <th>이미지</th>
                            <th>제목</th>
                          	 
                            <th>작성자</th>
                            <th>조회수</th>
                            <th>등록일</th>
                         	
                        </tr>
                      </thead>
                    <tbody>
 						 <c:choose>
	                            <c:when test="${empty boardList}">
	                                <!-- 게시글 목록 조회 결과가 비어있다면 -->
	                                <tr>
	                                    <th colspan="6">게시글이 존재하지 않습니다.</th>
	                                </tr>
	                            </c:when>
	
	                             <c:otherwise>
							        <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
							        <c:forEach var="boardList" items="${boardList}">
							            <tr>
                                            <td><input type="checkbox" name="boardChk" value="${boardList.boardId}" id="boardChkbox" ></td>
                          
							                <td>${boardList.boardId}</td> 
							              <c:choose>
											    <c:when test="${empty boardList.boardFile2}">
											        <td>
											            <p>이미지 없음</p>
											        </td>
											    </c:when>
											    <c:otherwise>
											        <td>
											            <img src="${boardList.boardFile2}" style="width: 80px; height: 80px">
                                                         
											        </td>

											    </c:otherwise>
												</c:choose>
							               	<td>
			                                    <a href="#" onclick="window.open('${contextPath}/board/detail/1/${boardList.boardId}?cp=${pagination.currentPage}', 'popupWindow', 'width=1500,height=1500,location=no,status=no,scrollbars=yes'); return false;">
			                                        ${boardList.boardTitle}
			                                    </a>
			                                </td>
							               
							                <td>${boardList.memberNick}</td>
							                <td>${boardList.boardCNT}</td>
							                <td>${boardList.boardDt}</td> 
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
                
                <div class="admin-main-footer">
                    <input type="hidden" name="adminCode" value="${adminCode}">
				    <button type="submit" class="admin-btn" id="boardBtn">삭제</button>
		 
                </div>
              
              </div>
        </div>
    </main>
    <div class="modal">
        <span id="modal-close">&times;</span>
        <img id="modal-image" src="${contextPath}/resources/img/user.png">
    </div>
    
    <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
</div>
</div>

<script src="${contextPath}/resources/js/admin/admin-chat.js"></script> 
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</body>
</html>

 