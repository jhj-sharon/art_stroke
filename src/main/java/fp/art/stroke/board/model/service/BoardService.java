package fp.art.stroke.board.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Report;

public interface BoardService {

	List<BoardType> selectBoardType();

	Map<String, Object> selectBoardList(int cp, int boardCode);

	BoardDetail selectBoardDetail(int boardNo);

	Map<String, Object> selectWriter(int cp);

	Map<String, Object> selectWriterDetail(int memberId);

	int sendLetter(int memberId, String writerName, String sendName, String sendTitle, String sendText);

	int storeImage(Map<String, Object> map) throws Exception;

	int writeBoard(int boardCode, String title, String smartEditor, int memberId,String memberNick, String type,int boardId, String memberProfileImage);

	int deleteBoard(int boardCode, int no);

	int reportIt(Report report);

}
