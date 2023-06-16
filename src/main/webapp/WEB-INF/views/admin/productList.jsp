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
	<c:set var="productList" value="${map.productList}" />
	<c:set var="productImageList" value="${productImageList}" />


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <title>${adminName}</title>
        <link href="${contextPath}/resources/css/admin/admin-chat.css" rel="stylesheet" />
       <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />

        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css"> 
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">  
   
   
    

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
              <h2>상품 목록</h2>
            </div>
  
  
  
        <div class="admin-product-list">
	  		
	  		   <div class="admin-main-nav">
	  		     <div>


                    
                  </div>     
                  <div>
                    <form action="${adminCode}" method="get" id="memberSearch" onsubmit="return searchValidate()">
        
                        <select name="key" id="search-key"  name="admin-main-nav-input"   placeholder="검색">
                            <option value="t">상품명</option>
                            <option value="c">카테고리</option>
                            <option value="w">작가</option> 
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
            
              <table id="myTable" class="admin-main-table"  >
                    <thead>
                        <tr>
                            <th>품번</th>
                          	<th>썸네일</th>
                            <th>카테고리</th>
                            <th>상품명</th>
                            <th>아티스트</th>
                            <th>가격</th>
                            <th>등록일</th>
                        </tr>
                      </thead>
                    <tbody>
                  
				    <c:choose>
				        <c:when test="${empty productList}">
				            <!-- 게시글 목록 조회 결과가 비어있다면 -->
				            <tr>
				                <th colspan="7">게시글이 존재하지 않습니다.</th>
				            </tr>
				        </c:when>
				
				 
				        <c:otherwise>
				            <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
				            <c:forEach var="productList" items="${productList}">
				                <tr>
				                <td>${productList.productId}</td>
				       
				   				 <td> 
				                    <c:if test="${!empty productList.productImage}">
				                        <img class="list-thumbnail" src="${contextPath}${productList.productImage}">
				                    </c:if>
				                           <a onclick="window.open('${contextPath}/admin/product/${adminCode}/detail/${productList.productId}?cp=${pagination.currentPage}${sURL}',
				                            'window_name','width=1300,height=1300,location=no,status=no,scrollbars=yes');"></a>
				                   
				                
				                <c:if test="${empty productList.productImage}">
						 			<p>바봉</p>
						         </c:if>
				                  </td>
					 	 
				                    <td>${productList.productType}</td>
				                      <td>${productList.productName}</td>
				                    <td>${productList.productArtist}</td>
				                   <td><span class="formatted-price"><fmt:formatNumber value="${productList.productPrice}" pattern="###,###원"/></span></td>
				                    <td>${productList.productRDate}</td>
				                </tr>
					            </c:forEach>
					        </c:otherwise>
					    </c:choose>
					</tbody>
                   
                </table>
              </div> 
             
              
             
          
        	<div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언  -->
                <c:set var="url" value="${adminCode}?cp="/>
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
            <div class="admin-main-footer">
           
            <button class="admin-btn">삭제</button>
          </div>						 
       							
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
    <script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
  <script src="${contextPath}/resources/js/admin/admin-common.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-product.js"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
  
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

 


</body>
</html>

 