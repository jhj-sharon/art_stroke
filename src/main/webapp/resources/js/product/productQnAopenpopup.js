var clickElement = null;
var clickElementNum = null;
function openPopup(element,qnaId, count) {
    var popup = document.getElementById("popup");
    var qnaPwForm = document.getElementById("qnaPwForm");
    clickElement = element;
    clickElementNum = count;
    console.log(isFunctionExecutedArray[clickElementNum]);
    if(isFunctionExecutedArray[clickElementNum] == false){
    qnaPwForm.action = contextPath +"/product/productDetailQnA/confirmPw?qnaId=" + qnaId;
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
    var qnaPwForm = document.getElementById("qnaPwForm");
    
    $.ajax({
        url: qnaPwForm.action,
        data:{"qnaPw" :  qnaPw_input.value},
        method:"post",
        success:function(result){
            if(result!=null){
                closePopup();
                const addTr = document.createElement("tr");
                const addTdHello = document.createElement("td");
                const addTdContent = document.createElement("td");
                addTdHello.innerText = "답변 내용";
                addTdContent.setAttribute("colspan",3);

                addTdContent.innerHTML = result.qna.qnaContent;
                addTdContent.style.wordBreak = "break-all";
                addTdContent.style.padding = "10px";
                addTdContent.style.height = "100px";
                addTdHello.style.borderRight = "1px solid lightgray";
                addTr.append(addTdHello);
                addTr.append(addTdContent);
                addTr.style.padding = "10px";
                clickElement.insertAdjacentElement("afterend", addTr);
                isFunctionExecutedArray[clickElementNum] = true;
            }else{
                console.log("실패");
            }
        },
        error:function(result){
            console.log("안돼");
        }
        
    });
    
}