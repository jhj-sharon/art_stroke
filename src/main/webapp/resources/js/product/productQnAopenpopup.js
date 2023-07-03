var clickElement = null;
var clickElementNum = null;
var connectAction = null;

function openPopup(element,qnaId, count) {
    var popup = document.getElementById("popup");
    // var qnaPwForm = document.getElementById("qnaPwForm");
    clickElement = element;
    clickElementNum = count;
    console.log(isFunctionExecutedArray[clickElementNum]);
    if(isFunctionExecutedArray[clickElementNum] == false){
        connectAction = contextPath +"/product/productDetailQnA/confirmPw?qnaId=" + qnaId;
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    }
}

function closePopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function confirmPw(){
    const qnaPw_input =document.getElementById("qnaPw_input");
    
    
    $.ajax({
        url: connectAction,
        data:{"qnaPw" :  qnaPw_input.value},
        method:"post",
        success:function(result){
            if(result.success>0){
                closePopup();
                const addTr = document.createElement("tr");
                const addTr2 = document.createElement("tr");
                const addTdHello2 = document.createElement("td");
                const addTdContent2 = document.createElement("td");
                const addTdHello1 = document.createElement("td");
                const addTdContent1 = document.createElement("td");
                addTdHello1.innerText = "문의 글";
                addTdContent1.setAttribute("colspan",3);

                addTdContent1.innerHTML = result.qna.qnaContent;
                addTdContent1.style.wordBreak = "break-all";
                addTdContent1.style.padding = "10px";
                addTdContent1.style.height = "100px";
                addTdHello1.style.borderRight = "1px solid lightgray";
                addTr.append(addTdHello1);
                addTr.append(addTdContent1);
                addTr.style.padding = "10px";
                
                if(result.replyList.length >0){
                
                addTdHello2.innerText = "답변 내용";
                addTdContent2.setAttribute("colspan",3);
                addTdContent2.innerHTML = result.qna.qnaAnswer;
                addTdContent2.innerHTML = result.replyList[0].replyContent;
                addTdContent2.style.wordBreak = "break-all";
                addTdContent2.style.padding = "10px";
                addTdContent2.style.height = "100px";
                addTdContent2.style.borderTop = "1px solid lightgray";
                addTdHello2.style.borderRight = "1px solid lightgray";
                addTr2.append(addTdHello2);
                addTr2.append(addTdContent2);
                addTr2.style.padding = "10px";
                }
                
                if(result.replyList != null){
                clickElement.insertAdjacentElement("afterend", addTr2);
                }
                clickElement.insertAdjacentElement("afterend", addTr);
                isFunctionExecutedArray[clickElementNum] = true;
            }else{
                alert("비밀번호를 다시 입력해주세요");
            }
        },
        error:function(result){
            console.log("안돼");
        }
        
    });
    
}

 