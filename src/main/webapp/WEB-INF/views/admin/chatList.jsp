<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="pagination" value="${map.pagination}" />
<c:set var="chatRoomList" value="${map.chatRoomList}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>채팅방 목록</title>

<link href="${contextPath}/resources/css/admin/admin-styles.css"
   rel="stylesheet" />
<link href="${contextPath}/resources/css/admin/admin-main.css"
   rel="stylesheet" />
<link href="${contextPath}/resources/css/admin/admin-chat.css"
   rel="stylesheet" />
<link href="${contextPath}/resources/css/chat/chat-styles.css"
   rel="stylesheet" />
<link rel="stylesheet"
   href="${contextPath}/resources/css/admin/admin-icon.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
   rel="stylesheet">

   
</head>

<body class="sb-nav-fixed">

   <!-- 제목 -->
   <jsp:include page="/WEB-INF/views/common/adminHeader.jsp" />

   <div id="layoutSidenav">
      <jsp:include page="/WEB-INF/views/common/adminSideMenu.jsp" />


      <div id="layoutSidenav_content">
         <main>
            <div class="container-fluid px-4">
               <div class="admin-container">
                  <div class="admin-main-header">
                     <h2  >
                         <a href="${contextPath}/admin/chat/chatList" class="main-title">
                           채팅
                         </a>
                       </h2>
                   </div>

                  <div class="admin-main">
                     <div class="table-chat-div">
                        <table class="list-table" border="1px solid black">
                           <thead>
                              <tr>
                                 <th>No</th>
                                 <th>방번호</th>
                                 <th>닉네임</th>
                                 <th>답변하기</th>
                               
                              </tr>
                           </thead>

                          
                           <tbody>
                              <c:choose>
                                <c:when test="${empty chatRoomList}">
                                  <tr>
                                    <td colspan="4">존재하는 채팅방이 없습니다.</td>
                                  </tr>
                                </c:when>
                                <c:otherwise>
                                  <c:forEach var="chatRoom" items="${chatRoomList}">
                                    <c:if test="${chatRoom.chatStatus != 'Y'}">
                                      <tr>
                                        <td>
                                          <input type="checkbox" name="chatRoomChk" value="${chatRoom.chatRoomId}" id="chatRoomChkbox">
                                        </td>
                                        <td>
                                          <div id="${chatRoom.chatRoomId}" class="chatId">${chatRoom.chatRoomId}</div>
                                        </td>
                                        <td>
                                          <div id="chatEnter">
                                            <c:choose>
                                              <c:when test="${chatRoom.socialType != 'N'}">

                                                ${chatRoom.memberEmail}
                                              </c:when>
                                              <c:otherwise>
                                                ${chatRoom.memberNick}
                                               
                                              </c:otherwise>
                                            </c:choose>
                                          </div>
                                        </td>
                                        <td>
                                          <button class="selectBtn" onclick="openPopup3('${chatRoom.chatRoomId}')">참여</button>
                                        </td>
                                      </tr>
                                    </c:if>
                                  </c:forEach>
                                </c:otherwise>
                              </c:choose>
                            </tbody>
                            
                            
                        </table>
                     </div>
                     <!-- table-chat-div -->

                            <div id="popup3" class="popup3">
                                <div class="popup-content3">
                                    <div class="myPage-popupTag">
                                        <h4>| 관리자와의 채팅</h4>
                                        <div class="close" onclick="closePopup3()">&times;</div>
                                    </div>
                                    <div class="admin-chat">
                                        <div class="chat-bg"></div>
                                        <div class="chat-input">
                                            <input type="hidden" id="chatRoomId" name="chatRoomId" value="">
                                            <input type="text" size="30" id="chattingInput"
                                                onkeyup="inputEnter()">
                                            <button onclick="readValue()">입력</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                     <%-- 로그인이 되어있는 경우 --%>
                  
                     <div class="pagination-area">

                        <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언  -->
                        <c:set var="url" value="?cp="/>
                    <div>
                           <ul class="pagination">
                                <!-- 첫 페이지로 이동 -->
                                <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
            
                                <!-- 이전 목록 마지막 번호로 이동 -->
                                <li><a href="${url}${pagination.currentPage - 1}${sURL}">&lt;</a></li>
            
                                <!-- 범위가 정해진 일반 for문 사용 -->
                                <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
            
                                    <c:choose>
                                        <c:when test="${i == pagination.currentPage}">
                                            <li><a class="current">${i}</a></li>
                                        </c:when>
            
                                        <c:otherwise>
                                            <li><a href="${url}${i}${sURL}">${i}</a></li>        
                                        </c:otherwise>
                                    </c:choose>
            
                                </c:forEach>
                                
                                <!-- 다음 목록 시작 번호로 이동 -->
                                <li><a href="${url}${pagination.currentPage + 1}${sURL}">&gt;</a></li>
            
                                <!-- 끝 페이지로 이동 -->
                                <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
            
                            </ul>
                       </div>
                    </div>	
                  </div>
            

                  <div class="admin-main-footer">
                     <input type="hidden" name="adminCode" value="${adminCode}">
                     <button type="submit" class="admin-btn" id="chatDeleteBtn">삭제</button>

                  </div>

               </div>
            </div>
         </main>
 
         <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
      </div>
   </div>

 
   <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 
   <script src="${contextPath}/resources/js/admin/admin-common.js"></script>
   <script src="${contextPath}/resources/js/admin/admin-chat.js"></script>
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"></script>
   <script src="${contextPath}/resources/js/admin/admin-product.js"></script>
   <script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>

  
   <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"></script>


</body>
</html>
