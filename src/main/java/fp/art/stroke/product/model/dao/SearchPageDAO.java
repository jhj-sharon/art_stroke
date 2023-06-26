package fp.art.stroke.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.PopularKeyword;
import fp.art.stroke.product.model.vo.Product;

@Repository 
public class SearchPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 키워드 검색 
	public List<Product> searchKeyword(String productName, String productType, String productArtist,
			String productCategory) {
		
		Map<String, Object> searchKeywordMap = new HashMap<>();
		searchKeywordMap.put("productName", productName);
		searchKeywordMap.put("productType", productType);
		searchKeywordMap.put("productArtist", productArtist);
		searchKeywordMap.put("productCategory", productCategory);
		
		return sqlSession.selectList("searchPageMapper.searchKeyword", searchKeywordMap);
	}

	
	// 카테고리 검색 
	public List<Product> searchCategory(String productCategory) {
		Map<String, Object> searchCategoryMap = new HashMap<>();
		searchCategoryMap.put("productCategory", productCategory);
		
		return sqlSession.selectList("searchPageMapper.searchCategory", searchCategoryMap);
	}


	// 키워드 하트 검
	public List<Product> searchKeywordHeart(int memberId, String productName, String productType, String productArtist,
			String productCategory) {
		Map<String, Object> searchKeywordHeartMap = new HashMap<>();
		
		searchKeywordHeartMap.put("memberId", memberId);
		searchKeywordHeartMap.put("productName", productName);
		searchKeywordHeartMap.put("productType", productType);
		searchKeywordHeartMap.put("productArtist", productArtist);
		searchKeywordHeartMap.put("productCategory", productCategory);
		
		
		return sqlSession.selectList("searchPageMapper.searchKeywordHeart", searchKeywordHeartMap);
		
	}


	// 카테고리 하트 검색 
	public List<Product> searchCategoryHeart(int memberId, String productCategory) {
		
		Map<String, Object> searchCategoryHeartMap = new HashMap<>();
		
		searchCategoryHeartMap.put("memberId", memberId);
		searchCategoryHeartMap.put("productCategory", productCategory);
		
		return sqlSession.selectList("searchPageMapper.searchCategoryHeart", searchCategoryHeartMap);
	}


	public int addSearchWishList(int memberId, int productId) {
		
		Map<String, Object> searchWishMap = new HashMap<>();
		searchWishMap.put("memberId", memberId);
		searchWishMap.put("productId", productId);
		
		return sqlSession.insert("searchPageMapper.addSearchWishList", searchWishMap);
	}


	public int deleteSearchWishList(int memberId, int productId) {
		
		Map<String, Object> searchWishMap = new HashMap<>();
		searchWishMap.put("memberId", memberId);
		searchWishMap.put("productId", productId);
		
		return sqlSession.delete("searchPageMapper.deleteSearchWishList", searchWishMap);
	}


	
	// 인기검색어 확인 
	public int countPopularKeyword(String popularKeyword) {
		
		int result = sqlSession.selectOne("searchPageMapper.countPopularKeyword", popularKeyword);
		
		if(result > 0) {
			sqlSession.update("searchPageMapper.updateSearchCount", popularKeyword);
		} else {
			sqlSession.insert("searchPageMapper.insertPopularKeyword", popularKeyword);
		}
	
		return result;
	}

	// 인기검색어 헤더에 띄우기 
	public List<PopularKeyword> getPopularKeyword() {
		
		return sqlSession.selectList("searchPageMapper.getPopularKeyword");
	}

	
}
