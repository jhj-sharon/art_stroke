<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardBoardDetail.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <title>공지사항_</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap marB">
            <div class ="bBD-content-field">
                <div class = "full">
                    <div class = "bBD-title-field">
                        <span style = "font-size:40px"class = "font-Nano">공지사항 제목</span>
                    </div>
                    <div class = "bBD-sub-field">
                        <span class = "sub-title">예술이 있는 생활, 아트스트로크</span>
                    </div>
                    <div class = "bBD-content-field">
                        <div class = "content-title">
                            <div style = "padding:10px; font-family:'NaNo_SANS'">제목 : {공지사항 제목}</div>
                        </div>
                        <div class = "content-atr">
                            <div class="td1" style="flex-basis:15%; overflow:hidden">작성자 :</div>
                            <div class="td1" style="flex-basis:15%;">작성일 :</div>
                            <div class="td1">조회수 :</div>
                            <div></div>
                        </div>
                        <div class = "content-inputSentence">
                            <div class = "bBD-announce">공지사항을 알려드립니다.</div>
                        </div>
                    </div>
                    <div class ="PrevNext">
                        <div class="PreNext-box" onclick="location.href='*'">
                            <div class="PreNext-box1">다음 글 <i class="fa-solid fa-arrow-up"></i></div>
                            <div class = "PreNext-box2">글 제목</div>
                        </div>
                        <div class="PreNext-box" onclick="location.href='*'">
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class="PreNext-box1">이전 글 <i class="fa-solid fa-arrow-down"></i></div>
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class = "PreNext-box2">글 제목</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>