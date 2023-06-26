package fp.art.stroke.product.model.service;

import java.util.List;

import fp.art.stroke.product.model.vo.PopularKeyword;
import fp.art.stroke.product.model.vo.Product;

public interface SearchPageService {

	// 키워드 검색
	List<Product> searchKeyword(String productName, String productType, String productArtist, String productCategory);

	// 카테고리 검색 
	List<Product> searchCategory(String productCategory);

	// 키워드 하트 검색 
	List<Product> searchKeywordHeart(int memberId, String productName, String productType, String productArtist,
			String productCategory);

	// 카테고리 하트 검색 
	List<Product> searchCategoryHeart(int memberId, String productCategory);

	// 위시리스트 추가 
	int addSearchWishList(int memberId, int productId);

	// 위시리스트 삭제 
	int deleteSearchWishList(int memberId, int productId);

	
	// 인기검색어 확인 
	int countPopularKeyword(String popularKeyword);

	
	// 인기검색어 헤더에 띄우기 
	List<PopularKeyword> getPopularKeyword();


}
