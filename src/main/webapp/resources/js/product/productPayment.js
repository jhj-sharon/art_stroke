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

// 모달 창에서 선택된 주소의 addrId 값을 저장할 변수
var addrId;

 // 주소 선택 버튼 클릭 시 이벤트 처리
 var addressButtons = document.querySelectorAll('.address-btn');
 addressButtons.forEach(function(button) {
   button.addEventListener('click', function() {
     var addrId = this.getAttribute('data-addr-id');
     console.log(addrId); // 선택된 주소 ID 콘솔에 출력
   });
 });

 // 테스트 버튼 클릭 시 addrId 변수 값 출력
 var testButton = document.getElementById('tttest');
 testButton.addEventListener('click', function() {
   console.log(addrId); // addrId 변수 값 콘솔에 출력
 });

document.addEventListener('DOMContentLoaded', function() {
  var modal = document.querySelector('.modal');
  var addressListButton = document.querySelector('.address_list');
  var closeButton = document.querySelector('.close');
  var addressTable = document.querySelector('.myPageAddrList table');
  var deliveryInfoTable = document.querySelector('#delivery-info-table');



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
      // Get the selected address ID
      addrId = target.dataset.addrId;
      
      // Print the selected address ID to the console
      console.log('Selected Address ID:', addrId);
      
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
});




//주소선택 Ends--------------------------------------------------------
var totalPayment
var couponId = 0;

//최종금액 계산--------------------------------------------------------
function calculatePayment() {
  
  var unitPriceElements = document.querySelectorAll('.payment-item.unit-price');
  var qtyElements = document.querySelectorAll('.payment-item.qty');
  var totalProductPriceElement = document.querySelector('.pay-figure');
  var shippingFeeElement = document.querySelector('.pay-wrapper:nth-child(3) .pay-figure');
  var couponSelectElement = document.querySelector('.coupon-list');
  couponId = couponSelectElement.value;
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
  totalPayment = totalProductPrice - couponDiscount + shippingFee;
  
  // 계산된 값을 해당 요소에 업데이트
  totalProductPriceElement.textContent = totalProductPrice.toLocaleString();
  discountAmount.textContent = couponDiscount.toLocaleString();
  shippingFeeElement.textContent = shippingFee.toLocaleString();
  totalPaymentElement.textContent = totalPayment.toLocaleString();


}

// 쿠폰 선택이 변경될 때 calculatePayment 함수를 호출
var couponSelectElement = document.querySelector('.coupon-list');
couponSelectElement.addEventListener('change', function () {
  calculatePayment();
  

});


// 초기에 calculatePayment 함수를 호출합니다.
calculatePayment();


//최종금액 계산 Ends--------------------------------------------------------

// CHECKOUT 버튼 클릭 이벤트 핸들러--------------------------------------------------------
document.getElementById("pay-btn").addEventListener("click", function() {
  // 체크박스의 상태 확인
  var agreeCheckbox = document.getElementById("agree-checkbox");
  if (!agreeCheckbox.checked) {
    alert("구매약관에 동의해야 결제할 수 있습니다.");
  } else {

    console.log("결제 함수 콜링")
    requestPay();
  }
});

// CHECKOUT 버튼 클릭 이벤트 핸들러---------------------------------------------
//주문 상품 정보 객체배열 저장--------------------------------------------------------
var orderDetail = []; // 객체 배열 선언
var productIdList ="";
// payment-item-detail 클래스를 갖는 각 항목을 선택하여 정보를 객체로 저장
var paymentItemDetails = document.querySelectorAll('.payment-item-detail');
paymentItemDetails.forEach(function(item) {
  var productId = item.querySelector('.payment-item-name').id;
  var itemOption = item.querySelector('.payment-item.option span').textContent.trim().replace('Option :', '').replace(/^\s+/, '');
  var itemQty = item.querySelector('.payment-item.qty span').textContent;
  
  var paymentItem = {
    productId: productId,
    optionInfo: itemOption,
    quantity: itemQty
  };
  console.log(paymentItem);
  orderDetail.push(paymentItem); // 객체를 배열에 추가

  if (productIdList !== "") {
    productIdList += ",";
  }
  productIdList += productId;
});

var orderDetailJSON = JSON.stringify(orderDetail);
console.log(orderDetailJSON);
console.log("productIdList::"+productIdList);


//--------------------------------------------------------

//날짜형식 변환--------------------------------------------------------
function formatDateToYYYYMMDDHHMMSS(date) {
  var year = date.getFullYear();
  var month = ('0' + (date.getMonth() + 1)).slice(-2);
  var day = ('0' + date.getDate()).slice(-2);
  var hours = ('0' + date.getHours()).slice(-2);
  var minutes = ('0' + date.getMinutes()).slice(-2);
  var seconds = ('0' + date.getSeconds()).slice(-2);

  var formattedDate = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
  return formattedDate;
}
//--------------------------------------------------------

//아임포트 결제--------------------------------------------------------
//1. 필요 변수 저장하기
var orderNumber
//1) 주문번호
document.addEventListener("DOMContentLoaded", function() {
  orderNumber = document.getElementById("orderNumberSpan").innerText.trim().replace('주문번호 : ', '').replace(/^\s+/, '');
  // orderNumber 변수를 이용하여 원하는 방식으로 처리합니다.
  console.log(orderNumber);
});

