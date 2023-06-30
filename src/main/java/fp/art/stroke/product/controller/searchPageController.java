package fp.art.stroke.product.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.service.SearchPageService;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.PopularKeyword;


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
		
		
		List<Product> searhKeywordProduct = service.searchKeyword(productName, productType, productArtist, productCategory);

		return searhKeywordProduct;
		
	}
	
	// 카테고리 검색 
	@ResponseBody
	@GetMapping("/searchCategory")
	public List<Product> searchCategory(@RequestParam("productCategory") String productCategory){
		
		
		List<Product> searhCategoryProduct = service.searchCategory(productCategory);

		return searhCategoryProduct;
		
	}
	
	
	// 키워드 하트 검색 
	@ResponseBody
	@GetMapping("/searchKeywordHeart")
	public List<Product> searchKeywordHeart(@RequestParam("productName") String productName,
									   @RequestParam("productType") String productType,
									   @RequestParam("productArtist") String productArtist,
									   @RequestParam("productCategory") String productCategory,
									   HttpSession session){
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();		
		
		List<Product> searhKeywordHeart = service.searchKeywordHeart(memberId, productName, productType, productArtist, productCategory);

		return searhKeywordHeart;
		
	}
	
	// 카테고리 하트 검색 
	@ResponseBody
	@GetMapping("/searchCategoryHeart")
	public List<Product> searchCategoryHeart(@RequestParam("productCategory") String productCategory,
										     HttpSession session){
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();		
		
		List<Product> searhCategoryHeart = service.searchCategoryHeart(memberId, productCategory);

		return searhCategoryHeart;
		
	}
	
	// 위시리스트 추가 
	@ResponseBody
	@PostMapping("/addSearchWishList")
	public int AddSearchWishList(@RequestParam("productId") int productId,
							   HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();	
		
		int result = service.addSearchWishList(memberId, productId);
		
		return result;
	}
	
	
	// 위시리스트 삭제 
	@ResponseBody
	@PostMapping("/deleteSearchWishList")
	public int DeleteSearchWishList(@RequestParam("productId") int productId,
							   HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();	
		
		int result = service.deleteSearchWishList(memberId, productId);
		
		return result;
	}
	
	
	
	
	// 인기검색어 삽입 
	@ResponseBody
	@PostMapping("/insertPopularKeyword")
	public int InsertPopularKeyword(@RequestParam("popularKeyword") String popularKeyword) {
		
		int result = service.countPopularKeyword(popularKeyword);
		
		return result;
	}
	
	
	// 인기검색어 헤더에 띄우기 
	@ResponseBody
	@GetMapping("/getPopularKeyword")
	public List<PopularKeyword> GetPopularKeyword(){
		
		List<PopularKeyword> popularKeywords = service.getPopularKeyword();

		return popularKeywords;
		
	}

}
