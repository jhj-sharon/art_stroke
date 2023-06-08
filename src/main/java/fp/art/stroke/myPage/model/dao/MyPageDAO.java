package fp.art.stroke.myPage.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;

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

	public Addr getAddressByAddrId(int addrId) {
		return sqlSession.selectOne("myPageMapper.getAddressByAddrId", addrId);
	}

	public int updateAddress(Addr updateAddress) {
		// TODO Auto-generated method stub
		return sqlSession.update("myPageMapper.updateAddress", updateAddress);
	}

	public int insertAddress(Addr newAddress) {
		return sqlSession.insert("myPageMapper.insertAddress", newAddress);
	}

}
