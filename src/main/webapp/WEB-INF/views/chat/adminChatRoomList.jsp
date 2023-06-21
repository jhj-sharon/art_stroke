<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

   
       <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-chat.css" rel="stylesheet" />
          <link href="${contextPath}/resources/css/chat/chat-styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css"> 
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">  
 
 
</head>
<body>
 <main> 
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
                           <input type="text" size="30" id="chattingInput" onkeyup="inputEnter()">
                           <button onclick="readValue()">입력</button>
                        </div>
                     </div>
                  </div>
               </div>    
               
      </main>         
               
               
      <script src="${contextPath}/resources/js/admin/admin-chat.js"></script> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
    <script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
  <script src="${contextPath}/resources/js/admin/admin-common.js"></script> 
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-product.js"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
  
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
 
</body>

</html>