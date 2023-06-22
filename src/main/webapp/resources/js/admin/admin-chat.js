
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
 
  





function openPopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'block';

	console.log("chatRoomId:", chatRoomId);
}
  
function closePopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'none';
}

function readValue(){
    const bg = document.querySelector(".chat-bg")
    const input = document.querySelector("#chattingInput")
    if(input.value.trim().length >0){
        bg.innerHTML += "<p><span>"+ input.value +"</span></p>";
        bg.scrollTop = bg.scrollHeight;
    }
    
    const inputVal = input.value;
    const chatId = document.getElementById("chatRoomId").value;
    $.ajax({
        url: 'insertAdminChatMessage', //채팅방 번호를 만들어줄 url 입력합니다.
        type : 'POST',
        data : { inputVal : inputVal,
                chatId : chatId},
        success: function(result) {
          if (result >0) {
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




function inputEnter(){
    if(window.event.key == "Enter"){
        readValue();
    }
}







 