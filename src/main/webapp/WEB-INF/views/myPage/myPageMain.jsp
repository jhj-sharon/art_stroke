<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="myFollow" value="${myFollow}" />
<c:set var="chatMessages" value="${chatMessages}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>

<link rel="stylesheet"
   href="${contextPath}/resources/css/myPage/myPage-style.css">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
   href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300;400;500;600&family=Poppins:wght@300;400;500;600&display=swap"
   rel="stylesheet">
<script src="https://kit.fontawesome.com/a2e8ca0ae3.js"
   crossorigin="anonymous"></script>

<script type="text/javascript" charset="utf-8">
   sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}")
</script>
</head>

<body>
   <header class="header-style">
      <jsp:include page="/WEB-INF/views/common/header.jsp" />
   </header>

   <main class="main-style">
      <button class="admin_talk" id="admin_talk" type="button"
         onclick="openPopup3()">
         <i class="fa-solid fa-headset fa-2xl" style="color: #155139;"></i>
      </button>
      <div id="popup3" class="popup3">
         <div class="popup-content3">
            <div class="myPage-popupTag">
               <h4>| 관리자와의 채팅</h4>
               <div class="close" onclick="closePopup3()">&times;</div>
            </div>
            <div class="admin-chat">
               <div class="chat-bg"></div>
               <div class="chat-input">
                  <input type="hidden" id="chatRoomId" name="chatRoomId" value="">
                  <input type="text" size="30" id="chattingInput"
                     onkeyup="inputEnter()">
                  <button onclick="readValue(),sendMessage()">입력</button>
               </div>
            </div>
         </div>
      </div>

      <!-- 여기부터 추가 -->
      <section class="contents-wrap">
         <div class="mypagemainlist">
            <a href="${contextPath}/myPage/myPageMessage"><i
               class="fa-solid fa-envelope fa-lg" style="color: #155139;"></i></a>
         </div>
         <div class="mypagemain">
            <div class="mypagemainprofile" onclick="openPopup2()">
               <c:if test="${empty loginMember.profileImage}">
                  <img
                     src="${contextPath}/resources/img/memberProfile/defaultUser.png"
                     alt="프로필 이미지">
               </c:if>

               <c:if test="${!empty loginMember.profileImage}">
                  <img src="${contextPath}/${loginMember.profileImage}"
                     alt="프로필 이미지">
               </c:if>
               <i class="fa-solid fa-camera" style="color: rgb(34, 34, 34);"></i>
            </div>

            <div id="popup2" class="popup2">
               <div class="popup-content2">
                  <div class="myPage-popupTag">
                     <h4>| 프로필 이미지 변경</h4>
                     <div class="close" onclick="closePopup2()">&times;</div>
                  </div>
                  <form action="profile" method="POST" name="myPage-form"
                     enctype="multipart/form-data" onsubmit="return profileValidate()">
                     <div class="myPageProfileImage-area">
                        <div class="myPageProfileImage">
                           <c:if test="${empty loginMember.profileImage}">
                              <img
                                 src="${contextPath}/resources/img/memberProfile/defaultUser.png"
                                 alt="프로필 이미지" id="profile-image">
                           </c:if>

                           <c:if test="${!empty loginMember.profileImage}">
                              <img src="${contextPath}/${loginMember.profileImage}"
                                 alt="프로필 이미지" id="profile-image">
                           </c:if>
                        </div>

                        <div class="myPageProfile-btn-area">
                           <div class="myPageProfile-label">
                              <div class="input-image">| 이미지 선택</div>
                              <div class="input-Profile">
                                 <input type="file" name="uploadImage" id="input-image"
                                    accept="image/*">
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="myPageProfile-btn">
                        <button type="submit" class="profileImg-btn" id="changeImg">변경하기</button>
                        <button type="button" class="profileImg-btn" id="defaultUser">기본
                           이미지로 변경하기</button>
                     </div>
                     <input type="hidden" name="delete" id="delete" value="0">
                  </form>
               </div>
            </div>
            <div class="mypagemain2">

               <div class="mypagemainptage">
                  <div class="myPageInfo-wrap">
                     <div class="myPageHello-wrap">
                        <div>안녕하세요 ${loginMember.memberName} 고객님</div>
                        <div class="myPageFollowing" onclick="openPopup()">팔로잉</div>
                     </div>
                     <ul>
                        <!-- 주문조회 -->
                        <li><a href="${contextPath}/myPage/myPageOrderList">주문
                              내역</a></li>
                        <!-- 쿠폰목록 -->
                        <li><a href="${contextPath}/myPage/myPageCouponList">쿠폰
                              목록</a></li>
                        <!-- 최근 본 상품 -->
                        <li><a href="${contextPath}/myPage/myPageResentViewList">최근
                              본 상품</a></li>
                        <!-- 관심상품 -->
                        <li><a href="${contextPath}/myPage/myPageWishList">관심
                              상품</a></li>
                        <!-- 내가 쓴 게시글 -->
                        <li><a href="${contextPath}/myPage/myPageBoardList">내
                              게시글</a></li>
                        <li><a
                           href="/stroke/board/detailWriter?member_id=${loginMember.memberId}">내
                              판매 상품</a></li>
                     </ul>
                  </div>
                  <div id="popup" class="popup">
                     <div class="popup-content">
                        <div class="myPage-popupTag">
                           <h4>| 팔로잉 목록</h4>
                           <div class="close" onclick="closePopup()">&times;</div>
                        </div>
                        <div class="myPageFollowingList">
                           <ul>
                              <c:choose>
                                 <c:when test="${empty myFollow}">
                                    <li>
                                       <p class="noitem">팔로우 목록이 없습니다.</p>
                                    </li>
                                 </c:when>
                                 <c:otherwise>
                                    <c:forEach var="myFollow" items="${myFollow}">
                                       <li>
                                          <div>
                                             <c:if test="${empty myFollow.profileImage}">
                                                <img
                                                   src="${contextPath}/resources/img/memberProfile/defaultUser.png"
                                                   alt="프로필 이미지">
                                             </c:if>

                                             <c:if test="${!empty myFollow.profileImage}">
                                                <img src="${contextPath}/${loginMember.profileImage}"
                                                   alt="프로필 이미지">
                                             </c:if>
                                          </div>
                                          <div>
                                             <span>${myFollow.memberNick}</span>
                                          </div>
                                       </li>
                                    </c:forEach>

                                 </c:otherwise>
                              </c:choose>
                           </ul>
                        </div>
                     </div>
                  </div>
                  <div class="myPageBtn-wrap">
                     <div class="myPageMain-btn">
                        <a href="${contextPath}/myPage/myPageModify">회원정보 수정 </a>
                     </div>
                     <div class="myPageMain-btn">
                        <a href="${contextPath}/myPage/myPageAddrList"> 배송주소록 관리</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>

      <section class="contents-wrap">
         <div>
            <div class="myPageMainName">
               <h4>| 주문 상품 정보</h4>

               <div class="myPage-btn">
                  <a href="${contextPath}/myPage/myPageOrderList">더보기</a>
               </div>
            </div>
            <div class="myPageContent">
               <table>
                  <thead>
                     <tr>
                        <th>주문번호</th>
                        <th>이미지</th>
                        <th>상품정보</th>
                        <th>수량</th>
                        <th>금액</th>
                        <th>주문처리상태</th>
                     </tr>
                  </thead>
                  <tbody>

                  </tbody>
               </table>
            </div>
         </div>
         <div>
            <div class="myPageMainName">
               <h4>| 내 쿠폰 목록</h4>

               <div class="myPage-btn">
                  <a href="${contextPath}/myPage/myPageCouponList">더보기</a>
               </div>
            </div>
            <div class="myPageContent">
               <table>
                  <thead>
                     <tr>
                        <th>쿠폰번호</th>
                        <th>적용상품</th>
                        <th>쿠폰정보</th>
                        <th>구매금액</th>
                        <th>혜택</th>
                        <th>비고</th>
                     </tr>
                  </thead>
                  <tbody>

                  </tbody>
               </table>
            </div>
         </div>


         <div>

            <div class="myPageMainName">
               <h4>| 최근 본 상품</h4>
               <div class="myPage-btn">
                  <a href="${contextPath}/myPage/myPageResentViewList">더보기</a>
               </div>
            </div>
            <div class="myPageContent">
               <table>
                  <thead>
                     <tr>
                        <th>No</th>
                        <th>이미지</th>
                        <th>상품정보</th>
                        <th>옵션</th>
                        <th>판매가</th>
                        <th>주문</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr>
                        <td>1</td>
                        <td><img src="../resources/img/aaa.png" alt="이미지 설명"
                           style="width: 80px; height: 80px"></td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>6</td>
                     </tr>
                     <tr>
                        <td>1</td>
                        <td><img src="../resources/img/aaa.png" alt="이미지 설명"
                           style="width: 80px; height: 80px"></td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>6</td>
                     </tr>

                  </tbody>
               </table>
            </div>
         </div>

         <div>

            <div class="myPageMainName">
               <h4>| 관심 상품</h4>
               <div class="myPage-btn">
                  <a href="${contextPath}/myPage/myPageWishList">더보기</a>
               </div>
            </div>
            <div class="myPageContent">
               <table>
                  <thead>
                     <tr>
                        <th>주문번호</th>
                        <th>이미지</th>
                        <th>상품정보</th>
                        <th>옵션</th>
                        <th>금액</th>
                        <th>주문</th>
                     </tr>
                  </thead>
                  <tbody>

                  </tbody>
               </table>
            </div>
         </div>


         <div>
            <div class="myPageMainName">
               <h4>| 내 게시글</h4>
               <div class="myPage-btn">
                  <a href="${contextPath}/myPage/myPageBoardList">더보기</a>
               </div>
            </div>
            <div class="myPageContent">
               <table>
                  <thead>
                     <tr>
                        <th>번호</th>
                        <th>분류</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th>좋아요</th>
                     </tr>
                  </thead>
                  <tbody>

                  </tbody>
               </table>
            </div>
         </div>


      </section>
   </main>

   <footer class="footer-style">
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
   </footer>

   <script>
      var memberId = '${loginMember.memberId}';
      document.documentElement.setAttribute('data-memberId', memberId);
   </script>
   <!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
   <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
  
   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   <script src="${contextPath}/resources/js/myPage/myPageMain.js"></script>
   <script src="${contextPath}/resources/js/main.js"></script>
</body>

</html>