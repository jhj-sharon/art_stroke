package fp.art.stroke.myPage.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fp.art.stroke.myPage.model.vo.Addr;

public interface MyPageService {

	int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
			String addrTel, int memberId, int addrId);

	List<Addr> selectAddrList(int memberId);

	int deleteAddr(int addrId);

	int updateProfile(Map<String, Object> map)throws IOException;

}
