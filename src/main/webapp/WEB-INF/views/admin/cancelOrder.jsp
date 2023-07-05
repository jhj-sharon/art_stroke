<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<c:forEach var="adminType" items="${adminTypeList}">
	    <c:if test="${adminCode == adminType.adminCode}">
	        <c:set var="adminName" value="${adminType.adminName}" />
	    </c:if>
	</c:forEach>
	
	
	<c:set var="pagination" value="${map.pagination}" />
	<c:set var="cancelOrderList" value="${map.cancelOrderList}" />
 



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
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      
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
                        <a href="${contextPath}/admin/order/cancel" class="main-title">
                          취소 요청 목록
                        </a>
                      </h2>
                  </div>
               
                <div class="admin-main-navv-order">
                  
                    <div class="admin-main-nav-div"> 
                       
                    <div>
                        <form action="${adminCode}" method="get" id="orderSearch" onsubmit="return searchValidate()">
        
                        <select name="key" id="search-key"  name="admin-main-nav-input"   placeholder="검색">
                            <option value="t">주문번호</option>
                            <option value="c">주문자</option>
                        </select>
        			
                        <input type="text" name="query"  id="search-query" class="admin-main-nav-input" placeholder="검색어를 입력해주세요.">
        
                       <button class="admin-btn">검색</button>
                    </form>
                    </div>
                    </div>
                    
               
           
      
                </div>
            
           

                <div class="admin-main">
                <div>
                <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
           		</c:if>

				   
              <table class="admin-main-table" id="cancelTable">
                    <thead>
                        <tr>
                        
                           <th colspan="2">주문번호</th> 
                            <th>주문자</th>
                            <th>주문상품</th>
                            <th>취소사유</th>
                            <th>요청일</th>
                            <th>승인여부</th>
                            <th>처리일</th>  
                        </tr>
                      </thead>
                    <tbody>
 						 <c:choose>
	                            <c:when test="${empty cancelOrderList}">
	                                <!-- 게시글 목록 조회 결과가 비어있다면 -->
	                                <tr>
	                                    <th colspan="8">주문내역이 존재하지 않습니다.</th>
	                                </tr>
	                            </c:when>
	
	                            <c:otherwise>
	                                <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
	
	                                <!-- 향상된 for문처럼 사용 -->
	                                <c:forEach var="cancelOrderList" items="${cancelOrderList}">
	                                    <tr>
                                            <td><input type="checkbox" name="cancelChk" value="${cancelOrderList.cancelorderId}" id="cancelChkbox" ></td>
                          
	                                        <td>${cancelOrderList.orderId}</td>
	                                        <td>
                                                <a href="#" class="" onclick="window.open('${contextPath}/admin/member/message/${cancelOrderList.memberId}/writeForm?memberNick=${cancelOrderList.memberNick}', 'popupWindow', 'width=600,height=600,location=no,status=no,scrollbars=yes'); return false;">                      
                                                ${cancelOrderList.memberNick}
                                                </a>
                                            </td>
	                   						<td>${cancelOrderList.productIds}</td>
	                   							
	                                        <td>
                                                <a href="#" class="" onclick="window.open('${contextPath}/admin/order/cancel/detail?cancellationReason=${cancelOrderList.cancellationReason}', 'popupWindow', 'width=1500,height=1500,location=no,status=no,scrollbars=yes'); return false;">
                                                ${cancelOrderList.cancellationReason}
                                                </a>
                                            </td>
   
	                                        <td>${cancelOrderList.requestedDate}</td>
	                                        
	                                        <td>${cancelOrderList.approvalStatus}</td>
                                            <td>${cancelOrderList.approvalDate}</td>
	                                    </tr>
	                          		 
						             
	                                </c:forEach>
	
	                            </c:otherwise>
	                      </c:choose>
                    </tbody>
                </table>
              </div>  

 
    
             <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언-->
                <c:set var="url" value="?cp="/> 

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

               
                </div>
                <div class="admin-main-footer">
                    <input type="hidden" name="adminCode" value="${adminCode}">
                    <button type="submit" class="admin-btn" id="cancelBtn">승인</button>
                    <input type="hidden" name="adminCode" value="${adminCode}">
                    <button type="submit" class="admin-btn" id="cancelBBtn">거절</button>
                </div>
                </div>
                
                 
              
               
        </div>
        
    </main>
    <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
     </div>
 
</div>
 


 
<script src="${contextPath}/resources/js/admin/admin-common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-chat.js"></script> 
</body>
</html>

 