package fp.art.stroke.chat.model.service;

import java.util.List;

import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;

public interface ChatService {
	
	/** 채팅 목록 조회
	 * @return chatRoomList
	 */
	List<ChatRoom> selectChatRoomList();

	int openChatRoom(ChatRoom room);
	
	/** 관리자 채팅 삭제
	 * @param chatRoomChk
	 * @return
	 */
	int deleteChat(List<Integer> chatRoomChk);

 
}
