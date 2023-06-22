package fp.art.stroke.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class searchPageController {
	
	
//	@Autowired
//	private MainpageService service;
//	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	// 검색페이지 연결 
	@GetMapping("/searchPage")
	public String myPageMain() {
		return "/product/searchPage";
	}

}
