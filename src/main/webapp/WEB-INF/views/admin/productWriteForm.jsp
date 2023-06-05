<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin-main.css" rel="stylesheet" />
        
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
<body class="admin-product-body"> 
    <main>
       
  
	 
       <div class="container-fluid px-4">
            <div class="admin-product-main-form"> 
                <div class="admin-product-form"> 
                
                
            <form action="productWrite" enctype="multipart/form-data" method="POST" class="board-write"
            onsubmit="return writeValidate()">
             
             
             	      <c:forEach items="${detail.imageList}" var="productImage">

                <c:choose>
                    <c:when test="${productImage.imageLevel == 0}">
                        <%-- c:set 변수는 page scope가 기본값 (조건문이 끝나도 사용 가능)  --%>
                        <c:set var="img0"  value="${contextPath}${productImage.imageReName}" />
                    </c:when> 
                </c:choose>
            </c:forEach>


                   
                <div class="form-floating" >
                 
		            <div class="img-box">
		                <div class="boardImg thumbnail">
		                    <label for="img0">
		                        <img class="preview" src="${img0}">
		                    </label>
		                    <input type="file" class="inputImage" id="img0" name="images" accept="image/*">
		                    <span class="delete-image">&times;</span>
		                    <!-- &times;  :  x 모양의 문자 -->
		                </div>
		            </div>
               	  <label for="floatingInput">썸네일</label>
				 
							 
				</div>
               
                
                 
                <div class="form-floating">
                  <input type="text" class="form-control" id="floatingProductName" name="productName" placeholder="상품명">
                  <label for="floatingPassword=">상품명</label>
                </div>
                
                 <div class="form-floating">
                     <label for="floatingPassword">대분류</label>
                 	<select class="form-control" id="floatingProductType" name="productType">
                 		<option value="포스터">포스터</option>
                 		<option value="홈패브릭">홈패브릭</option>
                 		<option value="스마트폰 케이스">스마트폰 케이스</option>
                 	</select> 
                </div>
                
                
                   <div class="form-floating">
                     <label for="floatingPassword">소분류</label>
                 	<select class="form-control" id="floatingProductCategory" name="productCategory" >
                 		<option value="식물">식물</option>
                 		<option value="패턴">패턴</option>
                 		<option value="동물">동물</option>
                 		<option value="사진">사진</option>
                 		<option value="드로잉">드로잉</option>
                 		<option value="그래픽디자인">그래픽디자인</option>
                 	</select> 
                </div>
                
                
                
                
                 <div class="form-floating">
                  <input type="text" class="form-control" id="floatingProductArtist" name="productArtist" placeholder="아티스트">
                  <label for="floatingPassword">작가</label>
                </div>
                
                
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingPrice" name="productPrice" placeholder="가격">
                    <label for="floatingPassword">가격</label>
                  </div>
			
                  <div class="form-floating">
                     <label for="floatingPassword">옵션1</label>
                 	<select class="form-control" id="floatingProductOption1" name="productOption1" >
                 		<option value="S/M/L">S/M/L</option>
                 	</select> 
                </div>

                <label for="floatingPassword">옵션2</label>
                <div  class="form-control" id="option2" placeholder="기본옵션">
                	<input type="checkbox" name="option2" id="option1" class="admin-option" value="1">아이폰13
              	    <input type="checkbox" name="option2" id="option2" class="admin-option" value="2">아이폰13PRO
              	    <input type="checkbox" name="option2" id="option3" class="admin-option" value="3">아이폰13MAX
              	    <input type="checkbox" name="option2" id="option4" class="admin-option" value="4">아이폰14
              	    <input type="checkbox" name="option2" id="option5" class="admin-option" value="5">아이폰14PRO
              	    <input type="checkbox" name="option2" id="option6" class="admin-option" value="6">아이폰14sMAX
               
                   
                  </div>
                 


                  <div class="form-floating">
                  	<textarea rows="80" cols="10" class="form-control" id="floatingDetail" name="productContent" placeholder="상품설명"></textarea>
                   
                    <label for="floatingPassword">상세설명</label>
                  </div>
                  
                   
            
            <!-- 버튼 영역 -->
            <div class="board-btn-area">
                <button type="submit" id="writebtn">등록</button>

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

            <input type="hidden" name="productId" value="${empty param.no ? 0 : param.no}">
            
            <!-- 현재 페이지 -->
            <input type="hidden" name="cp" value="${param.cp}">
            
            <!-- 존재하던 이미지가 제거되었음을 기록하여 전달하는 input -->
            <!-- value에 제거된 이미지의 레벨을 기록 (X버튼 클릭 시)-->
            <!-- DELETE FROM BOARD_IMG 
                 WHERE BOARD_NO = 1000 
                 AND IMG_LEVEL IN (0,3,1,2) -->
                
            <input type="hidden" name="deleteList" id="deleteList" value="">
                
         
              </form>
            </div>
            </div>
              </div>
            
    </main>
       <c:if test="${ !empty message }">
    <script>
        alert("${message}");
    </script>
</c:if>
       <script>
        const adminCode = "${adminCode}"; // 게시판 코드를 전역변수로 생성
    </script>
   
 
 
 <script src="${contextPath}/resources/js/product/productWriteForm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
 
</body>
</html>

 