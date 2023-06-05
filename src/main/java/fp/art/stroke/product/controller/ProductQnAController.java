package fp.art.stroke.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.product.model.service.ProductQnAService;
import fp.art.stroke.product.model.vo.ProductQnA;

@Controller
@RequestMapping("/productQnA")
@SessionAttributes({"loginMember"})
public class ProductQnAController {
	
	@Autowired
	private ProductQnAService service;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	
	//qna글쓰기
	
	public void wirteProductQnA(ProductQnA qna) {
		
		
	}
}
