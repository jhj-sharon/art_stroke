package fp.art.stroke.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.emory.mathcs.backport.java.util.Arrays;
import fp.art.stroke.board.model.exception.InsertFailException;
import fp.art.stroke.board.model.service.BoardService;
import fp.art.stroke.board.model.service.ReplyService;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardGood;
import fp.art.stroke.board.model.vo.BoardImage;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.board.model.vo.PhotoSmart;
import fp.art.stroke.board.model.vo.Reply;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.common.Util;
import fp.art.stroke.member.controller.MemberController;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;

@Controller 
@SessionAttributes({"loginMember"}) 
@RequestMapping("/board") 						
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyService replyService;
	
	
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
	//insert와 update를 하기위해 스마트에디터로이동
	@GetMapping("/boardWrite/{boardCode}")
	public String boardWrite(@PathVariable("boardCode") int boardCode,
							 @RequestParam(value = "type", required = false, defaultValue = "1")String type,
							 @RequestParam(value="no", required = false, defaultValue = "0")int boardId,
							 Model model) {
		 
		if(type.equals("update")) {
			
			BoardDetail detail = service.selectBoardDetail(boardId);
			detail.setBoardContent(Util.newLineClear(detail.getBoardContent()));
			logger.info(detail.getBoardContent());
			model.addAttribute("detail",detail);
		}
		
		return "board/boardWrite";
	}
	
	//게시판 상세페이지 이동
	@GetMapping("/detail/{boardCode}/{boardId}")
	public String boardDetail(@PathVariable("boardCode") int boardCode,
							  @PathVariable("boardId")int boardId,
							  @RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							  HttpSession session,
								HttpServletRequest req, HttpServletResponse resp,
							  Model model) {
		int result = 0;
		Map<String,Object> map = new HashMap();
		BoardDetail detail = service.selectBoardDetail(boardId);
		List<Reply> reply = replyService.selectReplyList(boardId);
		List<BoardGood> gList = service.selectBoardGoodList(boardId);
		List<Follow> fList = service.selectFollowList(boardId);
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberId = 0;
		if(loginMember != null) {
			memberId = loginMember.getMemberId();
		}
		if(detail.getMemberId() != memberId) {
			Cookie cookie = null;
			Cookie[] cArr = req.getCookies();
			if(cArr != null & cArr.length >0) {
				for(Cookie c: cArr) {
					if(c.getName().equals("readBoardId")) {
						cookie = c;
					}
				}
			}
			if(cookie == null) {
				cookie = new Cookie("readBoardId",boardId+"");
				result = service.updateReadCount(boardId);
			}else {
				String[] temp = cookie.getValue().split("/");
				List<String> list = Arrays.asList(temp);
				if(list.indexOf(boardId+"") == -1) {
					cookie.setValue(cookie.getValue()+"/"+boardId);
					result = service.updateReadCount(boardId);
				}
			}
			if(result>0) {
				detail.setReadCount(detail.getReadCount()+1);
				cookie.setPath(req.getContextPath());
				cookie.setMaxAge(60*60*1);
				resp.addCookie(cookie);
			}
		}
		
		
		
		
		
		
		map.put("detail", detail);
		model.addAttribute("rList",reply);
		model.addAttribute("gList",gList);
		map.put("rList", reply);
		model.addAttribute("fList",fList);
		model.addAttribute("map",map);
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
	//작가 종합페이지 이동
	@GetMapping("/writer")
	public String selectWriter(@RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							   Model model) {
		Map<String, Object> map = service.selectWriter(cp);
		model.addAttribute("map",map);
		return "board/boardWriter";
	}
	//작가 상세페이지 이동
	@GetMapping("/detailWriter/{memberId}")
	public String detailWriter(@PathVariable("memberId") int memberId,
							   Model model) {
		
		Map<String, Object> map = service.selectWriterDetail(memberId);
		List<Follow> fList = service.selectmemberFollowList(memberId);
		map.put("fList", fList);
		model.addAttribute("map",map);
		return "board/boardWriterDetail";
	}
	//쪽지보내기
	@PostMapping("/detailWriter/{memberId}/sendLetter")
	public String SendWriterLetter(@PathVariable int memberId,
								   RedirectAttributes ra,	
								   Message message,
								   HttpSession session
								   
								   ) {
		
		int result = 0;
	
		String me = "";
		result = service.sendLetter(message);
		
		if(result != 1) {
			me="존재하지 않는 작가입니다.";
			ra.addFlashAttribute("me",me);
		}
		return "redirect:/board/detailWriter/"+ memberId;
	}
	
		
	

	
	//사진 단일 업로드
	@PostMapping("/write/img")
	public String photoUpload(HttpServletRequest request, PhotoSmart vo) {
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		
		try {
			if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename()!=null && ! vo.getFiledata().getOriginalFilename().equals("")) {
				//파일이 존재하면
				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".")+1);
				//파일 기본경로
				String defaultPath = request.getSession().getServletContext().getRealPath("/");
				//파일 기본경로_상세경로
				String path = defaultPath + "resources" + File.separator + "images" + File.separator +"boardImg" + File.separator;
				File file = new File(path);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				String realname = UUID.randomUUID().toString() + "." + ext;
				
				vo.getFiledata().transferTo(new File(path+realname));
				file_result += "&bNewLine=true&sFileName=" +original_name + "&sFileURL=/comm/resources/images/boardImg/"+realname;
				
			}else {
				file_result+= "&errstr=error";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func="+callback_func+file_result;
	}
	//사진 멀티 업로드
	@PostMapping("/multiphotoUpload")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String sFileInfo = "";
			
			String filename = request.getHeader("file-name");
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
			
			filename_ext = filename_ext.toLowerCase();
			
			String dftFilePath = request.getSession().getServletContext().getRealPath("resources");
			//String dftFilePath = "\\art_stroke";
			logger.info(dftFilePath);
			// application 내장 객체 얻어오기
			
			String filePath = dftFilePath + File.separator + "images" + File.separator+"boardImg" +File.separator;
			logger.info(filePath);
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyHHmmss");
			String today = formatter.format(new java.util.Date());
			realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + realFileNm;
			//서버에 파일쓰기
			
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while((numRead = is.read(b,0,b.length)) != -1) {
				os.write(b,0,numRead);
			}
			if(is != null) {
				is.close();
			}
			os.flush();
			os.close();
			sFileInfo+="&bNewLine=true";
			sFileInfo += "&sFileName="+ filename;;
			sFileInfo += "&sFileURL="+"/stroke/resources/images/boardImg/"+realFileNm;
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시물 등록 및 수정
	@PostMapping("/write/{boardCode}")
	public String writeBoard(@PathVariable int boardCode,
							 @RequestParam String title,
							 @RequestParam String smartEditor,
							 @RequestParam(value = "type", required=false, defaultValue = "insert")String type,
							 @RequestParam(value = "no", required=false, defaultValue = "0")int boardId,
							 HttpSession session) {
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberId = loginMember.getMemberId();
			String memberProfileImage = loginMember.getProfileImage();
			String memberNick = loginMember.getMemberNick();
			//smartEditor=smartEditor.substring("/comm".length());
			int result = service.writeBoard(boardCode,title,smartEditor,memberId,memberNick,type,boardId,memberProfileImage);
			
			String path="";
			
				path = "redirect:/board/list/"+boardCode;
			
		return path;
	}
	
	@GetMapping("/delete/{boardCode}")
	public String deleteBoard(@PathVariable int boardCode,
							  @RequestParam(value = "no")int no){
		int result = service.deleteBoard(boardCode,no);
		String path ="";
		if(result ==1) {
			path = "'redirect:/board/list/'+ boardCode";
		}else {
			
		}
		return "redirect:/board/list/"+boardCode;
	}
	
	@GetMapping("/report/{boardCode}")
	public String reportBoard(@PathVariable int boardCode,
							  @RequestParam(value = "no")int no,
							  @RequestParam(value = "type", required = false)String type,
							  Model model){
		BoardDetail board;
		Reply reply;
		if(type.equals("board")) {
			board = service.selectBoardDetail(no);
			logger.info(board.getBoardTitle());
			model.addAttribute("board",board);
		}else if(type.equals("reply")) {
			reply = replyService.selectReply(no);
			model.addAttribute("reply",reply);
		}

		return "common/boardReport";
	}
	//신고기능.
	//no는 댓글, 게시판번호
	//type은 댓글인지 게시판인지 유무 판가름
	@PostMapping("/reportDetail/{boardCode}")
	public String report(@PathVariable int boardCode,
						 @RequestParam int no,
						 @RequestParam String type,
						 Report report,
						 HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		report.setReportTargetType(type);

		report.setReportTarget(no);
		report.setReportSendId(member.getMemberId());
		report.setReportSendNick(member.getMemberNick());
		int result = service.reportIt(report);
		return "common/close";
	}
	@ResponseBody
	@PostMapping("/ddobongDochi")
	public int ddoBongdochi(BoardDetail detail) {
		
		int result = service.insertGood(detail);
		
		if(result>0) {
			
			result = service.selectGoodList(detail);
			//난 서브쿼리로 가져와야한다고 생각함.
			//얘는 조회수같이 같은 테이블에서 관리하는게 아니라 다른 테이블에서 유저까지 넣어서
			//데이터베이스에 넣는 과정이 더 많아짐. 내가 데이터베이스에 많다고 생각하는 기준은
			//페이지의 이동처럼 한번 한번 할 수 있는 수준보다는 비동기처럼 한꺼번에 많이 하는 기준을 의미.
			//하지만 하겠음.
			//하지만 여기 페이지에서는 서브쿼리로 갯수를 입력해온 것을 뿌려오므로 참고되지 않을 거임.
			//BoardDetail에서 가져오면 됨.
			detail.setBoardGood(result);
			int temp = service.updateBoardGood(detail);
		}
		return result;
	}
	
	@ResponseBody
	@PostMapping("/deleteGood")
	public int deleteGood(BoardDetail detail) {
		
		int result = service.deleteGood(detail);
		if(result>0) {
			result = service.selectGoodList(detail);
			detail.setBoardGood(result);
			int temp = service.updateBoardGood(detail);
		}
		return result;
	}
	
	//필터적용
	@ResponseBody
	@PostMapping("/boardSorting")
	public Map boardSorting(@RequestParam(value = "sort")String sort,
							@RequestParam int boardCode,
							@RequestParam(value = "cp", required = false, defaultValue = "1")int cp,
							Map<String,Object> map) {
		logger.info(cp+"");
		logger.info(sort+"값은 제대로 들어가지?");
		map = service.selectBoardSortList(cp, boardCode, sort);
		map.put("sort",sort);
		return map;
	}
	
	
	
	
		
}
				
