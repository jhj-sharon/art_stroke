package fp.art.stroke.chat.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


	public int selectChatRoomIdByMemberId(int memberId) {
		Integer chatRoomId = sqlSession.selectOne("chattingMapper.selectChatRoomIdByMemberId", memberId);
		return chatRoomId != null ? chatRoomId : 0;
}



	public int insertChatRoom(ChatRoom newChatRoom) {
		sqlSession.insert("chattingMapper.insertChatRoom", newChatRoom);
		return newChatRoom.getChatRoomId();
	}

	public int insertChatMessage(int memberId, String memberEmail, String memberNick, String inputVal, int chatRoomId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberEmail", memberEmail);
		map.put("memberNick", memberNick);
		map.put("inputVal", inputVal);
		map.put("chatRoomId", chatRoomId);
		
		return sqlSession.insert("chattingMapper.insertChatMessage", map);
		
	}



	public int deleteChat(List<Integer> chatRoomChk, Integer chatRoomId) {
	    // 채팅 메시지 삭제를 위한 매개변수 설정
	    Map<String, Object> params = new HashMap<>();
	    params.put("chatRoomChk", chatRoomChk);
	    params.put("chatRoomId", chatRoomId);
	    
	    return  sqlSession.update("chattingMapper.deleteChatMessagesByChatRoomIds", params);
	}

	
	/**
	 * 채팅 기록 가져오기
	 * @param chatRoomId
	 * @return
	 */
	public List<ChatMessage> getChatMessagesByChatRoomId(int chatRoomId) {
	
		return sqlSession.selectList("chattingMapper.getChatMessagesByChatRoomId", chatRoomId);
	}
 
}	
	
	
 

	
	
 
