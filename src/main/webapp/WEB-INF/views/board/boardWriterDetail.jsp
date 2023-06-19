<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="productList" value="${map.productList}" />
<c:set var="boardList" value="${map.boardList}" />
<c:set var="member" value="${map.member}" />
<c:set var="fList" value = "${map.fList}"/>

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
    // 로그인한 회원 번호
    const loginMemberId = "${loginMember.memberId}";
    const writerNick = "${member.memberNick}";
    const writerId = "${member.memberId}";
    const loginMemberNick = "${loginMember.memberNick}";
    
    // -> 로그인 O  : "10";
    // -> 로그인 X  : "";  (빈문자열)
   
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">  
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/style.css">
    <link rel = "stylesheet" href = "${contextPath}/resources/css/board/boardWriterDetail.css">
    <title>${member.memberNick} 작가님의 뜰</title>
</head>
<body>
    
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <main class="main-style">

        <!--작가 상세페이지의 설명부분 대표 섬네일과 -->
        <section class="contents-wrap">
            <div class = "boardWriterDetail-WriterProfile-Area">
                <div class = "boardWriterDetail-WriterProfile-img-Area">
                    <div class = "boardWriter-circle">
                        <c:if test = "${empty member.profileImage}">
                            <img class = "boardWriterDetail-img-content" src = "${contextPath}/resources/images/boardImg/boardWriterDefault.jpg">
                        </c:if>
                        <c:if test = "${!empty member.profileImage}">
                            <img class = "boardWriterDetail-img-content" src = "${contextPath}/${member.profileImage}">
                        </c:if>
                    </div>
                </div>
                <div class = "boardWriterDetail-WriterProfile-text-Area">
                    <div class ="heightfull boardWriterDetail-profile">
                        <div class = "bwd-t">
                            <span class = "boardWriterDetail-content-Writer">${member.memberNick}</span>

                            <c:if test = "${!empty loginMember}">
                                <c:if test = "${loginMember.memberId !=member.memberId}">

                                
                                    <span id = "follow-Btn" class = "boardWriterDetail-content-follow font-nano-sub">팔로우</span>

                                    <button class = "boardWriterDetail-content-letter font-nano-sub" onclick="openPopup()">쪽지보내기</button>                
                                </c:if>
                            </c:if>
                            
                <!--   ========================================================================        -->
                        </div>
                        
                        <div class = "boardWriterDetail-content-sub">${member.memberIntro}</div>
                        <button class = "boardWriterDetail-goSns">Go SNS</button>
                    </div>
                </div>
            </div>
            
        </section>

        <section class="contents-wrap">
            <div class = "boardWriterDetail-area">
                <div class = "heightfull">
                    <div class ="boardWriterDetail-direction">
                        <span class = "boardWriterDetail-title">Writer's product</span>
                        <span class = "boardWriterDetail-sub">Choose products And Enhance the quality of your life</span>
                    </div>
                    <!--제품 리스트가 들어갈 영역-->
                    <div class = "boardWriterDetail-content-area">
                        <c:choose>
                            <c:when test = "${empty productList}">
                                <div class = "noElement">No Product.</div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var = "product" items="${productList}">
                                    <div class = "boardWriterDetail-content-element">
                                        <div class = "boardWriterDetail-product-area">
                                            <div class = "imgFlex">
                                                <c:if test = "${empty product.productImage}">
                                                    <img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg">
                                                </c:if>
                                                <c:if test = "${!empty product.productImage}">
                                                    <img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/${product.productImage}">
                                                </c:if>
                                            </div>
                                            <div class = "contextFlex">
                                                <span class = "BWD-pTitle">${product.productName}</span>
                                                <span class = "BWD-pContext">${product.productPrice}</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        
                    </div>
                </div>
            </div>
        </section>

        <section class="contents-wrap">
            <div class = "boardWriterDetail-area">
                <div class = "heightfull">
                    <div class ="boardWriterDetail-direction">
                        <span class = "boardWriterDetail-title">Writer's board</span>
                        <span class = "boardWriterDetail-sub">Communicate with Writer And Feel Writer's Dream</span>
                    </div>
                    <!--게시판 리스트가 들어갈 영역-->
                    <div class = "boardWriterDetail-content-area">
                        <c:choose>
                            <c:when test = "${empty boardList}">
                                <div class = "noElement">No Board.</div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var = "board" items="${boardList}">
                                    <div class = "boardWriterDetail-content-element" style = "cursor:pointer;" onclick="location.href = '../detail/${board.boardCode}/${board.boardId}'">
                                        <div class = "boardWriterDetail-product-area">
                                            <div class = "imgFlex">
                                                <c:choose>
                                                    <c:when test = "${empty board.boardFile2}">
                                                        <img class="boardProject-img" src = "${contextPath}/resources/images/boardImg/board_defaultImg.jpg">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img class="boardProject-img" src = "${board.boardFile2}">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class = "contextFlex">
                                                <span class = "BWD-pTitle">${board.boardTitle}</span>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        
                    </div>
                </div>
            </div>
        </section>
        


    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<!-- 쪽지보내기 -->
<div id="popup" class="popup-overlay">
    <div class="popup-content">
        
        <h4>| 쪽지보내기</h4>
        <form action="../detailWriter/${member.memberId}/sendLetter" method = "post" onsubmit ="return letterValidate()">
                <div class="popup-table">
                    <table style = "width:100%;">
                        <tr>
                            <td>작가 명</td>
                            <td><input type="text" id="writerName" name="writerName"
                                maxlength="30" autocomplete="off"
                                required value = ${member.memberNick}></td>
                        </tr>
                        <!-- 여기서는  session에 등록된 로그인 계정의 닉네임. -->
                        <tr>
                            <td>성명</td>
                            <td><input type="text" id="sendName"
                                name="sendName" placeholder="성명" maxlength="30"
                                autocomplete="off" required value = "${loginMember.memberNick}"></td>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td><input type="text" id="sendTitle"
                                name="messageTitle" placeholder="제목" maxlength="30" required
                                autocomplete="off"></td></td>
                        </tr>
                        <!-- 보낼 내용 -->
                        <tr>
                            <td>보낼 내용</td>
                            <td><textarea class = "sendText" name = "messageContent"></textarea></td>
                        </tr>
                </div>
                </table>
                <div class="popupBtn-wrap">
                    <button class="letter-btn" id="Send" type="submit">등록하기</button>
                    <button class="letter-btn" type = "button" onclick="closePopup()">취소</button>
                </div>

                <input name = "receiverId" style ="display:none" value = ${member.memberId}>
                <c:if test = "${!empty loginMember}">   
                    <input name = "senderId" style ="display:none" value = ${loginMember.memberId}>
                </c:if>
            </form>
    </div>
</div>
    <script src = "${contextPath}/resources/js/board/boardOpenPopup.js"></script>
    <script src = "${contextPath}/resources/js/board/boardWriterDetail.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
  
</body>
</html>

<script>
    var isMemberfollowed = "${isMemberfollowed}";
  if (isMemberfollowed == "true") {
    followBtn.innerHTML = "팔로잉";
  }
</script>