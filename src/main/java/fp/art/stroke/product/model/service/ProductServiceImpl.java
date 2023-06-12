package fp.art.stroke.product.model.service;

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
	
	
	
}
