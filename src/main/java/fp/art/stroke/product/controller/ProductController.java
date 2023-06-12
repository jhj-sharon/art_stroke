package fp.art.stroke.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

import java.util.ArrayList;
import java.util.List;
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
	

//	   @GetMapping("/productMain")
//	   public String productMain() {
//	      return "product/productMain";
//	   }

	   
	   @GetMapping("/productMain")
	   public String productMain() {
	      return "product/productMain";
	   }

	   
	   @GetMapping("/productDetailReview")
	   public String productDetailReview() {
		   
		   return"product/productDetailReview";
	   }
	   
	   @GetMapping("/productDetailQnA")
	   public String productDetailQnA() {
		   
		   return"product/productDetailQnA";
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
		
		
	   //loadProductDetail
	   // @ResponseBody
		@GetMapping("/productDetail")
	    public String loadProductDetail(@RequestParam("product_id") int productId, 
	    								Model model) {
	    	logger.info("productID::"+productId);
	    	
	    	return "product/productDetail";
	    }
		
		//wishlist등록
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
						 return 2;
					 }else {
						 //중복 x
						 
					        // 위시리스트 추가 로직 수행
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
			 
			 int memberId = loginMember.getMemberId();
		 
	    	logger.info("ajax 실행중");
	    	List<Integer> wishList = new ArrayList<>();

	    	wishList = service.loadWishlist(memberId);
	    	
	    	return new Gson().toJson(wishList);

			
		}



}
