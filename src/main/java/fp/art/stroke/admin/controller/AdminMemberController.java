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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import fp.art.stroke.admin.model.service.AdminMemberService;
import fp.art.stroke.member.model.vo.Member;

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
		public String memberReport(	@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				Model model, Member memberId,
				@RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;
			
			if(paramMap.get("key") == null) {
				map = service.selectMemberReport(cp);
			
			} else {
				paramMap.put("cp", cp);
				paramMap.put("memberId", memberId);
				map = service.searchMemberReport(paramMap);
			}
			
			logger.info("멤버 신고 CONTROLLER" + map);
			model.addAttribute("map", map);
			
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
		
	 
}

 