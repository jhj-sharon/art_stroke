<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/d89904c156.js" crossorigin="anonymous"></script>
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardDetail.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/reply-style.css">
    
    <title>artstroke_이어진 획</title>
</head>
<body>
    <header class="header-style">
        <section class = "boardDetail-content-1">
            <div id = "boardDetail-title-area">   
                <div>
                </div>
            </div>
        </section>
    </header>

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap">
            <div class = "boardDetail_writer_area">
                <div class = "boardDetail_writer_profile">
                    <img src = "${contextPath}/resources/images/boardImg/cat.jpg">
                </div>
                <div><span class="board_member_Nick">닉네임</span></div>
                <div><button style = "cursor: pointer;">팔로우</button></div>
            </div>
        </section>

        <section class="contents-wrap">
            <div class = "board_Content">
                <div class = "board_Content_field">

                </div>
                <div class = "board_after_Banner">
                    <div class = "flex-left">
                        <div><span class = "font-color term_right">공유하기</span></div>
                        <div><span class = "font-color term_right">좋아요</span></div>
                    </div>
                    <div class = "flex-right">
                        <div><span class = "font-color term_left">수정하기</span></div>
                        <div><span class = "font-color term_left">삭제하기</span></div>
                        <div><span class = "font-color term_left">신고하기</span></div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 댓글 -->
        <jsp:include page="/WEB-INF/views/board/reply.jsp"/>
    </main>

    <footer class="footer-style">
        footer 영역
    </footer>
        <!-- jQuery 추가 -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>    
</body>
</html>