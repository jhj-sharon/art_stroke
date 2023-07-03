package fp.art.stroke.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminMemberDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.ProductQnA;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {
	
	@Autowired
	private AdminMemberDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AdminMemberServiceImpl.class);
 
	/** 관리자 회원 목록
	 *
	 */
	@Override
	public Map<String, Object> selectMemberList(int cp, int adminCode) {

		int listCount = dao.getListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Member> memberList = dao.selectMemberList(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		map.put("adminCode", adminCode);
		
		
		return map;
	}

	/** 관리자 회원 검색 목록
	 *
	 */
	@Override
	public Map<String, Object> searchMemberList(Map<String, Object> paramMap) {
		int listCount = dao.searchListCount( paramMap  );
		
		
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<Member> memberList = dao.searchMemberList(paramMap, pagination);
		
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		
		logger.info("service Search" + memberList + map + paramMap);
		return map;
	}

	/** 관리자 문의 목록
	 *
	 */
	@Override
	public Map<String, Object> selectAdminMemberQA(int cp, int adminCode) {
		int listCount = dao.getAdminQnAListCount(adminCode);
		Pagination pagination = new Pagination(cp, adminCode);
		
		List<ProductQnA> memberQA = dao.selectAdminMemberQA(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberQA", memberQA);
		map.put("adminCode", adminCode);
		
		logger.info("관리자 !문의! 서비스" + map);
		
		return map;
	}
	
	
	
	

	/** 관리자 문의 검색
	 *
	 */
	@Override
	public Map<String, Object> searchAdminMemberQA(Map<String, Object> paramMap) {
	int listCount = dao.searchAdminQnAListCount( paramMap  );
		
		
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<ProductQnA> memberQA = dao.searchAdminMemberQA(paramMap, pagination);
		
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberQA", memberQA);
		
		logger.info("service Search" + memberQA + map + paramMap);
		return map;
	}

 
	
	/** 관리자 문의 처리
	 *
	 */
	@Override
	public int updateAdminMemberQA(List<Integer> selectedIds) {
	   // List<Integer> result = new ArrayList<>();
	    int result =0;
	    if (selectedIds != null) {
	        for (Integer qnaId : selectedIds) {
	            int updatedCount = dao.updateAdminMemberQA(selectedIds, qnaId);
	         //   result.add(updatedCount);
	            result = updatedCount;
	            logger.info("업데이트된 큐앤에이: " + selectedIds);
	            logger.info("업데이트된 레코드 수: " + updatedCount);
	            logger.info("서비스임플result: " + result);
	            
	        }
	    }

	    return result;
	}

	/** 관리자 신고 목록
	 *
	 */
	@Override
	public Map<String, Object> selectMemberReport(int cp) {
	    int listCount = dao.getMemberReportListCount();
	    Pagination pagination = new Pagination(cp, listCount);
	   
	    List<Report> reportList = dao.selectMemberReport(pagination);

	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("pagination", pagination);
	    map.put("reportList", reportList);

	    return map;
	}

	/** 관리자 신고 검색
	 *
	 */
	@Override
	public Map<String, Object> searchMemberReport(Map<String, Object> paramMap) {
	    int listCount = dao.searchMemberReportListCount(paramMap);

	    Pagination pagination = new Pagination((int) paramMap.get("cp"), listCount);
	   
	    List<Report> reportList = dao.searchMemberReport(paramMap, pagination);

	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("pagination", pagination);
	    map.put("reportList", reportList);

	    logger.info("service 신고 Search" + reportList + map + paramMap);
	    return map;
	}

	/** 관리자 신고 처리
	 *
	 */
	@Override
	public int updateAdminMemberReport(List<Integer> reportChk) {
		  int result =0;
		    if (reportChk != null) {
		        for (Integer reportId : reportChk) {
		            result = dao.updateAdminMemberReport(reportChk, reportId);
		         
		            
		            logger.info("업데이트된 REPORT 큐앤에이: " + reportChk); 
		            logger.info("서비스임플 REPORT result: " + result);
		            
		        }
		    }

		    return result;
		}

	/** 관리자 작가 승인
	 *
	 */
	@Override
	public int updateAdminAuth(List<Integer> authChk) {

		int result = 0;
		if(authChk != null) {
			for(Integer memberId : authChk) {
				result = dao.updateAdminAuth(authChk, memberId);
				
				logger.info("authChk: " + result);
			}
		}
		
		return result;
	}

	/** 관리자 리뷰 목록
	 *
	 */
	@Override
	public Map<String, Object> selectAdminReview(int cp) {
		 int listCount = dao.getAdminReviewListCount();
		    Pagination pagination = new Pagination(cp, listCount);
		   
		    List<Report> reviewList = dao.selectAdminReview(pagination);

		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("pagination", pagination);
		    map.put("reviewList", reviewList);

		    return map;
		}


	/** 관리자 리뷰 검색
	 *
	 */
	@Override
	public Map<String, Object> searchAdminReview(Map<String, Object> paramMap) {
		  int listCount = dao.searchAdminReviewListCount(paramMap);

		    Pagination pagination = new Pagination((int) paramMap.get("cp"), listCount);
		   
		    List<Report> reviewList = dao.searchAdminReview(paramMap, pagination);

		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("pagination", pagination);
		    map.put("reviewList", reviewList);

		    logger.info("service reviewList Search" + reviewList + map + paramMap);
		    return map;
		}

	
	/** 관리자 리뷰 삭제
	 *
	 */
	@Override
	public int deleteAdminReview(List<Integer> reviewChk) {
		int result = 0;
		if(reviewChk != null) {
			for(Integer reviewId : reviewChk) {
				result = dao.deleteAdminReview(reviewChk, reviewId);
				
				logger.info("reviewChk: " + result);
			}
		}
		
		return result;
	}

	/** 관리자 회원 탈퇴
	 *
	 */
	@Override
	public int adminDeleteMember(List<Integer> authChk) {
		int result = 0;
		if(authChk != null) {
			for(Integer memberId : authChk) {
				result = dao.adminDeleteMember(authChk, memberId);
				
				logger.info("authChk: " + result);
			}
		}
		
		return result;
	}

	@Override
	public int sendBack(String memberNick, String sendName, String messageTitle, String messageContent, int senderId,
			int memberId) {
		
		Message newMessage = new Message();
		newMessage.setMemberNick(memberNick);
		newMessage.setMessageTitle(messageTitle);
		newMessage.setMessageContent(messageContent);
		newMessage.setSenderId(memberId);
		newMessage.setReceiverId(senderId);
		
		return dao.sendBack(newMessage);
	}
	
	
}
