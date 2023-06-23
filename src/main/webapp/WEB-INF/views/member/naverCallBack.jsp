<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
    <h1>naver Login Callback</h1>
    <% 
      String apiResponse = (String) request.getAttribute("apiResponse");
      out.println(apiResponse);
    %>
  </body>
</html>






