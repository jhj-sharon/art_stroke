<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <title>제품 문의하기</title>
</head>
<body>
    <header class="header-style">
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
    </header>

    <main class="main-style">


        <section class="contents-wrap">
            <div class ="qna-form-area">
                <div class="qna-header"">
                    <h1>| Q&A</h1>
                    <p>제품에 관련한 질문에 답변드립니다.</p>
                </div>
                <div class="product-info-wrapper">
                    <div class="product-thumnail">
                        <img src="${contextPath}/resources/img/thumbnail/thumbnail_bigbaby.jpg" style="width: 100px;" alt="">
                    </div>
                    <div class="product-info-detail">
                        <div class="product-name">
                            <span>고요속의 타오름 케이스</span><br>
                            <span><strong>18,900</strong>원</span>
                            <div class="product-btn">
                                <a href="">상품상세보기</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="qna-form">
                <form action ="productQnAWrite" method ="post" class = "widthfull"  onsubmit="return writeValidate()">
                    <div id = "qna-title">
                        <input type ="text" name="qnaTitle" placeholder="제목을 입력해주세요." class ="qnaInputTitle">
                    </div>
                    <div id ="smarteditor">
                        <textarea name="qnaContent" id = "editorTxt"
                                  row="20" cols="10"
                                  placeholder="내용을 입력해주세요"
                                  style="width: 500px">${qna.qnaContent}</textarea>
                    </div>
                    <!-- 버튼영역 -->
                    <div class ="qna-btn-area">
                        <button type="submit" id="qna-btn">작성하기</button>

                    <!-- insert 모드 -->
                    <c:if test="${param.mode == 'insert'}">
                        <button type="button" id="goToListBtn">목록으로</button>
                    </c:if>
                    
                    <!-- update 모드 -->
                    <c:if test="${param.mode == 'update'}">
                        <button type="button" onclick="location.href='${header.referer}'">이전으로</button>                           
                    </c:if>
                    </div>

                                <!-- 숨겨진 값(hidden) -->
                                <!-- 동작 구분 -->
                                <input type="hidden" name="mode" value="${param.mode}">

                                <!-- 게시글 번호 (커맨드객체 BoardDetail.boardNo 세팅)-->

                                <input type="hidden" name="qnaId" value="${empty param.no ? 0 : param.no}">
                                
                                <!-- 현재 페이지 -->
                                <input type="hidden" name="cp" value="${param.cp}">
            
                </form>
            </div>
            </div>
        </section>


        <script>
            let oEditors = [];
            smartEditor = function() {
              nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "editorTxt",
                sSkinURI: "${contextPath}/resources/static/SmartEditor2Skin.html",
                fCreator: "createSEditor2"
              });
            };

            // $(document).ready(function() {
            //   smartEditor();
            // });
            smartEditor();
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