package fp.art.stroke.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.dao.MyPageDAO;
@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageDAO dao;

	@Override
	public int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
			String memberTel, int memberId) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("addrName", addrName);
		map.put("receiverName", receiverName);
		map.put("postcode", postcode);
		map.put("roadAddress", roadAddress);
		map.put("detailAddress", detailAddress);
		map.put("memberTel", memberTel);
		map.put("memberId", memberId);
		
		return dao.addrReg(map);
	}


}
