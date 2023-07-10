package fp.art.stroke.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.controller.ReplyController;
import fp.art.stroke.board.model.vo.Reply;

@Repository
public class ReplyDAO {
	private Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	//댓글 리스트 출력
	public List<Reply> selectReplyList(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("replyMapper.selectReplyList",boardId);
	}
	//댓글 삽입
	public int replyInsert(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.insert("replyMapper.replyInsert",reply);
	}
	//댓글수정
	public int replyUpdate(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.update("replyMapper.replyUpdate",reply);
	}
	//댓글삭제
	public int replyDelete(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.update("replyMapper.replyDelete",reply);
	}
	//신고하기에서 댓글신고
	public Reply selectReply(int replyId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("replyMapper.selectReply",replyId);
	}
}
