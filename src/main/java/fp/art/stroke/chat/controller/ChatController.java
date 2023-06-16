package fp.art.stroke.chat.controller;

import java.util.List;
 
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatRoom;
 
@Controller
@SessionAttributes({"loginMember", "chatRoomId"})
public class ChatController {
	
	@Autowired
	private ChatService service;
	
	@GetMapping("/admin/chat/chatList")
	public String adminChatList(Model model) {
		
		List<ChatRoom> chatRoomList = service.selectChatRoomList();
				
		model.addAttribute("chatRoomList", chatRoomList);
			
		
		return "admin/chatList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
