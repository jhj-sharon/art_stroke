<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="writerList" value="${map.writerList}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardWriter.css">
    <title>artstroke_작가를 만나다</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!-- <header class="header-style">
        <section class = "board-content-1">
            <div id = "board-title-area">   
                <div>
                   
                </div>
            </div>
        </section>
    </header> -->

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap">
            <div class = "boardWriter-content">
                <c:choose>
                    <c:when test = "${empty writerList}">
                        <span>게시물이 존재하지 않음.</span>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="writer" items="${writerList}">
                            <div class = "boardWriter-content-field" style="cursor:pointer;" onclick="location.href='../board/detailWriter/${writer.memberId}';">
                                <div class = "boardWriter-img-field">
                                    <c:choose>
                                        <c:when test="${!empty writer.profileImage}">
                                            <img class ="boardWriter-img" src ="${contextPath}${writer.profileImage}" alt="">
                                        </c:when>
                                        <c:otherwise>
                                            <img class = "boardWriter-img" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class = "boardWriter-text-field">
                                    <span class = "boardWriter-text-title">${writer.memberNick}</span>
                                    <span class = "boardWriter-text-sub">${writer.memberIntro}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>



    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    
</body>
</html>