package fp.art.stroke.product.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Repository 
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Product> getWriterProductList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.getWriterProductList",memberId);
	}

	/**전체 상품 수 조회 DAO
	 * @return
	 */
	public int getListCount() {
		
		return sqlSession.selectOne("productMapper.getListCountP");
	}

	/**상품 목록 조회 DAO
	 * @return
	 */
	public List<Product> loadProductList() {
		 
		//현재 페이지에 해당하는 만큼의 상품만 가져오기
		return sqlSession.selectList("productMapper.loadProductList");
	}

	/**상품 상세페이지 상품 조회
	 * @param productId
	 * @return
	 */
	public Product loadProductDetail(int productId) {
		
		return sqlSession.selectOne("productMapper.loadProductDetail");
	}

	/**위시 리스트 추가
	 * @param productId
	 * @return
	 */
	public int addWishList(WishList wishList) {
		
		return sqlSession.insert("productMapper.addWishList", wishList);
	}

	/**위시 리스트 중복검사
	 * @param wishList
	 * @return
	 */
	public int wishListCheck(WishList wishList) {
		
		return sqlSession.selectOne("productMapper.wishListCheck", wishList);
	}
	
	
    /**위시리스트 로드하기
     * @param memberId
     * @return
     */
    public List<Integer> loadWishlist(int memberId) {
        return sqlSession.selectList("productMapper.loadWishList", memberId);
    }

	/**위시리스트 삭제하기
	 * @param productId
	 * @return
	 */
	public int wishListDelete(int productId) {
		
		return sqlSession.delete("productMapper.wishListDelete", productId);
	}

	/**상세페이지 이동하기
	 * @param productId
	 * @return
	 */
	public Product getProductById(int productId) {

		return sqlSession.selectOne("productMapper.getProductById", productId);
	}



	/** wishList 객체 로드
	 * @param memberId
	 * @return
	 */
	public List<WishList> loadWishlistObj(int memberId) {
		
		return sqlSession.selectList("productMapper.loadWishlistObj", memberId);
	}

	/**쿼리스트링 조건에 만족하는 상품 목록 만들기
	 * @param paramMap
	 * @return
	 */
	public List<Product> loadProductList2(Map<String, Object> paramMap) {
	
		return sqlSession.selectList("productMapper.loadProductList2", paramMap);
	}

	/**best 상품 정렬
	 * @param paramMap
	 * @return
	 */
	public List<Product> loadProductBest(Map<String, Object> paramMap) {
	
		return sqlSession.selectList("productMapper.loadProductBest", paramMap);
	}

	/**NEW 상품 정렬
	 * @param paramMap
	 * @return
	 */
	public List<Product> loadProductNew(Map<String, Object> paramMap) {
	
		return sqlSession.selectList("productMapper.loadProductNew", paramMap);
	}
}
