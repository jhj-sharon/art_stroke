<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
    <title>상품 상세 조회</title>
   
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<main>
	<div class="admin-detail-container">
		  <c:if test="${!empty detail.imageList}">
                
                <!-- 썸네일이 있을 경우 변수 생성 -->
                <c:if test="${detail.imageList[0].imageLevel == 0}">
                    <c:set var="productImage" value="${detail.imageList[0]}" />
                    <!-- page scope (페이지 어디서든 사용 가능) -->
                </c:if>


                <!-- 썸네일 영역(썸네일이 있을 경우) -->
                <c:if test="${!empty productImage}">
                    <h5>썸네일</h5>
                    <div class="img-box">
                        <div class="boardImg thumbnail">
                            <img src="${contextPath}${productImage.imageReName}">
                            <a href="${contextPath}${productImage.imageReName}" download="${productImage.imageOriginal}">다운로드</a>         
                        </div>
                    </div>
                </c:if>



                <c:if test="${empty thumbnail}"> <!-- 썸네일 X -->
                    <c:set var="start" value="0"/>
                </c:if>
                
                <c:if test="${!empty thumbnail}"> <!-- 썸네일 O -->
                    <c:set var="start" value="1"/>
                </c:if>


                <!-- 업로드 이미지가 있는 경우 -->
                <c:if test="${fn:length(detail.imageList) > start}">

                    <!-- 업로드 이미지 영역 -->
                    <h5>업로드 이미지</h5>
                    <div class="img-box">
                        <c:forEach var="i" begin="${start}" end="${fn:length(detail.imageList) -1 }">
                        
                            <div class="boardImg">
                                <img src="${contextPath}${detail.imageList[i].imageReName}">
                                <a href="${contextPath}${detail.imageList[i].imageReName}" download="${detail.imageList[i].imageOriginal}">다운로드</a>                
                            </div>

                        </c:forEach>
                    </div>

                </c:if>        

            </c:if>
	
		
	
	</div>	  
	 
 

</main>
</body>
</html>