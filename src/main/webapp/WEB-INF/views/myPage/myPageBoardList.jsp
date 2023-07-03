<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="BoardList" value="${BoardList}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="../resources/css/myPage/myPageBoardList.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
	<script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
<title>내 게시물</title>
</head>
<body>
	<header class="header-style">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>

	<main class="main-style">

		<section class="contents-wrap">
		<h4>| 내 게시글</h4>
		<div class="myPageBtn-wrap">
			<input type="text" class ="boardSearch" id="boardSearch" name="boardSearch"
								placeholder="검색할 게시글 제목을 입력해주세요" autocomplete="off">
			<button class="myPage-btn">검색</button>
		</div>
			<div class="myPageBoardList-wrap">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" id="BoardListSelectAll"></th>
							<th>이미지</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>좋아요</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							   <c:when test="${empty BoardList}">
                           <tr>
                              <td colspan="6" rowspan="6"><div class="noItemWrap"><p class="noitem">작성한 게시글이 없습니다.</p></div></td>
                           </tr>
                           
                        </c:when>
							   <c:otherwise>
                           <c:forEach items="${BoardList}" var="BoardList">
                              <tr>
                                 <td><input type="checkbox" class="checkList"
                                    id="${BoardList.boardId}"></td>
								<c:choose>
									<c:when test="${BoardList.boardFile2 == null}">
										<td><img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg"
											style="width: 80px; height: 80px" onclick="location.href ='/stroke/board/detail/${BoardList.boardCode}/${BoardList.boardId}'">
											</td>
									</c:when>
									<c:otherwise>
										<td><img src="${BoardList.boardFile2}"
											style="width: 80px; height: 80px" onclick="location.href ='/stroke/board/detail/${BoardList.boardCode}/${BoardList.boardId}'">
											</td>
									</c:otherwise>
								</c:choose>
                                 <td><a href="/stroke/board/detail/${BoardList.boardCode}/${BoardList.boardId}">${BoardList.boardTitle}</a></td>
                                 <td>${BoardList.boardDt}</td>
                                 <td>${BoardList.boardCNT}</td>
                                 <td>${BoardList.boardGood}</td>
                              </tr>
                        </c:forEach>
                        </c:otherwise>
                     </c:choose>
					</tbody>
				</table>
			</div>
			
			<div class= "myPageBtn-wrap">
				<button class="myPage-button" id="check-delete-btn">선택 삭제</button>
			</div>
		</section>

	</main>
	
	<footer class="footer-style">
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</footer>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="${contextPath}/resources/js/myPage/myPageBoardList.js"></script>
	<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>