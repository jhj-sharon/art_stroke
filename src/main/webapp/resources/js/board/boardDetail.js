console.log(contextPath);
console.log(boardCode);
console.log(boardId);
const boardGoodBtn = document.getElementById("boardGood");

boardGoodBtn.addEventListener("click",function(){
    
    if(isMemberLiked=="false"){
        ddobongDochi();
    }else{
        deleteGood();
    }
});


function ddobongDochi(){
    const listSize = document.getElementById("listSize");
    console.log(isMemberLiked);
    $.ajax({
        url: contextPath + "/board/ddobongDochi",
        data : {"boardCode":boardCode,
                "boardId":boardId,
                "memberId":loginMemberId},
        type:"post",
        success: function(result){
            
                //따봉도치~
                heart.classList.add("fa-solid");
                heart.classList.remove("fa-regular");
                isMemberLiked = "true";
                listSize.innerHTML=result;
                console.log(result);
            
        }

    });
}

function deleteGood(){
    $.ajax({
        url: contextPath + "/board/deleteGood",
        data : {"boardCode":boardCode,
                "boardId":boardId,
                "memberId":loginMemberId},
        type:"post",
        success: function(result){
            
                //주겄도치~
                heart.classList.add("fa-regular");
                heart.classList.remove("fa-solid");
                isMemberLiked = "false";
                listSize.innerHTML=result;
                console.log(result+"이다");
        }

    });
}