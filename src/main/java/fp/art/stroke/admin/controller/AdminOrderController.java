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
		
		 
  
		
		
		
}
