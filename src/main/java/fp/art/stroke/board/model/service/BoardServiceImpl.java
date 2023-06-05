package fp.art.stroke.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.board.model.dao.BoardDAO;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.member.model.service.MemberService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAO dao;
	@Override
	public List<BoardType> selectBoardType() {
		// TODO Auto-generated method stub
		return dao.selectBoardType();
	}
	@Override
	public Map<String, Object> selectBoardList(int cp, int boardCode) {
		// TODO Auto-generated method stub
		//해당 리스트 전체count값 가져오기.
		int listCount = dao.getListCount(boardCode);
		Pagination pagination = new Pagination(cp,listCount);
		//3. 게시판 목록 조회
		List<Board> boardList = dao.selectBoardList(pagination, boardCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList",boardList);
		map.put("boardCode", boardCode);
		return map;
	}
	@Override
	public BoardDetail selectBoardDetail(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectBoardDetail(boardId);
	}

}
