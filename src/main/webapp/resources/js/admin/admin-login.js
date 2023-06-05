// Chnage Label Position

// call when focus on input
function labelUp(input){
    input.parentElement.children[0].setAttribute("class", "change_label");    
}  

// call when focus out on input
function labelDown(input){
    if(input.value.length === 0){
        input.parentElement.children[0].classList.remove("change_label");
    }
        
} 

// show & hide password
var eye_icon_signup = document.getElementById('eye_icon_signup');
var eye_icon_login = document.getElementById('eye_icon_login');
var sign_up_password = document.getElementById("signup_password");
var login_password = document.getElementById("login_password");
eye_icon_signup.addEventListener('click', ()=>{
    hideAndShowPass(eye_icon_signup, signup_password); 
});
eye_icon_login.addEventListener('click', ()=>{
    hideAndShowPass(eye_icon_login, login_password);  
});

const hideAndShowPass = (eye_icon, password) => {
    if(eye_icon.classList.contains("fa-eye-slash")){
        eye_icon.classList.remove('fa-eye-slash');
        eye_icon.classList.add('fa-eye');
        password.setAttribute('type', 'text');
        
    }
    else{
        eye_icon.classList.remove('fa-eye');
        eye_icon.classList.add('fa-eye-slash');
        password.setAttribute('type', 'password');
    }
};


// Sign Up & Sign In Toggle
let to_signup = document.getElementById('to_signup');
let to_login = document.getElementById('to_login');
to_signup.addEventListener('click', function(){
   let_change();
});
to_login.addEventListener('click', function(){
    let_change();
 });
const let_change = () => {
    let login = document.getElementById('login');
    login.classList.toggle('login_form');
    let signup = document.getElementById('signup');
    signup.classList.toggle('signup_form');
}





console.log("main.js loaded.");

// 로그인 시 이메일(아이디)/비밀번호 입력 확인

// -> 미작성 시 alert() 이용해서 메세지를 출력하고
//    로그인 form 태그의 제출을 막는 기본 이벤트 제거 진행

function loginValidate(){ // 로그인 유효성 검사
    // validate : 유효하다    
    // invalidate : 무효하다    

    // 이메일 입력 input 요소 
    const inputEmail = document.getElementsByName("adminEmail")[0];

    // 비밀번호 입력 input 요소
    const inputPw = document.getElementsByName("adminPw")[0];


    // 이메일이 입력되지 않은 경우 false를 반환
    if( inputEmail.value.trim().length == 0 ){
        // 문자열.trim() : 문자열 양쪽 공백을 제거
        // 문자열.length : 문자열 길이(몇 글자?)

        alert("이메일을 입력해주세요.");
        inputEmail.value = ""; // 이메일 input에 입력된 내용을 모두 삭제
        inputEmail.focus(); // 이메일 input에 포커스를 맞춰줌
        return false; // 기본 이벤트 제거를 위해 false 반환
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
if(document.getElementById("saveId")  != null){ 
    document.getElementById("saveId").addEventListener("change", function(){
    
        // 체크 여부 확인
        console.log(this.checked)
        // this : change 이벤트가 발생한 요소(체크박스)
        // 체크박스요소.checked   :  체크 여부 반환(true/false)
    
        // 체크박스요소.checked = true;  : 체크박스 체크
        // 체크박스요소.checked = false; : 체크박스 체크 해제
    
    
        
    
            if( !confirm(str)  ){ // 취소를 눌렀을 때
                this.checked = false; // 체크 해제
            }
        
    
    
    });
}

    