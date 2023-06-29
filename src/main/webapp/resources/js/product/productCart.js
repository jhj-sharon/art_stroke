
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
      data: { cartIds: JSON.stringify(cartIds) }, // cartIds 배열을 서버로 전송합니다
      success: function (response) {
         if(response === 1 ){
             // 선택한 요소들을 화면에서 삭제합니다
             console.log("장바구니 삭제 완료")
             checkboxes.closest('tr').remove();
             calculateCartItemPrices()
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


//선택상품 주문-------------------------------------------------------
// 선택상품 주문 함수
function orderSelectedItems() {
  const checkboxes = $('#cart-item-table input[type="checkbox"]:checked');

  // 선택한 cartId를 저장할 배열 선언
  const cartIds = [];

  // 체크된 체크박스에 대한 처리를 수행합니다
  if (checkboxes.length > 0) {
    checkboxes.each(function () {
      // 체크박스 요소의 부모인 <tr> 요소를 변수에 저장합니다
      const row = $(this).closest('tr');
      const cartId = row.attr('id'); // 해당 <tr>의 cartId를 가져옵니다
      cartIds.push(cartId); // cartIds 배열에 cartId를 추가합니다
    });
  } else {
    // 체크된 체크박스가 없을 경우 모든 <tr>의 cartId를 가져옵니다
    $('#cart-item-table tbody tr').each(function () {
      const cartId = $(this).attr('id'); // 해당 <tr>의 cartId를 가져옵니다
      cartIds.push(cartId); // cartIds 배열에 cartId를 추가합니다
    });
  }

  console.log("carid:::",cartIds);
 // 서버로 cartIds 배열을 전송하여 orderItems 추가를 요청합니다
 $.ajax({
   url: 'selectedOrder', // 서버의 URL을 입력합니다
   type: 'POST',
   data: { cartIds: JSON.stringify(cartIds) }, // cartIds 배열을 서버로 전송합니다
   success: function (response) {
      if(response === 1 ){
          // 선택한 요소들 OrderItems에 저장
          //${contextPath}/product/productPayment이동
          if(response >0){
           alert("주문페이지로 이동합니다.");
           location.replace('http://localhost:8080/stroke/product/productPayment');
          }else{
           alert("등록 실패")
          }
          

      }else{
         alert("구매 페이지 이동에 실패했습니다. 잠시후에 다시 시도하세요.");
      }
   },
   error: function (xhr, status, error) {
     // 서버 요청이 실패한 경우의 처리를 수행합니다
     console.error('Delete request failed:', error);
   }
 });
}
// 선택상품 주문 버튼 클릭 시 orderSelectedItems 함수 호출
$('#selectOrder-btn').click(function() {
 orderSelectedItems();
});


//선택상품 주문 End--------------------------------------------------------


//상품 수량 조절 버튼--------------------------------------------------------
// 이벤트 핸들러 등록
function registerQuantityChangeHandlers() {
  const minusButtons = document.querySelectorAll('.qtybtn.minus-btn');
  const plusButtons = document.querySelectorAll('.qtybtn.plus-btn');

  minusButtons.forEach(button => {
    button.addEventListener('click', decreaseQuantity);
  });

  plusButtons.forEach(button => {
    button.addEventListener('click', increaseQuantity);
  });
}

// 수량 감소 처리
function decreaseQuantity(event) {
  const quantityInput = event.target.parentElement.querySelector('.qty');
  let quantity = parseInt(quantityInput.value);
  
  if (quantity > 1) {
    quantity--;
    quantityInput.value = quantity;
    calculateCartItemPrices();
  }
}

// 수량 증가 처리
function increaseQuantity(event) {
  const quantityInput = event.target.parentElement.querySelector('.qty');
  let quantity = parseInt(quantityInput.value);
  
  quantity++;
  quantityInput.value = quantity;
  calculateCartItemPrices();
}

// 페이지 로드 시 이벤트 핸들러 등록
window.addEventListener('DOMContentLoaded', () => {
  registerQuantityChangeHandlers();
  calculateCartItemPrices();
});

//상품 수량 조절 버튼 End--------------------------------------------------------

//상품 개별가격 & 총가격 --------------------------------------------------------
// 상품 개별 금액 및 총 가격 계산 함수
function calculateCartItemPrices() {
  const cartItems = document.querySelectorAll('#cart-item-table tbody tr');
  let itemSum = 0;
  
  cartItems.forEach(item => {
    const quantity = parseInt(item.querySelector('.qty').value);
    const productPrice = parseInt(item.querySelector('.cart-item-unit-price').textContent);
    const totalPrice = quantity * productPrice;

    const cartItemPriceElement = item.querySelector('.cart-item-price');
    cartItemPriceElement.textContent = totalPrice.toLocaleString();

    itemSum += totalPrice;
  });

  let deleverySum = 3000;
  if (itemSum >= 50000) {
    deleverySum = 0;
  }
  const totalSum = itemSum + deleverySum;

  const itemSumElement = document.querySelector('.itemSum');
  const deleverySumElement = document.querySelector('.deleverySum');
  const totalSumElement = document.querySelector('.totalSum');

  itemSumElement.textContent = itemSum.toLocaleString();
  deleverySumElement.textContent = deleverySum.toLocaleString();
  totalSumElement.textContent = totalSum.toLocaleString();
}

// 수량 조절 버튼 이벤트 핸들러 등록
function registerQuantityChangeHandlers() {
  const minusButtons = document.querySelectorAll('.qtybtn.minus-btn');
  const plusButtons = document.querySelectorAll('.qtybtn.plus-btn');

  minusButtons.forEach(button => {
    button.addEventListener('click', decreaseQuantity);
  });

  plusButtons.forEach(button => {
    button.addEventListener('click', increaseQuantity);
  });
}

// 수량 감소 처리
function decreaseQuantity(event) {
  const quantityInput = event.target.parentElement.querySelector('.qty');
  let quantity = parseInt(quantityInput.value);

  if (quantity > 1) {
    quantity--;
    quantityInput.value = quantity;
    calculateCartItemPrices();
  }
}

// 수량 증가 처리
function increaseQuantity(event) {
  const quantityInput = event.target.parentElement.querySelector('.qty');
  let quantity = parseInt(quantityInput.value);

  quantity++;
  quantityInput.value = quantity;
  calculateCartItemPrices();
}

// 페이지 로드 시 초기화 함수 호출
window.addEventListener('DOMContentLoaded', () => {
  registerQuantityChangeHandlers();
  calculateCartItemPrices();
});


//상품 총가격 ends--------------------------------------------------------

//장바구니에 상품이 없을 경우 payment 페이지 이동 불가---------------------
function noProduct(){
  alert("장바구니에 상품이 없습니다. 상품을 추가하세요.")
}
//-----------------------------------------------------------------------
