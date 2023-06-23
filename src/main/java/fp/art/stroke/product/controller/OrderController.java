package fp.art.stroke.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.service.OrderService;
import fp.art.stroke.product.model.service.ProductService;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Order;

@Controller
@RequestMapping("/order")
@SessionAttributes({"loginMember"})
public class OrderController {
	
	@Autowired
	private OrderService payService;
	
	@Autowired
	private ProductService service;
	
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	 //${}로 프로퍼티 정보 불러오기 가능
    @Value("${payment.init}")
    private String init;
    
    @Value("${payment.restKey}")
    private String restKey;
    
    @Value("${payment.restSecret}")
    private String restSecret;
    
    private IamportClient api;
    public OrderController() {
    	 
    	this.api = new IamportClient("8865856345760661", "dEiwyjMHgETHXsWDIWNTXRZY3lpltPY0lWgiW5gMAFPA0rqdTNJbLbLk1fwLIYUA0VYSbn2CuwLVtdXu");
    	
    	
    }
    
    //결제를 위한 토큰 발급 메서드
    @ResponseBody
    @PostMapping("/verify_iamport")
    public IamportResponse<Payment> verifyIamportPOST(@RequestParam(value = "imp_uid") String imp_uid
    		) throws IamportResponseException, IOException {
        logger.info("imp_uid*******************************::" + imp_uid);
        System.out.println("restKey*******************************" + restKey);
        return api.paymentByImpUid(imp_uid);
    }
    

    @ResponseBody
    @PostMapping("/complete")
    public int paymentComplete(@RequestBody Order order, 
					           @RequestParam("orderDetails") String orderDetails,
    							HttpSession session) throws Exception {
    	
    		System.out.println("orderDetails:::::::::::::"+orderDetails);
    	    
    	    String token = payService.getToken();
    	    System.out.println("token::::::::::::::"+token);
    	    
    	    // 결제 완료된 금액
    	    String amount = payService.paymentInfo(order.getImp_uid(), token);
    	    System.out.println("amount::::::::::::::"+amount);
    	    
    	    int res = 1;
    	    int webTotal = order.getTotalPrice();
    	    if (webTotal != Long.parseLong(amount)) {
    			res = 0;
    			System.out.println("return rest webTotal != Long.parseLong(amount)::::::::::::::::::::"+ res);
    			
    			// 결제 취소
    			return res;
    		}
    		res = payService.insert_pay(order);
    		
    		System.out.println("return rest::::::::::::::::::::"+ res);
    		return res;
    	 
    }
//    
//    
//    @PostMapping("/complete")
//    @ResponseBody
//    public int completeOrder(@RequestBody Order order) {
//      // 주문 정보 처리 로직을 구현합니다.
//      // order 객체를 활용하여 필요한 작업을 수행합니다.
//
//      // 예시: 주문 정보를 저장하고 저장된 결과를 반환합니다.
//      boolean orderSaved = saveOrder(order);
//
//      if (orderSaved) {
//        return new ResponseEntity<>("주문 정보 저장 성공", HttpStatus.OK);
//      } else {
//        return new ResponseEntity<>("주문 정보 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//    }
//
//    // 예시: 주문 정보를 저장하는 메서드
//    private boolean saveOrder(Order order) {
//      // 주문 정보를 저장하는 로직을 구현합니다.
//      // order 객체의 필드를 활용하여 데이터베이스에 주문 정보를 저장합니다.
//      // 저장이 성공하면 true를 반환하고, 실패하면 false를 반환합니다.
//
//      // orderDetail 생성 및 저장
//      List<OrderDetail> orderDetails = new ArrayList<>();
//      for (PaymentItem paymentItem : order.getPaymentItems()) {
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOption(paymentItem.getOption());
//        orderDetail.setOrderId(order.getOrderId());
//        
//        orderDetails.add(orderDetail);
//      }
//      
//      // order, orderDetail을 데이터베이스에 저장하는 로직을 구현합니다.
//
//      return 0; // 예시로 항상 성공으로 가정합니다.
//    }
// }
}
