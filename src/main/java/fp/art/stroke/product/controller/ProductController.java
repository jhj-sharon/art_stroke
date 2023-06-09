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

import fp.art.stroke.product.model.service.ProductService;
import fp.art.stroke.product.model.vo.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	   @GetMapping("/productDetail")
	   public String prouctDetail() {
	      return "product/productDetail";
	   }
	   
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
			
			
		     // 상품 목록을 보여주는 뷰의 이름을 반환
			
		}
		
		
	   





}
