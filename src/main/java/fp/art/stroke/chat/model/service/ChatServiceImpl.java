package fp.art.stroke.chat.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.chat.model.dao.ChatDAO;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;
import fp.art.stroke.myPage.controller.MyPageController;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO dao;
	private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
	/**
	 * 채팅방 번호 생성
	 */
	@Override
	public int getChatRoomId(int memberId) {
		int chatRoomId = dao.selectChatRoomIdByMemberId(memberId);
		if (chatRoomId == 0) {
			ChatRoom newChatRoom = new ChatRoom();
			newChatRoom.setMemberId(memberId);
			dao.insertChatRoom(newChatRoom);
			
			chatRoomId = dao.selectChatRoomIdByMemberId(memberId);			

			return chatRoomId;
		}
		return chatRoomId;
	}
	@Override
	public int insertChatMessage(int memberId, String memberEmail, String memberNick, String inputVal, int chatRoomId) {
		
		return dao.insertChatMessage(memberId, memberEmail, memberNick, inputVal, chatRoomId);
	}
	
}
