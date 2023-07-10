


// 유효성 검사 여부를 기록할 객체 생성
const checkObj = { 
    "memberEmail"     : false,
    "memberPw"        : false,
    "memberPwConfirm" : false,
    "memberNick"      : false,
    "memberName"      : false,
   // "memberSns"       : false,
    "memberAddr"      : false,
    "memberTel"       : false,
   // "TelcNumber"      : false 
     "sendEmail"       : false ,  // 인증번호 발송 체크
     "sendSms"         : false,
     
    
};


// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.querySelector("#emailMessage");

memberEmail.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if( memberEmail.value.length == 0 ){
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");
        emailMessage.style.color = "black";
        checkObj.memberEmail = false; // 유효 X 기록
        return;
    }

    // 입력된 경우
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if( regExp.test(memberEmail.value) ){ // 유효한 경우
        
    //    ****** 이메일 중복 검사(ajax) 진행 예정 ******
//      $.ajax( {k:v , k:v} );  // jQuery ajax 기본형태

     //   memberEmail.value  == 입력된 이메일

        $.ajax({
            url : contextPath +"/member/emailDupCheck",   
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
                    emailMessage.style.fontSize = '13px';
                    emailMessage.style.color = "red";
                    checkObj.memberEmail = false; // 유효 X 기록

                } else { // 중복 X
                    emailMessage.innerText = "사용 가능한 이메일 입니다.";
                    emailMessage.classList.add("confirm");
                    emailMessage.classList.remove("error");
                    emailMessage.style.fontSize = '13px';
                    emailMessage.style.color = "green";
                    checkObj.memberEmail = true; // 유효 O 기록
                }
            },
            
            error : function(req, status, error){
                console.log(req.responseText);
            }
        });
        
        // checkObj.memberEmail = true; 


    }else{
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        emailMessage.style.color = "red"; // 글자 색상을 빨간색으로 설정
        emailMessage.style.fontSize = '13px';

        checkObj.memberEmail = false; // 유효 X 기록
    }

});

//이메일 인증번호
// 인증번호 보내기
const sendBtn = document.getElementById("sendBtn");
const cMessage = document.getElementById("cMessage");

// 타이머에 사용될 변수
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;

sendBtn.addEventListener("click", function(){

    if( checkObj.memberEmail ){ // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기

        $.ajax({
            url : contextPath +"/member/sendEmail"  ,
            data : { "inputEmail" : memberEmail.value },
            type : "GET",
            success : function(result){
                console.log("이메일 발송 성공");
                console.log(result);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkObj에 기록
                checkObj.sendEmail = true;

            },
            error : function(){
                console.log("이메일 발송 실패")
            }
        });


        // Mail 발송 Ajax 코드는 동작이 느림....
        // -> 메일은 메일대로 보내고, 타이머는 버튼이 클릭 되자 마자 바로 실행
        // --> ajax 코드가 비동기이기 때문에 메일이 보내지는 것을 기다리지 않고
        //      바로 다음 코드가 수행된다!!

        // 5분 타이머
        // setInerval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행 (반복)
      
        cMessage.innerText = "5:00"; // 초기값 5분
        min = 4;
        sec = 59; // 분, 초 초기화

        cMessage.classList.remove("confirm");
        cMessage.classList.remove("error");

        // 변수에 저장해야지 멈출 수 있음
        checkInterval = setInterval(function(){
            if(sec < 10) sec = "0" + sec;
            cMessage.innerText = min + ":" + sec;

            if(Number(sec) === 0){
                min--;
                sec = 59;
            }else{
                sec--;
            }

            if(min === -1){ // 만료
                cMessage.classList.add("error");
                cMessage.innerText = "인증번호가 만료되었습니다.";
                cMessage.style.color = "red"; // 글자 색상을 빨간색으로 설정
                cMessage.style.fontSize = '13px';

                clearInterval(checkInterval); // checkInterval 반복을 지움
            }

        }, 1000); // 1초 지연 후 수행

        
        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
    }
});


// 인증번호 확인 클릭시에 대한 동작
const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");
// + cMessage, memberEmail 요소도 사용

