package fp.art.stroke.myPage.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.myPage.model.vo.OrderInfo;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Repository
public class MyPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*
	 * public int addrReg(Map<String, Object> map) { return
	 * sqlSession.insert("myPageMapper.addrReg", map); }
	 */

	public List<Addr> selectAddrList(int memberId) {
		return sqlSession.selectList("myPageMapper.selectAddrList", memberId);
	}

	/**
	 * 배송지 중복검사
	 * 
	 * @param addrId
	 * @return
	 */
	public Addr getAddressByAddrId(int addrId) {
		return sqlSession.selectOne("myPageMapper.getAddressByAddrId", addrId);
	}

	public int updateAddress(Addr updateAddress) {
		return sqlSession.update("myPageMapper.updateAddress", updateAddress);
	}

	public int insertAddress(Addr newAddress) {
		return sqlSession.insert("myPageMapper.insertAddress", newAddress);
	}

	/**
	 * 배송지 삭제 DAO
	 * 
	 * @param addrId
	 * @return
	 */
	public int deleteAddr(int addrId) {

		return sqlSession.delete("myPageMapper.deleteAddr", addrId);
	}

	/**
	 * 프로필 이미지 수정
	 * 
	 * @param map
	 * @return result
	 */
	public int updateProfile(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.updateProfile", map);
	}

	/**
	 * 관심 목록 가져오기
	 * 
	 * @param memberId
	 * @return
	 */
	public List<WishList> myPageWishList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.myPageWishList", memberId);
	}

	public int cartInsert(int productId, int memberId, String selectedOption, int productPrice) {
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		map.put("memberId", memberId);
		map.put("selectedOption", selectedOption);
		map.put("productPrice", productPrice);
		return sqlSession.insert("myPageMapper.cartInsert", map);
	}

	/**
	 * 카트 목록 조회
	 * 
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public Cart getCartList(int memberId, int productId, String selectedOption) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		map.put("selectedOption", selectedOption);
		return sqlSession.selectOne("myPageMapper.getCartList", map);
	}

	/**
	 * 
	 * @param cartNum
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public int cartUpdate(int cartNum, int memberId, int productId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		map.put("cartNum", cartNum);
		return sqlSession.update("myPageMapper.cartUpdate", map);
	}

	/**
	 * 위시리스트 삭제 dao
	 * 
	 * @param productId
	 * @param memberId
	 * @return
	 */
	public int deleteWishlist(int productId, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteWishlist", map);
	}

	public int secession(int memberId) {

		return sqlSession.update("myPageMapper.secession", memberId);
	}

	/**
	 * 선택된 위시리스트 삭제 dao
	 * 
	 * @param productIds
	 * @param memberId
	 * @return
	 */
	public int deleteSelectedWishlist(List<Integer> productIds, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("productIds", productIds);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteSelectedWishlist", map);
	}

	/**
	 * 회원정보 수정 DAO
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateInfo(Map<String, Object> paramMap) {

		return sqlSession.update("myPageMapper.updateInfo", paramMap);
	}

	public int nicknameDupCheck(String memberNick, int memberId) {

		return sqlSession.selectOne("myPageMapper.nicknameDupCheck", memberNick);
	}

	/**
	 * 내 게시글 가져오기 DAO
	 * 
	 * @param memberId
	 * @return
	 */
	public List<Board> selectBoardList(int memberId) {

		return sqlSession.selectList("myPageMapper.selectBoardList", memberId);
	}

	/**
	 * 내 게시물 삭제 DAO
	 * 
	 * @param boardIds
	 * @param memberId
	 * @return
	 */
	public int deleteSelectedBoard(List<Integer> boardIds, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("boardIds", boardIds);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteSelectedBoard", map);
	}

	public List<Product> recentProduct(int[] recentListInt) {
		return sqlSession.selectList("myPageMapper.recentProduct", recentListInt);
	}

	/**
	 * 팔로우 가져오는 dao
	 * 
	 * @param memberId
	 * @return
	 */
	public List<Follow> myFollow(int memberId) {

		return sqlSession.selectList("myPageMapper.myFollow", memberId);
	}

	/**
	 * 쪽지 불러오기
	 * 
	 * @param memberId
	 * @return
	 */
	public List<Message> messageList(int memberId) {

		return sqlSession.selectList("myPageMapper.messageList", memberId);
	}

	/**
	 * 쪽지 개별 삭제
	 * 
	 * @param messageId
	 * @param memberId
	 * @return
	 */
	public int deletleMessage(int messageId, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("memberId", memberId);

		return sqlSession.update("myPageMapper.deletleMessage", map);
	}

	/**
	 * 쪽지 선택 삭제
	 * 
	 * @param messageIds
	 * @return
	 */
	public int deleteSelectedMessage(List<Integer> messageIds) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("messageIds", messageIds);
		return sqlSession.update("myPageMapper.deleteSelectedMessage", parameterMap);
	}

	/**
	 * 쪽지 전송
	 * @param newMessage
	 * @return
	 */
	public int sendBack(Message newMessage) {
		
		return sqlSession.insert("myPageMapper.insertSendBack", newMessage);
	}
	/**
	 * 쿠폰목록 가져오기
	 * @param memberId
	 * @return
	 */
	public List<Coupon> myCoupon(int memberId) {
		return sqlSession.selectList("myPageMapper.myCoupon", memberId);
	}
	/**
	 * 주문상품 정보 가져오기
	 * @param memberId
	 * @return
	 */
	public List<OrderInfo> myOrderInfo(int memberId) {
		
		return sqlSession.selectList("myPageMapper.myOrderInfo", memberId);
	}
	/**
	 * 취소했을때 해당하는 상품 id 값들 가져오기
	 * @param orderId
	 * @return
	 */
	public List<Object> selectProductIds(String orderId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.selectProdeuctIds", orderId);
	}
	/**
	 * 취소 insert
	 * @param orderId
	 * @param productIds
	 * @param cancelReason
	 * @param memberId
	 * @return
	 */
	public int insertCancelOrder(String orderId, String joinedProductIds, String cancelReason, int memberId) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("productIds", joinedProductIds);
		map.put("cancelReason", cancelReason);
		map.put("memberId", memberId);
		
		return sqlSession.insert("myPageMapper.insertCancelOrder", map);
	}
	/**
	 * 취소상태 update
	 * @param orderId
	 * @param memberId
	 * @return
	 */
	public int updateCancelOrder(String orderId, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("orderId", orderId);
		return sqlSession.update("myPageMapper.updateCancelOrder", map);
	}
	/**
	 * 리뷰 insert
	 * @param map
	 * @return
	 */
	public int reviewInsert(Map<String, Object> map) {
	
		return sqlSession.insert("myPageMapper.reviewInsert", map);
	}

	public int updateReview(Map<String, Object> map) {
		
		return sqlSession.update("myPageMapper.updateReview", map);
	}

	public int readMessage(int messageId) {
		// TODO Auto-generated method stub
		return sqlSession.update("myPageMapper.readMessage", messageId);
	}

}
