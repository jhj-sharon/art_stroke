package fp.art.stroke.myPage.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

public interface MyPageService {

	int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
			String addrTel, int memberId, int addrId);

	List<Addr> selectAddrList(int memberId);

	int deleteAddr(int addrId);

	int updateProfile(Map<String, Object> map)throws IOException;

	List<WishList> myPageWishList(int memberId);

	int cartInsert(int productId, int memberId,  String selectedOption, int productPrice);

	int deleteWishlist(int productId, int memberId);

	int secession(int memberId);

	int deleteSelectedWishlist(List<Integer> productIds, int memberId);

	int updateInfo(Map<String, Object> paramMap);

	int nicknameDupCheck(String memberNick, int memberId);
	/**
	 * 내 게시물 조회
	 * @param memberId
	 * @return
	 */
	List<Board> selectBoardList(int memberId);

	int deleteSelectedBoard(List<Integer> boardIds, int memberId);
	/**
	 * 최근 본 상품 조회
	 * @param recentListInt
	 * @return
	 */
	List<Product> recentProduct(int[] recentListInt);
	/**
	 * 팔로우 목록 조회
	 * @param memberId
	 * @return
	 */
	List<Follow> myFollow(int memberId);

	/**
	 * 쪽지 목록 조회
	 * @param memberId
	 * @return
	 */
	List<Message> messageList(int memberId);
	/**
	 * 쪽지 개별 삭제
	 * @param messageId
	 * @param memberId
	 * @return
	 */
	int deleteMessage(int messageId, int memberId);
	/**
	 * 쪽지 선택 삭제
	 * @param messageIds
	 * @return
	 */
	int deleteSelectedMessage(List<Integer> messageIds);
	/**
	 * 쪽지 전송
	 * @param memberNick
	 * @param sendName
	 * @param messageTitle
	 * @param messageContent
	 * @param receiverId
	 * @param memberId
	 * @return
	 */
	int sendBack(String memberNick, String sendName, String messageTitle, String messageContent, int senderId,
			int memberId);

	List<Coupon> myCoupon(int memberId);





}
