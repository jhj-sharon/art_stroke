
$("#boardBtn").click(function() {
    var boardChk = [];

    $("input[name='boardChk']:checked").each(function() {
        boardChk.push($(this).val());
        console.log("체크된 값 boardChk : " + boardChk);
    });
    console.log(boardChk);

    $.ajax({
        url: "deleteAdminBoard",
        type: "post",
        traditional: true,
        data: { boardChk: boardChk },
        
        success: function(result) {
            if (result > 0) {
                alert("게시판 삭제 성공!");
                location.reload();
                console.log("성공!");
                 
            } else {
                alert("처리 결과가 없습니다.");
             
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
          
        }
    });

});
 
  



$("#adminBtn123").click(function() {
    var selectedIds = [];

    $("input[name='selectedIds']:checked").each(function() {
        selectedIds.push($(this).val());
        console.log("체크된 값 selectedIds : " + selectedIds);
    });
    console.log(selectedIds);
    modifyList(selectedIds);

});
 
   
function modifyList(selectedIds) {
    // AJAX를 사용하여 selectedIds를 컨트롤러에 전송
    $.ajax({
        url: "modifyData",
        type: "post",
        traditional: true,
        data: { selectedIds: selectedIds },
        
        success: function(result) {
            if (result > 0) {
                alert("성공!");
               
                location.reload();
                console.log("성공!");
                 
            } else {
                alert("처리 결과가 없습니다.");
             
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
          
        }
    });
}

 

$("#reportBtn").click(function() {
    var reportChk = [];

    $("input[name='reportChk']:checked").each(function() {
        reportChk.push($(this).val());
        console.log("체크된 값 reportChk : " + reportChk);
    });
    console.log(reportChk);
    reportDeleteData(reportChk);

});
 
   
function reportDeleteData(reportChk) {
    // AJAX를 사용하여 selectedIds를 컨트롤러에 전송
    $.ajax({
        url: "report/reportDeleteData",
        type: "post",
        traditional: true,
        data: { reportChk: reportChk },
        
        success: function(result) {
            if (result > 0) {
                alert("성공!");
                location.reload();
                console.log("성공!");
                 
            } else {
                alert("처리 결과가 없습니다.");
             
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
          
        }
    });
}

 

$("#updateAuthBtn").click(function() {
    var authChk = [];

    $("input[name='authChk']:checked").each(function() {
        authChk.push($(this).val());
        console.log("체크된 값 authChk : " + authChk);
    });
    console.log(authChk);

    $.ajax({
        url: "updateAdminAuth",
        type: "post",
        traditional: true,
        data: { authChk: authChk },
        
        success: function(result) {
            if (result > 0) {
                alert("작가 업데이트 성공!");
                location.reload();
                console.log("성공!");
                 
            } else {
                alert("처리 결과가 없습니다.");
             
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
          
        }
    });

});
 
  

 
function reportApply() {
    
    var normalButton = document.getElementById("normalButton5");
    var withdrawnButton = document.getElementById("withdrawnButton5");
    var memberTable = document.getElementById("reportTable");
    var memberRows = memberTable.getElementsByTagName("tr");

    for (var i = 1; i < memberRows.length; i++) {
        var authCell = memberRows[i].cells[6]; 
        var displayOption = "";

        if (normalButton.checked) {
            if (authCell.innerText === "N"   || authCell.innerText === "") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (withdrawnButton.checked) {
            if (authCell.innerText === "Y") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        }

        memberRows[i].style.display = displayOption;
    }
}




function memberApply() {
    var allButton = document.getElementById("allButton");
    var normalButton = document.getElementById("normalButton");
    var artistButton = document.getElementById("artistButton");
    var withdrawnButton = document.getElementById("withdrawnButton");
    var memberTable = document.getElementById("memberTable");
    var memberRows = memberTable.getElementsByTagName("tr");

    for (var i = 1; i < memberRows.length; i++) {
        var authCell = memberRows[i].cells[7];
        var secessionFlCell = memberRows[i].cells[8];
        var displayOption = "";

        if (normalButton.checked) {
            if (authCell.innerText == "0") {
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
        } else if (artistButton.checked) {
            if (authCell.innerText === "1") {
                displayOption = "";
            } else {
                displayOption = "none";
            }

        
    }
    memberRows[i].style.display = displayOption;
}
}


function askApply() {
    var normalButton = document.getElementById("normalButton4");
    var withdrawnButton = document.getElementById("withdrawnButton4");
    var memberTable = document.getElementById("memberQnATable");
    var memberRows = memberTable.getElementsByTagName("tr");

    for (var i = 1; i < memberRows.length; i++) {
        var authCell = memberRows[i].cells[4];
        var displayOption4 = "";

        if (normalButton.checked) {
            if (authCell.textContent.trim() == "0") {
                displayOption4 = "";
            } else {
                displayOption4 = "none";
            }
        } else if (withdrawnButton.checked) {
            if (authCell.textContent.trim() == "1") {
                displayOption4 = "";
            } else {
                displayOption4 = "none";
            }
        } else {
                displayOption4 = "";
        }
        memberRows[i].style.display = displayOption4;
    }
}


function orderApply() {
    var radio10 = document.getElementById("radio10");
    var radio11 = document.getElementById("radio11");
    var radio12 = document.getElementById("radio12");
    var radio13 = document.getElementById("radio13");
    var orderTable = document.getElementById("orderTable");
    var orderRows = orderTable.getElementsByTagName("tr");

    for (var i = 1; i < orderRows.length; i++) {
        var paymentCell = orderRows[i].cells[8];
        var displayOption = "";

        if (radio10.checked) {
            if (paymentCell.textContent.trim() === "카드") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (radio11.checked) {
            if (paymentCell.textContent.trim() === "무통장입금") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        
        } else if (radio13.checked) {
            if (paymentCell.textContent.trim() === "kakaopay") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else {
            displayOption = "";
        }

        orderRows[i].style.display = displayOption;
    }
}



function boardApply() {
   
    var normalButton = document.getElementById("normalButton3");
    var withdrawnButton = document.getElementById("withdrawnButton3");
    var memberTable = document.getElementById("boardListTable");
    var memberRows = memberTable.getElementsByTagName("tr");

    for (var i = 1; i < memberRows.length; i++) {
        var authCell = memberRows[i].cells[2]; 
        var displayOption = "";

        if (normalButton.checked) {
            if (authCell.innerText === "N") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (withdrawnButton.checked) {
            if (authCell.innerText === "Y") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else {
            displayOption = "";
        }
 
        memberRows[i].style.display = displayOption;
    }
}


function stockApply() {
   
    var posterButton = document.getElementById("posterButton");
    var homeButton = document.getElementById("homeButton");
    var phoneButton = document.getElementById("phoneButton");
    
    var stockTable = document.getElementById("stockTable");
    var memberRows = stockTable.getElementsByTagName("tr");

    for (var i = 1; i < memberRows.length; i++) {
        var authCell = memberRows[i].cells[2]; 
        var displayOption = "";

        if (posterButton.checked) {
            if (authCell.innerText === "포스터") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (homeButton.checked) {
            if (authCell.innerText === "홈패브릭") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (phoneButton.checked) {
                if (authCell.innerText === "스마트폰 케이스") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
        } else {
            displayOption = "";
        }
 
        memberRows[i].style.display = displayOption;
    }

}


// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");
    const option = document.querySelectorAll("#search-key > option");
    const input = document.getElementById("search-query");

    if (select != null) {
        const params = new URL(location.href).searchParams;
        const key = params.get("key");
        const query = params.get("query");

        input.value = query;

        for (let op of option) {
            if (op.value == key) {
                op.selected = true;
            }
        }
    }
})();

// 검색 유효성 검사(검색어를 입력 했는지 확인)
function searchValidate() {
    const query = document.getElementById("search-query");

    if (query.value.trim().length == 0) {
        query.value = "";
        query.focus();
        return false;
    }

    return true;
}
 




var startDateElement = document.getElementById("startDateHidden");
var endDateElement = document.getElementById("endDateHidden");

 

function setDateRange(days) {
  var endDate = new Date();
  var startDate = new Date();

  startDate.setDate(startDate.getDate() - days);

  var formattedStartDate = formatDate(startDate);
  var formattedEndDate = formatDate(endDate);

  startDateElement.value = formattedStartDate;
  endDateElement.value = formattedEndDate;

  selectAdminDateList();
}

function formatDate(date) {
  var year = date.getFullYear();
  var month = ("0" + (date.getMonth() + 1)).slice(-2);
  var day = ("0" + date.getDate()).slice(-2);

  return year + "-" + month + "-" + day;
}

function selectAdminDateList() {
  var startDate = startDateElement.value;
  var endDate = endDateElement.value;



 $.ajax({
  url: 'selectAdminDateList',
  type: 'POST',
  data: {
    startDate: startDate,
    endDate: endDate
  },
  success: function(list) {
    if (list != null) {
      console.log("날짜 LIST 성공");
        
      var orderTable = document.getElementById("orderTable");
      var tbody = orderTable.querySelector("tbody");

      // 이전에 생성된 주문 목록 제거
      tbody.innerHTML = "";
      console.log("tbody: " + tbody);
      console.log("list " + list);
      
      // 주문 목록을 순회하며 테이블에 동적으로 추가
      for (var i = 0; i < list.length; i++) {
        var order = list[i];
        var row = document.createElement("tr");

        var orderIdCell = document.createElement("td");
        orderIdCell.textContent = order.orderId;
        row.appendChild(orderIdCell);

        var orderDateCell = document.createElement("td");
        orderDateCell.textContent = order.orderDate;
        row.appendChild(orderDateCell);

        var orderMemberIdCell = document.createElement("td");
        orderMemberIdCell.textContent = order.memberId;
        row.appendChild(orderMemberIdCell);

        var orderQuantityCell = document.createElement("td");
        orderQuantityCell.textContent = order.quantity;
        row.appendChild(orderQuantityCell);

        var orderTotalPriceCell = document.createElement("td");
        var totalPriceSpan = document.createElement("span");
        totalPriceSpan.className = "formatted-price";
        if (order.totalPrice !== undefined) {
          totalPriceSpan.textContent = order.totalPrice.toLocaleString() + "원";
        } else {
          totalPriceSpan.textContent = "N/A";
        }
        orderTotalPriceCell.appendChild(totalPriceSpan);
        row.appendChild(orderTotalPriceCell);

        var orderAddrIdCell = document.createElement("td");
        orderAddrIdCell.textContent = order.addrId;
        row.appendChild(orderAddrIdCell);

        var orderPaymethodCell = document.createElement("td");
        orderPaymethodCell.textContent = order.paymethod;
        row.appendChild(orderPaymethodCell);

        // 나머지 필드도 동일한 방식으로 추가

        tbody.appendChild(row);
      }
    } else {
      console.log("날짜 List 실패");
      // 실패 처리 로직 작성
    }
  },
  error: function() {
    console.log('날짜에러에러 ajax 오류');
    // 에러 처리 로직 작성
  }
});
 

}

      
  function showSelectedOption() {
    var selectElement = document.getElementById("search-key");
    var selectedOption = selectElement.options[selectElement.selectedIndex].text;
    var selectedOptionDiv = document.getElementById("selected-option");
    selectedOptionDiv.textContent = "선택된 옵션: " + selectedOption;
}
