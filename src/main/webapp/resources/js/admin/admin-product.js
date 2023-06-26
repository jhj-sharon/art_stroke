 


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



$("#productDeleteBtn").click(function() {
    var productChk = [];

    $("input[name='productChk']:checked").each(function() {
        productChk.push($(this).val());
        console.log("체크된 값 productChk : " + productChk);
    });

    console.log(productChk);
    showConfirmationDialog(productChk);
});

function showConfirmationDialog(productChk) {
    if (confirm("정말 삭제하시겠습니까?")) {
        deleteAdminProduct(productChk);
    }
}

function deleteAdminProduct(productChk) {
    $.ajax({
        url: "deleteAdminProduct",
        type: "post",
        traditional: true,
        data: { productChk: productChk },
        success: function(result) {
            if (result > 0) {
                alert("삭제되었습니다.");
                location.reload();
                console.log("삭제성공!");
            } else {
                alert("상품을 체크해주세요.");
            }
        },
        error: function() {
            console.log("AJAX 요청이 실패하였습니다.");
        }
    });
}

 