package fp.art.stroke.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Alarm;
import fp.art.stroke.board.model.vo.AlarmDetail;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardGood;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.member.controller.MemberController;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;


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


	public int sendLetter(Message message) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.sendLetter",message);
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


	public int updateBoard(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateBoard",detail);
	}


	public int updateBoardImage(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateBoardImage",detail);
	}


	public int deleteBeforeImage(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.deleteBeforeImage",boardId);
	}


	public int reportIt(Report report) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.reportIt",report);
	}


	public int insertGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertGood",detail);
	}


	public int selectMemberGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectMemberGood",detail);
	}


	public List<BoardGood> selectBoardGoodList(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectBoardGoodList",boardId);
	}


	public int deleteGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.delete("boardMapper.deleteGood",detail);
	}


	public int selectGoodList(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectGoodList",detail);
	}


	public int updateBoardGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateBoardGood",detail);
	}


	public List<Follow> selectFollowList(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectFollowList",boardId);
	}


	public List<Board> selectBestList(int boardCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectBestList",boardCode);
	}


	public int updateReadCount(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateReadCount",boardId);
	}


	public List<Follow> selectmemberFollowList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectmemberFollowList",memberId);
	}

	//게시판 정렬 기능(검색후 포함)
	public List<Board> selectBoardSortList(Pagination pagination, int boardCode, String sort, String key, String query) {
	    int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	    Map<String, Object> map = new HashMap<>();
	    
	    map.put("boardCode", boardCode);
	    map.put("sort", sort);
	    map.put("key", key);
	    map.put("query", query);
	    return sqlSession.selectList("boardMapper.selectBoardSortList", map, rowBounds);
	}

	//게시판 검색기능.
	public List<Board> selectBoardSearchList(Pagination pagination, int boardCode, String query, String key) {
		// TODO Auto-generated method stub
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
	    RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	    Map<String, Object> map = new HashMap<>();
	    map.put("key", key);
	    map.put("query", query);
	    map.put("boardCode", boardCode);
	    
		return sqlSession.selectList("boardMapper.selectBoardSearchList",map,rowBounds);
	}


	public int getListSearchCount(int boardCode, String query, String key) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("query", query);
		map.put("key", key);
		return sqlSession.selectOne("boardMapper.getListSearchCount",map);
	}


	public AlarmDetail selectAlarm(int boardId) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("boardMapper.selectAlarm",boardId);
	}


	public List<Alarm> selectAlarmList(Pagination pagination, String key, String query) {
		// TODO Auto-generated method stub
		
		logger.info(key+"sdf" + query);
		int offset = ( pagination.getCurrentPage()-1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		Map<String,Object> map = new HashMap();
		map.put("key", key);
		map.put("query", query);
		return sqlSession.selectList("boardMapper.selectAlarmList",map,rowBounds);
	}






	public int updateAlarm(AlarmDetail alarm) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateAlarm",alarm);
	}


	public int insertAlarm(AlarmDetail alarm) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertAlarm",alarm);
	}


	public int deleteBeforeAlarmImage(int alarmId) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.deleteBeforeAlarmImage",alarmId);
	}


	public int updateAlarmImage(AlarmDetail alarm) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.updateAlarmImage",alarm);
	}


	public int insertAlarmImage(AlarmDetail alarm) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertAlarmImage",alarm);
	}


	public int getListAlarmCount(String query, String key) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("query", query);
		map.put("key", key);
		return sqlSession.selectOne("boardMapper.getListAlarmCount",map);
	}


	public int updateAlarmCnt(int alarmId) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateAlarmCnt",alarmId);
	}


	public Alarm selectPrevAlarm(int alarmId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectPrevAlarm",alarmId);
	}


	public Alarm selectNextAlarm(int alarmId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectNextAlarm",alarmId);
	}


	public int writerIntro(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.writerIntro",member);
	}


	




	

}
