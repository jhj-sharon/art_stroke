 
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

import fp.art.stroke.admin.model.service.AdminBoardService;

@Controller
@RequestMapping("/admin/board")
@SessionAttributes({"loginMember"})
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminBoardController.class);

	@GetMapping("{adminCode}")
	public String boardList(@PathVariable("adminCode") int adminCode,
						@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
						Model model,
						@RequestParam Map<String, Object> paramMap) {
		
 
		Map<String, Object> map = null;
 
		if(paramMap.get("key") == null) {  

			map = service.selectBoardList(cp, adminCode);
			
		}else {   
			
			paramMap.put("cp", cp);   
			paramMap.put("adminCode", adminCode);
			
			map = service.searchBoardList(paramMap);
			 
		} 
		
		model.addAttribute("map", map); 
		logger.info("ADMIN MANAGER BOARD CONTROLLER" + map);
	 
		return "admin/boardList"; 
	}
	 
	
	@ResponseBody
	@PostMapping("deleteAdminBoard")
	public String deleteAdminBoard(@RequestParam(value="boardChk", required=false) List<Integer> boardChk ) {
		logger.info("게시판삭제" + boardChk);
		
		int result = 0;
		
		if(boardChk != null) {
			result = service.deleteAdminBoard(boardChk);
			
			logger.info("게시판삭제222" + boardChk);
		}
		
		return new Gson().toJson(result);
		
	}
	
 
	
	
} 