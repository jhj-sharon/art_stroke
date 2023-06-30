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
            <form action="write" method="post" name="adminProductRegister"   enctype="multipart/form-data" onsubmit="return profileValidate()">
                
                <div id="admin-product-thumbnail" class="form-floating">
                <!-- 
                      <c:if test="${empty map.productImage}">
                          <img src="${contextPath}/resources/images/user.png" id="profile-image">
                      </c:if>
                      
                        <c:if test="${!empty Product.productImage}">
                            <img src="${contextPath}${map.profileImage}" id="profile-image">
                        </c:if>
  -->
			   </div>
            
            <!-- 
                <div class="form-floating">
                  <input type="file" class="form-control" id="floatingThumbnail" accept="img/*" name="productImage" placeholder="이미지">
               	  <label for="floatingInput">썸네일</label>
                </div>
                 -->
                 
                 
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
                  
                   
                  
                  <script>
                    $("#floatingThumbnail").change(function(){
                     if(this.files && this.files[0]) {
                      var reader = new FileReader;
                      reader.onload = function(data) {
                       $(".select_img img").attr("src", data.target.result).width(500);        
                      }
                      reader.readAsDataURL(this.files[0]);
                     }
                    });
                   </script>
            
               
                <button class="w-100 btn btn-lg btn-primary" type="submit">등록하기</button>
               
              </form>
            </div>
            </div>
              </div>
            </div> 
    </main>
      <c:if test="${ !empty message }">
    <script>
        alert("${message}");
        // EL 작성 시 scope를 지정하지 않으면
        // page -> request -> session -> application 순서로 검색하여
        // 일치하는 속성이 있으면 출력
    </script>
</c:if> 
    
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

 