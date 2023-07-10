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
    <title>artstroke_작가를 만나다</title>
     <!-- font awesome -->
     <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
     <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardWriter.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>


    <main class="main-style">

        <div class="artists-top">
            <span>Artists</span>
            <span>그림 속에 담긴 이야기, 무한한 상상력으로 이루어진 세계</span>
        </div>
        

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
                                            <img class ="boardWriter-img" src ="${contextPath}/${writer.profileImage}" alt="">
                                        </c:when>
                                        <c:otherwise>
                                            <img class = "boardWriter-img" src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" class="d-block w-100 imgSize" alt="...">
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class = "boardWriter-text-field">
                                    <span class = "boardWriter-text-title">${writer.memberNick}</span>
                                    <!--<span class = "boardWriter-text-sub">${writer.memberIntro}</span>-->
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>



    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/main.js"></script>

</body>
</html>