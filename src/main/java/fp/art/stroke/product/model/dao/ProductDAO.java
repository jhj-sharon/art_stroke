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
import fp.art.stroke.product.model.vo.Order;
import fp.art.stroke.product.model.vo.OrderDetail;
import fp.art.stroke.product.model.vo.OrderItems;
import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Payment;
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

	/**주문정보 저장하기 
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productMapper.insertOrder", order);
	}

	/**쿠폰아이디로 쿠폰 가져오기
	 * @param couponId
	 * @return
	 */
	public Coupon getCouponById(int couponId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("productMapper.getCouponById", couponId);
	}
	
	
	/**order-detail 삽입
	 * @param orderDetail
	 * @return
	 */
	public int insertOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productMapper.insertOrderDetail", orderDetail);
	}

	/**결제 삽입 
	 * @param payment
	 * @return
	 */
	public int insertPayment(Payment payment) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productMapper.insertPayment", payment);
	}

	/**사용한 쿠폰 삭제
	 * @param couponId
	 * @return
	 */
	public int deleteCoupon(int couponId) {
		// TODO Auto-generated method stub
		return sqlSession.delete("productMapper.deleteCoupon", couponId);
	}

	/**결제한 상품 장바구니에서 삭제
	 * @param params
	 * @return
	 */
	public int payDeleteCart(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSession.delete("productMapper.payDeleteCart", params);
	}

	/**구매한 상품 판매량 증가
	 * @param params
	 * @return
	 */
	public int increaseSales(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return sqlSession.update("productMapper.increaseSales", orderDetail);
	}

	/**주문번호로 주문정보 가져오기
	 * @param orderNumber
	 * @return
	 */
	public Order selectOrder(String orderId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("productMapper.selectOrder", orderId);
	}


	

	public List<Product> selectBoardProductList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.selectBoardProductList",memberId);
	}

	/**카트 아이디로 카트가져오기
	 * @param cartIdList
	 * @return
	 */
	public List<Cart> selectedCart(List<Integer> cartIdList) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.selectedCart", cartIdList);
	}
	
	
	/**orderItems 삽입하기
	 * @param orderItemsList
	 * @return
	 */
	public int insertOrderItems(OrderItems orderItems) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productMapper.insertOrderItems", orderItems);
	}

	/**OrderItems 로드하기
	 * @param memberId
	 * @return
	 */
	public List<OrderItems> loadOrderItems(int memberId) {
		// TODO Auto-generated method stub
		return  sqlSession.selectList("productMapper.loadOrderItems", memberId);

	}


}
