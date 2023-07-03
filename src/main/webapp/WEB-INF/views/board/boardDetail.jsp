<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="detail" value="${map.detail}" />
<c:set var="rList" value = "${map.rList}"/>
<c:set var="gList" value = "${gList}"/>
<c:set var="fList" value = "${fList}"/>
<c:set var="listSize" value="${fn:length(gList)}" />

<c:set var="isMemberLiked" value="false" />
<c:forEach var="item" items="${gList}">
  <c:if test="${item.memberId == loginMember.memberId}">
    <c:set var="isMemberLiked" value="true" />
  </c:if>
</c:forEach>

<c:set var="isMemberfollowed" value="false" />
<c:forEach var="follow" items="${fList}">
  <c:if test="${follow.followerId == loginMember.memberId}">
    <c:set var="isMemberfollowed" value="true" />
  </c:if>
</c:forEach>
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
    
    const type = "board";
    // 로그인한 회원 번호
    const loginMemberId = "${loginMember.memberId}";
    const writerNick = "${detail.memberNickname}"
    const writerId = "${detail.memberId}";
    const loginMemberNick = "${loginMember.memberNick}"
    // -> 로그인 O  : "10";
    // -> 로그인 X  : "";  (빈문자열)

    const gList = "${gList}";
   
</script>
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

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    
    <title>artstroke_이어진 획</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <!-- 썸네일 이미지 -->
    <section class="board-detail-thumbnail">
        <c:choose>
            <c:when test = "${!empty detail.boardThumbNail}">
                <img src="${detail.boardThumbNail}" alt="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" >
            </c:when>
            <c:otherwise>
                <img src="${contextPath}/resources/images/boardImg/board_defaultImg.jpg" >
            </c:otherwise>
        </c:choose>
        
    </section>

             

    <main class="main-style-post">
        <section class="contents-wrap">

            <div class = "board_Title_field">
                ${detail.boardTitle}
            </div>

            <div class = "boardDetail_writer_area">
                <div>
                    <div class = "boardDetail_writer_profile">
                        <c:choose>
                            <c:when test = "${!empty detail.profileImage}">
                                <img src = "${contextPath}/${detail.profileImage}">
                            </c:when>
                            <c:otherwise>
                                <img src = "${contextPath}/resources/img/memberProfile/defaultUser.png">
                            </c:otherwise>
                        </c:choose>
                    </div>
    
                    <div>
                        <c:choose>
                            <c:when test = "${detail.memberSocialType == 'N'}">
                                <span class="board_member_Nick">${detail.memberNickname}</span>  
                            </c:when>
                            <c:otherwise>
                                <c:if test = "${!empty detail.memberNickname}">
                                    <span class="board_member_Nick">${detail.memberNickname}</span>
                                </c:if>
                                <c:if test = "${empty detail.memberNickname}">
                                    <span class="board_member_Nick">소셜${detail.memberId}회원</span>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        
                        <c:set var="createDate" value="${fn:substring(detail.createDate, 0, 13)}" />
                        <span id="board-post-date">${createDate}</span>

                    </div>
                </div>

                <c:if test = "${!empty loginMember}">
                <c:if test="${loginMember.memberId != detail.memberId}">

                    <c:if test ="${detail.memberId == 1}">
                   	<div><button id = "follow-Btn" class = "follow-Btn"style = "cursor: pointer;">+ 팔로우</button></div>
                    </c:if>

                </c:if>
            </c:if>
            </div>


        </section>

             

        <section class="contents-wrap">

               


            <div class = "board_Content">

                

                
                <div class="board-content-wrap">
                    <!-- 본문 -->
                    <div class = "board_Content_field">
                        ${detail.boardContent}
                    </div>
                    
                    <!-- 게시글 관련 버튼 -->
                    <div class="like-btn-area">   
                        <c:if test="${!empty loginMember}">
                            <div id ="boardGood">
                                    <i id = "heart" class="fa-regular fa-heart"></i>
                                </span>
                            </div>
                        </c:if>
            
                        <div id="board-share-btn" onclick="urlShare(); return false;">
                            <i class="fa-solid fa-share-nodes"></i>
                        </div>
                    </div>
                </div>


                

                <!-- 게시글 정보 -->
                <div class = "board_after_Banner">
                    <div class = "flex-left">
                        좋아요 
                        <span id = "listSize"style = "margin-right: 10px;">&nbsp;${listSize}</span>
                        조회 
                        <span>&nbsp;${detail.readCount}</span>
                    </span>
                    </div>

                    

                    <div class = "flex-right">
                        
                        <c:if test="${loginMember.memberId == detail.memberId}">
                            <div style = "cursor:pointer" onclick="location.href = '../../boardWrite/${boardCode}?no=${boardId}&type=update'"><span class = "font-color term_left">수정</span></div>
                            |
                            <div style = "cursor:pointer" onclick="location.href = '../../delete/${boardCode}?no=${boardId}'"><span class = "font-color term_left">삭제</span></div>
                            
                        </c:if>
                        <c:if test = "${!empty loginMember}">
                            <c:if test="${loginMember.memberId != detail.memberId}">
                                <div style = "cursor:pointer" id = "report-btn">
                                    <span class = "font-color term_left">신고하기</span>
                                </div>
                            </c:if>
                        </c:if>
                        
                    </div>
                </div>

            </div>
        </section>
            
        
        <!-- 댓글 -->
        <div>
        <jsp:include page="/WEB-INF/views/common/reply.jsp"/>
    </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

        <!-- jQuery 추가 -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>    
        <c:if test = "${!empty loginMember}">
            <c:if test="${loginMember.memberId != detail.memberId}">
            <script src = "${contextPath}/resources/js/common/report.js"></script>
        </c:if>
        </c:if>
        
        
        
</body>
</html>

<script>
  const heart = document.getElementById("heart");
  var isMemberLiked = "${isMemberLiked}";
  if (isMemberLiked == "true") {
    heart.classList.add("fa-solid");
    heart.classList.remove("fa-regular");
  }

  //
  const followBtn = document.getElementById("follow-Btn");
  var isMemberfollowed = "${isMemberfollowed}";
  if (isMemberfollowed == "true") {
    followBtn.classList.add("board_member_follow");
    followBtn.classList.remove("board_member_unfollow");
    followBtn.innerText = "팔로잉";
  }

  // url 복사하기 
  function urlShare() {
    let url = '';
    let textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = window.document.location.href;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    alert("url이 복사되었습니다.")

  }
</script>

<script src = "${contextPath}/resources/js/board/reply.js"></script>
<script src = ${contextPath}/resources/js/board/boardDetail.js></script>
<script src="${contextPath}/resources/js/main.js"></script>