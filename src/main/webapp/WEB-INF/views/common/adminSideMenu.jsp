<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


 

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
             
                
                <!-- 상품 -->
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseProducts"
                aria-expanded="false" aria-controls="collapseProducts">
                    
                    <span class="material-icons" style="margin-right: 0.5rem;">&#xe16b;</span>
                    Products
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseProducts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    
                    <nav class="sb-sidenav-menu-nested nav">
                       
                        <a class="nav-link" href="${contextPath}/admin/product/1">상품 목록</a>
                        <a class="nav-link" href="${contextPath}/admin/product/1/productWriteForm">상품 등록</a>
                         <a class="nav-link" href="${contextPath}/admin/example">didiiiiiiiiiiiiiii</a>
                <a class="nav-link" href="${contextPath}/admin/admin">Admin.jsp</a>
                         <a class="nav-link" href="${contextPath}/admin/example1">example1.jsp</a>
                                  
                
                    </nav>
                </div>
				   
				   
                  <!-- 회원 -->
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseMembers" aria-expanded="false" aria-controls="collapseMembers">
                
                <span class="material-icons" style="margin-right: 0.5rem;">&#xe87c;</span>
                    Members
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseMembers" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
   			            <a class="nav-link" href="${contextPath}/admin/member/2">회원 목록</a>
                        <a class="nav-link" href="${contextPath}/admin/member/2/QnA">문의</a>
                        <a class="nav-link" href="${contextPath}/admin/member/report">신고 관리</a>
                        <a class="nav-link" href="${contextPath}/admin/member/review">리뷰 관리</a>
                    </nav>
                </div>
 
                  <!-- 주문 -->
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseOrders" aria-expanded="false" aria-controls="collapseOrders">
                    <span class="material-icons" style="margin-right: 0.5rem;">&#xebbe;</span>
                    Orders
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseOrders" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="${contextPath}/admin/order/3">주문 목록</a>
                        <a class="nav-link" onclick="window.open('${contextPath}/admin/order/refund','window_name','width=1300,height=1300,location=no,status=no,scrollbars=yes');">
                            취소/반품</a>
                        
                    </nav>
                </div>

                  <!-- 재고 -->
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseStock"
                aria-expanded="false" aria-controls="collapseStock">
                <span class="material-icons" style="margin-right: 0.5rem;">&#xeac6;</span>
                    Stock
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseStock" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                       <a class="nav-link" href="${contextPath}/admin/stock/4">재고 목록</a> 
                    </nav>
                </div>


                  <!-- 게시판 -->
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseBoard"
                aria-expanded="false" aria-controls="collapseBoard">
                <span class="material-icons" style="margin-right: 0.5rem;">&#xf8f7;</span>
                    Board
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseBoard" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                       <a class="nav-link" href="${contextPath}/admin/board/5">게시판 목록</a>
                    </nav>
                </div>
 
                
                
              
</div>
</div>

      
    </nav>
</div>