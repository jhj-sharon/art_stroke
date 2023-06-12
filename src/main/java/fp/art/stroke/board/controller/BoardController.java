package fp.art.stroke.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.board.model.exception.InsertFailException;
import fp.art.stroke.board.model.service.BoardService;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardImage;
import fp.art.stroke.board.model.vo.PhotoSmart;
import fp.art.stroke.common.Util;
import fp.art.stroke.member.controller.MemberController;
import fp.art.stroke.member.model.vo.Member;

@Controller 
@SessionAttributes({"loginMember"}) 
@RequestMapping("/board") 						
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
		int result = 0;
		
		BoardDetail detail = service.selectBoardDetail(boardId);
		if(detail != null) {
//			
//			List<Reply> rList = replyService.selectReplyList(boardNo);
//			model.addAttribute("rList",rList);
//			Member loginMember = (Member)session.getAttribute("loginMember");
//			int memberNo = 0;
//			if(loginMember != null) {
//				loginMember.getMemberNo();
			model.addAttribute(detail);
		}
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
	
	@PostMapping("/detailWriter/{memberId}/sendLetter")
	public String SendWriterLetter(@PathVariable int memberId,
								   RedirectAttributes ra,	
								   @RequestParam(value = "writerName")String writerName,
								   @RequestParam(value = "sendName")String sendName,
								   @RequestParam(value = "sendTitle")String sendTitle,
								   @RequestParam(value = "sendText")String sendText
								   ) {
		
		int result = 0;
		String message="";
		result = service.sendLetter(memberId,writerName,sendName,sendTitle,sendText);
		
		if(result != 1) {
			message="존재하지 않는 작가입니다.";
			ra.addFlashAttribute("message",message);
		}
		return "redirect:/board/detailWriter/"+memberId;
	}
	
		
	
//	@Transactional(rollbackFor ={Exception.class})
//	@PostMapping("/multiphotoUpload")
//	public void smarteditorMultiImageUpload(@RequestParam("file")MultipartFile imageList,
//											HttpServletRequest request, HttpServletResponse response
//											){
//		try {
//			//파일경로
//			String webPath = "/resources/images/boardImg/";
//			String filePath = request.getSession().getServletContext().getRealPath(webPath);
//			
//				if(!imageList.isEmpty()) {
//					//i번째 요소에 업로드된 이미지가 있을 경우
//					//변경된 파일명 저장
//					//BoardImage 객체를 생성하여 값 세팅후 boardImageList추가
//					//2025024022_20
//					String reName = Util.fileRename(imageList.getOriginalFilename());
//					//BoardImage객체를 생성하여 값 세팅후 boardImageList 추가.
//				
//					imageList.transferTo(new File( filePath + reName ));
//				}else {
//					//이미지삽입 실패시 rollback
//					//강제로 예외를 발생시켜 rollback을 수행하게 함.
//					// -> 사용자 정의 예외
//					throw new InsertFailException();
//				}
////			//파일정보
////			String sFileInfo = "";
////			//파일명을 받는다 - 일반 원본파일명
////			String sFilename = request.getHeader("file-name");
////			//파일 확장자
////			String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
////			//확장자를소문자로 변경
////			sFilenameExt = sFilenameExt.toLowerCase();
////				
////			//이미지 검증 배열변수
////			String[] allowFileArr = {"jpg","png","bmp","gif"};
////
////			//확장자 체크
////			int nCnt = 0;
////			for(int i=0; i<allowFileArr.length; i++) {
////				if(sFilenameExt.equals(allowFileArr[i])){
////					nCnt++;
////				}
////			}
////
////			//이미지가 아니라면
////			if(nCnt == 0) {
////				PrintWriter print = response.getWriter();
////				print.print("NOTALLOW_"+sFilename);
////				print.flush();
////				print.close();
////			} else {
//				//디렉토리 설정 및 업로드	
//				
//				
////				logger.info(filePath);
////				String reName = Util.fileRename(sFilename.get)
////				
////         		File file = new File(filePath);
////				
////				if(!file.exists()) {
////					file.mkdirs();
////				}
////				
////				String sRealFileNm = "";
////				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
////				String today= formatter.format(new java.util.Date());
////				sRealFileNm = today+UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
//				
////				String rlFileNm = filePath + sRealFileNm;
////				
////				///////////////// 서버에 파일쓰기 ///////////////// 
////				InputStream inputStream = request.getInputStream();
////				OutputStream outputStream=new FileOutputStream(rlFileNm);
////				int numRead;
////				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
////				while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
////					outputStream.write(bytes,0,numRead);
////				}
////				if(inputStream != null) {
////					inputStream.close();
////				}
////				outputStream.flush();
////				outputStream.close();
////				
////				///////////////// 이미지 /////////////////
////				// 정보 출력
////				sFileInfo += "&bNewLine=true";
////				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
////				sFileInfo += "&sFileName="+ sFilename;
////				sFileInfo += "&sFileURL="+filePath+sRealFileNm;
//				PrintWriter printWriter = response.getWriter();
//				printWriter.print(imageList.getOriginalFilename());
//				printWriter.flush();
//				
//		
//				printWriter.close();
////			}	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
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
	
	@PostMapping("/multiphotoUpload")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String sFileInfo = "";
			
			String filename = request.getHeader("file-name");
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
			
			filename_ext = filename_ext.toLowerCase();
			
			String dftFilePath = request.getSession().getServletContext().getRealPath("/");
			String filePath = dftFilePath + "resources" + File.separator + "images" + File.separator+"boardImg" +File.separator;
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
			sFileInfo += "&sFileURL=" + "/comm/resources/images/boardImg/"+realFileNm;
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping("/write/{boardCode}")
	public String writeBoard(@PathVariable int boardCode,
							 @ModelAttribute Member loginMember,
							 @RequestParam String title,
							 @RequestParam String smartEditor) {
			
			
			int memberId = loginMember.getMemberId();
			String memberNick = loginMember.getMemberNick();
			int result = service.writeBoard(boardCode,title,smartEditor,memberId,memberNick);
			String path="";
			if(result>0) {
				path = "redirect:/board/list/"+boardCode;
			}else {
				path = "redirect:/boardWrite/"+"?type=insert";
			}
		return path;
	}
	
	@PostMapping("/delete/{boardCode}")
	public String deleteBoard(@PathVariable int boardCode,
							  @RequestParam(value = "no")int no){
		int result = service.deleteBoard(boardCode,no);
		String path ="";
		if(result ==1) {
			path = "'redirect:/board/list/'+ boardCode ";
		}else {
			
		}
		return "redirect:/board/list/"+boardCode;
	}
	
	
	
	
		
}
				
