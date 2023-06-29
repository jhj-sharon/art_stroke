package fp.art.stroke.product.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Order;
import fp.art.stroke.product.model.vo.OrderDetail;
import fp.art.stroke.product.model.vo.Payment;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ProductDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
    
    //토큰 받아오는 함수
	@Override
	public String getToken() throws Exception {

		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/users/getToken");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		JsonObject json = new JsonObject();


		json.addProperty("imp_key", "8865856345760661");
		json.addProperty("imp_secret", "dEiwyjMHgETHXsWDIWNTXRZY3lpltPY0lWgiW5gMAFPA0rqdTNJbLbLk1fwLIYUA0VYSbn2CuwLVtdXu");
		




		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		Gson gson = new Gson();

		String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();


		String token = gson.fromJson(response, Map.class).get("access_token").toString();
		
		System.out.println("token:::::::::::::::::::::::::::::::::::::::::::::::::"+ token);

		br.close();
		conn.disconnect();

		return token;
	}

	
    //결제 정보 불러오기
	@Override
	public String paymentInfo(String imp_uid, String access_token) throws IOException, ParseException, org.json.simple.parser.ParseException {
		HttpsURLConnection conn = null;

		URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", access_token);
		conn.setDoOutput(true);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		JSONParser parser = new JSONParser();

		JSONObject p = (JSONObject) parser.parse(br.readLine());
		
		String response = p.get("response").toString();
		
		p = (JSONObject) parser.parse(response);
		
		String amount = p.get("amount").toString();
		return amount;
	}

	//결제 완료된 금액과 실제 계산되어야 할 금액이 다를 경우 결제 취소
	@Override
	public void payMentCancle(String token, String imp_uid, String amount, String string) {
		// TODO Auto-generated method stub
		
	}

	//order_tbl 삽입 서비스
	@Override
	public int insert_pay(Order order) {
		// TODO Auto-generated method stub
		return dao.insertOrder(order);
	}
	//order-detail 삽입 서비스
	@Override
	public int insertOrderDetail(List<OrderDetail> orderDetailList) {
	    int result = 0;
	    for (OrderDetail orderDetail : orderDetailList) {
	        result += dao.insertOrderDetail(orderDetail);
	    }
	    return result;
	}

	//결제 정보 저장
	@Override
	public int insertPayment(Payment payment) {
		
		return dao.insertPayment(payment);
	}

	//사용한 쿠폰 삭제
	@Override
	public int deleteCoupon(int couponId) {
		
		return dao.deleteCoupon(couponId);
	}

	//결제한 상품 장바구니에서 삭제
	@Override
	public int payDeleteCart(String[] productIdArray, int memberId) {
		int result =0;
		System.out.println("결제한 상품 장바구니에서 삭제");
	    Map<String, Object> params = new HashMap<>();
               for (String productId : productIdArray) {
                	logger.info("productId 서비스임플:::"+productId);
	    params.put("productId", productId );
	    params.put("memberId", memberId);
	    result +=dao.payDeleteCart(params);
                }
                return result;
	}
	
	//구매한 상품 판매량 증가
	@Override
	public int increaseSales(OrderDetail[] orderDetails1) {
	    int result =0;
	    for(OrderDetail orderDetail : orderDetails1) {
	    	
	    	result += dao.increaseSales(orderDetail);
	    }
	    return result;
	}


	
	

	
	
	
}
