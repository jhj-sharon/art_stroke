<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "stylesheet" type = "text/css" href = "${contextPath}/resources/css/board/boardWrite.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_in.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_items.css">
    <link rel="stylesheet" type = "text/css" href="${contextPath}/resources/static/css/smart_editor2_out.css">
    <script type ="text/javascript" src="${contextPath}/resources/static/js/HuskyEZCreator.js" charset = "utf-8"></script>
    <title>글쓰기</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <header class="header-style">
        
    </header>

    <main class="main-style">

        <!-- 여기부터 추가 -->
        <section class="contents-wrap">
            
        </section>

        <section class="contents-wrap">
            <div class ="board_form_area">
                <div class="board_write_title2">
                    <h3>여러분의 생각을 자유롭게 나누어보세요.</h3>
                </div>
                <form action ="../write/${boardCode}" method ="post" class = "widthfull">
                    <div id = "board_Write_title">
                        <input type ="text" placeholder="제목" class ="boardInputTitle">
                    </div>
                    <div id ="smarteditor">
                        <textarea name="editorTxt" id = "editorTxt"
                                  row="20" cols="10"
                                  placeholder="내용을 입력해주세요"
                                  style="width: 500px"></textarea>
                    </div>
                    <div class ="board_btn_area">
                        <input type="submit" value="작성하기">
                    </div>
                </form>
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

            submitPost = function(){
                oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD",[]);
                let content = document.getElementById("editorTxt").ariaValueMax;
                if(content == ''){
                    alert("내용을 입력해주세요.");
                    oEditors.getById["editorTxt"].exec("FOCUS");
                    return;
                }else{
                    console.log(content);
                }
            }
        </script>


    </main>

    <footer class="footer-style">
        
    </footer>

    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>