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
    
    // 입력된 값이 비어 있지 않고 "https://"로 시작하지 않는 경우
    if (inputValue !== '' && !inputValue.startsWith('https://')) {
        this.value = 'https://' + inputValue;
    }
});

// 회원 탈퇴 유효성 검사
function secessionValidate(){

    const agree = document.getElementById("agree");
    // 약관 동의 체크 여부
    // - 체크박스요소.checked  : 체크 시 true, 해제 시 false 반환

    if( !agree.checked ){ // 체크를 안했을 때
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        agree.focus();
        return false;
    }

    // 정말 탈퇴할지 확인
    // - window.confirm("내용") : 대화 상자에 확인/취소 생성
    //      확인 클릭 시 true / 취소 클릭 시 false
    //      window는 생략 가능
    
    if( !confirm("정말 탈퇴 하시겠습니까?") ){ //  취소를 누른 경우
        return false;
    }

    return true;
}

// 유효성 검사 여부를 기록할 객체 생성
const checkObj = { 
  "memberPw"        : false,
  "memberPwConfirm" : false,
  "memberNick"      : true
};


const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");


memberPw.addEventListener("input", function(){
    const regExp = /^[\w!@#_-]{6,30}$/;

    if (regExp.test(memberPw.value)) { // 비밀번호 유효
        pwMessage.innerText = "유효한 비밀번호 형식입니다.";
        pwMessage.style.color = "green";
        pwMessage.style.fontSize='8px';
        checkObj.memberPw = true;

      } else {
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.style.fontSize='8px';
        pwMessage.style.color = "red";
        checkObj.memberPw = false; // 유효 X
      }
});


//비밀번호와 비밀번호확인 일치검사
memberPwConfirm.addEventListener("input", function() {
    if(memberPw.value == memberPwConfirm.value){
      pwMessage.innerText = "비밀번호가 일치합니다.";
      pwMessage.style.fontSize='8px';
      pwMessage.style.color = "green";
      checkObj.memberPwConfirm = true;
    } else{
      pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
      pwMessage.style.fontSize='8px';
      pwMessage.style.color = "red";
      checkObj.memberPwConfirm = false;
    }
});




// 닉네임 유효성 검사
const memberNick = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNick.addEventListener("input", function(){
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(memberNick.value) ){ // 유효한 경우

        $.ajax({
            url : "/stroke/myPage/nicknameDupCheck",  // 필수 작성 속성
            data : { "memberNick" : memberNick.value }, // 서버로 전달할 값(파라미터)
            success : function(result){ // 비동기 통신 성공 시(에러 발생 X)
                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

                if(result == 0
                  ){ // 닉네임 중복 X
                    nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    nicknameMessage.style.fontSize='8px';
                    nicknameMessage.style.color = "green";
                    checkObj.memberNick = true; 
                } else { // 닉네임 중복 O
                    nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                    nicknameMessage.style.fontSize='8px';
                    nicknameMessage.style.color = "red";
                    checkObj.memberNick = false; 
                }
            },
            error : function(){ // 비동기 통신 중 에러가 발생한 경우
                console.log("에러 발생");
            }
        });
    }else{
        nicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
        nicknameMessage.style.fontSize='8px';
        nicknameMessage.style.color = "red";
        checkObj.memberNick = false;
    }
});



// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수
function modifyValidate(){
  let str;

  for( let key  in checkObj ){
      if( !checkObj[key] ){ 
          switch(key){
          case "memberPw":        str="비밀번호가"; break;    
          case "memberPwConfirm": str="비밀번호 확인이"; break;
          case "memberNick":      str="닉네임이"; break;
          }
          str += " 유효하지 않습니다.";
          alert(str);
          return false; // form태그 기본 이벤트 제거
      }
  }
    // 모든 유효성 검사가 통과한 후에 닉네임 중복 체크를 확인합니다.
  if (!checkObj.memberNick) {
    return false; // form 태그 기본 이벤트 제거
  }

  return true;
}
const contextPath = getContextPath();
function getContextPath() {
	return sessionStorage.getItem("contextpath");
}
document.getElementById("btn").addEventListener("click",function(){

  window.location.href = contextPath +"/myPage/myPageMain";
})