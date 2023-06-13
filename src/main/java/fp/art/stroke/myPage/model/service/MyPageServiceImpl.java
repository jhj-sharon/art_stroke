package fp.art.stroke.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fp.art.stroke.common.Util;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.dao.MyPageDAO;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.WishList;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
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

	@Override
	public int nicknameDupCheck(String memberNick, int memberId) {
		
		return dao.nicknameDupCheck(memberNick, memberId);
	}

}
