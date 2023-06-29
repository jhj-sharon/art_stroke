/* 모두 체크 */
const allCheckBox = document.getElementById("MessageListSelectAll");
const checkList = document.getElementsByClassName("checkList");

allCheckBox.addEventListener("change", function() {
  const isChecked = allCheckBox.checked;

  for (let i = 0; i < checkList.length; i++) {
    checkList[i].checked = isChecked; // 모두 체크박스들의 체크 상태를 allCheckBox의 체크 여부와 동일하게 설정합니다.
  }

});

// 모든 체크박스(blindList)가 눌러지면 allCheckBox가 체크됨/해제됨
for (let i = 0; i < checkList.length; i++) {
    checkList[i].addEventListener("change", function () {
    let allChecked = true;
    for (let j = 0; j < checkList.length; j++) {
      if (!checkList[j].checked) {
        allChecked = false;
        break;
      }
    }
    allCheckBox.checked = allChecked;
  });
}

var deleteButtons = document.querySelectorAll('.myPage-btn#delete-btn'); // 모든 삭제 버튼을 선택합니다
deleteButtons.forEach(function(button) {
  button.addEventListener('click', function(event) {
    var button = event.target; // 클릭된 버튼 요소를 가져옵니다
    var row = button.closest('tr'); // 클릭된 버튼 요소의 가장 가까운 tr 요소를 탐색합니다

    if (row) {
      var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
      var messageId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다

      // 삭제 요청을 서버에 보냅니다
      deleteItem(messageId);
    }
  });
});

function deleteItem(messageId) {
    $.ajax({
      url: '/stroke/myPage/deleteMessage', // 삭제를 처리할 서버의 URL을 입력합니다.
      data: { messageId: messageId }, // 삭제 요청에 필요한 데이터를 입력합니다.
      success: function(result) {
        if (result > 0) {
          alert("쪽지가 삭제되었습니다.");
         
          location.reload();
        } else {
          alert("쪽지가 삭제되지 않았습니다.");
        }
      },
      error: function() {
        console.log('쪽지 삭제 ajax 오류');
      }
    });
};
    

// 전체 삭제 버튼 클릭 이벤트 핸들러
document.getElementById("check-delete-btn").addEventListener("click", function() {
    // 체크된 체크박스 요소들을 가져옵니다.
    var checkedCheckboxes = document.querySelectorAll(".checkList:checked");
  
    if (checkedCheckboxes.length > 0) {
      var messageIds = [];
      
      // 체크된 체크박스 요소들의 상품 ID를 수집합니다.
      checkedCheckboxes.forEach(function(checkbox) {
        var row = checkbox.closest("tr"); // 현재 체크박스가 속한 행(row)을 찾습니다.
        if (row) {
          var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
          var messageId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다
          messageIds.push(messageId);
        }
      });
  
      // 삭제 요청을 서버에 보냅니다
      deleteItems(messageIds);
    }
  });

  // 전체 삭제 함수
function deleteItems(messageIds) {
    $.ajax({
      url: '/stroke/myPage/deleteSelectedMessage', // 삭제를 처리할 서버의 URL을 입력합니다.
      traditional: true, // 배열 데이터 전달을 위해 traditional 옵션을 추가합니다.
      data: { messageIds: messageIds }, // 삭제 요청에 필요한 데이터를 입력합니다.
      success: function(result) {
        if (result > 0) {
          alert("선택된 쪽지가 삭제되었습니다.");
          location.reload();
        } else {
          alert("쪽지가 삭제되지 않았습니다.");
        }
      },
      error: function() {
        console.log('선택 쪽지 삭제 ajax 오류');
      }
    });
  };
  

function openPopup(messageTitle, senderId, messageContent, memberNick, messageId) {
    document.getElementById('messageTitle').value ="re:"+messageTitle;
    document.getElementById('senderId').value = senderId;
    document.getElementById('messageContent').value = "re:"+messageContent;
    document.getElementById('memberNick').value = memberNick;
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";

    $.ajax({
      url: '/stroke/myPage/readMessage', 
      data: { messageId: messageId }, 
      success: function(result) {
        if (result > 0) {
          console.log("읽음처리 완료");
        }else {
          console.log("읽음처리 불가");          
        }
      },
      error: function() {
        console.log('쪽지 읽음 ajax 오류');
      }
    });
}
  
function closePopup() {
  location.reload();
  var popup = document.getElementById("popup");
  popup.style.visibility = "hidden";
  popup.style.opacity = "0";
  
}
  