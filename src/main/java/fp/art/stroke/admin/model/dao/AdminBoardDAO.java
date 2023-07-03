
package fp.art.stroke.admin.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.member.model.vo.Member;

@Repository
public class AdminBoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 관리자 게시판 리스트 Code
	 * @param adminCode
	 * @return
	 */
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("boardMapper.getAdminListCount", adminCode);

	}

	/** 관리자 게시판 리스트
	 * @param pagination
	 * @param adminCode
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, int adminCode) {
	int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectAdminBoardList", adminCode, rowBounds);
	}

	/** 관리자 게시판 검색 개수
	 * @param paramMap
	 * @return
	 */
	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("boardMapper.searchAdminListCount", paramMap);

	}

	
	/** 관리자 게시판 검색 리스트 
	 * @param paramMap
	 * @param pagination
	 * @return
	 */
	public List<Board> searchBoardList(Map<String, Object> paramMap, Pagination pagination) {
	int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.searchAdminBoardList", paramMap, rowBounds);
	}

	
	/** 관리자 게시판 삭제
	 * @param boardChk
	 * @param boardId
	 * @return
	 */
	public int deleteAdminBoard(List<Integer> boardChk, Integer boardId) {
		Map<String, Object> params = new HashMap<>();
		params.put("boardChk", boardChk);
		params.put("boardId", boardId);
		return sqlSession.update("boardMapper.deleteAdminBoard", params);
	}

	 

}