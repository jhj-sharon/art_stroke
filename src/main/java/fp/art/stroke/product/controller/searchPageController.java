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

import fp.art.stroke.product.model.service.SearchPageService;
import fp.art.stroke.product.model.vo.Product;


@Controller
@RequestMapping("/product")
public class searchPageController {
	
	
	@Autowired
	private SearchPageService service;
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	// 검색페이지 연결 
	@GetMapping("/searchPage")
	public String myPageMain() {
		return "/product/searchPage";
	}
	
	// 키워드 검색 
	@ResponseBody
	@GetMapping("/searchKeyword")
	public List<Product> searchKeyword(@RequestParam("productName") String productName,
									   @RequestParam("productType") String productType,
									   @RequestParam("productArtist") String productArtist,
									   @RequestParam("productCategory") String productCategory){
		
		
		
		return null;
		
	}

}
