package fp.art.stroke.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import fp.art.stroke.product.model.service.MainpageService;
import fp.art.stroke.product.model.vo.Product;

@Controller
@RequestMapping("/mainpage")
@SessionAttributes({"loginMember"})
public class MainpageController {
	

	@Autowired
	private MainpageService service;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	
	
	/** 베스트 상품 불러오기 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/mainBestProduct")
	public List<Product> selectMainBestProdcut(@RequestParam("productName") String productName) {
		
		List<Product> mainBestProdcut = service.selectMainBestProdcut(productName);
		
		return mainBestProdcut;
	}

}