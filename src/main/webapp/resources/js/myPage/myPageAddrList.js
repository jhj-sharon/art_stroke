function openPopup(deliveryName, receiverName, addrTel, addr,addrId) {
  const addrArr = addr.split(",,");
  document.getElementById('addrName').value = deliveryName;
  document.getElementById('receiverName').value = receiverName;
  document.getElementById('addrTel').value = addrTel;
  document.getElementById("addrId").value = addrId;
  if (addrArr.length === 0 || addrArr[0] === '') {
    document.getElementById('sample4_postcode').value = '';
    document.getElementById('sample4_roadAddress').value = '';
    document.getElementById('sample4_detailAddress').value = '';
  } else {
    document.getElementById('sample4_postcode').value = addrArr[0];
    document.getElementById('sample4_roadAddress').value = addrArr[1];
    document.getElementById('sample4_detailAddress').value = addrArr[2];
  }
  
  var popup = document.getElementById("popup");
  popup.style.visibility = "visible";
  popup.style.opacity = "1";
}

function closePopup() {
		var popup = document.getElementById("popup");
		popup.style.visibility = "hidden";
		popup.style.opacity = "0";
}

document.addEventListener('DOMContentLoaded', function() {
  var tbodyElement = document.querySelector('tbody');
  
  tbodyElement.addEventListener('click', function(event) {
    var targetElement = event.target;
    
    if (targetElement.classList.contains('fa-trash')) {
      var addrId = targetElement.id;
      
      $.ajax({
        url: '/stroke/myPage/deleteAddress',
        data: { addrId : addrId },
        success: function(result) {
          if(result > 0) {
            alert("배송지가 삭제되었습니다.");
            location.reload();
          } else {
            alert("배송지가 삭제되지 않았습니다.");
          }
        },
        error: function() {
          console.log('배송지 삭제 ajax 오류');
        }
      });
    }
  });
});


document.getElementById('sample4_postcode').addEventListener('click', sample4_execDaumPostcode);
document.getElementById('sample4_roadAddress').addEventListener('click', sample4_execDaumPostcode);



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
