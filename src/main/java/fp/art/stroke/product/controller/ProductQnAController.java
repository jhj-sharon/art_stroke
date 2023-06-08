package fp.art.stroke.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.service.ProductQnAService;
import fp.art.stroke.product.model.vo.ProductQnA;

@Controller
@RequestMapping("product/productDetailQnA")
@SessionAttributes({"loginMember"})
public class ProductQnAController {
	
	@Autowired
	private ProductQnAService service;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
   @GetMapping("/productQnAWrite")
   public String productQnAWrite() {
	   
	   return"product/productQnAWrite";
   }
	

	
	//qna글쓰기
	@PostMapping("/productQnAWrite")
	public String insertProductQna(
			ProductQnA qna //qna에 대한 모든 정보
			, @ModelAttribute("loginMember") Member loginMember
			, HttpServletRequest req
			, RedirectAttributes ra
			) throws IOException{
		
		//1) 로그인한 회원 정보 얻어와서 qna에 세팅
		qna.setMemberNick( loginMember.getMemberNick());
		qna.setMemberId( loginMember.getMemberId());
		
		//2) 작성시간 세팅
        Date currentDate = new Date();

        // 날짜 형식 지정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        // 날짜 형식에 맞게 변환
        String formattedDate = dateFormat.format(currentDate);
		qna.setQnaRDate(formattedDate);
		
		//3) 읽기 체크
		qna.setQnaCheck(0);
		
		logger.info("질의하기 컨테츠"+qna.getQnaContent());
		

			int qnaId = service.insertProductQna(qna);
			
			String path = null;
			String message = null;
			
			if(qnaId>0) {
				// /product/qna/write/1
				// /product/qna/detail/1/1500
				path = "product_id=" + qna.getProductId()+"/"+qnaId;
				message = "게시글이 등록되었습니다.";
			}else {
				path = req.getHeader("referer");
				message = "게시글 등록 실패. 다시 시도해주세요";
			}
			
			ra.addFlashAttribute("message", message);
			return "redirect:" + path;
			
	
		
		
	}
}
