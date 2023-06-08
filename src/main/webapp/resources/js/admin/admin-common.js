  function productApply() {
        var allButton = document.getElementById("allButton");
        var normalButton = document.getElementById("normalButton");
        var withdrawnButton = document.getElementById("withdrawnButton");
        var memberTable = document.getElementById("memberTable");
        var memberRows = memberTable.getElementsByTagName("tr");

        for (var i = 1; i < memberRows.length; i++) {
            var authCell = memberRows[i].cells[6];
            var secessionFlCell = memberRows[i].cells[7];
            var displayOption = "";

            if (normalButton.checked) {
                if (authCell.innerText === "1" && secessionFlCell.innerText === "N") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            } else if (withdrawnButton.checked) {
                if (secessionFlCell.innerText === "Y") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            }

            memberRows[i].style.display = displayOption;
        }
    }
    
    


    function orderApply() {
        var radio1 = document.getElementById("radio1");
        var radio2 = document.getElementById("radio2");
        var radio3 = document.getElementById("radio3");
        var radio4 = document.getElementById("radio4");
        var radio5 = document.getElementById("radio5");
        var radio6 = document.getElementById("radio6");
        var radio7 = document.getElementById("radio7");
        var radio8 = document.getElementById("radio8");
        var radio9 = document.getElementById("radio9");
        var radio10 = document.getElementById("radio10");
        var radio11 = document.getElementById("radio11");
        var radio12 = document.getElementById("radio12");
        var radio13 = document.getElementById("radio13"); 


        var orderTable = document.getElementById("orderTableBody");
        var orderRows = orderTable.getElementsByTagName("tr");

        for (var i = 1; i < orderRows.length; i++) {
            var paymentCell = orderRows[i].cells[8]; // 9번째 셀로 수정
            var displayOption = "";
        
            if (radio10.checked) {
                if (paymentCell.innerText === "카드") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            } else if (radio11.checked) {
                if (paymentCell.innerText === "무통장입금") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            } else {
                displayOption = ""; // 추가된 부분
            }
            
        
            orderRows[i].style.display = displayOption;
        }
    
    }

    






















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