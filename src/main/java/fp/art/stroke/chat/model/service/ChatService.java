package fp.art.stroke.chat.model.service;

import java.util.List;

import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;

public interface ChatService {

	int openChatRoom(ChatRoom room);

	List<ChatMessage> joinChatRoom(ChatRoomJoin join);

}
