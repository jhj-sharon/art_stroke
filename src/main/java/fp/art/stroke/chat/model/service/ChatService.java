package fp.art.stroke.chat.model.service;

import java.util.List;

import fp.art.stroke.chat.model.vo.ChatRoom;

public interface ChatService {

	List<ChatRoom> selectChatRoomList();

}
