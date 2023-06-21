package fp.art.stroke.chat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;
import fp.art.stroke.member.model.vo.Member;

@Controller  
public class ChatController {

	@Autowired
	private ChatService service;
 
	private Logger logger = LoggerFactory.getLogger(ChatController.class);
	
 
	@GetMapping("/admin/chat/chatList")
	public String adminChatList(Model model) {
		
		List<ChatRoom> chatRoomList = service.selectChatRoomList();
				
		model.addAttribute("chatRoomList", chatRoomList);
			 
		logger.info("챗챗룸리스트 " + chatRoomList);
		return "admin/chatList";
	}
	
	
	/** 관리자 채팅 삭제
	 * @param chatRoomChk
	 * @return
	 */
	@ResponseBody
	@PostMapping("deleteChat")
	public String deleteChat(@RequestParam(value="chatRoomChk", required=false) List<Integer> chatRoomChk ) {
		logger.info("챗챗 삭제" + chatRoomChk);
		
		int result = 0;
		
		if(chatRoomChk != null) {
			result = service.deleteChat(chatRoomChk);
			
			logger.info("챗챗 삭제222" + chatRoomChk);
		}
		
		return new Gson().toJson(result);
		
	}
	 
	
//	   @ResponseBody
//	   @PostMapping("/insertChatMessage")
//	   public int insertChatMessage(@ModelAttribute("loginMember") Member loginMember,
//	                        @RequestParam("inputVal") String inputVal,
//	                        @RequestParam("chatId") String chatId) {
//	      int memberId = loginMember.getMemberId();
//	      String memberEmail = loginMember.getMemberEmail();
//	      String memberNick = loginMember.getMemberNick();
//	      
//	      int chatRoomId = Integer.parseInt(chatId);
//	      int result = service.insertChatMessage(memberId, memberEmail, memberNick, inputVal, chatRoomId);
//	      
//	      return result;
//	   }
 

//	@GetMapping("/admin/chat/chatList")
//	public String adminChatList(Model model) {
//
//		List<ChatRoom> chatRoomList = service.selectChatRoomList();
//
//		model.addAttribute("chatRoomList", chatRoomList);
//
//		return "admin/chatList";
//	}

//	// 채팅방 만들기
//	@ResponseBody
//	@GetMapping("/openChatRoom")
//	public int openChatRoom(@ModelAttribute("loginMember") Member loginMember, Model model, ChatRoom room,
//			RedirectAttributes ra, ChatRoomJoin join) {
//
//		room.setMemberId(loginMember.getMemberId());
//
//		int chatRoomId = service.openChatRoom(room);
//
//		join.setChatRoomId(chatRoomId);
//		join.setMemberId(loginMember.getMemberId());
//		List<ChatMessage> list = service.joinChatRoom(join);
//		int result = 0;
//		if (list != null) {
//			model.addAttribute("list", list);
//			model.addAttribute("chatRoomId", chatRoomId); // session에 올림
//			result = 1;
//		} else {
//			ra.addFlashAttribute("message", "채팅방이 존재하지 않습니다.");
//			result = 0;
//		}
//		return result;
//	}

//	// 채팅방 입장
//	@GetMapping("/chat/room/{chatRoomId}")
//	public String joinChatRoom(@ModelAttribute("loginMember") Member loginMember, Model model,
//			@PathVariable("chatRoomId") int chatRoomId, ChatRoomJoin join, RedirectAttributes ra) {
//
//		join.setMemberId(loginMember.getMemberId());
//		
//
//		if (list != null) {
//			model.addAttribute("list", list);
//			model.addAttribute("chatRoomId", chatRoomId); // session에 올림
//			return "chat/chatRoom";
//		} else {
//			ra.addFlashAttribute("message", "채팅방이 존재하지 않습니다.");
//			return "redirect:../roomList";
//		}
//	}
 
 
	
	
	
   
}
