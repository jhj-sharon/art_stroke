<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/member/searchIdPw-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
  <header class="header-style">
       <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
  </header>
<@ include file="${contextPath}member/SearchEmailModal.jsp"%>
  <main class="searchIdPw-main-style">

      <!-- 여기부터 추가 -->
      <section class="searchIdPw-contents-wrap">
       
       <tab>
          <ul class="tabnav">
            <li><a href="#tab01">아이디찾기</a></li>
            <li><a href="#tab02">비밀번호찾기</a></li>
          </ul>
          <div class="tabcontent">
           
            <div id="tab01">

              
              <h3>*가입하셨던 방법으로 이메일 찾기가 가능합니다.</h3>
              <div class="selectWay_area">
                가입방법 : Email<input type="radio" id="FindEmail_Email" name="searchType">
                Tel<input type="radio" id="FindEmail_Tel" name="searchType"></p>
              </div>
                <form action="searchId" method="post" validation="" name="searchId">
                  <div class="searchEmail_area">
                 <table>
                    
                     <tbody class="emailTbody">
                     
                    
                    </tbody>
                 </table>
                </div>
                 <div class="searchEmailBtn_area">
                 <button id="searchEmailBtn">이메일찾기</button>
                </div>
             </form>


            </div>
            <div id="tab02">
              <h3>*가입방법으로 비밀번호 찾기가 가능합니다.</h3>
              <div class="selectWay_area">
                가입방법 : Email<input type="radio" id="FindPw_Tel" name="searchType">
                Tel<input type="radio" id="FindPw_Tel" name="searchType"></p>
              </div>
                <form action="searchPw" method="post" validation="">
                  <div class="searchPw_area">
                 <table>
                 <tbody class="telTbody">
                
                </tbody>
                 </table>
                </div>
                 <div class="searchEmailBtn_area">
                 <button id="searchPwBtn">비밀번호찾기</button>
                 </div>
             </form>
           
          </div>
        </tab><!--tab-->
       
         </section>
    </main>
    
    <footer class="footer-style">
       <jsp:include page ="/WEB-INF/views/common/footer.jsp"/>
    </footer>
    	<!--main.js-->
	<!-- <script src="${contextPath}/resources/js/main.js"></script> -->
    <script src="${contextPath}/resources/js/member/SearchIdPw.js"></script>
    	<!-- jQuery 라이브러리 추가 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
  <script>
    

$(function(){
  $('.tabcontent > div').hide();
  $('.tabnav a').click(function () {
    $('.tabcontent > div').hide().filter(this.hash).fadeIn();
    $('.tabnav a').removeClass('active');
    $(this).addClass('active');
    return false;
  }).filter(':eq(0)').click();
  });

  </script>
  
 
    </body>
    </html>
    
    