package fp.art.stroke.myPage.controller;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
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
	
	@GetMapping("/profile")
	public String profile() {
		return "myPage/myPageMain";
	}
	
	
	// 프로필 수정
	@PostMapping("/profile")
	public String updateProfile( @ModelAttribute("loginMember") Member loginMember
							   , @RequestParam(value = "uploadImage", required = false) MultipartFile uploadImage /* 업로드 파일 */
							   , @RequestParam Map<String, Object> map /* delete 담겨있음 */
							   , HttpServletRequest req /* 파일 저장 경로 탐색용 */
							   , RedirectAttributes ra , HttpSession session) throws IOException {


		
		// 1) 웹 접근 경로  (/comm/resources/images/memberProfile/)
		String webPath = "/resources/img/memberProfile/";
		
		// 2) 서버 저장 폴더 경로
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		// C:\workspace\7_Framework\comm\src\main\webapp\resources\images\memberProfile
		
		
		// map에 경로 2개, 이미지, delete, 회원번호 담기
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("uploadImage", uploadImage);
		map.put("memberId", loginMember.getMemberId());
		
		int result = service.updateProfile(map);
		
		
		String message = null;
		
		if(result > 0) {
			message = "프로필 이미지가 변경되었습니다.";
			
			//  DB-세션 동기화
			
			// 서비스 호출 시 전달된 map은 실제로는 주소만 전달(얕은복사)
			// -> 서비스에서 추가된 "profileImage"는 원본 map에 그대로 남아있음~!
			
			
			loginMember.setProfileImage( (String)map.get("profileImage")  );
			
		}else {
			message = "프로필 이미지 변경 실패...";
		}
		
		ra.addFlashAttribute("message", message);
		return "redirect:profile";
	}
}
