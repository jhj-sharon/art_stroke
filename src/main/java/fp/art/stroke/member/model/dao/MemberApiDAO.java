package fp.art.stroke.member.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.member.model.vo.GoogleMember;
import fp.art.stroke.member.model.vo.Member;

@Repository
public class MemberApiDAO {
	@Autowired // root-context.xml 에서 생성된 SqlSessionTemplate bean을 의존성 주입(DI)
	private SqlSessionTemplate sqlSession;

	private Logger logger = LoggerFactory.getLogger(MemberApiDAO.class);

	public int selectApiMemberCount(String email, String type) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
		map.put("email", email);
		map.put("type", type);
		return sqlSession.selectOne("memberMapper.selectApiMemberCount",map);
	}

	public Member selectMemberApi(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectMemberApi",map);
	}

	public int insertApiMember(GoogleMember googleMember) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.insertApiMember",googleMember);
	}

	

}
