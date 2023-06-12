package fp.art.stroke.board.model.service;

import java.util.List;

import fp.art.stroke.board.model.vo.Reply;

public interface ReplyService{

	List<Reply> selectReplyList(int boardId);

}
