package fp.art.stroke.product.model.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import fp.art.stroke.product.model.vo.Order;
import fp.art.stroke.product.model.vo.OrderDetail;
import fp.art.stroke.product.model.vo.Payment;

public interface OrderService {

	//결제서버에서 토큰 받아오기
	String getToken() throws Exception;
	
	//토큰을 활용해 결제 정보를 가져오기 
	String paymentInfo(String imp_uid, String token) throws IOException, ParseException, org.json.simple.parser.ParseException;

	//주문 완료된 금액과 실제 계산되어야 할 금액이 다를 경우 결제 취소
	void payMentCancle(String token, String imp_uid, String amount, String string);
	
	//주문정보 서버에 저장
	int insert_pay(Order order);
	
	//주문 디테일 저장 리스트형식
	int insertOrderDetail(List<OrderDetail> orderDetailList);
	
	//결제 테이블 저장
	int insertPayment(Payment payment);
	
	//사용한 쿠폰 삭제
	int deleteCoupon(int couponId);

	//구매한 상품 장바구니에서 삭제
	int payDeleteCart(String[] productIdArray, int memberId);
	
	//구매한 상품 판매량 증가
	int increaseSales(OrderDetail[] orderDetails1);
	
	//장바구니 삭제 전 중복조회
	int checkProductExistInCart(String productId, int memberId);

	


}
