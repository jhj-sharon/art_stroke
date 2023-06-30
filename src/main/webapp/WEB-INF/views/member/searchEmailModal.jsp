<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div id="background_modal" class="background-modal">
  <div class="modal_contents">
    <h4>
      <b>회원님의 아이디는? </b><span class ="close">&times;</span>
    </h4><br>
    <span><c:out value="${EmailResult}" /></span>
    <br>
    <button type="button" id="searchEmail_btn">확인</button>
  </div>
</div>




</body>
</html>