package fp.art.stroke.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.chat.model.dao.ChatDAO;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;


@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO dao;


	@Override
	public List<ChatRoom> selectChatRoomList() {
		return dao.selectChatRoomList();
	}
	

	// 채팅방 만들기
	@Override
	public int openChatRoom(ChatRoom room) {
		return dao.openChatRoom(room);
	}

	// 채팅방 입장 + 내용 얻어오기
	@Override
	public List<ChatMessage> joinChatRoom(ChatRoomJoin join) {

		// 현재 회원이 해당 채팅방에 참여하고 있는지 확인
		int result = dao.joinCheck(join);

		if (result == 0) { // 참여하고 있지 않은 경우 참여
			dao.joinChatRoom(join);
		}

		// 채팅 메세지 목록 조회
		return dao.selectChatMessage(join.getChatRoomId());
	}


}
