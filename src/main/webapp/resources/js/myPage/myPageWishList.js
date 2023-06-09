/* 모두 체크 */
const allCheckBox = document.getElementById("wishListSelectAll");
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
      var productId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다

      // 삭제 요청을 서버에 보냅니다
      deleteItem(productId);
    }
  });
});

var cartButtons = document.querySelectorAll('.myPage-btn#cart-btn'); // 모든 장바구니 버튼을 선택합니다
cartButtons.forEach(function(button) {
  button.addEventListener('click', function(event) {
    var button = event.target; // 클릭된 버튼 요소를 가져옵니다
    var row = button.closest('tr'); // 클릭된 버튼 요소의 가장 가까운 tr 요소를 탐색합니다

    if (row) {
      var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
      var productId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다

      var optionSelect = row.querySelector('select[name="option1"]'); // 해당 행에서 name이 'option1'인 select 요소를 가져옵니다
      var selectedOption = optionSelect.value; // 선택된 옵션의 값을 가져옵니다

      var productPrice = row.querySelector('.productPrice').innerText;

      // 삭제 요청을 서버에 보냅니다
      cartItem(productId, selectedOption, productPrice);
    }
  });
});

// 삭제 요청을 서버에 보내는 함수
function deleteItem(productId) {
  $.ajax({
    url: 'deleteWishlist', // 삭제를 처리할 서버의 URL을 입력합니다.
    data: { productId: productId }, // 삭제 요청에 필요한 데이터를 입력합니다.
    success: function(result) {
      if (result > 0) {
        if (productId === 'all') {
          alert("선택된 위시리스트가 삭제되었습니다.");
        } else {
          alert("위시리스트가 삭제되었습니다.");
        }
        location.reload();
      } else {
        alert("위시리스트가 삭제되지 않았습니다.");
      }
    },
    error: function() {
      console.log('위시리스트 삭제 ajax 오류');
    }
  });
};
  
function cartItem(productId, selectedOption, productPrice) {
    $.ajax({
      url: 'cartInsert', 
      data: { productId: productId,
        selectedOption : selectedOption,
        productPrice : productPrice},
      success: function(result) {
        if (result > 0) {
          alert("장바구니에 등록되었습니다.");
          location.reload();
        } else {
          alert("장바구니에 등록되지 않았습니다.");
        }
      },
      error: function() {
        console.log('장바구니 ajax 오류');
      }
    });
  };

// 전체 삭제 버튼 클릭 이벤트 핸들러
document.getElementById("check-delete-btn").addEventListener("click", function() {
  // 체크된 체크박스 요소들을 가져옵니다.
  var checkedCheckboxes = document.querySelectorAll(".checkList:checked");

  if (checkedCheckboxes.length > 0) {
    var productIds = [];
    
    // 체크된 체크박스 요소들의 상품 ID를 수집합니다.
    checkedCheckboxes.forEach(function(checkbox) {
      var row = checkbox.closest("tr"); // 현재 체크박스가 속한 행(row)을 찾습니다.
      if (row) {
        var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
        var productId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다
        productIds.push(productId);
      }
    });

    // 삭제 요청을 서버에 보냅니다
    deleteItems(productIds);
  }
});

// 전체 삭제 함수
function deleteItems(productIds) {
  $.ajax({
    url: 'deleteSelectedWishlist', // 삭제를 처리할 서버의 URL을 입력합니다.
    traditional: true, // 배열 데이터 전달을 위해 traditional 옵션을 추가합니다.
    data: { productIds: productIds }, // 삭제 요청에 필요한 데이터를 입력합니다.
    success: function(result) {
      if (result > 0) {
        alert("선택된 위시리스트가 삭제되었습니다.");
        location.reload();
      } else {
        alert("위시리스트가 삭제되지 않았습니다.");
      }
    },
    error: function() {
      console.log('위시리스트 삭제 ajax 오류');
    }
  });
};
