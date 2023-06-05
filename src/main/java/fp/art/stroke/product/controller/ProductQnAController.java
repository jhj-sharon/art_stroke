package fp.art.stroke.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/productQnA")
@SessionAttributes({"loginMember"})
public class ProductQnAController {
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);

}
