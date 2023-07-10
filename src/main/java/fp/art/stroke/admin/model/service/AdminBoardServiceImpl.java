 
package fp.art.stroke.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminBoardDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.member.model.vo.Member;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	
	@Autowired
	private AdminBoardDAO dao;
	
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	Logger logger = LoggerFactory.getLogger(AdminBoardServiceImpl.class);
 
	 
	/** 관리자 게시판 리스트
	 *
	 */
	@Override
	public Map<String, Object> selectBoardList(int cp, int adminCode) {

		int listCount = dao.getListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = dao.selectBoardList(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		map.put("adminCode", adminCode);
		
		logger.info("관리자 BOARD 서비스 select" + map);
		
		return map;
	}

	 
	/** 관리자 게시판 검색
	 *
	 */
	@Override
	public Map<String, Object> searchBoardList(Map<String, Object> paramMap) {
		int listCount = dao.searchListCount( paramMap  );
		
	 
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<Board> boardList = dao.searchBoardList(paramMap, pagination);
		
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);

		logger.info("관리자 BOARD 서비스 search" + map);
		
		return map;
	}


	/** 관리자 게시판 삭제
	 *
	 */
	@Override
	public int deleteAdminBoard(List<Integer> boardChk) {
		int result = 0;
		if(boardChk != null) {
			for(Integer boardId : boardChk) {
				result = dao.deleteAdminBoard(boardChk, boardId);
				
				logger.info("업데이트 된 deleteBoard 값" + result);
			}
		}
	
	return result;
}

}
 