package fp.art.stroke.board.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.board.model.dao.ReplyDAO;
import fp.art.stroke.board.model.vo.Reply;

@Service
public class ReplyServiceImple implements ReplyService{
	
	@Autowired
	private ReplyDAO dao;
	@Override
	public List<Reply> selectReplyList(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectReplyList(boardId);
	}
	@Override
	public int replyInsert(Reply reply) {
		
		return dao.replyInsert(reply);
	}
	@Override
	public int replyUpdate(Reply reply) {
		// TODO Auto-generated method stub
		return dao.replyUpdate(reply);
	}
	@Override
	public int replyDelete(Reply reply) {
		// TODO Auto-generated method stub
		return dao.replyDelete(reply);
	}
	
	

}
