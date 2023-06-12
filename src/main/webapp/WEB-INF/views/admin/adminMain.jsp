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
                    <div class="container-fluid px-4">
                    
                      <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="row">
                        
		                    <div style="width: 1000px;  height: 500px;">
								<!--차트가 그려질 부분-->
								<canvas id="myChart"></canvas>
							</div> 
							
                     	</div>
                   </div>
                </main>
            </div>
                <jsp:include page="/WEB-INF/views/common/adminFooter.jsp" />
          
        </div>
      
 <script>
  var context = document.getElementById('myChart').getContext('2d');

  var productNames = ['포스터', '홈패브릭', '스마트폰 케이스'];

  var datasets = [];

  for (var i = 0; i < productNames.length; i++) {
    datasets.push({
      label: productNames[i],
      fill: false,
      data: [100, 200, 250, 230, 150, 200], // 초기화된 데이터 배열
      backgroundColor: 'rgba(255, 99, 132, 0.2)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1
    });
  }

  var myChart = new Chart(context, {
    type: 'line',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
      datasets: [{
          label: '포스터',
          data: [100, 200, 250, 150, 100, 200, 250, 150, 100, 200, 250, 150],
          hoverBackgroundColor: "rgba(189, 37, 113, 0.41)"
        },
        {
          label: '홈패브릭',
          data: [150, 230, 300, 180, 150, 230, 300, 180, 150, 230, 300, 180],
          hoverBackgroundColor: "rgba(189, 37, 113, 0.41)"
        },
        {
          label: '스마트폰 케이스',
          data: [120, 150, 320, 210, 120, 150, 320, 210, 120, 150, 320, 210],
          hoverBackgroundColor: "rgba(200, 150, 113, 0.41)"
        }
      ]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  // 데이터 업데이트 함수
  function updateData() {
    // 새로운 판매량 데이터를 가져온다고 가정
    var newSalesData = [60, 90, 110];

    // 데이터를 업데이트
    for (var i = 0; i < newSalesData.length; i++) {
      myChart.data.datasets[i].data = newSalesData[i];
    }

    // 그래프 업데이트
    myChart.update();
  }

  setInterval(updateData, 5000);
</script>

  
  
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
      
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
         
        <script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
        <script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
        <script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
    </body>
</html>
