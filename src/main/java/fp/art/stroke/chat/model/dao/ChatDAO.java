package fp.art.stroke.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;

@Repository
public class ChatDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/** 채팅방 만들기
	 * @param room
	 * @return chatRoomNo
	 */
	public int openChatRoom(ChatRoom room) {
		
		int result = sqlSession.insert("chattingMapper.openChatRoom", room);
		
		if(result > 0) return room.getChatRoomId();
		return 0; 
	}

	
	/** 채팅방 참여 여부 확인
	 * @param join
	 * @return result
	 */
	public int joinCheck(ChatRoomJoin join) {
		return sqlSession.selectOne("chattingMapper.joinCheck", join);
	}
	
	
	/** 채팅방 참여하기
	 * @param join
	 */
	public void joinChatRoom(ChatRoomJoin join) {
		sqlSession.insert("chattingMapper.joinChatRoom", join);
	}

	
	/** 채팅 메세지 목록 조회
	 * @param chatRoomNo
	 * @return list
	 */
	public List<ChatMessage> selectChatMessage(int chatRoomNo) {
		return sqlSession.selectList("chattingMapper.selectChatMessage", chatRoomNo);
	}
	
	
	
	
	
}
