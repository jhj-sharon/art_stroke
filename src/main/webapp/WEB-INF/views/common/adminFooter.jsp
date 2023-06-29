<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL은 사용되는 JSP 파일마다 작성되어야 한다 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer class="py-4 bg-light mt-auto">
   
 
 

    <a href="${contextPath}/admin/chat/chatList" id="admin-chat-btn">
        <i class="fa-solid fa-comment-dots" style="font-size: 100px;"></i>
    </div>
    </a>
 

    <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">
             
        </div>
      
    </div>
</footer>

<c:if test="${ !empty message }">
    <script>
        alert("${message}");
        // EL 작성 시 scope를 지정하지 않으면
        // page -> request -> session -> application 순서로 검색하여
        // 일치하는 속성이 있으면 출력
    </script>
</c:if> 