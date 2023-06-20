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
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.ProductQnAList;

@Repository
public class AdminMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

 
	/** 관리자 회원 총 인원 count
	 * @param adminCode
	 * @return
	 */
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("memberMapper.getListCount", adminCode);
	}

 
	/** 관리자 회원 검색 총 인원 count
	 * @param paramMap
	 * @return
	 */
	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("memberMapper.searchListCount", paramMap);
	}

		 
	/** 관리자 회원 리스트
	 * @param pagination
	 * @param adminCode
	 * @return
	 */
	public List<Member> selectMemberList(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("memberMapper.selectMemberList", adminCode, rowBounds);
	}
 
	/** 관리자 회원 검색 리스트
	 * @param paramMap
	 * @param pagination
	 * @return
	 */
	public List<Member> searchMemberList(Map<String, Object> paramMap, Pagination pagination) {

		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("memberMapper.searchMemberList", paramMap, rowBounds);
	}

	 
	

	/** 관리자 회원 문의 리스트 총 개수
	 * @param adminCode
	 * @return
	 */
	public int getAdminQnAListCount(int adminCode) {
		return sqlSession.selectOne("productQnAMapper.getAdminQnAListCount", adminCode);
	}
	
	/** 관리자 회원 문의 리스트
	 * @param pagination
	 * @param adminCode
	 * @return
	 */
	public List<ProductQnA> selectAdminMemberQA(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.selectAdminMemberQA", adminCode, rowBounds);
	}
 
	
	/** 관리자 회원 문의 검색 리스트
	 * @param paramMap
	 * @param pagination
	 * @return
	 */
	public List<ProductQnA> searchAdminMemberQA(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productQnAMapper.searchAdminMemberQA", paramMap, rowBounds);
 
	}

	/** 관리자 문의 처리 완료 여부
	 * @param selectedIds
	 * @param qnaId
	 * @return
	 */
	public int updateAdminMemberQA(List<Integer> selectedIds, Integer qnaId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("selectedIds", selectedIds);
	    params.put("qnaId", qnaId);
	    return sqlSession.update("productQnAMapper.updateAdminMemberQA", params);
	}

	
	/** 관리자 문의 리스트 총 개수
	 * @param paramMap
	 * @return
	 */
	public int searchAdminQnAListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("productQnAMapper.searchAdminQnAListCount", paramMap);
	}


	 
	/** 관리자 회원 신고 총 개수
	 * @return
	 */
	public int getMemberReportListCount() {
		return sqlSession.selectOne("boardMapper.getMemberReportListCount");
	}



	/** 관리자 회원 신고 리스트
	 * @param pagination
	 * @return
	 */
	public List<Report> selectMemberReport(Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectMemberReport", rowBounds);
	}



	/** 관리자 회원 신고 검색
	 * @param paramMap
	 * @param pagination
	 * @return
	 */
	public List<Report> searchMemberReport(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.searchMemberReport", paramMap, rowBounds);
 
	}



	/** 관리자 회원 신고 목록 개수
	 * @param paramMap
	 * @return
	 */
	public int searchMemberReportListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("boardMapper.searchMemberReportListCount", paramMap);
	}



	/** 관리자 회원 신고 처리 여부
	 * @param reportChk
	 * @param reportId
	 * @return
	 */
	public int updateAdminMemberReport(List<Integer> reportChk, Integer reportId) {
		  Map<String, Object> params = new HashMap<>();
		    params.put("reportChk", reportChk);
		    params.put("reportId", reportId);
		    return sqlSession.update("boardMapper.updateAdminMemberReport", params);
		}


	
	/** 관리자 작가 승인 여부
	 * @param authChk
	 * @param memberId
	 * @return
	 */
	public int updateAdminAuth(List<Integer> authChk, Integer memberId) {
		Map<String, Object> params = new HashMap<>();
			params.put("authChk", authChk);
			params.put("memberId", memberId);
		return sqlSession.update("memberMapper.updateAdminAuth", params);
	}

 

	 

 
	
}
