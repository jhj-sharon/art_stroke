package fp.art.stroke.product.controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.fasterxml.jackson.core.type.TypeReference;

import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.service.ProductQnAService;
import fp.art.stroke.product.model.service.ProductService;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.WishList;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
@RequestMapping("/product")
@SessionAttributes({"loginMember"})
@PropertySource("classpath:spring/appkey.properties")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductQnAService qnaService;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    //${}로 프로퍼티 정보 불러오기 가능
    @Value("${payment.init}")
    private String init;
    
    @Value("${payment.restKey}")
    private String restKey;
    
    @Value("${payment.restSecret}")
    private String restSecret;
    
    private IamportClient client = new IamportClient(restKey, restSecret);
    
    private IamportClient api;
    
    public ProductController() {
 
    	this.api = new IamportClient(restKey,restSecret);
    }
    

	   
	   @GetMapping("/productMain")
	   public String productMain() {
	      return "product/productMain";
	   }
	   

	   
	   @GetMapping("/productConfirm")
	   public String productConfirm() {
		   
		   return"product/productConfirm";
	   }
	   
	   @GetMapping("/productSearch")
	   public String productSearch() {
		   
		   return"product/productSearch";
	   }
	   
	   
		//상품 메인페이지 : 상품목록 ajax로 가져오기
	    @ResponseBody
		@PostMapping("/productMain")
		public String loadproductList(){
	    	logger.info("productMain***************************************************************");
	    	logger.info("ajax 실행중");
	    	List<Product> productList = new ArrayList<>();
	    	productList = service.loadProductList();
	    	
	    	
	    	return new Gson().toJson(productList);
			
		}

		
		//wishlist등록 & 삭제
		@ResponseBody
		@PostMapping("/wishlist")
		public int addWishList(HttpSession session
							  ,@RequestParam("productId") int productId) {
			//1. 로그인 여부 확인
			 Member loginMember = (Member) session.getAttribute("loginMember");
			 logger.info("productId::"+productId);
			 

			 
			    if (loginMember != null) {
			        // 로그인한 경우
			    	int memberId = loginMember.getMemberId();
			        WishList wishList = new WishList();
			        wishList.setMemberId(memberId);
			        wishList.setProductId(productId);
			        
					//1) 중복 검사
					 int result = service.wishListCheck(wishList);
					 if(result>0) {
						 //중복 0
						 logger.info("위시리스트 중복");
						 
						 //즉, 이미 존재하는 위시리스트 클릭(꽉 찬 하트) -> 삭제
						 
						 int result3 =0;
						 
						 //2)위시리스트 삭제
						 result3 = service.wishListDelete(productId);
						 logger.info("위시리스트 삭제 result3::" + result3);
						 
						 if(result3>0) {
							 logger.info("삭제성공");
							 return 2;
							 
						 }else {
							 return 0;						 
						 }

					 }else {
						 //중복 x 
						 
					        // 3)위시리스트 추가 로직 수행
					    	int result2 =0;
					    	result2 = service.addWishList(wishList);
					    	if(result2 >0) {
					    		//위시리스트 등록 성공
					    		return 1;
					    	}else {
					    		//위시리스트 등록 실패
					    		return 0;					    		
					    	}						 
					 }
			       			        
			    } else {
			        // 로그인하지 않은 경우
			        // 로그인 페이지로 리다이렉트 또는 오류 처리 등을 수행
			        
			        return -1;
			    }			
			}
	    

		//wishList 목록 불러오기
		@ResponseBody
		@GetMapping("/wishList")
		public String loadWishlist(HttpSession session) {
			
			 Member loginMember = (Member) session.getAttribute("loginMember");
			 
			    if (loginMember == null) {
			        return "0"; // 로그인 세션이 비어있는 경우 0 반환
			    }
			 
			 int memberId = loginMember.getMemberId();
		 
	    	logger.info("ajax 실행중");
	    	List<Integer> wishList = new ArrayList<>();

	    	wishList = service.loadWishlist(memberId);
	    	
	    	return new Gson().toJson(wishList);

			
		}
		
		//제품 상세페이지 이동
	   @GetMapping("/productDetail")
	    public String getProductDetailPage(@RequestParam("product_id")int productId, 
	    								   Model model) {
		   
		   logger.info("productId::" + String.valueOf(productId));
		    //1. 쿠키등록
		   
		   	//2. productId를 사용하여 상품 정보 조회
	        Product product = service.getProductById(productId);

	        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
	        model.addAttribute("product", product);

	        return "product/productDetail"; // productDetail.jsp와 같은 뷰 페이지를 반환합니다.
	    }
	   
		   
	   //상품 상세페이지 -리뷰
	   @GetMapping("/productDetailReview")
	   public String productDetailReview(@RequestParam("product_id")int productId,
			   							 Model model) {
		   
			// productId를 사용하여 상품 정보 조회
	        Product product = service.getProductById(productId);

	        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
	        model.addAttribute("product", product);
	        
	        //추가) 리뷰 만들어서 가져가기 (map)
		   
		   return"product/productDetailReview";
	   }
	   
	   
	   //상품 상세페이지 -QnA
	   @GetMapping("/productDetailQnA")
	   public String productDetailQnA(@RequestParam(value = "cp",required=false,defaultValue = "1")int cp,
			   							@RequestParam("product_id")int productId,
			   							Map<String,Object> map,
					 					Model model) {
		   	// productId를 사용하여 상품 정보 조회
	        Product product = service.getProductById(productId);

	        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
	        model.addAttribute("product", product);
	        
	        //추가) qna 만들어서 가져가기 (map)
		   map = qnaService.selectQnaList(productId,cp);
		   model.addAttribute("map",map);
		   return"product/productDetailQnA";
	   }
	   
	   
	   //상품메인페이지 JSP Version
	   @GetMapping("/productMain2")
	   public String productMain2(@RequestParam(value = "productType", required = false) String productType,
                                  @RequestParam(value = "productCategory", required = false) String[] productCategories,
			                      HttpSession session,
			   					  Model model,
			   					  @RequestParam Map<String, Object> paramMap) {
	
		   
		   //필요한 정보 : 
		   //1) productList - 파라미터에 따라 가져오기
		   //2) wishList - 로그인된 경우에만 wishList 가져오기 + 로그인 안된 경우 안가져와도됨
		   logger.info("****************************************전달된 productType::"+ productType);
		   logger.info("****************************************전달된 productCategory::"+ Arrays.toString(productCategories));
		   logger.info("productCategories::"+ productCategories);
		   
		  // Map<String, Object> map = null;
		   Map<String, Object> map = new HashMap<>();

		   Member loginMember = (Member)session.getAttribute("loginMember");

		   
		   if (loginMember != null) {// 1. 로그인된 경우 : productList/wishList 필요

		        if ("new".equals(productType)) {
		        	
		            // 1) new 일 때의 처리
		        	   int memberId = loginMember.getMemberId();
					   String strNumber = "" + memberId;
					   logger.info("memberId::"+ strNumber);
					   
					   // -productList 가져오기
		    		   List<Product> productList = new ArrayList<>();
					   paramMap.put("productType", productType);
					   paramMap.put("productCategories", productCategories);
					   
		    		   productList = service.loadProductNew(paramMap);
		    		   
		    		   //wishList 가져오기
					   List<WishList> wishList = new ArrayList<>();
					   wishList = service.loadWishlistObj(memberId);
					   
					   //map에 담아서 보내기
					   map.put("productList", productList);
					   map.put("wishList", wishList);
					   
					   model.addAttribute("map", map);
		        } else if ("best".equals(productType)) {
		        	
		            // 2) best 일 때의 처리
		        	
					   int memberId = loginMember.getMemberId();
					   String strNumber = "" + memberId;
					   logger.info("memberId::"+ strNumber);
					   
					   // -productList 가져오기
		    		   List<Product> productList = new ArrayList<>();
					   paramMap.put("productType", productType);
					   paramMap.put("productCategories", productCategories);
					   
		    		   productList = service.loadProductBest(paramMap);
		    		   
		    		   //wishList 가져오기
					   List<WishList> wishList = new ArrayList<>();
					   wishList = service.loadWishlistObj(memberId);
					   
					   //map에 담아서 보내기
					   map.put("productList", productList);
					   map.put("wishList", wishList);
					   
					   model.addAttribute("map", map);
					   
		        } else {
			            // 3) 그외의 경우는 productType으로 filtering
			     	   int memberId = loginMember.getMemberId();
					   String strNumber = "" + memberId;
					   logger.info("memberId::"+ strNumber);
					   
					   // -productList 가져오기
		    		   List<Product> productList = new ArrayList<>();
					   paramMap.put("productType", productType);
					   paramMap.put("productCategories", productCategories);
					   
		    		   productList = service.loadProductList2(paramMap);
		    		   
		    		   //wishList 가져오기
					   List<WishList> wishList = new ArrayList<>();
					   wishList = service.loadWishlistObj(memberId);
					   
					   //map에 담아서 보내기
					   map.put("productList", productList);
					   map.put("wishList", wishList);
					   
					   model.addAttribute("map", map);
		        }

		       
		    } else { // 2. 로그인 안된 경우

		        if ("new".equals(productType)) {
		            // 1) new 일 때의 처리
		    		   List<Product> productList = new ArrayList<>();
					   paramMap.put("productType", productType);
					   paramMap.put("productCategories", productCategories);
					   
		    		   productList = service.loadProductNew(paramMap);
		    		   
				    	model.addAttribute("map", Collections.singletonMap("productList", productList));

				    	model.addAttribute("productList", productList);
		        } else if ("best".equals(productType)) {
		            // 2) best 일 때의 처리
		    		   List<Product> productList = new ArrayList<>();
		    			
					   paramMap.put("productType", productType);
					   paramMap.put("productCategories", productCategories);
					   
		    		   productList = service.loadProductBest(paramMap);
		    		   
				    	model.addAttribute("map", Collections.singletonMap("productList", productList));

				    	model.addAttribute("productList", productList);
		        } else {
		            // 3) 그외의 경우는 기존 로직 그대로 처리
		            List<Product> productList = new ArrayList<>();
		            paramMap.put("productType", productType);
		            paramMap.put("productCategories", productCategories);
		            productList = service.loadProductList2(paramMap);
		            model.addAttribute("map", Collections.singletonMap("productList", productList));
		            model.addAttribute("productList", productList);
		        }


		    }


		    return "product/productMain2";
	   }

	   
	  //wishListDetail에서 관심상품 담기
		@ResponseBody
		@PostMapping("/wishListDetail")
		public int wishListDetail(HttpSession session
							  	 ,@RequestParam("productId") int productId) {
			
			 Member loginMember = (Member) session.getAttribute("loginMember");
			 logger.info("productId::"+productId);
			 

			    	int memberId = loginMember.getMemberId();
			        WishList wishList = new WishList();
			        wishList.setMemberId(memberId);
			        wishList.setProductId(productId);
			        
					//1) 중복 검사
					 int result = service.wishListCheck(wishList);
					 
					 if(result>0) {
						 //중복 0
						 logger.info("위시리스트 중복");
						 // 중복되면 삽입 안하면 됨. 하트가 버튼이라 더이상 동작 필요 없음
						 return 1;

					 }else {
						 //중복 x 
						 
					        // 2)위시리스트 추가 로직 수행
					    	int result2 =0;
					    	result2 = service.addWishList(wishList);
					    	if(result2 >0) {
					    		//위시리스트 등록 성공
					    		return 1;
					    	}else {
					    		//위시리스트 등록 실패
					    		return 0;					    		
					    	}						 
					 }
			   
			}
		
		@PostMapping("/addCart")
		@ResponseBody
		public int addCart(HttpSession session, @RequestBody List<Cart> cartList) {
		    Member loginMember = (Member) session.getAttribute("loginMember");
		    int memberId = loginMember.getMemberId();
		    
		    boolean success = true; // 모든 cart 삽입 성공 여부를 판단하기 위한 변수
		    
		    for (Cart cart : cartList) {
		        cart.setMemberId(memberId); // memberId를 Cart 객체에 설정

		        int result = 0;
		        int result2 = 0;
		        
		        //1. 중복검사
		        result = service.checkCart(cart);
		        if(result > 0) {//중복 : 수량증가 
		            result2 = service.updateCartQuantity(cart);
		            
		            if(result2 <= 0) { // 수량증가 실패
		                success = false;
		                break; // 더 이상 처리할 필요가 없으므로 반복문 종료
		            }
		            
		        } else { //2. 중복 x
		            result2 = service.addCart(cart);
		            if(result2 <= 0) { // insert 실패
		                success = false;
		                break; // 더 이상 처리할 필요가 없으므로 반복문 종료
		            }
		        }
		    }
		    
		    if (success) {
		        return 1; // 모든 cart 삽입 성공
		    } else {
		        return 0; // 삽입 실패
		    }
		}

		
		
		//장바구니 로드하기
	   @GetMapping("/productCart")
	   public String productCart( HttpSession session, Model model) {
		   
		    Member loginMember = (Member) session.getAttribute("loginMember");
		    int memberId = loginMember.getMemberId();
		    
		    //loadCart
		    List<Cart> cartList = new ArrayList<>();
		    
		    cartList = service.loadCart(memberId);
		    model.addAttribute("cartList", cartList);
		    
		   return"product/productCart";
	   }
	   
	   //장바구니 지우기
	   @PostMapping("/deleteCart")
	   @ResponseBody
	   public int deleteCart(HttpSession session, @RequestParam("cartIds") String cartIds) {
	       Member loginMember = (Member) session.getAttribute("loginMember");
	       int memberId = loginMember.getMemberId();
	       
	       logger.info("cartIds::::::::::::::::::::::::::::::"+cartIds);
	       
	       // 문자열을 제거하고 숫자만 남기도록 처리
	       String numbersOnly = cartIds.replaceAll("[^0-9,]", "");
	       
	       // 쉼표(,)를 기준으로 문자열을 분할하여 배열로 변환
	       String[] idArray = numbersOnly.split(",");
	       
	       // List로 변환
	       List<Integer> cartIdList = new ArrayList<>();
	       for (String id : idArray) {
	           cartIdList.add(Integer.parseInt(id.trim()));
	           
	       }
	       
	       logger.info("cartIdList: " + cartIdList);
	       
	       int result = 0;
	       
	       result = service.deleteCart(cartIdList);
	       
	       if(result>0) {
	    	   return 1;
	       }else {
	    	   return 0;
	       }

	   }
	   
	   @GetMapping("/productPayment")
	   public String productPayment(HttpSession session, Model model) {
	       Member loginMember = (Member) session.getAttribute("loginMember");
	       int memberId = loginMember.getMemberId();
	       
	    // 현재 시간을 가져오기 위한 SimpleDateFormat 설정
	       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	       // 현재 시간을 문자열로 변환
	       String currentTime = dateFormat.format(new Date());

	       // 주문 번호 생성
	       String orderNumber = "as" + memberId + currentTime;
	       System.out.println("ordernumber 테스트::" + orderNumber);
	       logger.info("**************************************");
	       
	       Map<String, Object> map = new HashMap<>();
	       
	       // 1) loadCart
	       List<Cart> cartList = service.loadCart(memberId);
	       map.put("cartList", cartList);
	       
	       // 2) CouponList
	       List<Coupon> couponList = service.loadCoupon(memberId);
	       map.put("couponList", couponList);
	       
	       // 3) Addr
	       List<Addr> addrList = service.loadAddr(memberId);
	       map.put("addrList", addrList);
	       
	       // 4) 주문 번호 모델에 추가
	       map.put("orderNumber", orderNumber);

	       model.addAttribute("map", map);

	       return "product/productPayment";
	   }
	   
	   @PostMapping("/newAddr")
	   @ResponseBody
	   public int newAddr(
	       HttpSession session,
	       @RequestParam("addrName") String addrName,
	       @RequestParam("receiverName") String receiverName,
	       @RequestParam("postcode") String postcode,
	       @RequestParam("roadAddress") String roadAddress,
	       @RequestParam("detailAddress") String detailAddress,
	       @RequestParam("memberTel") String memberTel) {
	       Member loginMember = (Member) session.getAttribute("loginMember");
	       int memberId = loginMember.getMemberId();
	       


	       logger.info("새주소 등록::::::::::::::::::::::::::::::");

	       Addr addr = new Addr();
	       addr.setDeliveryName(addrName);
	       addr.setReceiverName(receiverName);
	       addr.setAddr(postcode + " " + roadAddress + " " + detailAddress);
	       addr.setAddrTel(memberTel);
	       addr.setMemberId(memberId);

	       int result = service.newAddr(addr);


	       if (result > 0) {
	           return 1;
	       } else {
	           return 0;
	       }
	   }

	 
	   
}