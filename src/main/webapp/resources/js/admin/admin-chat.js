
$("#chatDeleteBtn").click(function() {
    var chatRoomChk = [];

    $("input[name='chatRoomChk']:checked").each(function() {
        chatRoomChk.push($(this).val());
        console.log("체크된 값 chatRoomChk : " + chatRoomChk);
    });
    console.log(chatRoomChk);

    $.ajax({
        url: "deleteChat",
        type: "post",
        traditional: true,
        data: { chatRoomChk: chatRoomChk },
        
        success: function(result) {
            if (result > 0) {
                alert("채팅 삭제 성공!");
                location.reload();
                console.log("성공!");
                 
            } else {
                alert("처리 결과가 없습니다.");
             
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
          
        }
    });

});

var webSocket = new WebSocket("ws://localhost:8080/stroke/websocket");
 var messageHtml="";
var chatId ="";
function openPopup3(chatRoomId) {
    const popup = document.getElementById('popup3');
    popup.style.display = 'block';
    
   
    document.getElementById("chatRoomId").value = chatRoomId;

    
    chatId = document.getElementById("chatRoomId").value
    console.log("openPopup3   "+ chatId);
    console.log("webSocket ?!!!" + webSocket);
   
    $.ajax({
        url: 'selectChatMessage', 
        data: { chatId: chatId },
        success: function(responseData) {
            const chatMessages = responseData.chatMessages;
            console.log("chatMessages  : "  + chatMessages);
            console.log("chatId     " + chatId);
            
            var memberId = responseData.memberId;
            console.log("내 로그인아이디" + memberId);
            
            const chatBg = document.querySelector(".chat-bg");
            chatBg.innerHTML = ''; // 이전 채팅 메시지를 지우기 위해 내용을 초기화합니다.
            
            if (chatMessages) {
                chatMessages.forEach(function (chatMessage) {
                    console.log("챗메세지-멤버아이디  " + chatMessage.memberId);
                    
                    if(chatMessage.memberId == memberId){
                        messageHtml = "<p class= 'myChat'><span class='myChatMessage'>" + chatMessage.message + '</span></p>'
                    }else{
                        messageHtml = "<p class= 'memberChat'><span class='memberMessage'>" + chatMessage.message + '</span></p>'
                    }
    
                    chatBg.innerHTML += messageHtml;
                });
            } else {
                console.log('채팅 메시지 데이터를 가져오지 못했습니다.');
            }
        },
        error: function() {
            console.log('채팅 오픈 ajax 오류');
        }
    });
}    
    

  
function closePopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'none';
    webSocket.close();
}

const inputVal = ""; // inputVal 변수 선언 및 할당


function readValue() {
    const bg = document.querySelector(".chat-bg");
    const input = document.querySelector("#chattingInput");
    const inputVal = input.value; // inputVal 변수 선언 및 할당

    if (inputVal.trim().length > 0) {
        bg.innerHTML += "<p class= 'myChat'><span class='myChatMessage'>"  + inputVal + '</span></p>';
       
        bg.scrollTop = bg.scrollHeight;
    }

    console.log(chatId);

 
    $.ajax({
        url: 'insertAdminChatMessage',
        type: 'POST',
        data: {
            inputVal: inputVal,
            chatId: chatId
        },
        success: function(result) {
            if (result > 0) {
                console.log("INSERT 성공");

                webSocket.send(inputVal);

                input.value = "";

            } else {
                console.log("INSERT 실패");
                input.value = "";
            }
        },
        error: function() {
            console.log('에러에러 ajax 오류');
        }
    });
}

function inputEnter() {
    if (window.event.key == "Enter") {
        readValue();
    }
}

webSocket.onopen = function() {
    // 콘솔 텍스트에 메시지를 출력한다.

    
    console.log(" Server connect...");
};
// WebSocket 서버와 접속이 끊기면 호출되는 함수
webSocket.onclose = function() {
    // 콘솔 텍스트에 메시지를 출력한다.
    console.log(" Server Disconnect...");
};
// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
webSocket.onerror = function() {
    // 콘솔 텍스트에 메시지를 출력한다.
    console.log( " error...");
};
 
 // WebSocket 서버로부터 메시지가 오면 호출되는 함수
 webSocket.onmessage = function(event) {
    var receivedMessage = event.data; // 서버로부터 수신한 메시지

    console.log( receivedMessage);
    // 이후에 원하는 동작 수행
  };