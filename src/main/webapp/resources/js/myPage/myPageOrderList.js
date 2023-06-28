function openPopup(productName, orderDetailId) {
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";

    document.getElementById("productName").innerText = productName;
    document.getElementById("orderDetailId").value = orderDetailId;
}
  
function closePopup() {
        var popup = document.getElementById("popup");
        popup.style.visibility = "hidden";
        popup.style.opacity = "0";
}

function openPopup2(orderId) {
    var popup = document.getElementById("popup2");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
    document.getElementById('hiddenOrderId').value = orderId;
    document.getElementById('orderId').innerText = orderId;
}
  
function closePopup2() {
        var popup = document.getElementById("popup2");
        popup.style.visibility = "hidden";
        popup.style.opacity = "0";
}
  
function reviewStatus(){
    alert("리뷰 작성이 불가 합니다.");
}
function cancelStatus(){
    alert("취소 신청이 불가 합니다.");
}