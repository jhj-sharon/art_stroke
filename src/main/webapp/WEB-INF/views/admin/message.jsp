<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:forEach var="adminType" items="${adminTypeList}">
	    <c:if test="${adminCode == adminType.adminCode}">
	        <c:set var="adminName" value="${adminType.adminName}" />
	    </c:if>
	</c:forEach>
	
<c:set var="messageList" value="${messageList}" />

<!DOCTYPE html>
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
     
		<div class="container-fluid px-4"> 
			<div class="admin-container"> 
				<div class="admin-main-header">
					<h2  >
						<a href="${contextPath}/admin/member/message" class="main-title">
						  쪽지함
						</a>
					  </h2>
				  </div>

				<div class="admin-main">
				<table class="admin-main-table">
					<thead>
						<tr>
							<th><input type="checkbox" id="MessageListSelectAll"></th>
							<th>보낸사람</th>
							<th>제목</th>
							<th>날짜</th>
							<th>삭제</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty messageList}">
								<tr>
									<td colspan="6" rowspan="6">
										<div class="noItemWrap">
											<p class="noitem">받은 쪽지가 없습니다.</p>
										</div>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${messageList}" var="messageList">
									<c:if test="${messageList.messageSt eq 'N'}">
										<tr>
											
											<c:choose>
												<c:when test="${messageList.messageOpen eq 'Y'}">
													<td><input type="checkbox" class="checkList"
														id="${messageList.messageId}"></td>
													<td class="messageOpened">${messageList.memberNick}</td>
													<td><div class="sendMessage-btn2" onclick="openPopup('${messageList.messageTitle}','${messageList.senderId}','${messageList.messageContent}','${messageList.memberNick}','${messageList.messageId}')">${messageList.messageTitle}</div></td>
													<td class="messageOpened">${messageList.messageDt}</td>
												</c:when>
												<c:otherwise>
													<td><input type="checkbox" class="checkList"
														id="${messageList.messageId}"></td>
													<td>${messageList.memberNick}</td>
													<td><div class="sendMessage-btn" onclick="openPopup('${messageList.messageTitle}','${messageList.senderId}','${messageList.messageContent}','${messageList.memberNick}','${messageList.messageId}')">${messageList.messageTitle}</div></td>
													<td>${messageList.messageDt}</td>
												</c:otherwise>
											</c:choose>
											
											<td>
												<button class="myPage-btn" id="delete-btn">삭제</button>
											</td>
											<td></td>
										</tr>
									</c:if>
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
			<div class="admin-main-footer">
 
				<a href="#" onclick="window.open('${contextPath}/admin/member/message/writeForm', 'popupWindow', 'width=600,height=600,location=no,status=no,scrollbars=yes'); return false;">
                                  
				<button type="submit" class="admin-btn BBtn" id="messageBtn">쪽지보내기</button>
				</a>
			</div>
		 </div>
		 
		
	</div>
 
	</main>
 
    <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
</div>
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
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</body>
</html>