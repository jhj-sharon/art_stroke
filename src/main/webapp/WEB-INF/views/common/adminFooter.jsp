<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL은 사용되는 JSP 파일마다 작성되어야 한다 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer class="py-4 bg-light mt-auto">
   
    <button class="admin_talk" onclick="openPopup3()">
        <i class="fa-solid fa-headset fa-2xl" style="color: #155139;"></i>
    </button>
    <div id="popup3" class="popup3">
        <div class="popup-content3">
            <div class="myPage-popupTag">
                <h4>| 관리자와의 채팅</h4>
                <div class="close" onclick="closePopup3()">&times;</div>	
            </div>
            <div class="admin-chat">
                <div class="chat-bg">


                </div>
                <div class="chat-input">
                    <input type="text" size="30" id="chattingInput" onkeyup="inputEnter()">
                    <button onclick="readValue()">입력</button>
                </div>	
            </div>

        </div>
    </div>


    <a href="${contextPath}/admin/chat/chatList" id="admin-chat-btn">
        <img src="${contextPath}/resources/img/hoon.jpg" width="250px" height="100px" alt="">
    </a>

	 

    <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">
            <div class="text-muted">Copyright &copy; Your Website 2023</div>
            <div>
                <a href="#">Privacy Policy</a>
                &middot;
                <a href="#">Terms &amp; Conditions</a>
             
            </div>
        </div>
      
    </div>
</footer>

<c:if test="${ !empty message }">
    <script>
        alert("${message}");
        // EL 작성 시 scope를 지정하지 않으면
        // page -> request -> session -> application 순서로 검색하여
        // 일치하는 속성이 있으면 출력
    </script>
</c:if> 