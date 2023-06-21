/*
*/
console.log("main.js loaded.");

// 로그인 시 이메일(아이디)/비밀번호 입력 확인

// -> 미작성 시 alert() 이용해서 메세지를 출력하고
//    로그인 form 태그의 제출을 막는 기본 이벤트 제거 진행

function loginValidate(){ // 로그인 유효성 검사

    const inputEmail = document.getElementsByName("memberEmail")[0];

    // 비밀번호 입력 input 요소
    const inputPw = document.getElementsByName("memberPw")[0];


    // 이메일이 입력되지 않은 경우 false를 반환
    if( inputEmail.value.trim().length == 0 ){
        // 문자열.trim() : 문자열 양쪽 공백을 제거
        // 문자열.length : 문자열 길이(몇 글자?)

        alert("이메일을 입력해주세요.");
        inputEmail.value = ""; // 이메일 input에 입력된 내용을 모두 삭제
        inputEmail.focus(); // 이메일 input에 포커스를 맞춰줌
        return false; // 기본 이벤트 제거를 위해 false 반환
    }
    //이메일에 공백이 포함된 경우 false반환
    if (inputEmail.value.includes(" ")) {
        alert("이메일에 공백을 제거해주세요.");
        return false;
      }
      
   //비밀번호에 공백이 포함된 경우 false반환
   if (inputPw.value.includes(" ")) {
    alert("비밀번호에 공백을 제거해주세요.");
    return false;
  }

    // 비밀번호를 입력하지 않은 경우 false 반환
    if(inputPw.value.trim() == ""){
        alert("비밀번호를 입력해주세요.");
        inputPw.value = "";
        inputPw.focus();
        return false;
    }


    return true; // form 태그 기본 이벤트 정상 수행
}



// 아이디 저장 체크박스가 체크 되었을 때 이벤트 처리

// radio, checkbox 체크 시 change 이벤트 발생
document.getElementById("saveId").addEventListener("change", function(){

    // 체크 여부 확인
    console.log(this.checked)
    // this : change 이벤트가 발생한 요소(체크박스)
    // 체크박스요소.checked   :  체크 여부 반환(true/false)

    // 체크박스요소.checked = true;  : 체크박스 체크
    // 체크박스요소.checked = false; : 체크박스 체크 해제


    if( this.checked ){ // 체크박스가 체크된 경우

        const str = "개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요.";

        //confirm(str) // 확인(true) / 취소(false) 대화상자

        if( !confirm(str)  ){ // 취소를 눌렀을 때
            this.checked = false; // 체크 해제
        }
    }


});

//구글로그인
// google signin API
var googleUser = {};
function init() {
	 gapi.load('auth2', function() {
	  console.log("init()시작");
	  auth2 = gapi.auth2.init({
	        client_id: '',
	        cookiepolicy: 'single_host_origin'
	      });
	      attachSignin(document.getElementById('google_login'));
	 });
}

//google signin API2
function attachSignin(element) {
    auth2.attachClickHandler(element, {},
        function(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	var id_token = googleUser.getAuthResponse().id_token;
	  	  console.log('ID: ' + profile.getEmail()); // Do not send to your backend! Use an ID token instead.
	  	  console.log('ID토큰: ' + id_token);
	  	  console.log('Name: ' + profile.getName());
	  
			$(function() {
				$.ajax({
				    url: '/member/loginGoogle',
				    type: 'post',
				    data: {
						"memberEmail" : profile.getEmail(),
						"memberPw" : "",
				        "username": profile.getName()
						
					    },
				    success: function (data) {
				            alert("구글아이디로 로그인 되었습니다");
				            location.href="/member/login";
				        }
				});
			})
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
    console.log("구글API 끝");
  }