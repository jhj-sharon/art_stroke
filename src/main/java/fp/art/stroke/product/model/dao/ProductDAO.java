package fp.art.stroke.product.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Cart;
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

	/**장바구니 중복체크
	 * @param cart
	 * @return
	 */
	public int checkCart(Cart cart) {
		
		return sqlSession.selectOne("productMapper.checkCart", cart);
	}

	/**장바구니 중복시 수량증가
	 * @param cart
	 * @return
	 */
	public int updateCartQuantity(Cart cart) {
		// TODO Auto-generated method stub
		return sqlSession.update("productMapper.updateCartQuantity", cart);
	}

	/**장바구니 추가
	 * @param cart
	 * @return
	 */
	public int addCart(Cart cart) {
		
		return sqlSession.insert("productMapper.addCart", cart );
	}

	/**장바구니 로드
	 * @param memberId
	 * @return
	 */
	public List<Cart> loadCart(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.loadCart", memberId);
	}

	/**장바구니 삭제
	 * @param cartIdList
	 * @return
	 */
	public int deleteCart(List<Integer> cartIdList) {
		// TODO Auto-generated method stub
		return sqlSession.delete("productMapper.deleteCart", cartIdList);
	}

	/**쿠폰 정보 들고오기
	 * @param memberId
	 * @return
	 */
	public List<Coupon> loadCoupon(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.loadCoupon", memberId);
	}

	/**주소 정보 들고오기
	 * @param memberId
	 * @return
	 */
	public List<Addr> loadAddr(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.loadAddr" , memberId);
	}

	/**결제 페이지에서 주소 등록하기
	 * @param addr
	 * @return
	 */
	public int newAddr(Addr addr) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productMapper.newAddr", addr);
	}
}
