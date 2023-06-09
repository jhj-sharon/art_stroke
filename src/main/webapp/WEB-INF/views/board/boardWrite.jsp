<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var = "detail" value="${detail}"/>
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
    
    <title>글쓰기</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>


    <main class="main-style">

        <section class="contents-wrap">
            <div class ="board_form_area">
                <c:choose>

                    
                    <c:when test = "${!empty detail}">
                        <form id = "noticeWriteForm" action ="../write/${boardCode}?type=update&no=${detail.boardId}" method ="post" class = "widthfull">
                            
                            <!-- 제목 -->
                            <div id = "board_Write_title">
                                <input id = "title" name = "title" type ="text" placeholder="제목" class ="boardInputTitle" value = "${detail.boardTitle}">
                            </div>

                            <!-- 본문 -->
                            <div id ="smarteditor">
                                <textarea name="smartEditor" id="smartEditor" rows="20" cols="10" style="width: 500px; height:400px;">${detail.boardContent}</textarea>
                            </div>

                            <!-- 작성 버튼 -->
                            <div class ="board_btn_area">
                                <input type="button" id="savebutton" value="작성" />
                            </div>
                        </form>
                    </c:when>

                    
                    <c:otherwise>
                        <form id = "noticeWriteForm" action ="../write/${boardCode}" method ="post" class = "widthfull">
                            <div id = "board_Write_title">
                                <input id = "title" name = "title" type ="text" placeholder="제목" class ="boardInputTitle">
                            </div>
                            <div id ="smarteditor">
                                <textarea name="smartEditor" id="smartEditor" rows="20" cols="10" style="width: 500px; height:400px;"></textarea>
                                
                            </div>
                            <div class ="board_btn_area">
                                <input class ="board_btn" type="button" id="savebutton" value="작성" />
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
                
            </div>
        </section>
        


    </main>


    <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    <script type ="text/javascript" src="${contextPath}/resources/static/js/HuskyEZCreator.js" charset = "utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>
    
</body>
</html>
<script>

    var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "smartEditor", //저는 textarea의 id와 똑같이 적어줬습니다.
		sSkinURI : "${contextPath}/resources/static/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요!
		fCreator : "createSEditor2",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,

			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : false,

			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : false
		}
	});

	$(function() {
		$("#savebutton").click(function() {
			oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); 
			//textarea의 id를 적어줍니다.

			
			var title = $("#title").val();
			var content = document.getElementById("smartEditor").value;;

			
			if (title == null || title == "") {
				alert("제목을 입력해주세요.");
				$("#title").focus();
				return;
			}
            if(title.length>30){
                alert("제목 최대 길이는 30자 입니다");
                return;
            }
			if(content == "" || content == null || content == '&nbsp;' || 
					content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){
				alert("본문을 작성해주세요.");
				oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱
				return;
			} //이 부분은 스마트에디터 유효성 검사 부분이니 참고.
			console.log(content);
			var result = confirm("작성하시겠습니까?");
			
			if(result){
				alert("작성이 완료되었습니다.");
				$("#noticeWriteForm").submit();
			}else{
				return;
			}
		});
	})
</script>