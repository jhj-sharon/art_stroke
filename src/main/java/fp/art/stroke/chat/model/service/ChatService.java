package fp.art.stroke.chat.model.service;

import java.util.List;

import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;

public interface ChatService {

	/**
	 * 채팅방 번호 생성
	 * @param memberId
	 * @return
	 */
	int getChatRoomId(int memberId);
	/**
	 * 메시지 삽입 서비스
	 * @param memberId
	 * @param memberEmail
	 * @param memberNick
	 * @param inputVal
	 * @param chatRoomId
	 * @return
	 */
	int insertChatMessage(int memberId, String memberEmail, String memberNick, String inputVal, int chatRoomId);



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
