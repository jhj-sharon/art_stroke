package fp.art.stroke.board.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.board.model.vo.Alarm;
import fp.art.stroke.board.model.vo.AlarmDetail;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardGood;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.member.model.vo.Follow;

public interface BoardService {

	List<BoardType> selectBoardType();

	Map<String, Object> selectBoardList(int cp, int boardCode);

	BoardDetail selectBoardDetail(int boardNo);

	Map<String, Object> selectWriter(int cp);

	Map<String, Object> selectWriterDetail(int memberId);

	

	int storeImage(Map<String, Object> map) throws Exception;

	int writeBoard(int boardCode, String title, String smartEditor, int memberId,String memberNick, String type,int boardId, String memberProfileImage);

	int deleteBoard(int boardCode, int no);

	int reportIt(Report report);

	int insertGood(BoardDetail detail);

	int sendLetter(Message message);

	List<BoardGood> selectBoardGoodList(int boardId);

	int deleteGood(BoardDetail detail);

	int selectGoodList(BoardDetail detail);

	int updateBoardGood(BoardDetail detail);

	List<Follow> selectFollowList(int boardId);

	int updateReadCount(int boardId);

	List<Follow> selectmemberFollowList(int memberId);

	Map<String, Object> selectBoardSortList(int cp, int boardCode, String sort, String key, String query);

	Map selectBoardSearchList(int boardCode, int cp, String key, String query);

	AlarmDetail selectAlarm(int boardId);

	Map<String, Object> selectAlarmList(int cp, String key, String query);

	int writeAlarm(AlarmDetail alarm, String type, int alarmId);

	int updateAlarmCnt(int alarmId);

	Alarm selectPrevAlarm(int alarmId);

	Alarm selectNextAlarm(int alarmId);





}
