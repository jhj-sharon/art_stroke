package fp.art.stroke.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import fp.art.stroke.admin.model.service.AdminOrderService;

@Controller
@RequestMapping("/admin/order")
@SessionAttributes({"loginMember"})
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
		
	 	
		
		// 관리자 - 주문취소/환불
		@GetMapping("refund")
		public String orderRefund() {
			return "admin/orderRefund";
		}
		
		
		@GetMapping("cancel")
		public String selectCancelOrder(@RequestParam(value="cp", required = false , defaultValue = "1") int cp,
				Model model,
				@RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;

			 
			if (paramMap.get("key") == null) {
			    map = service.selectCancelOrder(cp);
			} else {
			    paramMap.put("cp", cp);
			  
			    map = service.searchCancelOrder(paramMap);
			}
		
			logger.info("주문취소 map : " + map);
			model.addAttribute("map", map);
			
			
			
			return "admin/cancelOrder";
		}
		
			
			
		
		
		// 관리자 - 주문목록
		@GetMapping("{adminCode}")
		public String orderList(@PathVariable("adminCode") int adminCode,
							@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
							 @RequestParam(value = "orderDate", required = false) String orderDate,
							Model model,
							@RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;

			 
			if (paramMap.get("key") == null) {
			    map = service.selectOrderList(cp, adminCode);
			} else {
			    paramMap.put("cp", cp);
			    paramMap.put("adminCode", adminCode);
			    
			    // orderDate를 정수로 변환하여 paramMap에 저장
			    if (orderDate != null) {
			        int parsedOrderDate = Integer.parseInt(orderDate);
			        paramMap.put("orderDate", parsedOrderDate);
			    }
			    
			    map = service.searchOrderList(paramMap);
			}
		
			logger.info("관리자주문" + map);
			model.addAttribute("map", map);
			
			
			
			return "admin/orderList";
		}
		
		 
		@ResponseBody
		@PostMapping("selectAdminDateList")
		public String selectAdminDateList(
		    @RequestParam(value="startDate") String startDate,
		    @RequestParam(value = "endDate") String endDate,
		    Model model) {

		    logger.info("startDate ??? " + startDate);
		    logger.info("endDate ??? " + endDate);

		    // paramMap에 startDate와 endDate 설정
		    Map<String, Object> paramMap = new HashMap<>();
		    paramMap.put("startDate", startDate);
		    paramMap.put("endDate", endDate);

		    List<String> list = service.selectAdminDateList(paramMap);

		    logger.info("Admin Date List " + list);
		   
			 
		    
		    return new Gson().toJson(list);
		}


}
