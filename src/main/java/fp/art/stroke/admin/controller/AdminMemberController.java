package fp.art.stroke.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fp.art.stroke.admin.model.service.AdminMemberService;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.QnAReply;

/**
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/member")
@SessionAttributes({"loginMember"})
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminMemberController.class);
 
		
		 
		/** 관리자 - 멤버신고
		 * @param cp
		 * @param model
		 * @param memberId
		 * @param paramMap
		 * @return
		 */
		@GetMapping("report")
		public String memberReport(	@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				Model model, Member memberId,
				@RequestParam Map<String, Object> paramMap, HttpSession session) {
			
			Map<String, Object> map = null;
			map = service.selectMemberReport(cp);
			
			if(paramMap.get("key") == null) {
				 
			
			} else {
				paramMap.put("cp", cp);
				paramMap.put("memberId", memberId);
				map = service.searchMemberReport(paramMap);
			}
			
			logger.info("멤버 신고 CONTROLLER" + map);
			model.addAttribute("map", map); 
			 return "admin/memberReport";
		}
		
		 
		/** 관리자 - 멤버목록
		 * @param adminCode
		 * @param cp
		 * @param model
		 * @param memberId
		 * @param paramMap
		 * @return
		 */
		@GetMapping("{adminCode}")
		public String memberList(@PathVariable("adminCode") int adminCode,
									@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
									Model model, Member memberId,
									@RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;
 
			if(paramMap.get("key") == null) {  

				map = service.selectMemberList(cp, adminCode);
				
				
			}else {   
				
				paramMap.put("cp", cp);   
				paramMap.put("adminCode", adminCode);
				paramMap.put("memberId", memberId);
				map = service.searchMemberList(paramMap);
				 
			} 
			
			logger.info("MEMBER MANAGER CONTROLLER" + map);
			model.addAttribute("map", map);
			
			
			
			return "admin/memberList";
		}
		
		
		/** 관리자 - 문의
		 * @param adminCode
		 * @param cp
		 * @param paramMap
		 * @param model
		 * @return
		 */
		@GetMapping("{adminCode}/QnA")
		public String selectAdminMemberQA(@PathVariable("adminCode") int adminCode,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				@RequestParam Map<String, Object> paramMap
				, Model model) {
		
			Map<String, Object> map = null;
	 
			
			if(paramMap.get("key") == null) { 
				map = service.selectAdminMemberQA(cp, adminCode); 
				logger.info("관리자 문의" + map);
				
			} else {
				
				paramMap.put("cp", cp);   
				paramMap.put("adminCode", adminCode); 
		 
				map = service.searchAdminMemberQA(paramMap);
				
				logger.info("관리자 search문의" + map);
				
			}
			
			model.addAttribute("map", map);
			logger.info("MODEL 관리자 문의" + map);
			return "admin/memberQnA";
		}
		
		
		/** 관리자 작가 승인
		 * @param authChk
		 * @return
		 */
		@ResponseBody
		@PostMapping("updateAdminAuth")
		public String updateAdminAuth(@RequestParam(value="authChk", required=false) List<Integer> authChk) {
			
			logger.info("updateAuth Controller" + authChk);
			
			int result = 0;
		    if (authChk != null) {
		 
		    result	= service.updateAdminAuth(authChk);
 
		    logger.info("result: " + result);
		           
		    }
			
			return new Gson().toJson(result);
		}
		
		
		
		/** 관리자 회원 Q&A 처리
		 * @param selectedIds
		 * @return
		 */
		@ResponseBody
		@PostMapping("{adminCode}/modifyData")
		public String updateAdminMemberQA(@RequestParam( value="selectedIds", required=false) List<Integer> selectedIds) {
			

			logger.info("업데이트된큐앤에이리스트: " + selectedIds);		
			 
		 
			int result = 0;
		    if (selectedIds != null) {
		 
		    result	= service.updateAdminMemberQA(selectedIds);
 
		        logger.info("result: " + result);
		           
		    }
		    
		    return new Gson().toJson(result);
 
		}

		 
		/** 관리자 회원 신고 처리
		 * @param reportChk
		 * @return
		 */
		@ResponseBody
		@PostMapping("report/reportDeleteData")
		public String updateAdminMemberReport(@RequestParam(value="reportChk", required=false) List<Integer> reportChk) {
			
			logger.info("reportDelete Controller" + reportChk);
			
			int result = 0;
		    if (reportChk != null) {
		 
		    result	= service.updateAdminMemberReport(reportChk);
 
		    logger.info("result: " + result);
		           
		    }
			
			return new Gson().toJson(result);
		}
		
		
		
		/** 관리자 회원 리뷰
		 * @param cp
		 * @param model
		 * @param paramMap
		 * @return
		 */
		@GetMapping("review")
		public String adminReview(@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
									Model model, @RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;
 
			if(paramMap.get("key") == null) {  

				map = service.selectAdminReview(cp);
				 
			}else {   
				paramMap.put("cp", cp);   
				map = service.searchAdminReview(paramMap);
			} 
			
			logger.info("리뷰 CONTROLLER" + map);
			model.addAttribute("map", map);
			  
			return "admin/memberReview";
		}
		
		/** 관리자 리뷰 삭제
		 * @param reviewChk
		 * @return
		 */
		@ResponseBody
		@PostMapping("deleteReview")
		public String deleteAdminReview(@RequestParam(value="reviewChk", required=false) List<Integer> reviewChk) {
			
			logger.info("reviewChk Controller" + reviewChk);
			
			int result = 0;
		    if (reviewChk != null) {
		 
		    result	= service.deleteAdminReview(reviewChk);
 
		    logger.info("result: " + result);
		           
		    }
			
			return new Gson().toJson(result);
		}
		
		 
		/** 관리자 회원 탈퇴
		 * @param 
		 * @return
		 */
		@ResponseBody
		@PostMapping("adminDeleteMember")
		public String adminDeleteMember(@RequestParam(value="authChk", required=false) List<Integer> authChk) {
			
			logger.info("authChk Controller" + authChk);
			
			int result = 0;
		    if (authChk != null) {
		 
		    result	= service.adminDeleteMember(authChk);
 
		    logger.info("result: " + result);
		           
		    }
			
			return new Gson().toJson(result);
		}
		
		
		
		@GetMapping("/report/detail")
		public String adminReportDetail(@RequestParam("reportContent") String reportContent, Model model
			 ) {
		   
		    model.addAttribute("reportContent", reportContent);
		      logger.info("reportContent : " + reportContent);
		     
		    return "admin/reportDetail";
		}


	 
		@GetMapping("/message/{memberId}/writeForm")
		public String adminMemberMessageWriteForm(@RequestParam("memberNick") String memberNick,
				@PathVariable("memberId") Integer memberId, Model model) {
			
			model.addAttribute("memberNick", memberNick);
			model.addAttribute("memberId", memberId);
			
		    return "admin/messageWrite";
		}
		
		
		@GetMapping("/reportMessage/{reportSendId}/writeForm")
		public String adminMemberReportMessageWriteForm(@RequestParam("reportSendNick") String memberNick,
				@PathVariable("reportSendId") Integer memberId, Model model) {
			
			model.addAttribute("memberNick", memberNick);
			model.addAttribute("memberId", memberId);
			
		    return "admin/reportMessageWrite";
		}
		
		@GetMapping("/qnaMessage/{memberId}/writeForm")
 		public String adminMemberQnAMessageWriteForm(@RequestParam("qnaContent") String qnaContent,
				@PathVariable("memberId") Integer memberId, @RequestParam("qnaId") int qnaId, Model model, @RequestParam("memberNick") String memberNick) {
			
			model.addAttribute("qnaContent", qnaContent);
			model.addAttribute("memberId", memberId);
			model.addAttribute("qnaId", qnaId);
			model.addAttribute("memberNick", memberNick);
			
			
			logger.info("qna값"+model);
			logger.info("qna CONTENT 값  "+qnaContent);
			logger.info("qna memberId 값  "+memberId);
			logger.info("qna qnaId 값  "+qnaId);
			logger.info("qna memberNick 값  "+memberNick);
			
		    return "admin/qnaMessageWrite";
		}
		
		
		@PostMapping("/message/{memberId}/writeForm/sendBack")
		public String sendBack(@RequestParam("memberNick") String memberNick, @RequestParam("sendName") String sendName,
		        @RequestParam("messageTitle") String messageTitle, @RequestParam("messageContent") String messageContent,
		        @PathVariable("memberId") Integer senderId, HttpSession session,
		        RedirectAttributes ra) {

		    Member loginMember = (Member) session.getAttribute("loginMember");

		    int memberId = loginMember.getMemberId();

		    int result = service.sendBack(memberNick, sendName, messageTitle, messageContent, senderId, memberId);

		    String message = "";

		    if (result > 0) {
		        message = "쪽지가 보내졌습니다.";
		    } else {
		        message = "쪽지 보내기 실패하였습니다.";
		    }
		    ra.addFlashAttribute("message", message);

		    return "redirect:/admin/adminMain";
		}

 
		@PostMapping("reportMessage/{reportSendId}/writeForm/reportSendBack")
		public String reportSendBack(@RequestParam("memberNick") String memberNick, @RequestParam("sendName") String sendName,
		        @RequestParam("messageTitle") String messageTitle, @RequestParam("messageContent") String messageContent,
		        @PathVariable("reportSendId") Integer senderId, HttpSession session,
		        RedirectAttributes ra) {

		    Member loginMember = (Member) session.getAttribute("loginMember");

		    int memberId = loginMember.getMemberId();

		    int result = service.sendBack(memberNick, sendName, messageTitle, messageContent, senderId, memberId);

		    String message = "";

		    if (result > 0) {
		        message = "쪽지가 보내졌습니다.";
		    } else {
		        message = "쪽지 보내기 실패하였습니다.";
		    }
		    ra.addFlashAttribute("message", message);

		    return "redirect:/admin/adminMain";
		}
  
		
		@PostMapping("qnaMessage/{memberId}/writeForm/qnaSendBack")
		public String qnaSendBack(HttpSession session, QnAReply qnaReply,
		        RedirectAttributes ra) {

		    Member loginMember = (Member) session.getAttribute("loginMember");
		     logger.info("qnaReply: "+ qnaReply.getMemberNick());
		    int result = service.sendBack(qnaReply);

		    String message = "";

		    if (result > 0) {
		        message = "답변 완료~!";
		        result = service.updateSendBack(qnaReply);
		        logger.info("qnaReply~~~: "+qnaReply);
		    } else {
		        message = "답변 실패!";
		    }
		    ra.addFlashAttribute("message", message);

		    return "redirect:/admin/adminMain";
		}
		
	 
}

 