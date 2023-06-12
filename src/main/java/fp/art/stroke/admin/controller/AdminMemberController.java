package fp.art.stroke.admin.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.admin.model.service.AdminMemberService;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.ProductQnAList;

@Controller
@RequestMapping("/admin/member")
@SessionAttributes({"loginMember"})
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminMemberController.class);
 
		
		// 관리자 - 멤버리뷰
		@GetMapping("review")
		public String memberReview() {
			return "admin/memberReview";
		}
		 
		// 관리자 - 멤버신고
		@GetMapping("report")
		public String memberReport() {
			return "admin/memberReport";
		}
		
		 
		// 관리자 - 멤버목록
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
		
		
		// 관리자 - 문의
		@GetMapping("{adminCode}/QnA")
		public String selectAdminMemberQA(@PathVariable("adminCode") int adminCode,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				@RequestParam Map<String, Object> paramMap, ProductQnAList qnaId, Member memberId
				, Model model) {
		
			Map<String, Object> map = null;
	 
			
			if(paramMap.get("key") == null) { 
				map = service.selectAdminMemberQA(cp, adminCode); 
				logger.info("관리자 문의" + map);
				
			} else {
				
				paramMap.put("cp", cp);   
				paramMap.put("adminCode", adminCode);
				paramMap.put("memberId", memberId);
				map = service.searchAdminMemberQA(paramMap);
				
				logger.info("관리자 search문의" + map);
				
			}
			
			model.addAttribute("map", map);
			logger.info("MODEL 관리자 문의" + map);
			return "admin/memberQnA";
		}
		
		@PostMapping("/{adminCode}/modifyData")
		public String updateAdminMemberQA(Model model, 
		        @RequestParam(value="selectedIds", required=false) List<String> selectedIds,
		        @PathVariable("adminCode") int adminCode,  
		        @RequestParam(value = "qnaId", required = false) Integer qnaId,
		        ProductQnA productQnA, 
		        @RequestParam Map<String, Object> paramMap) {
		  
	 
				paramMap.put("qnaId", qnaId);
				paramMap.put("selectedIds", selectedIds);
			  
		    	
		        int result = service.updateAdminMemberQA(paramMap);
		        logger.info("업데이트된 레코드 수: " + result);
		        
		        if (result > 0) {
		            model.addAttribute("message", "업데이트 성공!");
		        } else {
		            model.addAttribute("message", "업데이트 실패!");
		        }
		  

		    return "admin/memberQnA";
		}




}

 