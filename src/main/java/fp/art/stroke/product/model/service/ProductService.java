package fp.art.stroke.product.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

public interface ProductService {

	
	/**전체 상품 리스트 조회 서비스
	 * @return
	 */
	List<Product> loadProductList();
	
	
	/**상품 상세 페이지
	 * @return
	 */
	Product loadProductDetail(int productId);

	/**위시리스트 중복검사
	 * @param productId
	 * @return
	 */
	int wishListCheck(WishList wishList);

	
	/**위시리스트 추가하기
	 * @param productId
	 * @return
	 */
	int addWishList(WishList wishList);
	
	
    /**위시리스트 로드하기
     * @param memberId
     * @return
     */
	List<Integer> loadWishlist(int memberId);


	
	/**위시리스트 삭제
	 * @param productId
	 * @return
	 */
	int wishListDelete(int productId);


	/**상세페이지 이동
	 * @param productId
	 * @return
	 */
	Product getProductById(int productId);


	/**JSTL 상품 목록 만들기
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> loadProductMain(Map<String, Object> paramMap);


	/**쿼리스트링 조건에 만족하는 상품 목록 만들기
	 * @param paramMap
	 * @return
	 */
	List<Product> loadProductList2(Map<String, Object> paramMap);


    /**위시리스트 객체 로드하기
     * @param memberId
     * @return
     */
	List<WishList> loadWishlistObj(int memberId);


	/**best 상품 정렬
	 * @param paramMap
	 * @return
	 */
	List<Product> loadProductBest(Map<String, Object> paramMap);

	
	/**NEW 상품 정렬
	 * @param paramMap
	 * @return
	 */
	List<Product> loadProductNew(Map<String, Object> paramMap);


	/**장바구니 중복체크
	 * @param cart
	 * @return
	 */
	int checkCart(Cart cart);


	/**옵션 중복시 장바구니 수량 증가
	 * @param cart
	 * @return
	 */
	int updateCartQuantity(Cart cart);


	/**장바구니 추가
	 * @param cart
	 * @return
	 */
	int addCart(Cart cart);


	/**장바구니 로드
	 * @param memberId
	 * @return
	 */
	List<Cart> loadCart(int memberId);


	/**장바구니 삭제
	 * @param cartIdList
	 * @return
	 */
	int deleteCart(List<Integer> cartIdList);


	/**쿠폰정보 들고오기
	 * @param memberId
	 * @return
	 */
	List<Coupon> loadCoupon(int memberId);


	/** 주소정보 들고오기
	 * @param memberId
	 * @return
	 */
	List<Addr> loadAddr(int memberId);


	/**결제페이지에서 주소 등록
	 * @param addr
	 * @return
	 */
	int newAddr(Addr addr);


}
