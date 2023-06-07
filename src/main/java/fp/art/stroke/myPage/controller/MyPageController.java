package fp.art.stroke.myPage.controller;

import java.io.Console;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.service.MyPageService;



@Controller
@RequestMapping("/myPage")
@SessionAttributes({"loginMember"})
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@GetMapping("/myPageMain")
	public String myPageMain() {
		return "myPage/myPageMain";
	}
	
	@GetMapping("/myPageOrderList")
	public String myPageOrderList() {
		return "myPage/myPageOrderList";
	}
	@GetMapping("/myPageCouponList")
	public String myPageCouponList() {
		return "myPage/myPageCouponList";
	}
	
	@GetMapping("/myPageResentViewList")
	public String myPageResentViewList() {
		return "myPage/myPageResentViewList";
	}
	@GetMapping("/myPageWishList")
	public String myPageWishList() {
		return "myPage/myPageWishList";
	}
	@GetMapping("/myPageBoardList")
	public String myPageBoardList() {
		return "myPage/myPageBoardList";
	}
	@GetMapping("/myPageModify")
	public String myPageMyReviewList() {
		return "myPage/myPageModify";
	}
	@GetMapping("/myPageAddrList")
	public String myPagemyPageAddrList() {
		return "myPage/myPageAddrList";
	}
	
    @PostMapping("/addrReg")
    public String addrReg(@RequestParam("addrName") String addrName,
                          @RequestParam("receiverName") String receiverName,
                          @RequestParam("postcode") String postcode,
                          @RequestParam("roadAddress") String roadAddress,
                          @RequestParam("detailAddress") String detailAddress,
                          @RequestParam("memberTel") String memberTel,
                          HttpSession session
    		) {
    	
    	Member loginMember = (Member)session.getAttribute("loginMember"); 
    	
    	int memberId= loginMember.getMemberId();
    	System.out.println(memberId+"현재 멤버아이디!!");
    	
    	int result = service.addrReg(addrName, receiverName, postcode, roadAddress, detailAddress, memberTel, memberId);
        
    	
        return "myPage/myPageAddrList";
    }
	
	@GetMapping("/myPageReviewList")
	public String myPageReviewList() {
		return "myPage/myPageReviewList";
	}
	@GetMapping("/myPageMessage")
	public String myPageMessage() {
		return "myPage/myPageMessage";
	}
}


