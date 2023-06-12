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
	
	public List<Reply> selectReplyList(int boardId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("replyMapper.selectReplyList",boardId);
	}
}
