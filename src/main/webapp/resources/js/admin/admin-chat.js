
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
  
var deleteButtons = document.querySelectorAll('#chatRoomClassBtn'); // 모든 삭제 버튼을 선택합니다
deleteButtons.forEach(function(button) {
  button.addEventListener('click', function(event) {
    var button = event.target; // 클릭된 버튼 요소를 가져옵니다
    var row = button.closest('tr'); // 클릭된 버튼 요소의 가장 가까운 tr 요소를 탐색합니다

    if (row) {
      var checkbox = row.querySelector('.chatRoomClass'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
      var chatId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다

      // 삭제 요청을 서버에 보냅니다
      readValue(chatId);
    }
  });
});



function openPopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'block';

    
}
  
function closePopup3() {
    const popup = document.getElementById('popup3');
    popup.style.display = 'none';
}





function readValue() {
    console.log("???????");
    const bg = document.querySelector(".chat-bg");
    const input = document.querySelector("#chattingInput");
    if (input.value.trim().length > 0) {
        bg.innerHTML += "<p><span>" + input.value + "</span></p>";
        bg.scrollTop = bg.scrollHeight;
         
    }

    //const inputVal = input.value;
    var selectBtns = document.querySelectorAll('.selectBtn');
    selectBtns.forEach(function(button) {
        console.log("!!!!!!!!!");
        button.addEventListener('click', function(event) {

            console.log("#####3");
          var button = event.target; // 클릭된 버튼 요소를 가져옵니다
          var row = button.closest('tr'); // 클릭된 버튼 요소의 가장 가까운 tr 요소를 탐색합니다
      
          console.log("rowssss"+row);
          if (row) {
            var checkbox = row.querySelector('.chatId');
            var chatId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다
      
        
            console.log("chatId::",chatId);
          }
        });
      });
    
    // $.ajax({
    //     url: 'insertAdminChatMessage',
    //     type: 'POST',
    //     data: {
    //         inputVal: inputVal,
    //         chatId: chatId },
    //     success: function(result) {
    //         if (result > 0) {
    //             console.log("INSERT 성공");
	// 			input.value = "";
    //         } else {
    //             console.log("INSERT 실패");
	// 			input.value = "";
    //         }
    //     },
    //     error: function() {
    //         console.log('에러에러 ajax 오류');
    //     }
    // });
}

function inputEnter() {
    if (window.event.key == "Enter") {
        readValue();
    }
}
