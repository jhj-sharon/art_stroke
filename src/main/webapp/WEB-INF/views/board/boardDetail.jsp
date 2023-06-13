<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="detail" value="${map.detail}" />
<c:set var="rList" value = "${map.rList}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/d89904c156.js" crossorigin="anonymous"></script>
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardDetail.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/reply-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 
    <title>artstroke_이어진 획</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <header class="header-style">
        <section class = "boardDetail-content-1">
            <div id = "boardDetail-title-area">   
                <div>
                    <span class = "mdb-title">art stroke.</span>
                    <span class = "mdb-sub">획을 관찰하는 공간</span>
                </div>
            </div>
        </section>
    </header>

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap">
            <div class = "boardDetail_writer_area">
                <div class = "boardDetail_writer_profile">
                            <img src = "${detail.profileImage}">
                 
                </div>
                <div><span class="board_member_Nick">${detail.memberNickname}</span></div>
                <div><button style = "cursor: pointer;">팔로우</button></div>
            </div>
        </section>

        <section class="contents-wrap">
            <div class = "board_Content">
                <div class = "board_Title_field">
                    ${detail.boardTitle}
                </div>
                <div class = "board_Content_field">
                    ${detail.boardContent}
                </div>
                <div class = "board_after_Banner">
                    <div class = "flex-left">
                        <div><span class = "font-color term_right">공유하기</span></div>
                        <div><span class = "font-color term_right">좋아요</span></div>
                    </div>
                    <div class = "flex-right">
                        
                        <c:if test="${loginMember.memberId == detail.memberId}">
                            <div style = "cursor:pointer" onclick="location.href = '../../boardWrite/${boardCode}?no=${boardId}&type=update'"><span class = "font-color term_left">수정하기</span></div>
                            <div style = "cursor:pointer" onclick="location.href = '../../delete/${boardCode}?no=${boardId}'"><span class = "font-color term_left">삭제하기</span></div>
                            
                        </c:if>
                        <c:if test = "${!empty loginMember}">
                            <div id = "reportBtn"><span class = "font-color term_left">신고하기</span></div>
                        </c:if>
                        
                    </div>
                </div>
            </div>
        </section>
            
        
        <!-- 댓글 -->
        <jsp:include page="/WEB-INF/views/common/reply.jsp"/>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- jQuery 추가 -->
        <script src = "${contextPath}/resources/js/common/report.js"></script>
        <script src = "${contextPath}/resources/js/board/reply.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>    
</body>
</html>

<script>
    // 댓글 관련 JS 코드에 필요한 값을 전역 변수로 선언

    // jsp 파일 : html, css, js, el, jstl 사용 가능
    // js  파일 : js

    // 코드 해석 순서  :   EL == JSTL > HTML > JS

    // ** JS 코드에서 EL/JSTL을 작성하게 된다면 반드시 ""를 양쪽에 추가 **

    // 최상위 주소
    const contextPath = "${contextPath}";
    
    // 게시글 번호
    const boardId = "${detail.boardId}"; // "500"
    const boardCode = "${boardCode}";
    // 로그인한 회원 번호
    const loginMemberId = "${loginMember.memberId}";
    // -> 로그인 O  : "10";
    // -> 로그인 X  : "";  (빈문자열)
</script>