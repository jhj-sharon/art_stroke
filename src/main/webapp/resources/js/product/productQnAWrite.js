
// 게시글 작성 유효성 검사
function writeValidate(){
    const qnaTitle = document.getElementsByName("qnaTitle")[0];
    const qnaContent = document.getElementsByName("qnaContent");

    if(qnaTitle.value.trim().length == 0){
        alert("제목을 입력해주세요.");
        qnaTitle.value = "";
        qnaTitle.focus();
        return false;
    }

    if(qnaContent.value.trim().length == 0){
        alert("내용을 입력해주세요.");
        qnaContent.value = "";
        qnaContent.focus();
        return false;
    }


    return true;
}
