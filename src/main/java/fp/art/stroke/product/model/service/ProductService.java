package fp.art.stroke.product.model.service;

import java.util.List;
import java.util.Map;

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


	/**위시리스트 추가하기
	 * @param productId
	 * @return
	 */
	int addWishList(WishList wishList);


	/**위시리스트 중복검사
	 * @param productId
	 * @return
	 */
	int wishListCheck(int productId);
	
	
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

}
