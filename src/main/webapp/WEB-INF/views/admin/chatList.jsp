
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>채팅방 목록</title>

<link href="${contextPath}/resources/css/admin/admin-styles.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/admin/admin-main.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/admin/admin-chat.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/chat/chat-styles.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${contextPath}/resources/css/admin/admin-icon.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">


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


						<div class="admin-main">
							<div class="table-chat-div">
								<table class="list-table" boarder="1px solid black">
									<thead>
										<tr>
											<th>방번호</th>
											<th>멤버아이디</th>
											<th>참여</th>
											<th>멤버닉</th>
										</tr>
									</thead>

									<%-- 채팅 목록 출력 --%>
									<tbody>
										<c:choose>

											<%-- 조회된 게시글 목록이 없을 때 --%>
											<c:when test="${empty chatRoomList }">
												<tr>
													<td colspan="4">존재하는 채팅방이 없습니다.</td>
												</tr>
											</c:when>

											<%-- 조회된 채팅방 목록이 있을 때 --%>
											<c:otherwise>

												<c:forEach var="chatRoom" items="${chatRoomList}">
													<tr>

														<td>
                                                            <div id="${chatRoom.chatRoomId}" class="chatId">${chatRoom.chatRoomId}</div>
														</td>
														<td>
															<button class="selectBtn" onclick="openPopup3('${chatRoom.chatRoomId}')">참여</button>
														</td>
														<td>${chatRoom.memberNick}</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- table-chat-div -->
                            <div id="popup3" class="popup3">
                                <div class="popup-content3">
                                    <div class="myPage-popupTag">
                                        <h4>| 관리자와의 채팅</h4>
                                        <div class="close" onclick="closePopup3()">&times;</div>
                                    </div>
                                    <div class="admin-chat">
                                        <div class="chat-bg"></div>
                                        <div class="chat-input">
                                            <input type="hidden" id="chatRoomId" name="chatRoomId" value="">
                                            <input type="text" size="30" id="chattingInput"
                                                onkeyup="inputEnter()">
                                            <button onclick="readValue()">입력</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<%-- 로그인이 되어있는 경우 --%>
							<c:if test="${!empty loginMember }">
								<div class="btn-area">
									<button id="openChatRoom">채팅방 만들기</button>
								</div>
							</c:if>
							<div class="pagination-area">

								<!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언  -->
								<c:set var="url" value="${adminCode}?cp=" />
								<div>
									<ul class="pagination">
										<!-- 첫 페이지로 이동 -->
										<li><a href="${url}1${sURL}">&lt;&lt;</a></li>

										<!-- 이전 목록 마지막 번호로 이동 -->
										<li><a href="${url}${pagination.currentPage - 1}${sURL}">&lt;</a></li>

										<!-- 범위가 정해진 일반 for문 사용 -->
										<c:forEach var="i" begin="${pagination.startPage}"
											end="${pagination.endPage}" step="1">

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
						<!-- admin-main -->


						<div class="admin-main-footer">
							<input type="hidden" name="adminCode" value="${adminCode}">
							<button type="submit" class="admin-btn" id="chatDeleteBtn">삭제</button>

						</div>

					</div>
				</div>
			</main>

			<div class="modal" id="chat-modal">
				<span id="modal-close">&times;</span>

				<form class="modal-body" id="open-form" method="POST"
					action="${contextPath}/chat/openChatRoom">
					<h3>채팅방 만들기</h3>

					<input type="text" id="chatRoomTitle" name="title"
						class="form-control" placeholder="채팅방 제목" required>

					<button type="submit">만들기</button>
				</form>

			</div>
			<jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
<!-- https://github.com/sockjs/sockjs-client -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
   
   const memberNo = "${loginMember.memberId}";
   const memberEmail = "${loginMember.memberEmail}";
   const memberNickname = "${loginMember.memberNick}";
   const chatRoomNo = "${chatRoomId}";
   const contextPath = "${contextPath}";

   // 로그인이 되어 있을 경우에만
   // /chat 이라는 요청 주소로 통신할 수 있는  WebSocket 객체 생성
   let chattingSock = new SockJS(contextPath+"/chat");
	  // -> websocket 프로토콜을 이용해서 해당 주소로 데이터를 송/수신 할 수 있다.


   /*  WebSocket
   
   - 브라우저와 웹 서버간의 전이중 통신을 지원하는 프로토콜

   * 전이중 통신(Full Duplex) : 두대의 단말기가 데이터를 동시에 송/수신 하기 위해
	 각각 독립된 회선을 사용하는 통신 방식(ex. 전화 )

   - HTML5 부터 지원
   - Java 7 부터 지원 (8 버전 이상 사용 권장)
   - Spring Framework 4 이상 부터 지원
   */


</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
	<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
	<script src="${contextPath}/resources/js/admin/admin-common.js"></script>
	<script src="${contextPath}/resources/js/admin/admin-chat.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/admin/admin-product.js"></script>
	<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
		crossorigin="anonymous"></script>


</body>
</html>

