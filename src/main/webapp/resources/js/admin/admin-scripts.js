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
  
  
  
  
  // 상세조회, 게시글 작성 - 목록으로 버튼

// 즉시 실행 함수
(function(){
    const goToListBtn = document.getElementById("goToListBtn");

    if(goToListBtn != null){ // 목록으로 버튼이 화면에 있을 때만 이벤트 추가

        goToListBtn.addEventListener("click", function(){

            // location 객체(BOM)

            const pathname = location.pathname; // 주소상에서 요청 경로 반환
            //  /community/board/detail

            // 문자열.substring(시작,끝) : 시작 이상 끝 미만 인덱스 까지 문자열 자르기

            // 문자열.indexOf("검색 문자열", 시작 인덱스)
            // : 문자열에서 "검색 문자열"의 위치(인덱스)를 찾아서 반환
            //  단, 시작 인덱스 이후 부터 검색

            // 이동할 주소 저장
            let url = pathname.substring(0,  pathname.indexOf("/", 1)); // 
            //   /community

            url += "/board/list/"+boardCode+"?";  //   /board/list/1?cp=1


            // URL 내장 객체 : 주소 관련 정보를 나타내는 객체
            // location.href : 현재 페이지 주소 + 쿼리스트링
            // URL.searchParams : 쿼리 스트링만 별도 객체로 반환

            // http://localhost:8080/community/board/detail?no=249&cp=6&type=1&key=c&query=9
            const params = new URL(location.href).searchParams;

            let cp;

            if(params.get("cp") != null){ // 쿼리스트링에 cp가 있을 경우
                cp =  "cp=" + params.get("cp");     
            }else{
                cp = "cp=1";
            }

            // 조립
            //   /commy/board/list/1?cp=1
            url += cp;


            // 검색 key, query가 존재하는 경우 url에 추가
            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query; // url 뒤에 붙이기
            }


            // location.href = "주소";  -> 해당 주소로 이동
            location.href = url; 

        });
    }

})();



// 즉시 실행 함수
(function(){
    const thumbnail = document.getElementsByClassName("list-thumbnail");

    if(thumbnail.length > 0){ // 목록에 썸네일 이미지가 있을 경우에만 이벤트 추가
      
        const modal = document.querySelector('.modal');
        const modalImage = document.getElementById("modal-image");
        const modalClose = document.getElementById("modal-close");

        for(let th of thumbnail){
            th.addEventListener("click", function(){
                modalImage.setAttribute("src", th.getAttribute("src") );
               
                /* on/off 스위치 */
                // classList.toggle("클래스명") : 클래스가 없으면 추가(add) 
                //                                클래스가 있으면 제거(remove)
                
                modal.classList.toggle('show'); // add
            });
        }

        // X버튼
        modalClose.addEventListener("click", function(){
            
            modal.classList.toggle('hide'); // hide 클래스 추가

            setTimeout(function(){ // 0.45초 후 동작
                modal.classList.toggle('hide'); // hide 클래스 제거

                modal.classList.toggle('show'); // remove
            }, 450);
        });


    }

})();


// 즉시 실행 함수 : 성능up, 변수명 중복 X
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); // 존재하지 않으면 null

    if(deleteBtn != null){ // 버튼이 화면에 존재할 때
        deleteBtn.addEventListener("click", function(){
            // 현재 : /board/detail/{boardCode}/{boardNo}
            // 목표 : /board/delete/{boardCode}/{boardNo}
      
            let url = contextPath + "/board/delete/" + boardCode + "/" +  boardNo;
            // 삭제 성공 -> 해당 게시판 목록 조회 1페이지로 리다이렉트
            // 삭제 실패 -> 요청 이전 페이지(referer)로 리다이렉트

            // UPDATE BOARD SET
            // BOARD_ST = 'Y'
            // WHERE BOARD_NO = #{boardNo}


            if( confirm("정말로 삭제 하시겠습니까?") ){
                location.href = url; // get방식으로 url에 요청
            }

        });
    }

})();


// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");

    // const option = select.children;
    const option = document.querySelectorAll("#search-key > option");

    const input = document.getElementById("search-query");

    if(select != null){ // 검색창이 화면이 존재할 때에만 코드 적용

        // 현재 주소에서 쿼리스트링(파라미터) 얻어오기
        const params = new URL(location.href).searchParams;

        // 얻어온 파라미터 중 key, query만 변수에 저장
        const key = params.get("key");
        const query = params.get("query");

        // input에 query 값 대입 
        input.value = query;

        // option을 반복 접근해서 value와 key가 같으면 selected 속성 추가
        for(let op of option){
            if(op.value == key ){
                op.selected = true;
            }
        }
    }

})();



// 검색 유효성 검사(검색어를 입력 했는지 확인)
function searchValidate(){

    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){ // 미작성
        query.value = ""; // 빈칸
        query.focus();

        return false;
    }

    return true;
}

window.addEventListener('DOMContentLoaded', function() {
  var formattedPrices = document.getElementsByClassName('formatted-price');
  
  for (var i = 0; i < formattedPrices.length; i++) {
    var price = formattedPrices[i].textContent;
    formattedPrices[i].textContent = formatPrice(price);
  }
});

function formatPrice(price) {
  return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


// 미리보기 관련 요소 모두 얻어오기
// -> 동일한 개수의 요소가 존재함 == 인덱스가 일치함
const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");

// 게시글 수정 시 삭제된 이미지의 레벨(위치)를 저장할 input 요소
const deleteList = document.getElementById("deleteList");

// 게시글 수정 시 삭제된 이미지의 레벨(위치)를 기록해둘 Set 생성
const deleteSet = new Set(); // 순서 X, 중복 허용 X (여러번 클릭 시 중복 값 저장 방지)



for(let i=0 ; i<inputImage.length ; i++){

    // 파일이 선택 되었을 때의 동작
    inputImage[i].addEventListener("change", function(){

        if(this.files[0] != undefined){ // 파일이 선택 되었을 때
            const reader = new FileReader(); // 선택된 파일을 읽을 객체 생성
            reader.readAsDataURL(this.files[0]);
            // 지정된 파일을 읽음 -> result에 저장(URL 포함) -> URL을 이용해서 이미지 볼 수 있음

            reader.onload = function(e){ // reader가 파일을 다 읽어온 경우
                // e.tartget == redaer
                // e.target.result == 읽어들인 이미지의 URL
                // preview[i] == 파일이 선택된 input태그와 인접한 preview 이미지 태그
                preview[i].setAttribute("src", e.target.result);

                // 이미지가 성공적으로 불러와졌을 때
                // deleteSet에서 해당 레벨을 제거(삭제 목록에서 제외)
                deleteSet.delete(i);
            }
      
        } else { // 파일이 선택되지 않았을 때 (취소)
            preview[i].removeAttribute("src"); // src 속성 제거
        }
    });



    // 미리보기 삭제 버튼(x)이 클릭 되었을 때의 동작
    deleteImage[i].addEventListener("click", function(){

        // 미리보기가 존재하는 경우에만 (이전에 추가된 이미지가 있을 때만 X버튼 동작)
        if( preview[i].getAttribute("src")  !=  "" ){

            // 미리보기 삭제
            preview[i].removeAttribute("src");

            // input의 값을 "" 만들기
            inputImage[i].value = "";

            // deleteSet에 삭제된 이미지 레벨(i) 추가
            deleteSet.add(i);
        }

    });

}

  