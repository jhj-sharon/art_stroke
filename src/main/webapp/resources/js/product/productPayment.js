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