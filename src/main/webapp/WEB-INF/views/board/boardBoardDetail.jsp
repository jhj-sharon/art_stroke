<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var = "alarm" value="${alarm}"/>
<c:set var = "alarmPrev" value = "${alarmPrev}"/>
<c:set var = "alarmNext" value = "${alarmNext}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardBoardDetail.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <title>공지사항_artstroke</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap marB">
            <div class ="bBD-content-field">
                <div class = "full">

                    <div class = "bBD-content-field">
                        <div class = "content-title">
                            <div style = "padding:10px; font-weight: 500; font-size: 20px;">${alarm.alarmTitle}</div>
                        </div>
                        <div class = "content-atr">
                            <div class = "content-detail-1">
                                <div class="td1" style="flex-basis:40%; overflow:hidden">작성자 : 운영자</div>
                                <div class="td1" style="flex-basis:60%;">작성일 : ${alarm.alarmDate}</div>    
                            </div>
                            <div class = "content-detail-2">
                                <div class="td1" style = "flex-basis:100%; display:flex; justify-content: end;">조회수 : ${alarm.readCount}</div>                            
                            </div>
                            <div></div>
                        </div>
                        <div class = "content-inputSentence">
                            <div class = "bBD-announce">${alarm.alarmContent}</div>
                        </div>
                    </div>
                    <div class ="PrevNext">
                        <c:choose>
                        <c:when test = "${!empty alarmNext}">
                        <div style = "cursor:pointer;" class="PreNext-box" onclick="location.href='${contextPath}/board/boardBoardDetail/${alarmNext.alarmId}?cp=${pagination.currentPage}'">
                            <div class="PreNext-box1" >다음 글 <i class="fa-solid fa-arrow-up"></i></div>
                            <div class = "PreNext-box2">${alarmNext.alarmTitle}</div>
                        </div>
                        </c:when>
                        <c:otherwise>
                            <div class="PreNext-box">
                                <div class="PreNext-box1">다음 글 <i class="fa-solid fa-arrow-up"></i></div>
                                <div class = "PreNext-box2">다음 글이 없습니다.</div>
                            </div>
                        </c:otherwise>
                       </c:choose>
                       <c:choose>
                        <c:when test = "${!empty alarmPrev}">
                        <div style = "cursor:pointer;" class="PreNext-box" onclick="location.href='${contextPath}/board/boardBoardDetail/${alarmPrev.alarmId}?cp=${pagination.currentPage}'">
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class="PreNext-box1">이전 글 <i class="fa-solid fa-arrow-up"></i></div>
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class = "PreNext-box2">${alarmPrev.alarmTitle}</div>
                        </div>
                        </c:when>
                        <c:otherwise>
                            <div class="PreNext-box">
                                <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class="PreNext-box1">이전 글 <i class="fa-solid fa-arrow-up"></i></div>
                                <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class = "PreNext-box2">이전 글이 없습니다.</div>
                            </div>
                        </c:otherwise>
                       </c:choose>
                        <!-- <div class="PreNext-box" onclick="location.href='*'">
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class="PreNext-box1">이전 글 <i class="fa-solid fa-arrow-down"></i></div>
                            <div style="border-top:1px solid rgba(161, 161, 161, 0.653);" class = "PreNext-box2">글 제목</div>
                        </div> -->
                    </div>
                </div>
            </div>
        </section>



    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>