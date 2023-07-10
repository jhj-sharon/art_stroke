<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<div id="reply-area">
    <!-- 댓글 목록 -->
    <div class="reply-list-area">
        
        <ul id="reply-list">

            <c:forEach var="reply" items="${rList}">
                <c:choose>
                <c:when test = "${reply.replySt == 'N'}">
                
                <li class="reply-row <c:if test='${reply.replyParentId != 0}'> 
                                      child-reply 
                                     </c:if>">
                                     
                    <div class="reply-first-row"> 

                        <div class="reply-writer"><!-- 댓글쓴이 이미지, 닉네임, 작성시간-->
                            <c:if test="${empty reply.profileImage}">
                                <!-- 프로필 이미지가 없을 경우 -->
                                <div class="reply-profile-img">
                                    <img src="${contextPath}/resources/img/memberProfile/defaultUser.png">
                                </div>
                            </c:if>

                            <c:if test="${!empty reply.profileImage}">
                                <!-- 프로필 이미지가 있을 경우 -->
                                <div class="reply-profile-img">
                                    <img src="${contextPath}${reply.profileImage}">
                                </div>
                            </c:if>

                            <div class="reply-profile-info">
                                <c:choose>
                                    <c:when test = "${reply.replySocialType == 'N'}">
                                        <span>${reply.memberNick}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test = "${!empty reply.memberNick}">
                                                <span>${reply.memberNick}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>소셜${reply.replyMemberId}회원</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                                

                                <c:set var="replyDate" value="${fn:substring(reply.replyDt, 0, 13)}" />
                                <span class="reply-date">${replyDate}</span>
                                
                            </div>
                        </div>

                        <!-- 신고, 수정, 삭제  -->
                        <div class="reply-btns-area">
                            <div class="reply-btns-menu">
                                <i class="fa-solid fa-ellipsis-vertical"></i>
                            </div>
                            
                

                            <div class="reply-btns-box">
                                <!-- 신고하기 -->
                                <c:if test="${!empty loginMember}">
                                    <span id = "reportReply-btn" onclick="window.open('${contextPath}/board/report/${boardCode}?no=${reply.replyId}&type=reply','popupWindow',options);">신고하기</span>
                                </c:if>
                            </div>
                        </div>

                    </div>

                    

                    
                    
                    <p class="reply-content">${reply.replyContent}</p>

                    
                        <%-- 로그인 상태인 경우 답글 버튼 출력 --%>
                    <c:if test="${!empty loginMember}">
                        <div class="reply-btn-area">

                            <button onclick="showInsertReply(${reply.replyId}, this)">답글</button>

                            <%-- 로그인한 회원의 댓글인 경우 --%>
                            <c:if test="${loginMember.memberId == reply.replyMemberId}">
                                <button onclick="showUpdateReply(${reply.replyId}, this);">수정</button>     
                                <button onclick="deleteReply(${reply.replyId})">삭제</button>
                            </c:if>

                        </div>
                    </c:if>
  
                </li>            
                </c:when>
                <c:otherwise>
                    <li class="reply-row <c:if test='${reply.replyParentId != 0}'> 
                        child-reply 
                       </c:if>">
                       
                    <div class="reply-first-row" style = "height:80px; color: gray;"> 
                        <p>삭제된 댓글입니다.</p>
                    </div>
                    </li>
                </c:otherwise>    
            </c:choose>
                
            </c:forEach>

            
        </ul>
    </div>
    

    <!-- 댓글 작성 부분 -->
    <div class="reply-write-area">
        <textarea id="replyContent"></textarea>
        <button id="addReply">
            댓글<br>
            등록
        </button>

    </div>

</div>

<script>
    const options = "width=600, height=600, top=50, left=400";
</script>