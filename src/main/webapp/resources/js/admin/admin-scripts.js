/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

/* 
function adminProductAll(){ // 회원 전체 조회 함수
    // ajax코드
    $.ajax({
        url : "adminProductAll",
        dataType : "json",    //  응답 데이터의 형식을 "json"으로 지정
                              // -> 자동으로 JS 객체로 변환됨
        success : function( list ){
            //console.log(list);
            // list == JS 객체 배열 

            // 1) #memberList 내용 삭제
            const productList = document.getElementById("productList");

            productList.innerHTML = "";

            // 2) list를 for문을 이용해서 반복 접근
            for(let item of list){
                // item == 회원 1명의 정보가 담긴 JS 객체

                // 3) tr 요소 생성
                const tr = document.createElement("tr");

                // 4) td 요소 생성 + 내용 추가 * 3
                const td1 = document.createElement("td");
                td1.innerText = item.productId; // 회원 번호 
                td1.style.textAlign = "center"; // 텍스트 가운데 정렬
                td1.style.padding = "8px"; // 패딩 8px

                const td2 = document.createElement("td");
                td2.innerText = item.productImage; // 회원 이메일

                const td3 = document.createElement("td");
                td3.innerText = item.productType; // 회원 이메일

                const td4 = document.createElement("td");
                td4.innerText = item.productName; // 회원 닉네임

                const td5 = document.createElement("td");
                td5.innerText = item.productArtist; // 회원 닉네임
                
                const td6 = document.createElement("td");
                td6.innerText = item.productPrice; // 회원 닉네임
                
                const td7 = document.createElement("td");
                td7.innerText = item.productRDate; // 회원 닉네임
                
                // 5) tr요소에 td요소 3개 추가
                tr.append(td1,   td3, td4, td5, td6, td7);

                // 6) memberList에 tr 추가
                productList.append(tr);
            }


        },
        error : function(){
            console.log("에러 발생");
        }
    });

}


// 즉시 실행 함수(속도 빠름, 변수명 중복 문제 해결)
(function(){
    adminProductAll(); // 함수 호출 -> 회원 목록을 먼저 조회

    //window.setInterval(함수, 딜레이(ms))
    window.setInterval(adminProductAll, 10000); // 10초
    // 함수 이름만 작성 -> 함수 코드가 대입
    // -> 10초마다 selectAll 함수 수행

    // setInterval()은 지연 -> 수행 -> 지연 -> 수행 ... 반복
    // --> 처음에 함수가 수행되지 않아서 공백인 상태가 있음

})();


 
 
function adminMemberAll(){ // 회원 전체 조회 함수
    // ajax코드
    $.ajax({
        url : "adminMemberAll",
        dataType : "json",    //  응답 데이터의 형식을 "json"으로 지정
                              // -> 자동으로 JS 객체로 변환됨
        success : function( list ){
            //console.log(list);
            // list == JS 객체 배열 

            // 1) #memberList 내용 삭제
            const memberList = document.getElementById("memberList");

            memberList.innerHTML = "";

            // 2) list를 for문을 이용해서 반복 접근
            for(let item of list){
                // item == 회원 1명의 정보가 담긴 JS 객체

                // 3) tr 요소 생성
                const tr = document.createElement("tr");

                // 4) td 요소 생성 + 내용 추가 * 3
                const td1 = document.createElement("td");
                td1.innerText = item.memberId; // 회원 번호 
                td1.style.textAlign = "center"; // 텍스트 가운데 정렬
                td1.style.padding = "8px"; // 패딩 8px

                const td2 = document.createElement("td");
                td2.innerText = item.memberEmail; // 회원 이메일

                const td3 = document.createElement("td");
                td3.innerText = item.memberNick; // 회원 이메일

                const td4 = document.createElement("td");
                td4.innerText = item.memberTel; // 회원 닉네임

                const td5 = document.createElement("td");
                td5.innerText = item.memberAddr; // 회원 닉네임
                
                const td6 = document.createElement("td");
                td6.innerText = item.memberEd; // 회원 닉네임
                
                const td7 = document.createElement("td");
                td7.innerText = item.auth; // 회원 닉네임
                
                const td8 = document.createElement("td");
                td7.innerText = item.secessionFl; // 회원 닉네임
                
                
                

                // 5) tr요소에 td요소 3개 추가
                tr.append(td1, td2,  td3, td4, td5, td6, td7, td8);

                // 6) memberList에 tr 추가
                memberList.append(tr);
            }


        },
        error : function(){
            console.log("에러 발생");
        }
    });

}


// 즉시 실행 함수(속도 빠름, 변수명 중복 문제 해결)
(function(){
    adminMemberAll(); // 함수 호출 -> 회원 목록을 먼저 조회

    //window.setInterval(함수, 딜레이(ms))
    window.setInterval(adminMemberAll, 10000); // 10초
    // 함수 이름만 작성 -> 함수 코드가 대입
    // -> 10초마다 selectAll 함수 수행

    // setInterval()은 지연 -> 수행 -> 지연 -> 수행 ... 반복
    // --> 처음에 함수가 수행되지 않아서 공백인 상태가 있음

})();
 */
  
  
     