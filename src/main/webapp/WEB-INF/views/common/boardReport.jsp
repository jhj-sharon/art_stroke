<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="board" value="${board}" />
<c:set var="reply" value = "${reply}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href="${contextPath}/resources/css/board/report-style.css">
    <script src="https://kit.fontawesome.com/d89904c156.js" crossorigin="anonymous"></script>
    <title>artstroke_report</title>
</head>
<body>
    <header class="header-style">
        <section class="contents-wrap">
            <div class = "boardReport_title">
                <div class = "full flex-row">
                    <div class = "boardReport-title-element-1 full flex-row">
                        <!--<button class="fa-solid fa-arrow-left arrow-style" style="cursor:pointer;"></button>-->
                    </div>
                    <div class = "boardReport-title-element-2 full flex-row"><h3>신고하기</h3></div>
                    <div class = "boardReport-title-element-3 full flex-row flex-row"></div>
                </div>
            </div>
        </section>
    </header>
    <form type="post" name = "report-form" id = "submitForm">
    <main class="main-style">
        <!-- 여기부터 추가 -->
        
        <section class="contents-wrap">
             <div class = "boardReport_content">
                <div>
                    <div class = "report-title-Write">
                        <h3>신고하기</h3>
                    </div>
                    <table class = "report-table">
                        <tr>
                            <td class = "report-td1">
                                신고대상
                            </td>
                            <td class = "report-td1-Nick"><c:choose>
                                <c:when test="${!empty board}">
                                    (<span name = "reportTarget">${board.memberId}</span>)<span name = "reportTargetNick">${board.memberNickname}</span>
                                </c:when>
                                <c:when test="${!empty reply}">
                                    <span name = "reportTargetNick">${reply.memberNick}</span>
                                </c:when>
                            </c:choose></td>
                        </tr>
                        <tr>
                            <td class = "report-td2">신고 게시글(댓글)</td>
                            <td>
                            <c:choose>                                
                                <c:when test="${!empty board}">
                                    (게시판)<input class = "report-Target-input" name ="reportTargetTitle" value ="${board.boardTitle}" readonly></span>
                                </c:when>
                                <c:when test="${!empty reply}">
                                    (댓글)<input class = "report-Target-input" name = "reportTargetContent" value = "${reply.replyContent}" readonly></span>
                                </c:when>
    
                            </c:choose>
                        </td>
                        </tr>
                    </table>
                    <div class = "report-reason-field"><h3>신고 사유</h3></div>
                    <textarea class = "boardReport-textarea" name = "reportContent"></textarea>
                </div>
                
            </div>
            
            
            
        </section>
    </main>
    <div style="cursor:pointer;" class = "report-btn" id = "report-btn"><input style="cursor:pointer;" type = "button" class = "report-btn-atr" value="제출하기"/></div>
    </form>
    <footer class="footer-style">
        
    </footer>
    
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src = "${contextPath}/resources/js/common/reportDetail.js"></script>
</body>
</html>
<script>
    
    const contextPath = "${contextPath}";
    
    // 게시글 번호
    const replyId = "${reply.replyId}";
    const boardId = "${board.boardId}"; // "500"
    const boardCode = "${boardCode}";
    const no = "${param.no}";
    const type = "${param.type}";

    
    // 로그인한 회원 번호
    const loginMemberId = "${loginMember.memberId}";
    // -> 로그인 O  : "10";
    // -> 로그인 X  : "";  (빈문자열)

    const submitBtn = document.getElementById("report-btn");

submitBtn.addEventListener("click", function() {
    $("#submitForm").attr("action", "${contextPath}/board/reportDetail/${boardCode}?no=${param.no}&type=${param.type}");
    $("#submitForm").attr("method","post");
    $("#submitForm").submit();
    //window.open("${contextPath}/common/close.html", "_self");
});
</script>