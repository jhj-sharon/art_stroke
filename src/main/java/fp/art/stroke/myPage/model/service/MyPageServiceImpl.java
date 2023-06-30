package fp.art.stroke.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.common.Util;
import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.dao.MyPageDAO;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.myPage.model.vo.CancelOrder;
import fp.art.stroke.myPage.model.vo.OrderInfo;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	/**
	 * 배송지 등록
	 */
	@Override
	public int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
			String addrTel, int memberId, int addrId) {

		Addr updateAddress = dao.getAddressByAddrId(addrId);
		String addr = postcode + ",," + roadAddress + ",," + detailAddress;
		String deliveryName = addrName;
		String addrMessage = "";
		if (updateAddress != null) {
			// 이미 주소가 존재하는 경우 업데이트 수행
			updateAddress.setAddrId(addrId);
			updateAddress.setDeliveryName(deliveryName);
			updateAddress.setReceiverName(receiverName);
			updateAddress.setAddr(addr);
			updateAddress.setAddrTel(addrTel);
			updateAddress.setMemberId(memberId);
			return dao.updateAddress(updateAddress);
		} else {
			// 주소가 존재하지 않는 경우 삽입 수행
			Addr newAddress = new Addr();
			newAddress.setDeliveryName(deliveryName);
			newAddress.setReceiverName(receiverName);
			newAddress.setAddr(addr);
			newAddress.setAddrTel(addrTel);
			newAddress.setMemberId(memberId);
			return dao.insertAddress(newAddress);
		}
	}

	/**
	 * 장바구니 담는 serviceImpl
	 */
	@Override
	public int cartInsert(int productId, int memberId, String selectedOption, int productPrice) {
		Cart updateCart = dao.getCartList(memberId, productId, selectedOption);
		
		if (updateCart != null) {
			int cartNum = updateCart.getQuantity();
			cartNum++;
			return dao.cartUpdate(cartNum, memberId, productId);
		}else {
			
			return dao.cartInsert(productId, memberId, selectedOption, productPrice);
		}
		
	}

	@Override
	public List<Addr> selectAddrList(int memberId) {

		return dao.selectAddrList(memberId);
	}

	@Override
	public int deleteAddr(int addrId) {

		return dao.deleteAddr(addrId);
	}

	@Override
	public int updateProfile(Map<String, Object> map) throws IOException {
		// 자주쓰는 값 변수에 저장
		MultipartFile uploadImage = (MultipartFile) map.get("uploadImage");
		String delete = (String) map.get("delete"); // "0" (변경) / "1" (삭제)

		// 프로필 이미지 삭제여부를 확인해서
		// 삭제가 아닌 경우(== 새 이미지로 변경) -> 업로드된 파일명을 변경
		// 삭제된 경우 -> NULL 값을 준비 (DB에 update)

		String renameImage = null; // 변경된 파일명 저장

		if (delete.equals("0")) { // 이미지가 변경된 경우
			// 파일명 변경
			// uploadImage.getOriginalFilename() : 원본 파일명
			renameImage = Util.fileRename(uploadImage.getOriginalFilename());

			map.put("profileImage", map.get("webPath") + renameImage);
			// /resources/images/memberProfile/20220624122315.png
		} else {

			map.put("profileImage", null); // null 값 준비
		}

		// DAO를 호출해서 프로필 이미지 수정
		int result = dao.updateProfile(map);

		// DB 수정 성공 시 메모리에 임시 저장되어있는 파일을 서버에 저장
		if (result > 0 && map.get("profileImage") != null) {
			uploadImage.transferTo(new File(map.get("folderPath") + renameImage));
			// transferTo() : 해당 파일을 지정된 경로+이름으로 저장
		}

		return result;
	}

	/**
	 * 관심목록 가져오는 serviceImpl
	 */
	@Override
	public List<WishList> myPageWishList(int memberId) {
		return dao.myPageWishList(memberId);
	}
	/**
	 * 위시리스트 삭제 impl
	 */
	@Override
	public int deleteWishlist(int productId, int memberId) {
		
		return dao.deleteWishlist(productId, memberId);
	}
	/**
	 * 회원탈퇴 impl
	 */
	@Override
	public int secession(int memberId) {
		return dao.secession(memberId);
	}
	/**
	 * 관심상품 선택 삭제
	 */
	@Override
	public int deleteSelectedWishlist(List<Integer> productIds, int memberId) {
		
		return dao.deleteSelectedWishlist(productIds, memberId);
	}
	/**
	 * 회원정보 수정!
	 */
	@Override
	public int updateInfo(Map<String, Object> paramMap) {
		
		paramMap.put("memberPw", bcrypt.encode((String)paramMap.get("memberPw")));
		
		return dao.updateInfo(paramMap);
	}
