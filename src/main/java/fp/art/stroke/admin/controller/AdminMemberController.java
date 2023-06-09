package fp.art.stroke.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.admin.model.service.AdminMemberService;
import fp.art.stroke.member.model.vo.Member;

@Controller
@RequestMapping("/admin/member")
@SessionAttributes({"loginMember"})
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminMemberController.class);

	 
		// 관리자 - 멤버문의
		@GetMapping("qna")
		public String memberQnA() {
			return "admin/memberQnA";
		}
		
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
		
		
		
		
		
		
		
}
