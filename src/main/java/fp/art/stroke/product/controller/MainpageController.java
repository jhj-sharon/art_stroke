package fp.art.stroke.product.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.service.MainpageService;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Controller
//@RequestMapping("${contextPath}")
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
	
	
	/** 키매, 하이퍼펜션 상품 불러오기 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/mainArtistProdcut")
	public List<Product> selectMainArtistProdcut() {
		
		List<Product> mainArtistProdcut = service.selectMainArtistProdcut();
		
		
		return mainArtistProdcut;
	}

	
	
	/** 로그인한 회원의 위시리스트에 있는 productId 얻어오기
	 * @return
	 */
	@ResponseBody
	@GetMapping("/mainWishProdcut")
	public List<WishList> selectWishProductId(HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();	
		
		List<WishList> mainWishProdcut = service.selectWishProductId(memberId);
		
		
		return mainWishProdcut;
	}
}
