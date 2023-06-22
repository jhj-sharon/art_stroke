var clickElement = null;
function openPopup(qnaId) {
    var popup = document.getElementById("popup");
    var qnaPwForm = document.getElementById("qnaPwForm");
    clickElement = this;
    qnaPwForm.action = contextPath +"/product/productDetailQnA/confirmPw?qnaId=" + qnaId;
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closePopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function confirmPw(){
    const qnaPw_input =document.getElementById("qnaPw_input");
    var qnaPwForm = document.getElementById("qnaPwForm");
    console.log(qnaPwForm.action);
    console.log(qnaPw_input.value);
    
    $.ajax({
        url: qnaPwForm.action,
        data:{"qnaPw" :  qnaPw_input.value},
        method:"post",
        success:function(result){
            if(result!=null){
                closePopup();
                let map = JSON.parse(result);
            }else{
                console.log("실패");
            }
        },
        error:function(result){
            console.log("안돼");
        }
    });

}