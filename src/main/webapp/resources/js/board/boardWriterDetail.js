const followBtn =document.getElementById("follow-Btn");
if(followBtn!= null){
followBtn.addEventListener("click",function(){
    console.log("안뜸");
    if(isMemberfollowed=="false"){
        insertFollow(writerId,writerNick,loginMemberId,loginMemberNick);
    }else{
        deleteFollow(writerId,writerNick,loginMemberId,loginMemberNick);
    }
});
}

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
                followBtn.classList.add("following");
                followBtn.classList.remove("notFollow");
                followBtn.innerHTML = `<img src="${contextPath}/resources/img/board/following.png" alt="팔로잉 이미지" style="width:18px">`;
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
                followBtn.classList.add("notFollow");
                followBtn.classList.remove("following");
                followBtn.innerHTML = `<img src="${contextPath}/resources/img/board/follow.png" alt="팔로우 이미지" style="width:18px"></img>`;
            }  
        }

    });
}