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
  


// // 버튼 클릭 이벤트 처리
// document.getElementById('pay-btn').addEventListener('click', function() {
//     // 이동할 URL 생성
//     var url = '${contextPath}/product/productConfirm';

//     // URL로 이동
//     window.location.href = url;
// });


// 아임포트 결제

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