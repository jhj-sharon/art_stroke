/* 모두 체크 */
const allCheckBox = document.getElementById("restViewSelectAll");
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

function getRecentProductsCookie() {
    var cookies = document.cookie.split("; ");
    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i].split("=");
      if (cookie[0] === "recent_products") {
        console.log(cookie[1]);
        return cookie[1];
      }
    }
    return ""; // 해당 쿠키가 없을 경우 빈 문자열 반환
  }


var deleteButtons = document.querySelectorAll('.myPage-btn#delete-btn'); // 모든 삭제 버튼을 선택합니다
deleteButtons.forEach(function(button) {
    button.addEventListener('click', function(event) {
      var button = event.target; // 클릭된 버튼 요소를 가져옵니다
      var row = button.closest('tr'); // 클릭된 버튼 요소의 가장 가까운 tr 요소를 탐색합니다
  
      if (row) {
        var checkbox = row.querySelector('.checkList'); // 해당 행에서 클래스가 'checkList'인 체크박스 요소를 가져옵니다
        var productId = checkbox.id; // 체크박스 요소의 id 값을 가져옵니다
        // 선택한 상품의 ID 값을 사용하여 쿠키를 삭제합니다
        alert("최근 본 상품이 삭제 되었습니다.");
        deleteRecentProductCookie(productId);
      }
    });
});
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
        
        // 삭제할 상품 ID들을 전달하여 쿠키 삭제를 수행합니다.
        
        deleteRecentProductCookies(productIds);
    }else{
        alert("삭제 하고싶은 상품을 선택 후 클릭 해주세요");
    }
});
function deleteRecentProductCookies(productIds) {
  var deletedCount = 0; // 삭제된 상품 개수를 카운트하는 변수

  productIds.forEach(function(productId) {
    var result = deleteRecentProductCookie(productId);
    if (result) {
      deletedCount++;
    }
  });

  if (deletedCount > 0) {
    if (deletedCount === productIds.length) {
      alert("모든 선택한 상품이 삭제되었습니다.");
    } else {
      alert(deletedCount + "개의 상품이 삭제되었습니다.");
    }
    location.reload();
  }
}
  function deleteRecentProductCookie(productId) {
    var cookieName = "recent_products";
    var cookies = document.cookie.split(";"); // 쿠키 문자열을 ; 로 분리하여 배열로 변환
  
    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i].trim(); // 쿠키 문자열의 앞뒤 공백 제거
      if (cookie.indexOf(cookieName) === 0) {
        // 쿠키 이름이 'recent_products'로 시작하는 경우
        var cookieValue = cookie.substring(cookieName.length + 1); // 쿠키 값 추출
        var products = cookieValue.split("/"); // 쿠키 값을 / 로 분리하여 배열로 변환
  
        // productId 값이 일치하는 쿠키 제거
        var updatedProducts = products.filter(function(product) {
          return product !== productId;
        });
  
        // 쿠키 값 업데이트
        if (updatedProducts.length > 0) {
          var updatedCookieValue = updatedProducts.join("/"); // / 로 분리된 배열을 다시 문자열로 결합
          document.cookie = cookieName + "=" + updatedCookieValue + "; path=/;";
          location.reload();
        } else {
          // 모든 상품이 제거되었을 경우 해당 쿠키를 삭제
          document.cookie = cookieName + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
          location.reload();
        }
        return true; // 삭제 성공 시 true 반환
      }
    }
    return false; // 해당 상품 ID를 가진 쿠키가 없으면 false 반환
}

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


  
function cartItem(productId, selectedOption, productPrice) {
    $.ajax({
      url: '/stroke/myPage/cartInsert', 
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
