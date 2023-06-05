
// JavaScript 코드 all check
var allCheck = document.querySelector('.allCheck'); // allCheck 클래스를 가진 체크박스 요소를 선택합니다.
var agreeChecks = document.querySelectorAll('.agreeCheck'); // agreeCheck 클래스를 가진 모든 체크박스 요소를 선택합니다.

allCheck.addEventListener('click', function() {
    for (var i = 0; i < agreeChecks.length; i++) {
        agreeChecks[i].checked = allCheck.checked; // 모든 체크박스의 선택 상태를 allCheck 체크박스와 동일하게 설정합니다.
    }
});

// allCheck 체크박스의 선택 상태가 변경될 때마다 나머지 체크박스의 선택 상태를 업데이트합니다.
for (var i = 0; i < agreeChecks.length; i++) {
    agreeChecks[i].addEventListener('change', function() {
        var allChecked = true;
        for (var j = 0; j < agreeChecks.length; j++) {
            if (!agreeChecks[j].checked) {
                allChecked = false;
                break;
            }
        }
        allCheck.checked = allChecked; // allCheck 체크박스의 선택 상태를 업데이트합니다.
    });
}

const memberTel=document.getElementsByClassName(".memberTel")













// 유효성 검사 여부를 기록할 객체 생성
const checkObj = { 
    "memberEmail"     : false,
    "memberPw"        : false,
    "memberPwConfirm" : false,
    "memberNickname"  : false,
    "memberTel"       : false,
    "sendEmail"       : false   // 인증번호 발송 체크
    
};


// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.querySelector("#emailMessage");

memberEmail.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if( memberEmail.value.length == 0 ){
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");

        checkObj.memberEmail = false; // 유효 X 기록
        return;
    }

    // 입력된 경우
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if( regExp.test(memberEmail.value) ){ // 유효한 경우
        
        // ****** 이메일 중복 검사(ajax) 진행 예정 ******

        //  $.ajax( {k:v , k:v} );  // jQuery ajax 기본형태

        // memberEmail.value  == 입력된 이메일

        $.ajax({
            url : "emailDupCheck",   
            //  필수 속성 url
            // 현재 주소 : /stroke/member/signUp
            // 상대 경로 : /stroke/member/emailDupCheck

            data : { "memberEmail" : memberEmail.value },
            // data속성 : 비동기 통신 시 서버로 전달할 값을 작성(JS 객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을
            //   "memberEmail" 이라는 key 값(파라미터)으로 전달
            type : "GET", // 데이터 전달 방식 type

            success : function(result){
                // 비동기 통신(ajax)가 오류 없이 요청/응답 성공한 경우
                
                // 매개변수 result : servlet에서 출력된 result 값이 담겨있음
                // console.log(result);

                if(result == 1){ // 중복 O
                    emailMessage.innerText = "이미 사용중인 이메일 입니다.";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkObj.memberEmail = false; // 유효 X 기록

                } else { // 중복 X
                    emailMessage.innerText = "사용 가능한 이메일 입니다.";
                    emailMessage.classList.add("confirm");
                    emailMessage.classList.remove("error");
                    checkObj.memberEmail = true; // 유효 O 기록
                }
            },
            
            error : function(req, status, error){
                console.log(req.responseText);
            }
        });
        


    }else{
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        emailMessage.style.color = "red"; // 글자 색상을 빨간색으로 설정
        emailMessage.style.fontWeight="bold";//bold로 설정

        checkObj.memberEmail = false; // 유효 X 기록
    }

});

// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input", function(){

    if(memberPw.value.length == 0){
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("confirm", "error");

        checkObj.memberPw = false; // 유효 X 기록
        return;
    }

    const regExp = /^[\w!@#_-]{6,30}$/;

    if( regExp.test(memberPw.value) ){ // 비밀번호 유효

        checkObj.memberPw = true; // 유효 O 기록

        if(memberPwConfirm.value.length == 0){ // 비밀번호 유효, 확인 작성 X
            pwMessage.innerText = "유효한 비밀번호 형식입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        
        } else { // 비밀번호 유효, 확인 작성 O
            checkPw(); // 비밀번호 일치 검사 함수 호출()
        }

    }else{
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");

        checkObj.memberPw = false; // 유효 X 기록
    }
});











// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(memberNickname.value.length == 0){
        nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm", "error");

        checkObj.memberNickname = false; // 유효 X 기록
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(memberNickname.value) ){ // 유효한 경우
        

        // ****** 닉네임 중복 검사(ajax) 진행 예정 ******

        //     /community/member/nicknameDupCheck
        $.ajax({
            url : "nicknameDupCheck",  // 필수 작성 속성
            data : { "memberNickname" : memberNickname.value }, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)

            success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

                if(res == 0){ // 닉네임 중복 X
                    nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    checkObj.memberNickname = true; // 유효 O 기록

                } else { // 닉네임 중복 O
                    nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    checkObj.memberNickname = false; // 유효 O 기록
                }
            },

            error : function(){ // 비동기 통신 중 에러가 발생한 경우
                console.log("에러 발생");
            }
        });



    }else{
        nicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");

        checkObj.memberNickname = false; // 유효 X 기록
    }

});
