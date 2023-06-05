function show () {
  document.querySelector(".background").className = "background show";
}

function close () { 
  document.querySelector(".background").className = "background";
}

document.querySelector("#myPage-secession").addEventListener('click', show);
document.querySelector("#close").addEventListener('click', close);

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


 const snsInput = document.getElementById('memberSNS');

snsInput.addEventListener('blur', function() {
    const inputValue = this.value.trim();
    
    // 입력된 값이 비어 있지 않고 "http://"로 시작하지 않는 경우
    if (inputValue !== '' && !inputValue.startsWith('http://')) {
        this.value = 'http://' + inputValue;
    }
});