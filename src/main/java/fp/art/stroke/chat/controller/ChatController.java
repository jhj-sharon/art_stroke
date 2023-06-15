package fp.art.stroke.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.chat.model.service.ChatService;
 
@Controller
@RequestMapping("/admin")
@SessionAttributes({"loginMember", "chatRoomId"})
public class ChatController {
	
	@Autowired
	private ChatService service;
	
	@GetMapping("/chatList")
	public String adminChatList() {
		return "admin/chatList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