cBtn.addEventListener("click", function(){

    // 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인
    if(checkObj.sendEmail){

        // 2. 입력된 인증번호가 6자리가 맞는지 확인
        if( cNumber.value.length == 6 ){ // 6자리 맞음

            $.ajax({
                url : contextPath +"/member/checkNumber",
                data : { "cNumber" : cNumber.value,
                         "inputEmail" : memberEmail.value },
                type : "GET",
                success : function(result){
                    console.log(result);  
                    // 1 : 인증번호 일치 O, 시간 만족O
                    // 2 : 인증번호 일치 O, 시간 만족X
                    // 3 : 인증번호 일치 X

                    if(result == 1){

                        clearInterval(checkInterval); // 타이머 멈춤     

                        cMessage.innerText = "인증되었습니다.";
                        cMessage.style.color = "green"; // 글자 색상을 빨간색으로 설정
                        cMessage.style.fontSize = '13px';
                        cMessage.classList.add("confirm");
                        cMessage.classList.remove("error");

                    } else if(result == 2){
                        alert("만료된 인증 번호 입니다.");

                    } else{ // 3
                        alert("인증 번호가 일치하지 않습니다.");
                    }


                },

                error : function(){
                    console.log("이메일 인증 실패")
                }
            });



        } else { // 6자리 아님
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }

    }else{ // 인증번호를 안받은 경우
        alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
    }

});





// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");
const pwConfirmMessage=document.getElementById("pwConfirmMessage");

memberPw.addEventListener("input", function(){

    if(memberPw.value.length == 0){
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        // pwMessage.style.fontSize="13px";
        pwMessage.classList.remove("confirm", "error");
        pwMessage.style.fontSize='13px';
        pwMessage.style.color = "black";

        checkObj.memberPw = false; // 유효 X 기록
        return;
    }

    const regExp = /^[\w!@#_-]{6,30}$/;

    if (regExp.test(memberPw.value)) { // 비밀번호 유효
        pwMessage.innerText = "유효한 비밀번호 형식입니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");
        pwMessage.style.color = "green";
        pwMessage.style.fontSize='13px';
        checkObj.memberPw = true;

      } else {
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        pwMessage.style.fontSize='13px';
        pwMessage.style.color = "red";
        checkObj.memberPw = false; // 유효 X 기록
      }
      
});


//비밀번호와 비밀번호확인 일치검사
memberPwConfirm.addEventListener("input", function() {
// -> 이벤트가 발생 되었을 때 정의된 함수를 호출하겠다


    if(memberPw.value == memberPwConfirm.value){
        pwConfirmMessage.innerText = "비밀번호가 일치합니다.";
        pwConfirmMessage.classList.add("confirm");
        pwConfirmMessage.classList.remove("error");
        pwConfirmMessage.style.fontSize='13px';
        pwConfirmMessage.style.color = "green";

        checkObj.memberPwConfirm = true; // 유효 O 기록

    } else{
        pwConfirmMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwConfirmMessage.classList.add("error");
        pwConfirmMessage.classList.remove("confirm");
        pwConfirmMessage.style.fontSize='13px';
        pwConfirmMessage.style.color = "red";

        checkObj.memberPwConfirm = false; // 유효 X 기록
    }

});







// 닉네임 유효성 검사
const memberNick = document.getElementById("memberNick");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNick.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(memberNick.value.length == 0){
        nicknameMessage.innerText = "영어(대문자 또는 소문자), 숫자, 또는 한글 문자 2~10글자로 이루어져야 합니다.";
        nicknameMessage.classList.remove("confirm", "error");
        nicknameMessage.style.fontSize='13px';
        nicknameMessage.style.color = "black";

        checkObj.memberNick = false; // 유효 X 기록
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(memberNick.value) ){ // 유효한 경우
        

        // ****** 닉네임 중복 검사(ajax) 진행 예정 ******

        //     /community/member/nicknameDupCheck
        $.ajax({
            url : contextPath +"/member/nicknameDupCheck",  // 필수 작성 속성
            data : { "memberNick" : memberNick.value }, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)

            success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

                if(res == 0){ // 닉네임 중복 X
                    nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    nicknameMessage.style.fontSize='13px';
                    nicknameMessage.style.color = "green";
                    checkObj.memberNick = true; // 유효 O 기록

                } else { // 닉네임 중복 O
                    nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    nicknameMessage.style.fontSize='13px';
                    nicknameMessage.style.color = "red";
                    checkObj.memberNick = false; // 유효 O 기록
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
        nicknameMessage.style.fontSize='13px';
        nicknameMessage.style.color = "red";

        checkObj.memberNick = false; // 유효 X 기록
    }

});

//주소(필수)-우편번호,도로명주소
const memberAddr = Array.from(document.getElementsByName("memberAddr"));

memberAddr.forEach(function (input) {
  input.addEventListener("input", function () {
    // 입력이 되지 않은 경우
    if (memberAddr[0].value.trim().length === 0 || memberAddr[1].value.trim().length === 0) {
      checkObj.memberAddr = false;
      return;
    }

    checkObj.memberAddr = true;
  });
});


//이름(필수)

