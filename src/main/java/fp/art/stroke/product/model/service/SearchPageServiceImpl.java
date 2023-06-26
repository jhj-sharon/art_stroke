package fp.art.stroke.product.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.model.dao.SearchPageDAO;
import fp.art.stroke.product.model.vo.PopularKeyword;
import fp.art.stroke.product.model.vo.Product;



@Service
public class SearchPageServiceImpl implements SearchPageService{
	
	@Autowired
	private SearchPageDAO dao;
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	// 키워드 검색 
	@Override
	public List<Product> searchKeyword(String productName, String productType, String productArtist,
			String productCategory) {
		
		return dao.searchKeyword(productName, productType, productArtist, productCategory);
	}


	// 카테고리 검색 
	@Override
	public List<Product> searchCategory(String productCategory) {
		
		return dao.searchCategory(productCategory);
	}


	// 키워드 하트 검색 
	@Override
	public List<Product> searchKeywordHeart(int memberId, String productName, String productType, String productArtist,
			String productCategory) {
		
		return dao.searchKeywordHeart(memberId, productName, productType, productArtist, productCategory);
	}

	// 카테고리 하트 검색 
	@Override
	public List<Product> searchCategoryHeart(int memberId, String productCategory) {
		
		return dao.searchCategoryHeart(memberId,productCategory);
	}


	// 위시리스트 추가 
	@Override
	public int addSearchWishList(int memberId, int productId) {
		
		return dao.addSearchWishList(memberId, productId);
	}


	// 위시리스트 삭제 
	@Override
	public int deleteSearchWishList(int memberId, int productId) {
		
		return dao.deleteSearchWishList(memberId, productId);
	}
	
	
	

	// 인기검색어 확인 
	@Override
	public int countPopularKeyword(String popularKeyword) {
		
		return dao.countPopularKeyword(popularKeyword);
	}

	// 인기검색어 헤더에 띄우기 
	@Override
	public List<PopularKeyword> getPopularKeyword() {
		
		return dao.getPopularKeyword();
	}


}
