/* 모두 체크 */
const allCheckBox = document.getElementById("BoardListSelectAll");
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
document.getElementById("check-delete-btn").addEventListener("click", function() {
    // 체크된 체크박스 요소들을 가져옵니다.
    var checkedCheckboxes = document.querySelectorAll(".checkList:checked");
  
    if (checkedCheckboxes.length > 0) {
      var boardIds = [];
      
      // 체크된 체크박스 요소들의 상품 ID를 수집합니다.
      checkedCheckboxes.forEach(function(checkbox) {
        var row = checkbox.closest("tr"); // 현재 체크박스가 속한 행(row)을 찾습니다.
        if (row) {
          var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
          var boardId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다
          boardIds.push(boardId);
        }
      });
  
      // 삭제 요청을 서버에 보냅니다
      deleteItems(boardIds);
    }
  });

// 전체 삭제 함수
function deleteItems(boardIds) {
    $.ajax({
      url: 'deleteSelectedBoard', // 삭제를 처리할 서버의 URL을 입력합니다.
      traditional: true, // 배열 데이터 전달을 위해 traditional 옵션을 추가합니다.
      data: { boardIds: boardIds }, // 삭제 요청에 필요한 데이터를 입력합니다.
      success: function(result) {
        if (result > 0) {
          alert("선택된 게시글이 삭제되었습니다.");
          location.reload();
        } else {
          alert("게시글이 삭제되지 않았습니다.");
        }
      },
      error: function() {
        console.log('게시글 삭제 ajax 오류');
      }
    });
  };

  // 검색한 내용과 제목 일치시키면 그것만 보여주기!
$(document).ready(function() {
    $(".myPage-btn").click(function() {
       var searchText = $("#boardSearch").val(); // 검색어 가져오기
  
       $(".myPageBoardList-wrap table tbody tr").hide(); // 모든 행 숨기기
  
       // 검색어와 일치하는 제목을 가진 행만 표시
       $(".myPageBoardList-wrap table tbody tr:contains('" + searchText + "')").show();
    });
  });
  