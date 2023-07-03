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
  "memberNick"      : true,
  "memberTel"       : true,
  "checkSms"        : true
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
          case "memberTel":       str="번호가"; break;
          case "checkSms":        str="인증번호가"; break;
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


// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

// ** input 이벤트 **
// -> 입력과 관련된 모든 동작(key관련, mouse관련, 붙여넣기)
memberTel.addEventListener("input", function(){
  checkObj.memberTel = false;
  checkObj.checkSms = false;
    // 입력이 되지 않은 경우
    if(memberTel.value.length == 0){
        telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";
        telMessage.style.fontSize='8px';
        telMessage.style.color = "black";
        telMessage.classList.remove("confirm", "error");

        checkObj.memberTel = true; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){ // 유효한 경우
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        telMessage.style.fontSize='8px';
        telMessage.style.color = "black";

        checkObj.memberTel = true; // 유효한 상태임을 기록
        
    } else{ // 유효하지 않은 경우
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        telMessage.style.fontSize='8px';
        telMessage.style.color = "red";

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록
    }
});


//휴대폰 인증번호
// 인증번호 보내기
const sendSmsBtn = document.getElementById("sendSmsBtn");
const smsCMessage = document.getElementById("smsCMessage");

// 타이머에 사용될 변수
var checkInterval; // setInterval을 저장할 변수
var min = 4;
var sec = 59;

sendSmsBtn.addEventListener("click", function(){
    if( checkObj.memberTel ){ // 유효한 전화번호가 작성되어 있을 경우에만 보내기

        $.ajax({
            url :"sendSms",
            data : { "inputTel" : memberTel.value },
            type : "Get",
            success : function(response){

                console.log("sms 발송 성공");
                console.log(response);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkObj에 기록
                checkObj.sendSms = true;

            },
            error: function(error) {
                var errorMessage = error || "Unknown error occurred.";
                console.log("SMS 발송 실패: " + errorMessage);
              
            }
            
        });
        smsCMessage.innerText = "5:00"; // 초기값 5분
        min = 4;
        sec = 59; // 분, 초 초기화
        smsCMessage.classList.remove("confirm");
        smsCMessage.classList.remove("error");

        // 변수에 저장해야지 멈출 수 있음
        checkInterval = setInterval(function(){
            if(sec < 10) sec = "0" + sec;
            smsCMessage.innerText = min + ":" + sec;

            if(Number(sec) === 0){
                min--;
                sec = 59;
            }else{
                sec--;
            }
            if(min === -1){ // 만료
                smsCMessage.classList.add("error");
                smsCMessage.innerText = "인증번호가 만료되었습니다.";
                smsCMessage.style.color = "red"; // 글자 색상을 빨간색으로 설정
                smsCMessage.style.fontSize = '8px';

                clearInterval(checkInterval); // checkInterval 반복을 지움
            }

        }, 1000); // 1초 지연 후 수행
        alert("인증번호가 발송되었습니다. 휴대폰을 확인해주세요.");
    }
});

// 인증번호 확인 클릭시에 대한 동작
const smsCNumber = document.getElementById("smsCNumber");
const smsCBtn = document.getElementById("smsCBtn");

smsCNumber.addEventListener("input",function(){
  checkObj.checkSms = false;
})
smsCBtn.addEventListener("click", function(){
    if(checkObj.sendSms){
        // 2. 입력된 인증번호가 4자리가 맞는지 확인
        if( smsCNumber.value.length == 4 ){ // 4자리 맞음
            $.ajax({
                url : "checkSmsNumber",
                data : { "smsCNumber" : smsCNumber.value,
                         "inputTel" : memberTel.value },
                type : "GET",
                success : function(result){
                    console.log(result);  
                    // 1 : 인증번호 일치 O, 시간 만족O
                    // 2 : 인증번호 일치 O, 시간 만족X
                    // 3 : 인증번호 일치 X
                    if(result>0){
                        alert('인증성공');
                       
                    }else{
                        alert('인증실패');

                    }
                    if(result == 1){
                        clearInterval(checkInterval); // 타이머 멈춤     
                        checkObj.checkSms = true;
                        smsCMessage.innerText = "인증되었습니다.";
                        smsCMessage.style.color = "black"; // 글자 색상을  설정
                        smsCMessage.style.fontSize = '8px';
                        smsCMessage.classList.add("confirm");
                        smsCMessage.classList.remove("error");

                    } else if(result == 2){
                        alert("만료된 인증 번호 입니다.");
                        checkObj.checkSms = false;

                    } else{ // 3
                        alert("인증 번호가 일치하지 않습니다.");
                        checkObj.checkSms = false;
                    }
                },
                error : function(){
                    console.log("이메일 인증 실패")
                }
            });
        } else { // 6자리 아님
            alert("인증번호를 정확하게 입력해주세요.");
            smsCNumber.focus();
        }
    }else{ // 인증번호를 안받은 경우
        alert("전송 버튼을 먼저 클릭해주세요.");
    }
});

