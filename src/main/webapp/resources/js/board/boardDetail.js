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


followBtn.addEventListener("click",function(){
    if(isMemberfollowed=="false"){
        insertFollow(writerId,writerNick,loginMemberId,loginMemberNick);
    }else{
        deleteFollow(writerId,writerNick,loginMemberId,loginMemberNick);
    }
});


function insertFollow(writerId,writerNick,loginMemberId,loginMemberNick){
    $.ajax({
        url: contextPath + "/member/insertFollow",
        data : {"writerId" : writerId,
                "followerId":loginMemberId,
                "writerNick":writerNick,
                "followerNick":loginMemberNick},
        type:"post",
        success: function(result){
            
            if(result > 0){
                followBtn.classList.add("board_member_follow");
                followBtn.classList.remove("board_member_unfollow");
                isMemberfollowed = "true";
                followBtn.innerText = "팔로잉";
            }
        }

    });
}
function deleteFollow(writerId,writerNick,loginMemberId,loginMemberNick){
    $.ajax({
        url: contextPath + "/member/deleteFollow",
        data : {"writerId" : writerId,
                "followerId":loginMemberId,
                "writerNick":writerNick,
                "followerNick":loginMemberNick},
        type:"post",
        success: function(result){
            if(result>0){
                //주겄도치~
                followBtn.classList.add("board_member_unfollow");
                followBtn.classList.remove("board_member_follow");
                isMemberfollowed = "false";
                followBtn.innerText = "팔로우";
            }  
        }

    });
}


