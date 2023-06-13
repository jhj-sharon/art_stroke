package fp.art.stroke.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.member.model.vo.Member;
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


	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("memberMapper.searchListCount", paramMap);
	}


	public List<Member> searchMemberList(Map<String, Object> paramMap, Pagination pagination) {

		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("memberMapper.searchMemberList", paramMap, rowBounds);
	}


	public List<ProductQnAList> selectAdminMemberQA(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.selectAdminMemberQA", adminCode, rowBounds);
	}


	public int updateAdminMemberQA(int qnaIdList) {
		return sqlSession.update("productQnAMapper.updateAdminMemberQA", qnaIdList);
	}


 
	
}
