<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<c:forEach var="adminType" items="${adminTypeList}">
	    <c:if test="${adminCode == adminType.adminCode}">
	        <c:set var="adminName" value="${adminType.adminName}" />
	    </c:if>
	</c:forEach>
	
	<c:forEach var="boardType" items="${boardTypeList}">
	    <c:if test="${boardCode == boardType.boardCode}">
	        <c:set var="boardName" value="${boardType.boardName}" />
	    </c:if>
	</c:forEach>
 
	<c:set var="pagination" value="${map.pagination}" />
	<c:set var="reportList" value="${map.reportList}" />
	


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Admin</title>
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
        <div class="container-fluid px-4">
            <div class="admin-container"> 
                <div class="admin-main-header">
                 
                    <h2  >
                      <a href="${contextPath}/admin/member/report" class="main-title">
                        신고 관리
                      </a>
                    </h2>
               
                  </div>
      
                  <div class="admin-member-report-list">
                <div class="admin-main-nav">
                  <div>
                   <input type="radio" name="displayOption5" id="allButton5" value="all" onchange="reportApply()" checked>전체
                    <input type="radio" name="displayOption5" id="normalButton5" value="normal" onchange="reportApply()">미처리
                    <input type="radio" name="displayOption5" id="withdrawnButton5" value="withdrawn" onchange="reportApply()">완료
                </div>
                

                <div>
                  <form action="${adminCode}" method="get" id="boardSearch" name="searchForm" onsubmit="return searchValidate()">
                      <select name="key" id="search-key" name="admin-main-nav-input" placeholder="검색">
                          <option value="w">신고번호</option>
                          <option value="tc">신고자</option>
                      </select>
                      <input type="text" name="query" id="search-query" class="admin-main-nav-input" placeholder="검색어를 입력해주세요.">
                      <button class="admin-btn">검색</button>
                  </form>
              </div>
              
      
                </div>
      
               <div class="admin-main"> 
                <div id="admin-report-main">
                <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
           		</c:if>
            	
               
                
              <table class="admin-main-table" id="reportTable">
                    <thead>
                        <tr>
                            <th colspan="2">번호</th>
                            <th>유형</th>
                            <th>제목</th>
                            <th>신고내용</th>
                            <th>신고자</th>
                            <th>신고당한회원</th>
                            <th>처리여부</th>
                        </tr>
                      </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty reportList}">
                              <!-- 게시글 목록 조회 결과가 비어있다면 -->
                              <tr>
                                <th colspan="8">신고 내역이 존재하지 않습니다.</th>
                              </tr>
                            </c:when>
                            
                            <c:otherwise>
                              <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
                              <c:forEach var="report" items="${reportList}">
                                <tr>
                                  <td><input type="checkbox" name="reportChk" value="${report.reportId}" id="reportChkbox" ></td>
                                  <td>${report.reportId}</td> 
                                  <td>${report.reportTargetType}</td>
                                  <td>
                                    <a href="#" onclick="window.open('${contextPath}/board/detail/1/${report.reportTarget}?cp=${pagination.currentPage}', 'popupWindow', 'width=1500,height=1500,location=no,status=no,scrollbars=yes'); return false;">
                                        ${report.reportTargetTitle}
                                    </a>
                                </td>
                                
                                <td>
                                <a href="#" onclick="window.open('${contextPath}/admin/member/report/detail?reportContent=${report.reportContent}', 'popupWindow', 'width=1500,height=1500,location=no,status=no,scrollbars=yes'); return false;">
                                ${report.reportContent}
                                </a>

                                </td>
                                
                                  <td>
                                    <a href="#" onclick="window.open('${contextPath}/admin/member/reportMessage/${report.reportSendId}/writeForm?reportSendNick=${report.reportSendNick}', 'popupWindow', 'width=600,height=600,location=no,status=no,scrollbars=yes'); return false;">
                               
                                    ${report.reportSendNick}
                                      </a>
                                  </td>
                                               
                                  <td>${report.reportTargetNick}</td>
                                  <td>${report.reportStatus}</td> 
                                </tr>
                              </c:forEach>
                            </c:otherwise>
                          </c:choose>
                          
                    </tbody>
                </table>
             </div>
                
             <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언  -->
                <c:set var="url" value="?cp="/>
				<div>
                   <ul class="pagination">
                        <!-- 첫 페이지로 이동 -->
                        <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
    
                        <!-- 이전 목록 마지막 번호로 이동 -->
                        <li><a href="${url}${pagination.currentPage - 1}${sURL}">&lt;</a></li>
    
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
                        <li><a href="${url}${pagination.currentPage + 1}${sURL}">&gt;</a></li>
    
                        <!-- 끝 페이지로 이동 -->
                        <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
    
                    </ul>
               </div>
            </div>	
               
              </div>
	          </div>
              <div class="admin-main-footer">
                <input type="hidden" name="adminCode" value="${adminCode}">
                <button type="submit" class="admin-btn BBtn" id="reportBtn">처리완료</button>
              </div>
              </div>
                 
           
               
        </div>
    </main>
    <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
</div>
</div>

<c:if test="${ !empty message }">
    <script>
        alert("${message}");
    </script>
</c:if>
 
 
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-common.js"></script>

<script src="${contextPath}/resources/js/admin/admin-chat.js"></script> 
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</body>
</html>

 