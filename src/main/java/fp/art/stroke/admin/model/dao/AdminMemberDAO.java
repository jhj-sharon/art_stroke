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
import fp.art.stroke.admin.model.vo.PaginationFive;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.ProductQnAList;

@Repository
public class AdminMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

 
	// Member 
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("memberMapper.getListCount", adminCode);
	}



	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("memberMapper.searchListCount", paramMap);
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

	 
	
	

	// Member  Q&A
	public int getAdminQnAListCount(int adminCode) {
		return sqlSession.selectOne("productQnAMapper.getAdminQnAListCount", adminCode);
	}
	
	public List<ProductQnA> selectAdminMemberQA(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.selectAdminMemberQA", adminCode, rowBounds);
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

	public int searchAdminQnAListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("productQnAMapper.searchAdminQnAListCount", paramMap);
	}


	
	
	
	// Member Report

	public int getMemberReportListCount() {
		return sqlSession.selectOne("boardMapper.getMemberReportListCount");
	}



	public List<Report> selectMemberReport(Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectMemberReport", rowBounds);
	}



	public List<Report> searchMemberReport(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.searchMemberReport", paramMap, rowBounds);
 
	}



	public int searchMemberReportListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("boardMapper.searchMemberReportListCount", paramMap);
	}



	public int updateAdminMemberReport(List<Integer> reportChk, Integer reportId) {
		  Map<String, Object> params = new HashMap<>();
		    params.put("reportChk", reportChk);
		    params.put("reportId", reportId);
		    return sqlSession.update("boardMapper.updateAdminMemberReport", params);
		}



	public int updateAdminAuth(List<Integer> authChk, Integer memberId) {
		Map<String, Object> params = new HashMap<>();
			params.put("authChk", authChk);
			params.put("memberId", memberId);
		return sqlSession.update("memberMapper.updateAdminAuth", params);
	}

 

	 

 
	
}
