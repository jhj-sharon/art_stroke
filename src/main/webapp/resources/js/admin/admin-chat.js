
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

var chatId ="";
function openPopup3(chatRoomId) {
    const popup = document.getElementById('popup3');
    popup.style.display = 'block';

    document.getElementById("chatRoomId").value = chatRoomId;

    
    chatId = document.getElementById("chatRoomId").value
    console.log("openPopup3   "+ chatId);

   
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
                    
                    const messageHtml = (chatMessage.memberId === memberId) 
                        ? '<p><span>' + chatMessage.message + '</span></p>'
                        : '<span><p>' + chatMessage.message + '</p></span>';
    
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
}





function readValue() {
    
    const bg = document.querySelector(".chat-bg");
    const input = document.querySelector("#chattingInput");
    if (input.value.trim().length > 0) {
        bg.innerHTML += "<p><span>" + input.value + "</span></p>";
        bg.scrollTop = bg.scrollHeight;
    }

    const inputVal = input.value;

    console.log(chatId);
    
    $.ajax({
        url: 'insertAdminChatMessage',
        type: 'POST',
        data: {
            inputVal: inputVal,
            chatId: chatId },
        success: function(result) {
            if (result > 0) {
                console.log("INSERT 성공");

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