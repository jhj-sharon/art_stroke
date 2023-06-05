package fp.art.stroke.admin.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.member.model.vo.Member;

@Repository
public class AdminMainDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member adminLogin(Member inputMember) {

		Member loginMember = sqlSession.selectOne("memberMapper.adminLogin", inputMember ); 
		
		return loginMember;
	}

	public List<AdminType> selectAdminType() {
		return sqlSession.selectList("adminMapper.selectAdminType");
	}
	
	
	
}
