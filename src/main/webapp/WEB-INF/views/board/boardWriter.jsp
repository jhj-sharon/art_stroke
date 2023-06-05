<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <div class = "boardWriter-content-field" style="cursor:pointer;" onclick="location.href='../detailWriter';">
                    <div class = "boardWriter-img-field"><img class ="boardWriter-img" src ="${contextPath}/resources/images/boardImg/boardWriterDefault.jpg" alt=""></div>
                    <div class = "boardWriter-text-field">
                        <span class = "boardWriter-text-title">작가 명</span>
                        <span class = "boardWriter-text-sub">작가 스스로의 소개</span>
                    </div>
                </div>
                <div class = "boardWriter-content-field" style="cursor:pointer;" onclick="location.href='*;'">
                    <div class = "boardWriter-img-field"><img class ="boardWriter-img" src ="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" alt=""></div>
                    <div class = "boardWriter-text-field">
                        <span class = "boardWriter-text-title">작가 명</span>
                        <span class = "boardWriter-text-sub"> 작가 스스로의 소개</span>
                    </div>
                </div>
            </div>
        </section>



    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    
</body>
</html>