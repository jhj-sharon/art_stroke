<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="messageList" value="${messageList}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${contextPath}/resources/css/myPage/myPageMessage.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/069a8eb008.js"
	crossorigin="anonymous"></script>
<title>내 쪽지함</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>

	<main class="main-style">
		<section class="contents-wrap">
			<h4>| 내 쪽지함</h4>
			<p class="messageInfo">제목을 클릭하면 내용을 확인하고 답장을 보낼 수 있습니다.</p>
			<div class="myPageMessage-wrap">
				<table>
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
									<td colspan="6" rowspan="6"><div class="noItemWrap">
											<p class="noitem">받은 쪽지가 없습니다.</p>
										</div></td>
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
			<div id="popup" class="popup-overlay">
				<div class="popup-content">
					<h4>| 답장 보내기</h4>
					<form action="sendBack" method="post">
						<div class="popup-table">
							<table>
								<tr>
									<td>작가 닉네임</td>
									<td><input type="text" id="memberNick" name="memberNick"
										maxlength="30" autocomplete="off" readonly></td>
								</tr>
								<!-- 여기서는  session에 등록된 로그인 계정의 닉네임. -->
								<tr>
									<td>닉네임</td>
									<td><input type="text" id="sendName"
										name="sendName" placeholder="성명" maxlength="30" readonly
										autocomplete="off" required value = "${loginMember.memberNick}"></td>
								</tr>
								<tr>
									<td>제목</td>
									<td><input type="text" id="messageTitle"
										name="messageTitle" placeholder="제목" maxlength="30" required
										autocomplete="off"></td></td>
								</tr>
								<!-- 보낼 내용 -->
								<tr>
									<td>보낼 내용</td>
									<td><textarea id = "messageContent" class="messageContent" name = "messageContent"></textarea></td>
								</tr>
							</table>
						</div>
						<input type="hidden" id="senderId" name="senderId" value="">
						<div class="popupBtn-wrap">
							<button class="myPage-btn" id="Send" type="submit">전송하기</button>
							<button class="myPage-btn" type="button" onclick="closePopup()">닫기</button>
						</div>
					</form>
				</div>
			</div>
			<div class="myPageMessageList-wrap">
				<button class="myPage-button" id="check-delete-btn">선택 품목
					삭제</button>
			</div>
		</section>

	</main>

	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/main.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageMessage.js"></script>
</body>
</html>