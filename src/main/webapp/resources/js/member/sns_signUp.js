// JavaScript 코드
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