
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
    console.log(chatId);
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
