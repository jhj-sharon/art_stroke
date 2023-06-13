package fp.art.stroke.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.board.model.service.ReplyService;
import fp.art.stroke.board.model.vo.Reply;
import fp.art.stroke.member.controller.MemberController;

@Controller 
@SessionAttributes({"loginMember"}) 
@RequestMapping("/reply") 	
public class ReplyController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private ReplyService service;
	
	@ResponseBody
	@GetMapping("/selectReplyList")
	public List<Reply> selectReplyList(@RequestParam int boardId,
								  Model model) {
		List<Reply> rList = service.selectReplyList(boardId);
		
		return rList;
	}
	@ResponseBody
	@PostMapping("/insert")
	public int replyInsert(Reply reply
						  ) {
		
		int result = service.replyInsert(reply);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/update")
	public int replyUpdate(Reply reply) {
		int result = service.replyUpdate(reply);
		return result;
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public int replyDelete(Reply reply) {
		int result = service.replyDelete(reply);
		return result;
	}
}
