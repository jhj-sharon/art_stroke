
const reportBtn = document.getElementById("report-btn");

reportBtn.addEventListener("click",function(){
    const options = "width=611, height=650, top=50, left=400";
    window.open(contextPath+"/board/report/" + boardCode+ "?no="+boardId+"&type="+type,"popupWindow",options);
});


