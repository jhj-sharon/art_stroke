package fp.art.stroke.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.product.model.service.ProductService;

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
	   @GetMapping("/productDetail")
	   public String prouctDetail() {
	      return "product/productDetail";
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
	   





}
