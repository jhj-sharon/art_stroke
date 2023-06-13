
const reportBtn = document.getElementById("reportBtn");

reportBtn.addEventListener("click",function(){
    const options = "width=596, height=598, top=50, left=400";
    window.open(contextPath+"/board/report/" + boardCode+ "?no="+boardId,"popupWindow",options);
})
