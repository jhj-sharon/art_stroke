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

import fp.art.stroke.admin.model.service.AdminStockService;

@Controller
@RequestMapping("/admin/stock")
@SessionAttributes({"loginMember"})
public class AdminStockController {
	
	@Autowired
	private AdminStockService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminStockController.class);
	 
	// 관리자 - 재고목록
//	@GetMapping("{adminCode}")
//	public String stockList(@PathVariable("adminCode") int adminCode,
//						@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
//						Model model,
//						@RequestParam Map<String, Object> paramMap) {
//		
//		Map<String, Object> map = null;
//
//		map = service.selectStockList(cp, adminCode);
//		if(paramMap.get("key") == null) {  
//		 
//			
//		}else {  
//			 
//			
//			paramMap.put("cp", cp);   
//			paramMap.put("adminCode", adminCode);
//			
//			map = service.searchStockList(paramMap);
//			
//		 
//			logger.debug("관리자멤버" + map);
//		} 
//		
//		model.addAttribute("map", map);
//		
//		
//		
//		return "admin/stockList";
//	}
	 
}
