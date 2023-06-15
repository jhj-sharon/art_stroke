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
        
        <link href="${contextPath}/resources/css/admin/admin-styles.css" rel="stylesheet" />
        <link href="${contextPath}/resources/css/admin/admin-main.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>

<body class="sb-nav-fixed">
 
    <main>
        <div class="container-fluid px-4"> 
            <div class="admin-order-cancel-container">
            <div class="admin-container"> 
                 
                <div class="admin-main-header">
                    <h2>취소 / 반품 요청</h2>
                  </div>
                <div class="admin-main-nav-order">
                    <form action="#">
                    <div class="admin-main-cancel-div"> 
                        <div class="admin-main-cancel-label">
                        <label>주문정보</label>
                        </div>
                    <div class="admin-cancel-main">
                        
                            <table class="admin-cancel-table">
                                <tr>
                                    <th>주문번호</th>
                                    <td>1232324-42424242</td> 
                                    <th>배송지</th>
                                    <td>서울 강남구 테헤란로ddddddddddd 110번길 10 이즈타워 111동 111호</td>
                                </tr>
                                <tr>
                                    <th>주문자</th>
                                    <td>홍길동</td>
                                    <th>연락처</th>
                                    <td>010-1234-4949</td>
                                    
                                </tr>
                            </table>
                          </div>
                      </div>

                      <div class="admin-main-cancel-div"> 
                        <div class="admin-main-cancel-label">
                        <label>취소/반품 정보</label>
                        </div>
                        <div class="admin-cancel-main">
                        
                            <table class="admin-cancel-table">
                              
                                <thead>
                                    <tr>
                                    <th>상품명</th>
                                    <th>옵션명</th>
                                    <th>수량</th>
                                    <th>금액</th>
                                    <th>취소반품여부</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>                            
                                    <td>아티스트침구</td>      
                                    <td>핑크</td>                    
                                    <td>1</td>                    
                                    <td>100000</td>                    
                                    <td>
                                        <select name="" id="">
                                            <option value="dd">취소요청</option>
                                            <option value="dd">반품요청</option>
                                           
                                        </select>
                                    </td>
                                    </tr>
                                    <tr>                            
                                        <td>이쁜포스터</td>      
                                        <td>검정</td>                    
                                        <td>1</td>                    
                                        <td>19800</td>                    
                                        <td>
                                            <select name="" id="">
                                                <option value="dd">취소요청</option>
                                                <option value="dd">반품요청</option>
                                               
                                            </select>
                                        </td>
                                        </tr>
                                        <tr>                            
                                            <td>이쁜포스터</td>      
                                            <td>검정</td>                    
                                            <td>1</td>                    
                                            <td>19800</td>                    
                                            <td>
                                                <select name="" id="">
                                                    <option value="dd">취소요청</option>
                                                    <option value="dd">반품요청</option>
                                                   
                                                </select>
                                            </td>
                                            </tr>
                                           
                                </tbody>

                            </table>
                          </div>
                      </div> 
                    </div> 

                    <div class="admin-main-cancel-div"> 
                        <div class="admin-main-cancel-label">
                        <label>취소/반품 사유</label>
                        </div>
                        <div>
                            <textarea name="" id="" cols="110" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="admin-cancel-footer"> 
                        <button class="admin-btn">변경</button>
                        <button class="admin-btn">취소</button>
                    </div>
            </form>
            </div>


              
              
               
        </div>
    </div>
        </div>
    </main>
 </body>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/admin-scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/assets/demo/chart-area-demo.js"></script>
<script src="${contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/admin/datatables-simple-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</body>
</html>

 