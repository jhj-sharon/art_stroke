package fp.art.stroke.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.member.controller.MemberController;


@Repository
public class BoardDAO {
	
	private Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public List<BoardType> selectBoardType() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectBoardType");
	}


	public List<Board> selectBoardList(Pagination pagination, int boardCode) {
		int offset = ( pagination.getCurrentPage()-1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("boardMapper.selectBoardList",boardCode,rowBounds);
	}


	public int getListCount(int boardCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.getListCount",boardCode);
	}


	public BoardDetail selectBoardDetail(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectBoardDetail",boardId);
	}


	public int getListCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectWriterCount");
	}


	public List<Board> selectWriterList(Pagination pagination) {
		// TODO Auto-generated method stub
		int offset = (pagination.getCurrentPage()-1)* pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("memberMapper.selectWriterList",rowBounds);
	}


	public List<Board> getWriterBoardList(int memberId) {

		return sqlSession.selectList("boardMapper.selectWriterBoardList",memberId);
	}


	public int sendLetter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.sendLetter",map);
	}


	public int insertImage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertImage",map);
	}

	//게시판 삽입.
	public int writeBoard(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.writeBoard", detail);
	}


	public int insertBoardImage(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertBoardImage", detail);
	}


	public int deleteBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.deleteBoard",map);
	}

	

}
