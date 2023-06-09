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
	<c:set var="orderList" value="${map.orderList}" />
	<c:set var="dateList" value="${dateList}" />



<!DOCTYPE html>
<html lang="kr">
    <head>
        <meta charset="UTF-8" />

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
                        <a href="${contextPath}/admin/order/3" class="main-title">
                          주문 목록
                        </a>
                      </h2>
                  </div>
               
                <div class="admin-main-nav-order">
                  
                    <div class="admin-main-nav-div"> 
                        <div>
                        <label>주문검색</label>
                    </div>
                    <div>
                        <form action="${adminCode}" method="get" id="orderSearch" onsubmit="return searchValidate()">
        
                        <select name="key" id="search-key"  name="admin-main-nav-input"   placeholder="검색">
                            <option value="t">주문번호</option>
                        
                            <option value="w">주문자</option> 
                        </select>
        			
                        <input type="text" name="query"  id="search-query" class="admin-main-nav-input" placeholder="검색어를 입력해주세요.">
        
                       <button class="admin-btn">검색</button>
                    </form>
                    </div>
                    </div>
                    
                    <div class="admin-main-nav-div"> 
                        <div>
                        <label>주문상태</label>
                        </div>
                    <div>
                        <input type="radio" name="displayRadio1" class="admin-radio" value="radio1" onchange="orderApply1()" id="radio1">결제확인
                        <input type="radio" name="displayRadio1" class="admin-radio" value="radio3" onchange="orderApply1()" id="radio2">배송시작
                        <input type="radio" name="displayRadio1" class="admin-radio" value="radio4" onchange="orderApply1()" id="radio3">배송완료
                    
                      	<input type="radio" name="displayRadio1" class="admin-radio" value="radio6" onchange="orderApply1()" id="radio4">취소대기
                        <input type="radio" name="displayRadio1" class="admin-radio" value="radio7" onchange="orderApply1()" id="radio5">취소완료 
                    </div>    
                    </div>

          
                    <div class="admin-main-nav-div"> 
                         
                            <div>
                              <label>주문일자</label>
                            </div>
                            <div>
                                <input type="date" id="startDateHidden" name="startDate" value="${startDate}">
                                <input type="date" id="endDateHidden" name="endDate" value="${endDate}">
                                  
                              <input type="radio" name="filterDate" class="admin-radio" value="1" onclick="setDateRange(7)" id="date7">일주일
                              <input type="radio" name="filterDate" class="admin-radio" value="2" onclick="setDateRange(30)" id="date30">1개월
                              <input type="radio" name="filterDate" class="admin-radio" value="3" onclick="setDateRange(90)" id="date90">3개월
                                 
                            </div> 
                    </div>
                 	 
                    <div class="admin-main-nav-div"> 
                        <div>
                        <label>결제방법</label>
                        </div>
                        <div>
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio0" onchange="orderApply()" id="radio0" checked>전체
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio10" onchange="orderApply()" id="radio10" >신용카드
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio11" onchange="orderApply()" id="radio11">핸드폰
                         <input type="radio" name="displayRadio" class="admin-radio" value="radio13" onchange="orderApply()" id="radio13">카카오페이 
                        </div>    
                    </div>
                   

      
                </div>
                <div class="admin-main-nav-div2">
                    
                        
            </div>
          
           

                <div class="admin-main">
                <div>
                <c:if test="${!empty param.key}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
           		</c:if>

				   
              <table class="admin-main-table" id="orderTable">
                    <thead>
                        <tr>
                        
                           <th>주문번호</th> 
                            <th>주문일자</th>
                            <th>주문자</th>
                            <th>수량</th>
                            <th>총액</th> 
                            <th>결제수단</th>
                             
                        </tr>
                      </thead>
                    <tbody>
 						 <c:choose>
	                            <c:when test="${empty orderList}">
	                                <!-- 게시글 목록 조회 결과가 비어있다면 -->
	                                <tr>
	                                    <th colspan="9">주문내역이 존재하지 않습니다.</th>
	                                </tr>
	                            </c:when>
	
	                            <c:otherwise>
	                                <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
	
	                                <!-- 향상된 for문처럼 사용 -->
	                                <c:forEach var="orderList" items="${orderList}">
	                                    <tr>
	                                        
	                                        <td>${orderList.orderId}</td>
	                                        <td>${orderList.orderDate}</td>
                                            <td>${orderList.memberNick}</td>
	                   							
	                                        <td>${orderList.quantity}</td>
	                                        <td><span class="formatted-price"><fmt:formatNumber value="${orderList.totalPrice}" pattern="###,###원"/></span></td>
                                         
	                                        <td>${orderList.paymethod}</td>
	                                        
	                                    </tr>
	                          		 
						             
	                                </c:forEach>
	
	                            </c:otherwise>
	                      </c:choose>
                    </tbody>
                </table>
              </div>  

 
    
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

                </div>
               
                </div>
                
                <div class="admin-main-footer">
                
                
                
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

 