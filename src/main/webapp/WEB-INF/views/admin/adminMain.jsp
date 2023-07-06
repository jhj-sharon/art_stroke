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
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-chat.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
       
        <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    </head>
    <body class="sb-nav-fixed">

        <!-- 제목 -->
        <jsp:include page="/WEB-INF/views/common/adminHeader.jsp" />
 
        <div id="layoutSidenav">
        <jsp:include page="/WEB-INF/views/common/adminSideMenu.jsp" />
		
 

            <div id="layoutSidenav_content">
                <main>
                <div class="mainadmin">
                    <div class="mainadmin1">
                        <a class="main-p" href="https://analytics.naver.com/summary/dashboard.html">
                            <img src="${contextPath}/resources/img/naverAnalytics.png" alt="" style="width: 300px; height: auto;">
                        </a>
                        
                    </div>
                    <div class="mainadmin2">


                    </div>
                </div>
                </main>
                <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
            </div>


               
     
      	 
  
  
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/resources/js/admin/admin-chat.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
         <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
        <script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
        <script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
    </body>
</html>
