package fp.art.stroke.myPage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.dao.MyPageDAO;
import fp.art.stroke.myPage.model.vo.Addr;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;

	  @Override
	    public int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
	            String addrTel, int memberId, int addrId) {

	        Addr updateAddress = dao.getAddressByAddrId(addrId);
	        String addr = postcode+" || "+ roadAddress+" || "+detailAddress;
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
	@Override
	public List<Addr> selectAddrList(int memberId) {

		return dao.selectAddrList(memberId);
	}
	@Override
	public int deleteAddr(int addrId) {
		
		return dao.deleteAddr(addrId);
	}

}
