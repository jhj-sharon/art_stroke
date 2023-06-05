package fp.art.stroke.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.board.model.service.BoardService;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.common.Util;
import fp.art.stroke.member.controller.MemberController;

@Controller // ������ bean�� Contorller���� ��� + bean ���
@SessionAttributes({"loginMember"}) // Model�� �߰��� ���� key�� ������̼ǿ� �ۼ��� ���� ������
@RequestMapping("/board") // localhost:8080/art_stroke/member ������ ��û�� ó���ϴ� ��Ʈ�ѷ�									// �ش� ���� session scope �̵���Ű�� ����
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list/{boardCode}")
	public String boardMember(@PathVariable("boardCode") int boardCode,
							  @RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							  
							  Model model) {
		String path ="";
		Map<String, Object> map = null;
		
		map = service.selectBoardList(cp, boardCode);
		model.addAttribute("map",map);
		
		if (boardCode == 1) {
		    path = "board/memberBoard";
		} else if(boardCode == 2){
		    path = "board/memberBoard";
		}
		
		return path;
	}
	@GetMapping("/boardWrite/{boardCode}")
	public String boardWrite(@PathVariable("boardCode") int boardCode,
							 @RequestParam(value = "type", required = false, defaultValue = "1")String type,
							 @RequestParam(value="no", required = false, defaultValue = "0")int boardId,
							 Model model) {
		if(type == "update") {
			BoardDetail detail = service.selectBoardDetail(boardId);
			detail.setBoardContent(Util.newLineClear(detail.getBoardContent()));
			model.addAttribute("detail",detail);
		}	
		
		return "board/boardWrite";
	}
	@GetMapping("/detail/{boardCode}/{boardId}")
	public String boardDetail(@PathVariable("boardCode") int boardCode,
							  @PathVariable("boardId")int boardId,
							  @RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							  HttpSession session,
								HttpServletRequest req, HttpServletResponse resp,
							  Model model) {
//		int result = 0;
//		
//		BoardDetail detail = service.selectBoardDetail(boardId);
//		if(detail != null) {
//			
//			List<Reply> rList = replyService.selectReplyList(boardNo);
//			model.addAttribute("rList",rList);
//			Member loginMember = (Member)session.getAttribute("loginMember");
//			int memberNo = 0;
//			if(loginMember != null) {
//				loginMember.getMemberNo();
//			}
		return "board/boardDetail";
	}
	
	@GetMapping("/detailWriter")
	public String boardDetailWriter() {
		return "board/boardWriterDetail";
	}
	@GetMapping("/boardBoard")
	public String boardBoard() {
		return "board/boardBoard";
	}
	@GetMapping("/boardBoardDetail")
	public String boardBoardDetail() {
		return "board/boardBoardDetail";
	}
	@GetMapping("/writer")
	public String selectWriter(@RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							   Model model) {
		Map<String, Object> map = service.selectWriter(cp);
		model.addAttribute("map",map);
		return "board/boardWriter";
	}
	@GetMapping("/detailWriter/{memberId}")
	public String detailWriter(@PathVariable("memberId") int memberId,
							   Model model) {
		
		Map<String, Object> map = service.selectWriterDetail(memberId);
		model.addAttribute("map",map);
		return "board/boardWriterDetail";
	}
	

	
}
