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