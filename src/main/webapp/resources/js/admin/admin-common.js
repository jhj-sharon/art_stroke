 

function closePopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'none';
}

$("#memberDeleteBtn").click(function() {
    var authChk = [];

    $("input[name='authChk']:checked").each(function() {
        authChk.push($(this).val());
        console.log("체크된 값 authChk : " + authChk);
    });
    console.log(authChk);

    $.ajax({
        url: "adminDeleteMember",
        type: "post",
        traditional: true,
        data: { authChk: authChk },
        
        success: function(result) {
            if (result > 0) {
                alert("회원 탈퇴 완료!");
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


$("#reviewBtn").click(function() {
    var reviewChk = [];

    $("input[name='reviewChk']:checked").each(function() {
        reviewChk.push($(this).val());
        console.log("체크된 값 reviewChk : " + reviewChk);
    });
    console.log(reviewChk);

    $.ajax({
        url: "deleteReview",
        type: "post",
        traditional: true,
        data: { reviewChk: reviewChk },
        
        success: function(result) {
            if (result > 0) {
                alert("리뷰 삭제 완료!");
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

$("#cancelBtn").click(function() {
    var cancelChk = [];
    var orderIds = []; // orderId를 저장할 배열 추가

    $("input[name='cancelChk']:checked").each(function() {
        cancelChk.push($(this).val());
        // 해당 체크박스의 부모 행에서 orderId 값을 찾아 orderIds 배열에 추가
        orderIds.push($(this).closest("tr").find("td:eq(1)").text());
    });
    
    console.log("체크된 값 cancelChk: " + cancelChk);
    console.log("체크된 값 orderIds: " + orderIds);

    $.ajax({
        url: "approvalCancel",
        type: "post",
        traditional: true,
        data: {
            cancelChk: cancelChk,
            orderIds: orderIds // orderIds도 데이터로 전송
        },
        
        success: function(result) {
            if (result > 0) {
                alert("취소 승인!");
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

 
  

$("#cancelBBtn").click(function() {
    var cancelChk = [];
    var orderIds = []; 

    $("input[name='cancelChk']:checked").each(function() {
        cancelChk.push($(this).val());
        orderIds.push($(this).closest("tr").find("td:eq(1)").text());
        console.log("체크된 값 cancelChk : " + cancelChk);
        console.log("체크된 값 orderIds: " + orderIds);
    });
    console.log(cancelChk);

    $.ajax({
        url: "approvalNotCancel",
        type: "post",
        traditional: true,
        data: { cancelChk: cancelChk,
            orderIds: orderIds},
        
        success: function(result) {
            if (result > 0) {
                alert("취소 거절 완료!");
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
        var authCell = memberRows[i].cells[7]; 
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
        var authCell = memberRows[i].cells[5];
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
    var radio13 = document.getElementById("radio13");
    var orderTable = document.getElementById("orderTable");
    var orderRows = orderTable.getElementsByTagName("tr");

    for (var i = 1; i < orderRows.length; i++) {
        var paymentCell = orderRows[i].cells[5];
        var displayOption = "";

        if (radio10.checked) {
            if (paymentCell.textContent.trim() == "card") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        } else if (radio11.checked) {
            if (paymentCell.textContent.trim() == "phone") {
                displayOption = "";
            } else {
                displayOption = "none";
            }
        
        } else if (radio13.checked) {
            if (paymentCell.textContent.trim() === "kakopay") {
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
        var authCell = memberRows[i].cells[6]; 
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

function searchValidate() {
    const query = document.getElementById("search-query");
    const searchForm = document.forms["searchForm"];

    if (query.value.trim().length == 0 ) {
        query.value = "";
        query.focus();
        return false;
    }

    // 검색어가 유효하면 검색 폼 전송
    searchForm.submit();

    // 폼 제출 후 폼 초기화
    searchForm.reset();

    return false; // 폼의 제출을 막기 위해 false를 반환
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
  
    // endDate에 1일을 더해주어 endDate를 포함하도록 설정
    var nextDay = new Date(endDate);
    nextDay.setDate(nextDay.getDate() + 1);
    var formattedEndDate = formatDate(nextDay);
    console.log("endDay  : "  + formattedEndDate);


 $.ajax({
  url: 'selectAdminDateList',
  type: 'POST',
  traditional: true,
  data: {
    startDate: startDate,
    endDate: formattedEndDate
  },
  success: function(list) {
   
      console.log("날짜 LIST 성공");
   
      dateList(list);
      
    
  },
  error: function() {
    console.log('날짜에러에러 ajax 오류');
    // 에러 처리 로직 작성
  }
});
 

}
 
function dateList(list) {
    var orderTable = document.getElementById("orderTable");
    var tbody = orderTable.querySelector("tbody");
    var parsedList = JSON.parse(list);
    
    tbody.innerHTML = ""; // 이전에 생성된 주문 목록 제거
    
    console.log("list : ", parsedList);
    console.log("list.length : ", parsedList.length);

    for (var i = 0; i < parsedList.length; i++) {
        var orderList = parsedList[i];
        var row = document.createElement("tr");

        var orderId = document.createElement("td");
        orderId.textContent = orderList.orderId;
        row.appendChild(orderId);

        var orderDate = document.createElement("td");
        orderDate.textContent = orderList.orderDate;
        row.appendChild(orderDate);

        var memberNick = document.createElement("td");
        memberNick.textContent = orderList.memberNick;
        row.appendChild(memberNick);

        var quantity = document.createElement("td");
        quantity.textContent = orderList.quantity;
        row.appendChild(quantity);

        var totalPrice = document.createElement("td");
        totalPrice.textContent = orderList.totalPrice;
        row.appendChild(totalPrice);
 
    
	                                       
        var paymethod = document.createElement("td");
        paymethod.textContent = orderList.paymethod;
        row.appendChild(paymethod);

        // 나머지 필드도 동일한 방식으로 추가

        tbody.appendChild(row);
    }
}

  function showSelectedOption() {
    var selectElement = document.getElementById("search-key");
    var selectedOption = selectElement.options[selectElement.selectedIndex].text;
    var selectedOptionDiv = document.getElementById("selected-option");
    selectedOptionDiv.textContent = "선택된 옵션: " + selectedOption;
}
