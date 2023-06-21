console.log('Hello World!');



function showAdress(className) {
    /* 컨텐츠 동작 */
    /* 모두 숨기기 */
    $(".addressInfo_input_div").css('display', 'none');
    /* 컨텐츠 보이기 */
    $(".addressInfo_input_div_" + className).css('display', 'block');
  
      // 쿠폰 영역의 위치 조정
  if (className === '2') {
    $('.discout-info').css('margin-top', '20px');
  } else {
    $('.discout-info').css('margin-top', '0');
  }

    /* 버튼 색상 및 글자 스타일 변경 */
    $(".address_btn").css('backgroundColor', '#eeeeee').css('font-weight', 'normal').css('border-bottom', 'none');
    $(".address_btn_" + className).css('backgroundColor', 'white').css('font-weight', 'bold').css('border-bottom', '1px solid white');
  }
  

  
// 다음 주소 API를 호출하여 주소 검색 팝업을 띄우는 함수
function sample4_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 검색 결과 처리 로직을 작성합니다.
      // 선택한 주소 정보는 data 객체에 담겨있습니다.
      // 예시: 우편번호, 도로명주소, 상세주소 정보 가져오기
      var postcode = data.zonecode; // 우편번호
      var roadAddress = data.roadAddress; // 도로명주소

      // 가져온 주소 정보를 원하는 위치에 적용하거나 다른 처리를 수행합니다.
      document.getElementById('sample4_postcode').value = postcode;
      document.getElementById('sample4_roadAddress').value = roadAddress;
    },
  }).open();
}

//새주소 등록하기--------------------------------------------------------
$(document).ready(function() {
  $('#addressForm').submit(function(event) {
    event.preventDefault(); // 폼 submit 기본 동작 방지

    var addrName = $("#addrName").val();
    var receiverName = $("#receiverName").val();
    var postcode = $("#sample4_postcode").val();
    var roadAddress = $("#sample4_roadAddress").val();
    var detailAddress = $("#sample4_detailAddress").val();
    var memberTel = $("#memberTel").val();

    var formData = {
      addrName: addrName,
      receiverName: receiverName,
      postcode: postcode,
      roadAddress: roadAddress,
      detailAddress: detailAddress,
      memberTel: memberTel
    };

    console.log(formData);

    $.ajax({
      url: 'newAddr',
      type: 'POST',
      data: formData,
      success: function(response) {
        console.log('주소 등록 성공');
        alert('주소지를 등록했습니다. 기존 배송지 선택에서 선택할 수 있습니다.')
      },
      error: function(xhr, status, error) {
        console.log('주소 등록 실패');
        alert('주소지 등록 실패. 기존 배송지 선택에서 선택할 수 있습니다.')
      }
    });
  });
});


//주소 모달--------------------------------------------------------
$(document).ready(function() {
  // 버튼 클릭 시 모달 창 열기
  console.log("================================")
  $(".address_list_btn").on("click", function() {
    $(".modal").css("display", "block");
  });

  // 모달 닫기 버튼 클릭 시 모달 창 닫기
  $(".close").on("click", function() {
    $(".modal").css("display", "none");
  });
});


//주소선택--------------------------------------------------------
// Get the modal element
var modal = document.querySelector('.modal');

// Get the button that opens the modal
var addressListButton = document.querySelector('.address_list');

// Get the close button element in the modal
var closeButton = document.querySelector('.close');

// Get the table in the modal
var addressTable = document.querySelector('.myPageAddrList table');

// Get the delivery information table
var deliveryInfoTable = document.querySelector('#delivery-info-table');

// Add a click event listener to the address list button
addressListButton.addEventListener('click', function() {
  // Display the modal
  modal.style.display = 'block';
});

// Add a click event listener to the close button
closeButton.addEventListener('click', function() {
  // Hide the modal
  modal.style.display = 'none';
});

// Add a click event listener to the address table
addressTable.addEventListener('click', function(event) {
  var target = event.target;
  
  // Check if the clicked element is a button
  if (target.tagName === 'BUTTON') {
    // Get the selected address information
    var selectedAddressRow = target.parentNode.parentNode;
    var selectedAddressCells = selectedAddressRow.getElementsByTagName('td');
    var selectedDeliveryName = selectedAddressCells[0].textContent;
    var selectedReceiverName = selectedAddressCells[1].textContent;
    var selectedAddress = selectedAddressCells[2].textContent;
    var selectedPhoneNumber = selectedAddressCells[3].textContent;
    
    // Format the phone number as 000-0000-0000
    var formattedPhoneNumber = selectedPhoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    
    // Update the delivery information table with the selected address information
    var deliveryInfoRows = deliveryInfoTable.getElementsByTagName('tr');
    for (var i = 0; i < deliveryInfoRows.length; i++) {
      var thElement = deliveryInfoRows[i].getElementsByTagName('th')[0];
      var tdElement = deliveryInfoRows[i].getElementsByTagName('td')[0];
      
      if (thElement.textContent === '주문자 성함') {
        // Update the member name
        tdElement.textContent = selectedReceiverName;
      } else if (thElement.textContent === '배송지') {
        // Update the delivery location
        tdElement.textContent = selectedDeliveryName;
      } else if (thElement.textContent === '주소') {
        // Update the address
        tdElement.innerHTML = selectedAddress;
      } else if (thElement.textContent === '전화번호') {
        // Update the phone number with the formatted value
        tdElement.textContent = formattedPhoneNumber;
      }
    }
    
    // Hide the modal after selecting the address
    modal.style.display = 'none';
  }
});


