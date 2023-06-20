package fp.art.stroke.common.listener.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository;

import fp.art.stroke.common.listener.model.vo.VisitCountVO;
 

@Repository
public class VisitCountDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertVisitor(VisitCountVO vo) throws Exception {
	    System.out.println("insertVisitor 메서드 호출됨");
	    return sqlSession.insert("adminMapper.insertVisitor", vo);
	}
	 
}