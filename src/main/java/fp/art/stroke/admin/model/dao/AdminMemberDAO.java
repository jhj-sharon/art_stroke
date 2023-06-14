package fp.art.stroke.admin.model.dao;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import fp.art.stroke.member.model.vo.Member;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.ProductQnAList;

@Repository
public class AdminMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

 
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("memberMapper.getListCount", adminCode);
	}


	public List<Member> selectMemberList(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("memberMapper.selectMemberList", adminCode, rowBounds);
	}


	public List<Member> searchMemberList(Map<String, Object> paramMap, Pagination pagination) {

		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("memberMapper.searchMemberList", paramMap, rowBounds);
	}


	public List<ProductQnA> selectAdminMemberQA(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.selectAdminMemberQA", adminCode, rowBounds);
	}

	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("productQnAMapper.searchAdminListCountQA", paramMap);
	}

	
	
	
	public List<ProductQnA> searchAdminMemberQA(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.searchAdminMemberQA", paramMap, rowBounds);
 
	}

	 


	public int updateAdminMemberQA(List<Integer> selectedIds, Integer qnaId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("selectedIds", selectedIds);
	    params.put("qnaId", qnaId);
	    return sqlSession.update("productQnAMapper.updateAdminMemberQA", params);
	}


 

	 

 
	
}