//주소선택 Ends--------------------------------------------------------

//최종금액 계산--------------------------------------------------------
function calculatePayment() {
  
  var unitPriceElements = document.querySelectorAll('.payment-item.unit-price');
  var qtyElements = document.querySelectorAll('.payment-item.qty');
  var totalProductPriceElement = document.querySelector('.pay-figure');
  var shippingFeeElement = document.querySelector('.pay-wrapper:nth-child(3) .pay-figure');
  var couponSelectElement = document.querySelector('.coupon-list');
  var couponValue = couponSelectElement.value;
  var couponName = couponSelectElement.options[couponSelectElement.selectedIndex].text;
  var totalPaymentElement = document.querySelector('.pay-figure.total span');
  var discountAmount = document.querySelector('.discountAmount');
  // 상품 총 가격
  var totalProductPrice = 0;
  for (var i = 0; i < unitPriceElements.length; i++) {
    var unitPrice = parseInt(unitPriceElements[i].textContent);
    var quantity = parseInt(qtyElements[i].textContent);
    totalProductPrice += unitPrice * quantity;
  }
  
  // 쿠폰 할인
  var couponDiscount = 0;
  if (couponName !== '보유 쿠폰을 선택하세요.') {
    if (couponName.includes('%')) {
      var percentage = parseInt(couponName.match(/\d+/)[0]);
      couponDiscount = (totalProductPrice * percentage) / 100;
    } else if (couponName.includes('배송비') && parseInt(shippingFeeElement.textContent) > 0) {
      couponDiscount = parseInt(shippingFeeElement.textContent);
      shippingFeeElement.textContent = '0';
    }
  }

  console.log(couponDiscount);
  
  // 배송비
  var shippingFee = totalProductPrice >= 50000 ? 0 : 3000;
  
  // 총 결제 금액
  var totalPayment = totalProductPrice - couponDiscount + shippingFee;
  
  // 계산된 값을 해당 요소에 업데이트
  totalProductPriceElement.textContent = totalProductPrice.toLocaleString();
  discountAmount.textContent = couponDiscount.toLocaleString();
  shippingFeeElement.textContent = shippingFee.toLocaleString();
  totalPaymentElement.textContent = totalPayment.toLocaleString();
}

// 쿠폰 선택이 변경될 때 calculatePayment 함수를 호출
var couponSelectElement = document.querySelector('.coupon-list');
couponSelectElement.addEventListener('change', calculatePayment);

// 초기에 calculatePayment 함수를 호출합니다.
calculatePayment();

//최종금액 계산 Ends--------------------------------------------------------
// 아임포트 결제
const portinit= config.portinit;
const portRESTAPIKey = config.portRESTAPIKey;
const portRESTAPIKeySecret =config.portRESTAPIKeySecret;
const IMP = window.IMP; // 생략 가능
IMP.init(apiKey); // 예: imp00000000a

// function iamport(){

//     var flag = $("#flag").val();
//     var principalId = $("#principalId").val();
//     var name = $("#name").val();
//     var phone = $("#phone").val();
//     var email = $("#email").val();
//     var postcode = $("#postcode").val();
//     var address = $("#address").val() + " " + $("#detailAddress").val();

//     var productName;
//     var productId = $("#productId").val();
//     var detailName = $("#productName").val();
//     var cartName = $("#cartName").val();
//     var amount = $("#amount").val();
//     var price = $("#total-price").text();


//     //가맹점 식별코드
//     IMP.init("imp20807674");
//     IMP.request_pay({
//         pg : 'kcp',
//         pay_method : 'card',
//         merchant_uid : 'merchant_' + new Date().getTime(),
//         name : productName,
//         amount : price,
//         buyer_email : email,
//         buyer_name : name,
//         buyer_tel : phone,
//         buyer_addr : address,
//         buyer_postcode : postcode
//     }, function(res) {

//         // 결제검증
//         $.ajax({
//             type : "POST",
//             url : "/verifyIamport/" + res.imp_uid
//         }).done(function(data) {

//             if(res.paid_amount == data.response.amount){
//                 alert("결제 및 결제검증완료");

//                 //결제 성공 시 비즈니스 로직

//             } else {
//                 alert("결제 실패");
//             }
//         });
//     });
// }