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
	<c:set var="orderList" value="${map.orderList}" />
	



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
        
        <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
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
                    <h2>주문 관리</h2>
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
                            <option value="c">주문자</option>
                            <option value="w">상품명</option> 
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
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio1" onchange="orderApply()" id="radio1">결제확인중
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio2" onchange="orderApply()" id="radio2">결제확인
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio3" onchange="orderApply()" id="radio3">배송중
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio4" onchange="orderApply()" id="radio4">배송완료
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio5" onchange="orderApply()" id="radio5">거래완료
                         <br>
                      	<input type="radio" name="displayRadio" class="admin-radio" value="radio6" onchange="orderApply()" id="radio6">취소요청
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio7" onchange="orderApply()" id="radio7">취소완료
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio8" onchange="orderApply()" id="radio8">반품요청
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio9" onchange="orderApply()" id="radio9">반품완료
                    </div>    
                    </div>

                <div>
                  
                    <div class="admin-main-nav-div"> 
                        <div>
                        <label>주문일자</label>
                    </div>
                    <div>
                        <input type="date" class="admin-date"> ~
                        <input type="date" class="admin-date">까지

                        <a href=""><button class="admin-btn">오늘</button></a>
                        <a href=""><button class="admin-btn">1주일</button></a>
                        <a href=""><button class="admin-btn">1개월</button></a>
                        <a href=""><button class="admin-btn">3개월</button></a> 
                      </div>
                    </div>
                 
                    <div class="admin-main-nav-div"> 
                        <div>
                        <label>결제방법</label>
                    </div>
                    <div>
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio10" onchange="orderApply()" id="radio10" >신용카드
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio11" onchange="orderApply()" id="radio11">무통장입금
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio12" onchange="orderApply()" id="radio12">휴대폰
                        <input type="radio" name="displayRadio" class="admin-radio" value="radio13" onchange="orderApply()" id="radio13">카카오페이 
                      </div>    
                    </div>
                   

      
                </div>
                <div class="admin-main-nav-div2">
                    
                        
            </div>
          
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
                           <th>송장번호</th> 
                            <th>주문일자</th>
                            <th>수령인</th>
                            <th>연락처</th>
                            <th>주소</th>
                            <th>수량</th>
                            <th>총액</th>
                            <th>결제수단</th>
                             
                        </tr>
                      </thead>
                    <tbody id="orderTableBody">
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
	                                        <td>${orderList.invoiceId}</td>
	                                        <td>${orderList.orderDT}</td>
	                                        <td>${orderList.receiver}</td>
	                                        <td>${orderList.receiverPhone}</td>
	                                        <td>${orderList.address}</td>
	                                        <td>${orderList.quantity}</td>
	                                        <td>${orderList.totalPrice}</td>
	                                        <td>${orderList.payment}</td>
	                                        
	                                    </tr>
	                          			<c:if test="${orderList.payment eq '카드'}">
						                    <tr>
							                       <td>${orderList.orderId}</td> 
		                                        <td>${orderList.invoiceId}</td>
		                                        <td>${orderList.orderDT}</td>
		                                        <td>${orderList.receiver}</td>
		                                        <td>${orderList.receiverPhone}</td>
		                                        <td>${orderList.address}</td>
		                                        <td>${orderList.quantity}</td>
		                                        <td>${orderList.totalPrice}</td>
		                                        <td>${orderList.payment}</td>
						                    </tr>
						                </c:if>
	                        			<c:if test="${orderList.payment eq '무통장입금'}">
						                    <tr>
							                       <td>${orderList.orderId}</td> 
		                                        <td>${orderList.invoiceId}</td>
		                                        <td>${orderList.orderDT}</td>
		                                        <td>${orderList.receiver}</td>
		                                        <td>${orderList.receiverPhone}</td>
		                                        <td>${orderList.address}</td>
		                                        <td>${orderList.quantity}</td>
		                                        <td>${orderList.totalPrice}</td>
		                                        <td>${orderList.payment}</td>
						                    </tr>
						                </c:if>
	         					 
	                                </c:forEach>
	
	                            </c:otherwise>
	                      </c:choose>
                    </tbody>
                </table>
              </div>  







                </div>
                <div>

                </div>

                </div>
                
                <div class="admin-main-footer">
                  <a href="${contextPath}/admin/adminProductForm"><button class="admin-btn">등록</button></a>
                  <button class="admin-btn">삭제</button>
                </div>
              
               
        </div>
        
    </main>
     </div>
    <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
</div>
 



<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
</body>
</html>

 