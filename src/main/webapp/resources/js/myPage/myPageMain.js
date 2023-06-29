
var webSocket = new WebSocket("ws://localhost:8080/stroke/websocket");

function openPopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'block';
   
}
  
function closePopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'none';
}
function openPopup2() {
    const popup2 = document.getElementById('popup2');
    popup2.style.display = 'block';
}
  
function closePopup2() {
    const popup2 = document.getElementById('popup2');
    popup2.style.display = 'none';
}
var messageHtml="";
function openPopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'block';
    const chatId = document.getElementById("chatRoomId");
    console.log("마이페지이 webSocket ?!!! :   " + webSocket);


    $.ajax({
        url: 'openChatRoom',
        success: function(responseData) {
            const chatRoomId = responseData.chatRoomId;
            const chatMessages = responseData.chatMessages;
            console.log(responseData);
            chatId.value = chatRoomId;

            var memberId = document.documentElement.getAttribute('data-memberId');
            
            if (chatMessages) {
                chatMessages.forEach(function (chatMessage) {
                    
                    if(chatMessage.memberId == memberId){
                        var messageHtml = "<p class= 'myChat'><span class='myChatMessage'>" + chatMessage.message + '</span></p>'
                    }else{
                        messageHtml = "<p class= 'adminChat'><span class='adminMessage'>" + chatMessage.message + '</span></p>'
                    }
                    const chatBg = document.querySelector(".chat-bg");
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
    const chatBg = document.querySelector(".chat-bg");
    chatBg.innerHTML = "";
    webSocket.close();
}
var inputMessage = "";
const bg = document.querySelector(".chat-bg")
function readValue(){
    const input = document.querySelector("#chattingInput")
    const inputVal = input.value;
    inputMessage = inputVal
    const chatId = document.getElementById("chatRoomId").value;
    if(input.value.trim().length >0){
        bg.innerHTML += "<p class= 'myChat'><span class='myChatMessage'>"+ input.value +"</span></p>";
        bg.scrollTop = bg.scrollHeight;
        const message = {
            inputVal: input.value,
            chatId: chatId
          };
       
        input.value = "";
    }
   
    $.ajax({
        url: 'insertChatMessage', //채팅방 번호를 만들어줄 url 입력합니다.
        type : 'POST',
        data : { inputVal : inputVal,
                chatId : chatId},
        success: function(result) {
          if (result >0) {
            console.log("INSERT 성공");

            webSocket.send(inputVal);
            
            input.value = "";
          } else {
            console.log("INSERT 실패");
            input.value = "";
          }
        },
        error: function() {
          console.log('채팅 오픈 ajax 오류');
        }
    });
    
}

function inputEnter(){
    if(window.event.key == "Enter"){
        readValue();
    }
}

const inputImage = document.getElementById("input-image");

if(inputImage != null){ // inputImage 요소가 화면에 존재 할 때
 
    // input type="file" 요소는 파일이 선택 될 때 change 이벤트가 발생한다.
    inputImage.addEventListener("change", function(){
       
        // this : 이벤트가 발생한 요소 (input type="file")

        // files : input type="file"만 사용 가능한 속성으로
        //         선택된 파일 목록(배열)을 반환
        //console.log(this.files)
        //console.log(this.files[0]) // 파일목록에서 첫 번째 파일 객체를 선택

        if(this.files[0] != undefined){ // 파일이 선택되었을 때

            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함.
            // - 읽어오는게 완료되면 result 속성 data:에 
            //   읽어온 파일의 위치를 나타내는 URL이 포함된다.  
            //  -> 해당 URL을 이용해서 브라우저에 이미지를 볼 수 있다.


            // FileReader.onload = function(){}
            // - FileReader가 파일을 다 읽어온 경우 함수를 수행
            reader.onload = function(e){ // 고전 이벤트 모델
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소(객체) -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src 속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
            }

        }
    });
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
    if(inputMessage != receivedMessage){
        bg.innerHTML += '<p class="adminChat"><span class="adminMessage">'+receivedMessage+'</span></p>'

    }

    console.log( "메시지="+ receivedMessage);
    // 이후에 원하는 동작 수행
  };



// 이미지 선택 확인
function profileValidate(){
    const inputImage = document.getElementById("input-image");
    const del = document.getElementById("delete");
    if( inputImage.value == "" && del.value == 0 ){ 
        // 빈문자열 == 파일 선택 X / del의 값이 0 == x를 누르지도 않았다
        // --> 아무것도 안하고 변경버튼을 클릭한 경우

        alert("이미지를 선택한 후 변경 버튼을 클릭해주세요.");
        return false;
    }
    return true;
}
const contextPath = getContextPath();
function getContextPath() {
   return sessionStorage.getItem("contextpath");
}

document.getElementById("defaultUser").addEventListener("click", function(){
    // 0 : 안눌러짐
    // 1 : 눌러짐
    const del = document.getElementById("delete");
    const defaultImg = contextPath + "/resources/img/memberProfile/defaultUser.png";
    if(del.value == 0){ // 눌러지지 않은 경우
        // 1) 프로필 이미지를 기본 이미지로 변경

        document.getElementById("profile-image").setAttribute("src", defaultImg );                     

        // 2) input type="file"에 저장된 값(value)에 "" 대입 
        document.getElementById("input-image").value = "";

        del.value = 1; // 눌러진걸로 인식
    }
}); 


 // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.
 //var webSocket = new WebSocket("ws://localhost:8080/storke/websocket");
 // 콘솔 텍스트 영역
//  var messageTextArea = document.getElementById("chattingInput");
//  // 접속이 완료되면
//  webSocket.onopen = function(message) {
//    // 콘솔에 메시지를 남긴다.
//    console.log("Server connect...")
//  };
//  // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
//  webSocket.onclose = function(message) { };
//  // 에러가 발생하면
//  webSocket.onerror = function(message) {
//    // 콘솔에 메시지를 남긴다.
//    messageTextArea.value += "error...\n";
//  };
 // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
//  webSocket.onmessage = function(message) {
//     //debugger;
//    //messageTextArea.value += "(operator) => " + message.data + "\n";
//    bg.innerHTML += '<p class="adminChat"><span class="adminMessage">'+message.data+'</span></p>'
//    console.log(message.data);
//  };
 // 서버로 메시지를 발송하는 함수
 // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
//  function sendMessage() {
//    // 텍스트 박스의 객체를 가져옴
//    let message = document.getElementById("textMessage");
//    // 콘솔에 메세지를 남긴다.
//    messageTextArea.value += "(me) => " + message.value + "\n";
//    // 소켓으로 보낸다.
//    webSocket.send(message.value);
//    // 텍스트 박스 추기화
//    message.value = "";
//  }
 // 텍스트 박스에서 엔터를 누르면
 function enter() {
   // keyCode 13은 엔터이다.
   if(event.keyCode === 13) {
     // 서버로 메시지 전송
     sendMessage();
     // form에 의해 자동 submit을 막는다.
     return false;
   }
   return true;
 } 
 function otherSocialType(){
    alert("접근권한이 없습니다.");
 }