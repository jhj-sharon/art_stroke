package fp.art.stroke.board.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardType;

public interface BoardService {

	List<BoardType> selectBoardType();

	Map<String, Object> selectBoardList(int cp, int boardCode);

	BoardDetail selectBoardDetail(int boardNo);

	Map<String, Object> selectWriter(int cp);

	Map<String, Object> selectWriterDetail(int memberId);
}