const memberName=document.getElementById("memberName");
memberName.addEventListener("input",function() {

if(memberName.value.trim().length===0){
    checkObj.memberName=false;
    return;
}
checkObj.memberName = true;

})


// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

// ** input 이벤트 **
// -> 입력과 관련된 모든 동작(key관련, mouse관련, 붙여넣기)
memberTel.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if(memberTel.value.length == 0){
        telMessage.innerText = "전화번호를 입력해주세요.(- 제외,12자이내)";
        telMessage.style.fontSize='13px';
        telMessage.style.color = "black";


        //telMessage.classList.remove("confirm");
        //telMessage.classList.remove("error");
        telMessage.classList.remove("confirm", "error");

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){ // 유효한 경우
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        telMessage.style.fontSize='13px';
        telMessage.style.color = "green";

        checkObj.memberTel = true; // 유효한 상태임을 기록
        
    } else{ // 유효하지 않은 경우
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        telMessage.style.fontSize='13px';
        telMessage.style.color = "red";

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록
    }
});


//휴대폰 인증번호
// 인증번호 보내기
const sendSmsBtn = document.getElementById("sendSmsBtn");
const smsCMessage = document.getElementById("smsCMessage");

// 타이머에 사용될 변수
 checkInterval; // setInterval을 저장할 변수
 min = 4;
 sec = 59;

sendSmsBtn.addEventListener("click", function(){

   


    if( checkObj.memberTel ){ // 유효한 전화번호가 작성되어 있을 경우에만 보내기

        $.ajax({
            url :contextPath +"/member/sendSms",
            data : { "inputTel" : memberTel.value },
            type : "Get",
            success : function(response){

                console.log("sms 발송 성공");
                console.log(response);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkObj에 기록
                checkObj.sendSms = true;

            },
            error: function(xhr, status, error) {
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
                        smsCMessage.style.fontSize = '13px';
        
                        clearInterval(checkInterval); // checkInterval 반복을 지움
                    }
        
                }, 1000); // 1초 지연 후 수행
        
                
                alert("인증번호가 발송되었습니다. 휴대폰을 확인해주세요.");
            }
        });

    


// 인증번호 확인 클릭시에 대한 동작
const smsCNumber = document.getElementById("smsCNumber");
const smsCBtn = document.getElementById("smsCBtn");


smsCBtn.addEventListener("click", function(){

    // 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인
    if(checkObj.sendSms){

        // 2. 입력된 인증번호가 4자리가 맞는지 확인
        if( smsCNumber.value.length == 4 ){ // 4자리 맞음

            $.ajax({
                url : contextPath +"/member/checkSmsNumber",
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

                        smsCMessage.innerText = "인증되었습니다.";
                        smsCMessage.style.color = "green"; // 글자 색상을  설정
                        smsCMessage.style.fontSize = '13px';
                        smsCMessage.classList.add("confirm");
                        smsCMessage.classList.remove("error");

                    } else if(result == 2){
                        alert("만료된 인증 번호 입니다.");

                    } else{ // 3
                        alert("인증 번호가 일치하지 않습니다.");
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





// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수
function signUpValidate(){

    // checkObj에 있는 모든 속성을 반복 접근하여
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거

    let str;

    for( let key  in checkObj ){ // 객체용 향상된 for문

        // 현재 접근 중인 key의 value가 false인 경우
        if( !checkObj[key] ){ 

            switch(key){
            case "memberEmail":     str="이메일이"; break;
            case "memberPw":        str="비밀번호가"; break; 
            case "memberName":       str="이름이";break;
            case "memberAddr":        str="주소가";break;
            case "memberPwConfirm": str="비밀번호 확인이"; break;
            case "memberNick":  str="닉네임이"; break;
            case "memberTel":       str="전화번호가"; break;
            case "sendEmail":      str="이메일 인증번호 전송이";break;
            case "sendSms":       str="전화번호 인증번호 전송이";break;
           
            }

            str += " 유효하지 않습니다.";

            alert(str);

            //document.getElementById(key).focus();
            
            return false; // form태그 기본 이벤트 제거
        }
    }

    return true; // form태그 기본 이벤트 수행

}




/*눈모양 보안아이콘*/
const eyeIcons = document.querySelectorAll('span.fas.fa-eye');

eyeIcons.forEach(function(eyeIcon) {
    const input = eyeIcon.previousElementSibling;

    eyeIcon.addEventListener("click", function() {
        input.classList.toggle("active");
        
        if (input.classList.contains("active")) {
            this.className = 'fas fa-eye-slash fa-lg';
            input.setAttribute('type', 'text');
        } else {
            this.className = 'fas fa-eye fa-lg';
            input.setAttribute('type', 'password');
        }
    });
});


