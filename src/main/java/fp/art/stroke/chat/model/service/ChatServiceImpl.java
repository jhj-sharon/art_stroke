package fp.art.stroke.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.chat.model.dao.ChatDAO;
import fp.art.stroke.chat.model.vo.ChatRoom;
 

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDAO dao;

	@Override
	public List<ChatRoom> selectChatRoomList() {
		return dao.selectChatRoomList();
	}
	
	
}
