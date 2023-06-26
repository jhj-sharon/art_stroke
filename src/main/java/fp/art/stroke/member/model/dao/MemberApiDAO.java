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

	public int selectApiMemberCount(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectApiMemberCount",member);
	}

	public Member selectaApiMember(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectApiMember",member);
	}

	public int signUpApiMember(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.signUpApiMember",member);
	}

	

}
