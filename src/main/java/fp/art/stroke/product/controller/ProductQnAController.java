package fp.art.stroke.product.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.board.model.vo.PhotoSmart;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.service.ProductQnAService;
import fp.art.stroke.product.model.service.ProductService;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductQnA;

@Controller
@RequestMapping("product/productDetailQnA")
@SessionAttributes({"loginMember"})
public class ProductQnAController {
	
	@Autowired
	private ProductQnAService service;
	@Autowired
	private ProductService productService;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
   @GetMapping("/productQnAWrite")
   public String productQnAWrite(@RequestParam(value = "productId")int productId,
		   						 Model model) {
	   Product product = productService.getProductById(productId);
	   model.addAttribute("product",product);
	   
	   return"product/productQnAWrite";
   }
   
 //사진 단일 업로드
 		@PostMapping("/singleImage")
 		public String photoUpload2(HttpServletRequest request, PhotoSmart vo) {
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
 					String path = defaultPath + "resources" + File.separator + "img" + File.separator +"productQnaImg" + File.separator;
 					File file = new File(path);
 					
 					if(!file.exists()) {
 						file.mkdirs();
 					}
 					String realname = UUID.randomUUID().toString() + "." + ext;
 					
 					vo.getFiledata().transferTo(new File(path+realname));
 					file_result += "&bNewLine=true&sFileName=" +original_name + "&sFileURL=/stroke/resources/img/productImg/"+realname;
 					
 				}else {
 					file_result+= "&errstr=error";
 				}
 			}catch(Exception e) {
 				e.printStackTrace();
 			}
 			return "redirect:" + callback + "?callback_func="+callback_func+file_result;
 		}
 //공지사항 멀티 업로드
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
 				
 				String filePath = dftFilePath + File.separator + "img" + File.separator+"productQnaImg" +File.separator;
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
 				sFileInfo += "&sFileURL="+"/stroke/resources/img/productQnaImg/"+realFileNm;
 				PrintWriter print = response.getWriter();
 				print.print(sFileInfo);
 				print.flush();
 				print.close();
 			}catch(Exception e) {
 				e.printStackTrace();
 			}
 		}

	
	//qna글쓰기
	@PostMapping("/productQnAWrite")
	public String insertProductQna(@RequestParam(value ="productId")int productId,
								   ProductQnA qna, //qna에 대한 모든 정보 
								   @ModelAttribute("loginMember") Member loginMember,
								   HttpServletRequest req,
								   RedirectAttributes ra) throws IOException{
		
		//1) 로그인한 회원 정보 얻어와서 qna에 세팅
		qna.setMemberNick( loginMember.getMemberNick());
		qna.setMemberId( loginMember.getMemberId());
		
//		//2) 작성시간 세팅
//        Date currentDate = new Date();
//
//        // 날짜 형식 지정
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//        // 날짜 형식에 맞게 변환
//        String formattedDate = dateFormat.format(currentDate);
//		qna.setQnaRdate(formattedDate);
//		logger.info(formattedDate);
		//3) 읽기 체크
		qna.setQnaCheck(0);
		qna.setProductId(productId);
			int qnaId = service.insertProductQna(qna);
			
			String path = null;
			String message = null;
			
			if(qnaId>0) {
				// /product/qna/write/1
				// /product/qna/detail/1/1500
				path = "/product/productDetailQnA?product_id=" + qna.getProductId();
				message = "게시글이 등록되었습니다.";
			}else {
				path = req.getHeader("referer");
				message = "게시글 등록 실패. 다시 시도해주세요";
			}
			
			ra.addFlashAttribute("message", message);
			return "redirect:" + path;
			
	
		
		
	}
	
	@ResponseBody
	@PostMapping("/confirmPw")
	public Map<String,Object> confirmPw(@RequestParam int qnaId,
									    @RequestParam String qnaPw,
									    Map<String,Object> map){
		map = service.selectQnADetail(qnaId,qnaPw);
		return map;
	}
}
