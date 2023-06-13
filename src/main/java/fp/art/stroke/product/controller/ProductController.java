package fp.art.stroke.product.controller;

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

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.service.ProductService;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/product")
@SessionAttributes({"loginMember"})
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	   
	   @GetMapping("/productMain")
	   public String productMain() {
	      return "product/productMain";
	   }

	   
	   
	   @GetMapping("/productCart")
	   public String productCart() {
		   
		   return"product/productCart";
	   }
	   
	   @GetMapping("/productPayment")
	   public String productPayment() {
		   
		   return"product/productPayment";
	   }
	   
	   @GetMapping("/productConfirm")
	   public String productConfirm() {
		   
		   return"product/productConfirm";
	   }
	   
	   @GetMapping("/productSearch")
	   public String productSearch() {
		   
		   return"product/productSearch";
	   }
	   
	   
		//상품 메인페이지 : 상품목록
	    @ResponseBody
		@PostMapping("/productMain")
		public String loadproductList(){
	    	
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
					 int result = service.wishListCheck(productId);
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
		// productId를 사용하여 상품 정보 조회
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
	   public String productDetailQnA(@RequestParam("product_id")int productId,
					 					Model model) {
		   	// productId를 사용하여 상품 정보 조회
	        Product product = service.getProductById(productId);

	        // 조회된 상품 정보를 모델에 추가하여 뷰로 전달
	        model.addAttribute("product", product);
	        
	        //추가) qna 만들어서 가져가기 (map)
		   
		   return"product/productDetailQnA";
	   }
	   
	   
	   //상품메인페이지 JSP Version
	   @GetMapping("/productMain2")
	   public String productMain2(@RequestParam(value = "productType", required = false) String productType,
			   					  @RequestParam(value = "productCategory", required = false) String[] productCategorys,
			   					  HttpSession session,
			   					  Model model,
			   					  @RequestParam Map<String, Object> paramMap) {
		   
		   //필요한 정보 : 
		   //1) productList - 파라미터에 따라 가져오기
		   //2) wishList - 로그인된 경우에만 wishList 가져오기 + 로그인 안된 경우 안가져와도됨
		   // -> wishList는 ajax로 구현
		   logger.info("main2");
		   
		   Map<String, Object> map = null;
		   
		   //1 로그인 여부
		   Member loginMember = (Member)session.getAttribute("loginMember");
		   
		   if(loginMember != null) { // 로그인이 된 경우 : productList, wishList 다 들고와야함
			   
			   int memberId = loginMember.getMemberId();
			   
			   String strNumber = "" + memberId;
			   logger.info("memberId::"+ strNumber);
			   logger.info("productType::"+ productType);
			   logger.info("productCategorys::"+ productCategorys);
			   

			   
			   paramMap.put("productType", productType);
			   paramMap.put("productCategorys", productCategorys);
			   paramMap.put("memberId", memberId);
			   
			   map = service.loadProductMain(paramMap);
			   
			   model.addAttribute("map", map);
			   
		   }else { //로그인이 안된 경우 : productList만 보내기
		    	List<Product> productList = new ArrayList<>();
		    	productList = service.loadProductList();
		    	
		    	model.addAttribute("productList", productList);
		   }
		   
		   
	      return "product/productMain2";
	   }

}
