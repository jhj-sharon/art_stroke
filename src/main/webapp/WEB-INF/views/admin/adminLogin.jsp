<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="keywords" content="SignUp, Login, Register">
  <meta name="keywords" content="Sign up, Sign in">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <!--Bootstrap Css-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <!--Font-aweome-->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <!--Custom Css-->
  <link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/admin/admin-sign-in.css" />
  <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-icon.css">

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body> 
 
  <div class="admin-login-header"> 
 
    <div>
      <h1>CodeStroke</h1>
    </div>
    <div>
      </div> 
    </div>

  <section class="login_section">
       
    <div class="container outer_container accounts_container">
      <div class="row h-100">
        <div class="col col-sm-12 col-md-12 col-lg-8 m-0 p-0 w-100 h-100 accounts_col">
          <div class="accounts_image w-100 h-100" >
            <img src="${contextPath}/resources/img/marilin.jpg" style="height: 80px;" alt="accounts_image" class="img-fluid w-100 h-100" />
            </div>
          <!--accounts_image-->
        </div>
        <!--account_col-->
        <div class="col col-sm-12 col-md-12 col-lg-4 m-0 p-0 accounts_col"  >
        
          <!--accounts_forms-->
          <div class="accounts_forms  w-100 h-100" id="login">
          
           
            <div class="title  mt-4 p-4 w-100">
              <h1>Admin</h1>
              <p class="mt-3">Bling Bling Art Stroke! </p>
            </div>
           
            <!--title-->
            <form action="adminLogin" method="post" name="login-form" onsubmit="return loginValidate()" class="form  w-100 p-4" id="form">
              <div class="form-group">
                <label for="email" class="admin-label">Email</label>
                <input type="email" name="memberEmail" class="form-control" id="email" onfocus="labelUp(this)" onblur="labelDown(this)"  value="${cookie.saveId.value}" required  />
              </div>
              <div class="form-group">
                <label for="login_password"  class="admin-label">Password</label>
                <i class="fa fa-eye-slash" id="eye_icon_login"></i>
                <input type="password" name="memberPw" class="form-control" id="login_password" onfocus="labelUp(this)" onblur="labelDown(this)" required />
              </div>
              <div class="form-group mb-0">
                <button type="submit" class="btn btn-primary register_btn w-100">Login</button>
              </div>
            
  
            <div class="already_member_box d-flex justify-content-between px-4">
            
 		      <c:if test="${ !empty cookie.saveId.value}">
                 <c:set var="chk" value="checked"/>
              </c:if>
    	      
            <label class="text-center">
    	       <input type="checkbox" name="saveId" ${chk}  id="saveId"><span>아이디 저장</span> 
            </label> 
	  
           
                 
     	  
               
              </div>
          
             </form>   
         
           
                      
         
            </div>
        
          </div>
          
          <!--accounts_forms-->
        </div>
          
        <!--account_col-->
      </div>
      <!--row-->
   
    
    <!--accounts_container-->
  </section>
  <!--login_section-->
  <c:if test="${ !empty message }">
    <script>
        alert("${message}");
        // EL 작성 시 scope를 지정하지 않으면
        // page -> request -> session -> application 순서로 검색하여
        // 일치하는 속성이 있으면 출력
    </script>
</c:if> 
  <!-- jQuery library -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <!-- Popper JS -->
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <!--Custom Js-->
  <script type="text/javascript" src="${contextPath}/resources/js/admin/admin-login.js"></script>

</body>

</html>