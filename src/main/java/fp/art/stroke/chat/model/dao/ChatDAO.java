package fp.art.stroke.chat.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.chat.controller.ChatController;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;


@Repository
public class ChatDAO {

	
	@Autowired
	private SqlSessionTemplate sqlSession;

	private Logger logger = LoggerFactory.getLogger(ChatDAO.class);
	
	public List<ChatRoom> selectChatRoomList() {
		return sqlSession.selectList("chattingMapper.selectChatRoomList");
	}

	public int openChatRoom(ChatRoom room) {
		int result = sqlSession.insert("chattingMapper.openChatRoom", room);
		
		if(result > 0) return room.getChatRoomId();
		return 0; 
	}

	public int deleteChat(List<Integer> chatRoomChk, Integer chatRoomId) {
		Map<String, Object> params = new HashMap<>();
		params.put("chatRoomChk", chatRoomChk);
		params.put("chatRoomId", chatRoomId);
		return sqlSession.delete("chattingMapper.deleteChat", params);
	}
 
	
	
	
}
