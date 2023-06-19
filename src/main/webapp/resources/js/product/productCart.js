//체크박스--------------------------------------------------------  
// thead의 checkbox
const theadCheckbox = document.querySelector("#cart-item-table thead input[type='checkbox']");

// tbody의 모든 checkbox 
const tbodyCheckboxes = document.querySelectorAll("#cart-item-table tbody input[type='checkbox']");


theadCheckbox.addEventListener("change", function() {
  // thead의 checkbox 상태에 따라 모든 tbody의 checkbox를 변경
  tbodyCheckboxes.forEach(function(checkbox) {
    checkbox.checked = theadCheckbox.checked;
  });
});


tbodyCheckboxes.forEach(function(checkbox) {
  checkbox.addEventListener("change", function() {
    // tbody의 checkbox 상태에 따라 thead의 checkbox를 변경
    const areAllChecked = Array.from(tbodyCheckboxes).every(function(checkbox) {
      return checkbox.checked;
    });
    theadCheckbox.checked = areAllChecked;
  });
});

//체크박스 End--------------------------------------------------------

//장바구니 상품삭제-------------------------------------------------------------

  // 선택상품 삭제 함수
  function deleteSelectedItems() {
    // 체크된 체크박스 요소들을 선택합니다
    const checkboxes = $('#cart-item-table input[type="checkbox"]:checked');
    // 선택한 cartId를 저장할 배열 선언
    const cartIds = [];

    // 체크된 체크박스에 대한 처리를 수행합니다
    checkboxes.each(function () {
      // 체크박스 요소의 부모인 <tr> 요소를 변수에 저장합니다
      const row = $(this).closest('tr');
      const cartId = row.attr('id'); // 해당 <tr>의 cartId를 가져옵니다
      cartIds.push(cartId); // cartIds 배열에 cartId를 추가합니다
    });

    // 서버로 cartIds 배열을 전송하여 삭제 처리를 요청합니다
    $.ajax({
      url: 'deleteCart', // 서버의 URL을 입력합니다
      type: 'POST',
      data: { cartIds: cartIds }, // cartIds 배열을 서버로 전송합니다
      success: function (response) {
         if(response === 1 ){
             // 선택한 요소들을 화면에서 삭제합니다
             checkboxes.closest('tr').remove();
         }else{
            alert("장바구니 아이템 삭제에 실패했습니다. 잠시후에 다시 시도하세요.");
         }
      },
      error: function (xhr, status, error) {
        // 서버 요청이 실패한 경우의 처리를 수행합니다
        console.error('Delete request failed:', error);
      }
    });
  }

  // 선택상품 삭제 버튼 클릭 시 deleteSelectedItems 함수 호출
  $('#delete-btn').on('click', deleteSelectedItems);


//상품삭제 End--------------------------------------------------------