var totalPrice;

// 2) 총 결제금액

console.log("totalPayment 마마지막@", totalPayment)

// 3) 배송메모
var memo;
var selectElement = document.querySelector('select[name="selec1 delivery-message"]');
if (selectElement) {
  selectElement.addEventListener('change', function() {
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    memo = selectedOption.text;
    console.log("memo: " + memo);
  });
}




//4) 배송비
var shippingFeeElement = document.getElementById('shippingFee');
var shippingFeeText = shippingFeeElement.textContent;
var shippingFee = shippingFeeText.replace(/,/g, '');
console.log("shippingFee"+shippingFee);

//5) 사용쿠폰
console.log(couponId);

//6) 주소
console.log(addrId);

//7) 결제방법
var paymethod = '';

// 결제 수단 선택 시 이벤트 처리
var paymentSelect = document.getElementById('payment');
paymentSelect.addEventListener('change', function() {
    paymethod = this.value;
    console.log('Selected Payment Method:', paymethod);
});

//8) 상품개수
var quantityElement = document.getElementById('quantity');
var quantity = quantityElement.innerText.trim();
console.log("Quantity: " + quantity);




const portinit= config.portinit;
const portRESTAPIKey = config.portRESTAPIKey;
const portRESTAPIKeySecret =config.portRESTAPIKeySecret;
// const IMP = window.IMP; 
// IMP.init(portinit); 

function requestPay() {
  // IMP.request_pay(param, callback) 결제창 호출
  var uid = '';
  var orderNumber = document.getElementById("orderNumberSpan").innerText;

  IMP.init("imp24626081");
  IMP.request_pay({ // param
      pg: 'kakaopay',
      pay_method: "card",
      merchant_uid: orderNumber, //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
      name: '아트스트로크', //결제창에 노출될 상품명
      amount: totalPayment, //금액 
      buyer_name : '전현정',
      buyer_tel : '010-1234-5678',
  }, function (rsp) { // callback
      if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
          uid = rsp.imp_uid;
          console.log("uid::",uid);
          console.log(rsp);
          // 결제검증
          $.ajax({
            url: '/stroke/order/verify_iamport',
            type: 'post',
            data: {
                imp_uid: rsp.imp_uid
            }
        }).done(function(data) {
              // 결제를 요청했던 금액과 실제 결제된 금액이 같으면 해당 주문건의 결제가 정상적으로 완료된 것으로 간주한다.
               console.log("totalPayment::", totalPayment);
               console.log("data.response.amount::", data.response.amount);
              if (totalPayment == data.response.amount) {
                  // jQuery로 HTTP 요청
                  // 주문정보 생성 및 테이블에 저장 

                
            
                      // 데이터를 json으로 보내기 위해 바꿔준다.
                      data = JSON.stringify({
                          "orderId" :  rsp.merchant_uid,
                          "addrId" : addrId, 
                          "totalPrice" : totalPayment, // 배송비포함 최종결제금액
                          "shippingFee": shippingFee,//배송비
                          "orderDate": formatDateToYYYYMMDDHHMMSS(new Date()),
                          "imp_uid" : rsp.imp_uid,
                          "shippingMemo" :  memo, // 배송메모
                          "paymethod" : paymethod,
                          "quantity" : quantity,
                          "orderDetails": orderDetailJSON,  
                          "couponId" :couponId                                         
                      });

        
                      jQuery.ajax({
                          url: "/stroke/order/complete", 
                          type: "POST",
                          dataType: 'json',
                          contentType: 'application/json',
                          data : data
                         
                      })
                      .done(function(res) {
                          if (res > 0) {
                              console.log('주문정보 저장 성공')
                             createPayInfo(uid); //결제테이블 
                          }
                          else {
                              console.log('주문정보 저장 실패');
                          }
                      })
              }
              else {
               
                  alert('결제 실패');
              }
          })
          } else {
              swal("결제에 실패하였습니다.","에러 내용: " +  rsp.error_msg,"error");
          }
      });
}

function createPayInfo(uid) {
  // 결제정보 생성 및 테이블 저장 후 결제완료 페이지로 이동 
  /**
   * 1)결제테이블 정보 입력( 결제ID + 주문 번호 + 주문 일시 + 주문 수단 + imp_uid)

2)사용한 쿠폰 삭제

3) 이미 결제된 장바구니 id 삭제 
   */
  // var uid = astest10111011;
 

console.log("createPayInfo 실행중")
  $.ajax({
      type: 'post',
      url: '/stroke/order/pay_info',
      data: {
           "orderId" :  orderNumber, // 주문 번호
           "paymentDate": formatDateToYYYYMMDDHHMMSS(new Date()),
           "paymethod" : paymethod,
           "totalPrice" : totalPayment, // 배송비포함 최종결제금액
           "couponId" :couponId,
           "productIdList" :productIdList

      },
      success: function(data) {
          
          alert('결제 성공 !',"결제완료 페이지로 이동합니다.").then(function(){
              
              // 결제완료 페이지로 이동
              location.replace('http://localhost:8080/stroke/product/productConfirm?'+uid);

          })
      },
      error: function() {
        
          alert('결제정보 저장 통신 실패');
      }
  });
}