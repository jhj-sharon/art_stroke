package fp.art.stroke.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;
import fp.art.stroke.member.model.vo.Member;

@Controller
@SessionAttributes({ "loginMember", "chatRoomId" })
public class ChatController {

	@Autowired
	private ChatService service;

	@GetMapping("/chatList")
	public String adminChatList() {
		return "admin/chatList";
	}

	// 채팅방 만들기
	@PostMapping("/chat/openChatRoom")
	public String openChatRoom(@ModelAttribute("loginMember") Member loginMember, Model model, ChatRoom room,
			RedirectAttributes ra) {

		room.setMemberId(loginMember.getMemberId());

		int chatRoomNo = service.openChatRoom(room);

		String path = "redirect:/chat/";

		if (chatRoomNo > 0) {
			path += "room/" + chatRoomNo;
		} else {
			path += "roomList";
			ra.addFlashAttribute("message", "채팅방 만들기 실패");
		}

		return path;
	}

	// 채팅방 입장
	@GetMapping("/chat/room/{chatRoomNo}")
	public String joinChatRoom(@ModelAttribute("loginMember") Member loginMember, Model model,
			@PathVariable("chatRoomNo") int chatRoomNo, ChatRoomJoin join, RedirectAttributes ra) {

		join.setMemberId(loginMember.getMemberId());
		List<ChatMessage> list = service.joinChatRoom(join);

		if (list != null) {
			model.addAttribute("list", list);
			model.addAttribute("chatRoomNo", chatRoomNo); // session에 올림
			return "chat/chatRoom";
		} else {
			ra.addFlashAttribute("message", "채팅방이 존재하지 않습니다.");
			return "redirect:../roomList";
		}
	}

}
