console.log(contextPath);
console.log(boardCode);
console.log(boardId);
const boardGoodBtn = document.getElementById("boardGood");

boardGoodBtn.addEventListener("click",function(){
    ddobongDochi();
});


function ddobongDochi(){
    $.ajax({
        url: contextPath + "/board/ddobongDochi",
        data : {"boardCode":boardCode,
                "boardId":boardId,
                "memberId":loginMemberId},
        type:"post",
        success: function(result){
            if(result>0){
                //따봉도치~
            }else{
                alert("이미 좋아요를 하셨는데..이건 deleteAjax로 변경");
            }
        }

    });
}