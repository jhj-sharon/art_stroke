 


  function writeValidate() {
    const productName = document.getElementsByName("productName")[0];
    const productContent = document.querySelector("[name='productContent']");

    if (productName.value.trim().length === 0) {
        alert("제목을 입력해주세요!!!");
        productName.value = "";
        productName.focus();
        return false;
    }

    if (productContent.value.trim().length === 0) {
        alert("내용을 입력해주세요!!!");
        productContent.value = "";
        productContent.focus();
        return false;
    }

    
    return true;
}


 