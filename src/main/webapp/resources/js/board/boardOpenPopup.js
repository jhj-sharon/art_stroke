function openPopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "visible";
    popup.style.opacity = "1";
}

function closePopup() {
    var popup = document.getElementById("popup");
    popup.style.visibility = "hidden";
    popup.style.opacity = "0";
}

function openPopup2() {
    var introPopup = document.getElementById("introPopup");
    introPopup.style.visibility = "visible";
    introPopup.style.opacity = "1";
    introPopup.style.zIndex = "0";
    console.log("dsf");
}

function closePopup2() {
    var introPopup = document.getElementById("introPopup");
    introPopup.style.visibility = "hidden";
    introPopup.style.opacity = "0";
}

function letterValidate() {
    let valueList = {
        letterTitle: false,
        letterWriter: false,
        letterSender: false,
        letterText: false
    }
    const writerName = document.getElementsByName("writerName");
    const senderName = document.getElementsByName("sendName");
    const titleValue = document.getElementsByName("sendTitle");
    const sendText = document.getElementsByName("sendText");

    if (writerName[0].value.length != 0) {
        valueList.letterWriter = true;
    } else {
        alert("보낼 작가를 쓰시오");
        valueList.letterWriter = false;
    }
    
    if (senderName[0].value.length != 0) {
        valueList.letterSender = true;
    } else {
        alert("보내는 사람의 닉네임을 쓰시오");
        valueList.letterSender = false;
    }

    if (titleValue[0].value.length != 0) {
        valueList.letterTitle = true;
    } else {
        alert("제목을 입력하세요.");
        valueList.letterTitle = false;
    }

    if (sendText[0].value.length != 0) {
        valueList.letterText = true;
    } else {
        alert("내용을 입력하세요.");
        valueList.letterText = false;
    }

    if (Object.values(valueList).reduce((acc, val) => acc + val, 0) === 4) {
        return true;
    } else {
        return false;
    }
}