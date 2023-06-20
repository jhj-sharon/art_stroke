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

//	@Override
//	public List<ChatRoom> selectChatRoomList() {
//		return dao.selectChatRoomList();
//	}
	

	// 채팅방 만들기
//	@Override
//	public List<ChatRoom> openChatRoom(ChatRoom room) {
//		  
//		if(room != null) {
//			//List<ChatRoom> selectChatId = dao.selectChatId(room);
//			
//			//logger.info("서비스챗챗챗 "+selectChatId);
//		}else {
//		 
//		}
//		logger.info("서비스룸 "+selectChatRoomList());
//		
//		return selectChatRoomList();
//	}
//
//	// 채팅방 입장 + 내용 얻어오기
//	@Override
//	public List<ChatMessage> joinChatRoom(ChatRoomJoin join) {

//		// 현재 회원이 해당 채팅방에 참여하고 있는지 확인
//		int result = dao.joinCheck(join);
//
//		if (result == 0) { // 참여하고 있지 않은 경우 참여
//			dao.joinChatRoom(join);
//		}
//
//		// 채팅 메세지 목록 조회
//		return dao.selectChatMessage(join.getChatRoomId());
//	}


}
