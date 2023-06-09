package fp.art.stroke.myPage.controller;

import java.io.Console;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.service.MyPageService;
import fp.art.stroke.myPage.model.vo.Addr;

@Controller
@RequestMapping("/myPage")
@SessionAttributes({ "loginMember" })
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
	public String myPagemyPageAddrList(HttpSession session, Model model) {

		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		List<Addr> addrList = service.selectAddrList(memberId);
		model.addAttribute("addrList", addrList);

		return "myPage/myPageAddrList";
	}

	@PostMapping("/addrReg")
	public String addrReg(@RequestParam("addrName") String addrName, @RequestParam("receiverName") String receiverName,
			@RequestParam("postcode") String postcode, @RequestParam("roadAddress") String roadAddress,
			@RequestParam("detailAddress") String detailAddress, @RequestParam("addrTel") String addrTel,
			@RequestParam(value = "addrId", required = false) String addrIdString, HttpSession session,
			RedirectAttributes ra) {

		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		System.out.println(memberId + "현재 멤버아이디!!");

		int addrId = 0; // 기본값 설정
		if (addrIdString != null && !addrIdString.isEmpty()) {
			try {
				addrId = Integer.parseInt(addrIdString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		int result = service.addrReg(addrName, receiverName, postcode, roadAddress, detailAddress, addrTel, memberId,
				addrId);

		String message = "";

		if (result >= 1) {
			message = "배송지가 등록되었습니다.";
		} else {
			message = "배송지 등록이 실패하였습니다.";
		}
		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/myPageAddrList";
	}

	@ResponseBody
	@GetMapping("/deleteAddress")
	public int deleteAddress(@RequestParam("addrId") int addrId) {
		
		int result = service.deleteAddr(addrId);
		
		return result;
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
