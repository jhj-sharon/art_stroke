<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var = "product" value = "${product}"/>
<script>
    console.log("${product}")
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href ="${contextPath}/resources/css/product/productQnAWrite.css">
    <script src="https://kit.fontawesome.com/069a8eb008.js" crossorigin="anonymous"></script> 
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_in.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_items.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_out.css">
    <script type ="text/javascript" src="${contextPath}/resources/static/js/HuskyEZCreator.js" charset = "utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>
    <title>제품 문의하기</title>
</head>
<body>
    <header class="header-style">
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
    </header>

    <main class="main-style">


        <section class="contents-wrap">
            <div class ="qna-form-area">
                <div style = "padding-top:50px;">
                <div class="qna-header">
                    <h1>| Q&A</h1>
                    <p>제품에 관련한 질문에 답변드립니다.</p>
                </div>
                
                <div class="product-info-wrapper">
                    <div class="product-thumnail">
                        <img src="${contextPath}/${product.productImage}" style="width: 100px;" alt="">
                    </div>
                    <div class="product-info-detail">
                        <div class="product-name">
                            <span>${product.productName}</span><br>
                            <span><strong>${product.productPrice}</strong>원</span>
                            <div class="product-btn">
                                <a href="${contextPath}/product/productDetailQnA?product_id=${product.productId}">상품상세보기</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="qna-form">
                <form action ="${contextPath}/product/productDetailQnA/productQnAWrite?productId=${product.productId}" id = "qna-form"method ="post" class = "widthfull">
                    <div id = "qna-title">
                        <input type ="text" name="qnaTitle" id = "qnaTitle-input" placeholder="제목을 입력해주세요." class ="qnaInputTitle">
                    </div>
                    <div id ="smarteditor">
                        <textarea name="qnaContent" id = "editorTxt"
                                  row="20" cols="10"
                                  placeholder="내용을 입력해주세요"
                                  style="width: 500px"></textarea>
                    </div>
                    <!-- 버튼영역 -->
                    <div  class = "qnaPw-area">
                        문의글 비밀번호 : <input id = "qnaPw_area" type = "password" name = "qnaPw" required>
                    </div>
                    <div class ="qna-btn-area">
                        <button type="button" id="qna-btn">작성하기</button>
                        <button type="button" id="goToListBtn" style = "margin-left:10px;"onclick="location.href='${header.referer}'">목록으로</button>
                    <!-- insert 모드 -->
                    <!-- <c:if test="${param.mode == 'insert'}">-->
                        
                    <!-- </c:if> -->
                        
                    <!-- update 모드 -->
                    <!-- <c:if test="${param.mode == 'update'}">
                        <button type="button" onclick="location.href='${header.referer}'">이전으로</button>                           
                    </c:if> -->
                    </div>

                                <!-- 숨겨진 값(hidden) -->
                                <!-- 동작 구분 -->
                                <input type="hidden" name="mode" value="${param.mode}">

                                <!-- 게시글 번호 (커맨드객체 BoardDetail.boardNo 세팅)-->

                                <!-- <input type="hidden" name="qnaId" value="${empty param.no ? 0 : param.no}"> -->
                                
                                <!-- 현재 페이지 -->
                                <!-- <input type="hidden" name="cp" value="${param.cp}"> -->
            
                </form>
            </div>
            </div>
            </div>
        </section>


        <script>
            let oEditors = [];
            smartEditor = function() {
              nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "editorTxt",
                sSkinURI: "${contextPath}/resources/static/SmartEditor2Skin3.html",
                fCreator: "createSEditor2"
              });
            };

            // $(document).ready(function() {
            //   smartEditor();
            // });
            smartEditor();

            $(function() {
		    $("#qna-btn").click(function() {
			oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", []); 
			//textarea의 id를 적어줍니다.

			
			var title = $("#qnaTitle-input").val();
			var content = document.getElementById("editorTxt").value;
            var qnaPw_area = $("#qnaPw_area").val();
			
			if (title == null || title == "") {
				alert("제목을 입력해주세요.");
				$("#qnaTitle-input").focus();
				return;
			}
            if(title.length>50){
                alert("제목 최대 길이는 50자 입니다");
                return;
            }
			if(content == "" || content == null || content == '&nbsp;' || 
					content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){
				alert("본문을 작성해주세요.");
				oEditors.getById["editorTxt"].exec("FOCUS"); //포커싱
				return;
			} //이 부분은 스마트에디터 유효성 검사 부분이니 참고.
            if (qnaPw_area == null || qnaPw_area == "") {
				alert("비밀번호를 입력해주세요.");
				$("#qnaPw_area").focus();
				return;
			}
			console.log(content);
			var result = confirm("발행 하시겠습니까?");
			
			if(result){
				alert("발행 완료!");
				$("#qna-form").submit();
			}else{
				return;
			}
		});
	})
        </script>


    </main>

    <footer class="footer-style">
        
        <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    </footer>
    <script src="${contextPath}/resources/js/product/productQnAWrite.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>