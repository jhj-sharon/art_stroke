package fp.art.stroke.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberKakaoDAO {
	
	
	@Autowired // root-context.xml 에서 생성된 SqlSessionTemplate bean을 의존성 주입(DI)
	private SqlSessionTemplate sqlSession;

	private Logger logger = LoggerFactory.getLogger(MemberKakaoDAO.class);
	
	
	
	


}
