package fp.art.stroke.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.MainPageReview;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;


@Repository
public class MainpageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 베스트 상품 불러오기 
	 * @param productName
	 * @return
	 */
	public List<Product> selectMainBestProdcut(String productName) {
		
		return sqlSession.selectList("mainpageMapper.selectMainBestProdcut", productName);
	}

	
	
	/** 키매, 하이퍼펜션 상품 불러오기
	 * @return
	 */
	public List<Product> selectMainArtistProdcut() {
		
		return sqlSession.selectList("mainpageMapper.selectMainArtistProdcut");
	}



	/** 로그인한 회원의 위시리스트에 있는 productId 얻어오기
	 * @return
	 */
	public List<WishList> selectWishProductId(int memberId) {
		
		return sqlSession.selectList("mainpageMapper.selectWishProductId", memberId);
	}



	/** 위시리스트 추가 
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public int addMainWishList(int memberId, int productId) {
		
		Map<String, Object> mainWishMap = new HashMap<>();
		mainWishMap.put("memberId", memberId);
		mainWishMap.put("productId", productId);
		
		return sqlSession.insert("mainpageMapper.addMainWishList",mainWishMap);
	}



	/** 위시리스트 삭제 
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public int deleteMainWishList(int memberId, int productId) {
		
		Map<String, Object> mainWishMap = new HashMap<>();
		mainWishMap.put("memberId", memberId);
		mainWishMap.put("productId", productId);
		
		return sqlSession.delete("mainpageMapper.deleteMainWishList",mainWishMap);
	}



	/** 리뷰 불러오기  
	 * @return
	 */
	public List<MainPageReview> getMainReview() {
		
		return sqlSession.selectList("mainpageMapper.getMainReview");
	}

}