/**
 * 닉네임 중복체크
 */
	@Override
	public int nicknameDupCheck(String memberNick, int memberId) {
		
		return dao.nicknameDupCheck(memberNick, memberId);
	}
	/**
	 * 내 게시글 가져오기
	 */
	@Override
	public List<Board> selectBoardList(int memberId) {
		
		return dao.selectBoardList(memberId);
	}
	/**
	 * 게시물 삭제!
	 */
	@Override
	public int deleteSelectedBoard(List<Integer> boardIds, int memberId) {
		
		return dao.deleteSelectedBoard(boardIds, memberId);
	}

	@Override
	public List<Product> recentProduct(int[] recentListInt) {
		// TODO Auto-generated method stub
		return dao.recentProduct(recentListInt);
	}
	/**
	 * 팔로우 목록 가져오는 impl
	 */
	@Override
	public List<Follow> myFollow(int memberId) {
		// TODO Auto-generated method stub
		return dao.myFollow(memberId);
	}

	@Override
	public List<Message> messageList(int memberId) {
		// TODO Auto-generated method stub
		return dao.messageList(memberId);
	}
	/**
	 * 쪽지 개별 삭제
	 */
	@Override
	public int deleteMessage(int messageId, int memberId) {
		// TODO Auto-generated method stub
		return dao.deletleMessage(messageId, memberId);
	}
	/**
	 * 쪽지 선택 삭제
	 */
	@Override
	public int deleteSelectedMessage(List<Integer> messageIds) {
		// TODO Auto-generated method stub
		return dao.deleteSelectedMessage(messageIds);
	}
	/**
	 * 쪽지 전송
	 */
	@Override
	public int sendBack(String memberNick, String sendName, String messageTitle, String messageContent, int senderId,
			int memberId) {
		
		Message newMessage = new Message();
		newMessage.setMemberNick(memberNick);
		newMessage.setMessageTitle(messageTitle);
		newMessage.setMessageContent(messageContent);
		newMessage.setSenderId(memberId);
		newMessage.setReceiverId(senderId);
		
		return dao.sendBack(newMessage);
	}
	/**
	 * 쿠폰가져오기
	 */
	@Override
	public List<Coupon> myCoupon(int memberId) {
		
		return dao.myCoupon(memberId);
	}
	/**
	 * 주문정보 가져오기
	 */
	@Override
	public List<OrderInfo> myOrderInfo(int memberId) {
		
		return dao.myOrderInfo(memberId);
	}
	/**
	 * 배송취소 등록
	 */
	@Override
	public int cancelOrder(String orderId, String cancelReason, int memberId) {
	
		List<Object> productIds = dao.selectProductIds(orderId);
		String joinedProductIds = String.join("/", productIds.stream().map(Object::toString).collect(Collectors.toList()));
		
		int insertCancelOrder = dao.insertCancelOrder(orderId, joinedProductIds, cancelReason, memberId);
		int result = 0;
		if(insertCancelOrder >= 1) {
			System.out.println("여기까지???");
			int updateCancelOrder = dao.updateCancelOrder(orderId, memberId);
			result = updateCancelOrder;
		}else {
			result = 0;
		}
		
		return result;
	}

	@Override
	public int reviewInsert(Map<String, Object> map) throws IOException {
		
		MultipartFile reviewImg = (MultipartFile) map.get("reviewImg");
		
		String renameImage = null;
		renameImage = Util.fileRename(reviewImg.getOriginalFilename());
		map.put("reviewImg", map.get("webPath") + renameImage);
		
		int result = dao.reviewInsert(map);
		int result2 =0;
		if(result >0) {
			reviewImg.transferTo(new File(map.get("folderPath") + renameImage));
			result2 = dao.updateReview(map);
			if(result2 >0) {
				return result2;
			}else {
				result2 = 0;
			}
			
		}
		return result2;
	}
	/**
	 * 쪽지 읽음처리
	 */
	@Override
	public int readMessage(int messageId) {
		
		return dao.readMessage(messageId);
	}
}
