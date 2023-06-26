package fp.art.stroke.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.fasterxml.jackson.databind.ObjectMapper;
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
import fp.art.stroke.product.model.vo.OrderDetail;

@Controller
@RequestMapping("/order")
@SessionAttributes({"loginMember"})
public class OrderController {
	
	@Autowired
	private OrderService payService;
	
	@Autowired
	private ProductService service;
	
	
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
    
    private IamportClient api;
    
    public OrderController() {

    	 
    	this.api = new IamportClient("aa", "bb");

    	
    	
    }
    
    //결제를 위한 토큰 발급 메서드
    @ResponseBody
    @PostMapping("/verify_iamport")
    public IamportResponse<Payment> verifyIamportPOST(@RequestParam(value = "imp_uid") String imp_uid
    		) throws IamportResponseException, IOException {
        logger.info("imp_uid*******************************::" + imp_uid);


        return api.paymentByImpUid(imp_uid);
    }
    
    // 문자열을 OrderDetail 객체 배열로 변환하는 메서드
    private OrderDetail[] parseOrderDetails(String orderDetailsString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(orderDetailsString, OrderDetail[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //서버에서 최종 결제금액을 계산하는 메서드
    public int calculateServerTotal(int memberId, int couponId) {
    	int serverTotal =0;
    	int productSum = 0;
    	int delivertFee = 0;
    	int discountValue = 0;
    	
    	//1. memberId가 일치하는 카트가져오기
	    List<Cart> cartList = new ArrayList<>();
	    cartList = service.loadCart(memberId);
	    
	    // 2. 카트 상품 가격을 모두 더하기
	    for (Cart cart : cartList) {
	    	productSum += cart.getProductPrice();
	    }
	    
	    // 3. 5만원 이하인 경우 배송비 3000원 추가
	    if (productSum < 50000) {
	    	delivertFee = 3000;
	    	 logger.info("delivertFee"+String.valueOf(delivertFee));
	    }
    	
	    // 4. 쿠폰 정보 가져오기
	    if (couponId != 0) { // couponId가 0이 아닌 경우에만 쿠폰 정보 조회
	        Coupon coupon = service.getCouponById(couponId);
	      
	        if (coupon != null) {
	            // 할인 금액 계산
	            if (coupon.getDiscountAmount().startsWith("0")) {
	                double discountPercentage = Double.parseDouble(coupon.getDiscountAmount());
	                discountValue = (int) (productSum * discountPercentage);
	            } else {
	                discountValue = Integer.parseInt(coupon.getDiscountAmount());
	            }

	            // 총 결제 금액에서 할인 금액 차감
	            logger.info("discountValue: " + String.valueOf(discountValue));
	        }
	    }
	    
	    // 쿠폰 정보를 활용하지 않는 경우는 productSum 값을 그대로 사용할 수 있습니다.
	 
	     serverTotal = productSum + delivertFee - discountValue;
	    logger.info("serverTotal: " + String.valueOf(serverTotal));
        return serverTotal;
    }
   
    
    //결제 메서드
    @ResponseBody
    @PostMapping("/complete")
    public int paymentComplete(@RequestBody Order order, 
                               HttpSession session) throws Exception {
    	 Member loginMember = (Member) session.getAttribute("loginMember");
	       int memberId = loginMember.getMemberId();
	       order.setMemberId(memberId);

        // Order orderId 형식 변경
	    String orderId = order.getOrderId();
	    String modifiedOrderId = orderId.substring(orderId.indexOf("a"));
	    order.setOrderId(modifiedOrderId);
	    
	    System.out.println("order.getOrderId(): " + order.getOrderId());
        System.out.println("order.getTotalPrice(): " + order.getTotalPrice());
        System.out.println("order.getOrderDate(): " + order.getOrderDate());
        System.out.println("order.getAddrId(): " + order.getAddrId());
        System.out.println("order.getShippingFee(): " + order.getShippingFee());
        System.out.println("order.getImp_uid(): " + order.getImp_uid());
        System.out.println("order.getShippingMemo(): " + order.getShippingMemo());
        System.out.println("order.getPaymethod(): " + order.getPaymethod());
        System.out.println("order.getOrderDetails(): " + order.getOrderDetails());
        System.out.println("order.getMemberId(): " + order.getMemberId());
        System.out.println("order.getQuantity(): " + order.getQuantity());
        System.out.println("order.getCouponId(): " + order.getCouponId());
        
        // orderDetail객체배열로 저장
        String orderDetailsString = order.getOrderDetails();
        OrderDetail[] orderDetails = parseOrderDetails(orderDetailsString);
        
        System.out.println(orderDetails);
        
        //1. 토큰값 가져오기
        String token = payService.getToken();
        System.out.println("token::::::::::::::" + token);

        //2. PG사 결제 금액 가져오기
        String amount = payService.paymentInfo(order.getImp_uid(), token);
        System.out.println("amount::::::::::::::" + amount);

        
        //3. 서버에서 최종 금액 :보안상의 이유로 PG 결제 금액은 서버에서 계산된 금액과 일치해야한다. 
        int serverTotal = calculateServerTotal(memberId, order.getCouponId());
        System.out.println("serverTotal::::::::::::::" + serverTotal);
              
        int res = 1;
        int result = 1;
        if (serverTotal != Long.parseLong(amount)) {
            res = 0;
            System.out.println("return rest serverTotal != Long.parseLong(amount)::::::::::::::::::::" + res);

            // 결제 취소
            return res;
        }
        result = payService.insert_pay(order);
        
        //OrderDetail 삽입하기
        if(result != 0) { //Order 삽입 성공 -> OrderDeatil 삽입
        	
        	int result2 = 0;
        	
            List<OrderDetail> orderDetailList = Arrays.asList(orderDetails);
            

            for (OrderDetail orderDetail : orderDetailList) {
                System.out.println("Product ID: " + orderDetail.getProductId());
                System.out.println("OptionId: " + orderDetail.getOptionInfo());
                System.out.println("Quantity: " + orderDetail.getQuantity());
                orderDetail.setMemberId(memberId);
                orderDetail.setOrderId(modifiedOrderId);
                
                System.out.println("-----------");
            }

            
            result2 = payService.insertOrderDetail(orderDetailList);
        	if(result2 == orderDetailList.size()) {
        		res =1;
        	}else {
        		res=0;
        	}
        	
        	
        }else { // Order 삽입 실패 -> OrderDeatil 삽입 안함
        	 res = 0;
        }

        System.out.println("return rest::::::::::::::::::::" + res);
        return res;
    }

   
    @ResponseBody
    @PostMapping("/pay_info")
    public int insertPayInfo(@RequestParam("orderId") String orderId,
                             @RequestParam("paymentDate") String paymentDate,
                             @RequestParam("paymethod") String paymethod,
                             @RequestParam("totalPrice") int totalPrice,
                             @RequestParam("couponId") int couponId, 
                             @RequestParam("productIdList") String productIdList, 
                             HttpSession session) {

        Member loginMember = (Member) session.getAttribute("loginMember");
        int memberId = loginMember.getMemberId();
        String memberName = loginMember.getMemberName();

        // 1. 결제테이블에 정보저장
        fp.art.stroke.product.model.vo.Payment payment = new fp.art.stroke.product.model.vo.Payment();
        payment.setOrderId(orderId);
        payment.setPaymentDate(paymentDate);
        payment.setTotalPrice(totalPrice);
        payment.setPaymethod(paymethod);
        payment.setMemberId(memberId);
        payment.setDepositName(memberName);

        int result = payService.insertPayment(payment);

        if (result > 0) {
            // 결제 테이블 삽입 성공

            // 2. 쿠폰 삭제
            int result2 = payService.deleteCoupon(couponId);

            if (result2 > 0) {
                // 3. 결제한 상품 장바구니에서 삭제
                String[] productIdArray = productIdList.split(",");

                int result3 = payService.payDeleteCart(productIdArray, memberId);

                if (result3 > 0) {
                    // 결제삽입/쿠폰삭제/카트삭제 성공
                    // 4. 상품 테이블 판매량 증가

                    int result4 = payService.increaseSales(productIdArray);

                    if (result4 > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            // 결제 테이블 삽입 실패
            return 0;
        }
    }

    
    
    
    
}//END OF CLASS
