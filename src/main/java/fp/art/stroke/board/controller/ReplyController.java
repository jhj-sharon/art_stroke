package fp.art.stroke.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.board.model.service.BoardService;
import fp.art.stroke.board.model.service.ReplyService;
import fp.art.stroke.member.controller.MemberController;

@Controller 
@SessionAttributes({"loginMember"}) 
@RequestMapping("/reply") 	
public class ReplyController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private ReplyService service;
}
