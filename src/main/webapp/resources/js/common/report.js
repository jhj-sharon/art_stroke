
const reportBtn = document.getElementById("report-btn");

reportBtn.addEventListener("click",function(){
    const options = "width=600, height=600, top=50, left=400";
    window.open(contextPath+"/board/report/" + boardCode+ "?no="+boardId+"&type="+type,"popupWindow",options);
});


