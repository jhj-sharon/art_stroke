package fp.art.stroke.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.controller.ProductController;
import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Service
public class ProductServiceImpl implements ProductService {
	
	

	@Autowired
	private ProductDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);


	//상품 목록 가져오기
	@Override
	public List<Product> loadProductList() {
		
		return dao.loadProductList();
	}

	//상품 상세 페이지
	@Override
	public Product loadProductDetail(int productId) {
		
		return dao.loadProductDetail(productId);
	}


	//위시리스트 추가
	@Override
	public int addWishList(WishList wishList) {
	
		return dao.addWishList(wishList);
	}
	


	//위시리스트 중복검사
	@Override
	public int wishListCheck(int productId) {
		
		return dao.wishListCheck(productId);
	}

	//위시리스트 로드
	@Override
	public List<Integer> loadWishlist(int memberId) {
		
		return dao.loadWishlist(memberId);
	}
	
	//위시리스트 삭제
	@Override
	public int wishListDelete(int productId) {
		return dao.wishListDelete(productId);
	}
	
	//상세페이지 이동
	@Override
	public Product getProductById(int productId) {
		
		return dao.getProductById(productId);
	}
	
	//JSTL 상품 목록 만들기
	@Override
	public Map<String, Object> loadProductMain(Map<String, Object> paramMap) {
		
		//1) 전체 상품 목록 조회
		List<Product> productList = new ArrayList<>();
		productList = dao.loadProductList();
		
		//2) wishList 가져오기
		int memberId = (int) paramMap.get("memberId");
		List<WishList> wishList = new ArrayList<>();
    	
    	wishList = dao.loadWishlistObj(memberId);
    	
    	//3) map 만들어 담기
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("productList", productList);
    	map.put("wishList", wishList);
		
		return map;
	}
	
	
	
	
}
