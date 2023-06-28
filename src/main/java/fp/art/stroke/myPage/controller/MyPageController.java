package fp.art.stroke.myPage.controller;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.chat.model.vo.ChatRoom;
import fp.art.stroke.chat.model.vo.ChatRoomJoin;
import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.service.MyPageService;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.myPage.model.vo.CancelOrder;
import fp.art.stroke.myPage.model.vo.OrderInfo;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Controller
@RequestMapping("/myPage")
@SessionAttributes({ "loginMember" })
public class MyPageController {

	@Autowired
	private MyPageService service;
	
	@Autowired
	private ChatService cservice;
	
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);
	/**
	 * 메인페이지
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageMain")
	public String myPageMain(HttpSession session, Model model) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		List<Follow> myFollow = service.myFollow(memberId);

		model.addAttribute("myFollow", myFollow);

		return "myPage/myPageMain";
	}

	@GetMapping("/myPageOrderList")
	public String myPageOrderList(HttpSession session, Model model) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		List<OrderInfo> myOrderInfo = service.myOrderInfo(memberId);
		
		model.addAttribute("myOrderInfo", myOrderInfo);
		return "myPage/myPageOrderList";
	}
	/**
	 * 쿠폰 목록 더보기
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageCouponList")
	public String myPageCouponList(HttpSession session, Model model) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		
		List<Coupon> myCoupon = service.myCoupon(memberId);

		model.addAttribute("myCoupon", myCoupon);
		
		return "myPage/myPageCouponList";
	}
	/**
	 * 최근 본 상품 가져오기
	 * @param recentProductsCookieValue
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageResentViewList")
	public String myPageResentViewList(
			@CookieValue(value = "recent_products", required = false) String recentProductsCookieValue, Model model) {
		if (recentProductsCookieValue == null || recentProductsCookieValue.isEmpty()) {

			model.addAttribute("noRecentProductMessage", "최근본 상품이 없습니다.");
			return "myPage/myPageResentViewList";
		}

		String[] recentList = recentProductsCookieValue.split("/");
		int[] recentListInt = new int[recentList.length];
		for (int i = 0; i < recentList.length; i++) {
			recentListInt[i] = Integer.parseInt(recentList[i]);
		}
		List<Product> recentProduct = service.recentProduct(recentListInt);

		model.addAttribute("recentProduct", recentProduct);

		return "myPage/myPageResentViewList";
	}
	/**
	 * 내 관심목록 가져오기
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageWishList")
	public String myPageWishList(HttpSession session, Model model) {
		// myPageWishList 페이지 들어갈 때 바로 리스트가져오는 컨트롤러
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		List<WishList> myPageWishList = service.myPageWishList(memberId);
		model.addAttribute("myPageWishList", myPageWishList);

		return "myPage/myPageWishList";
	}
	/**
	 * 내 게시물 가져오기
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageBoardList")
	public String myPageBoardList(HttpSession session, Model model) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		List<Board> BoardList = service.selectBoardList(memberId);
		model.addAttribute("BoardList", BoardList);

		return "myPage/myPageBoardList";
	}

	@GetMapping("/myPageModify")
	public String myPageMyReviewList() {
		return "myPage/myPageModify";
	}
	/**
	 * 배송지 리스트
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageAddrList")
	public String myPagemyPageAddrList(HttpSession session, Model model) {
		// 배송 조회 페이지 들어갈때 바로 리스트 가져오는 컨트롤러
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		List<Addr> addrList = service.selectAddrList(memberId);
		model.addAttribute("addrList", addrList);

		return "myPage/myPageAddrList";
	}
	/**
	 * 쪽지 전송 컨트롤러
	 * @param memberNick
	 * @param sendName
	 * @param messageTitle
	 * @param messageContent
	 * @param receiverId
	 * @param session
	 * @param ra
	 * @return
	 */
	@PostMapping("sendBack")
	public String sendBack(@RequestParam("memberNick") String memberNick, @RequestParam("sendName") String sendName,
			@RequestParam("messageTitle") String messageTitle, @RequestParam("messageContent") String messageContent,
			@RequestParam(value = "senderId", required = false) int senderId, HttpSession session,
			RedirectAttributes ra) {

		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		
		int result = service.sendBack(memberNick, sendName, messageTitle, messageContent, senderId, memberId);
		
		String message = "";

		if (result >= 1) {
			message = "쪽지가 보내졌습니다.";
		} else {
			message = "쪽지보내는것을 실패하였습니다.";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:/myPage/myPageMessage";
	}
	/**
	 * 배송지 등록, 수정!
	 * 
	 * @param addrName
	 * @param receiverName
	 * @param postcode
	 * @param roadAddress
	 * @param detailAddress
	 * @param addrTel
	 * @param addrIdString
	 * @param session
	 * @param ra
	 * @return
	 */
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

	/**
	 * 배송지 삭제 컨트롤러
	 * 
	 * @param addrId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/deleteAddress")
	public int deleteAddress(@RequestParam("addrId") int addrId) {

		int result = service.deleteAddr(addrId);

		return result;
	}

	/**
	 * 장바구니에 담기 컨트롤러
	 * 
	 * @param session
	 * @param productId
	 * @param selectedOption
	 * @param productPrice
	 * @return
	 */
	@ResponseBody
	@GetMapping("/cartInsert")
	public int cartInsert(HttpSession session, @RequestParam("productId") int productId,
			@RequestParam("selectedOption") String selectedOption, @RequestParam("productPrice") int productPrice) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		int result = service.cartInsert(productId, memberId, selectedOption, productPrice);

		return result;
	}
	/**
	 * 쪽지 개별 삭제 컨트롤러
	 * @param messageId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("deleteMessage")
	public int deleteMessage(@RequestParam("messageId") int messageId, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();
		
		int result = service.deleteMessage(messageId, memberId);
		
		return result;
	}
	/**
	 * 선택쪽지 삭제
	 * @return
	 */
	@ResponseBody
	@GetMapping("deleteSelectedMessage")
	public int deleteSelectedMessage(@RequestParam("messageIds") List<Integer> messageIds) {
		
		int result = service.deleteSelectedMessage(messageIds);
		
		return result;
	}
	/**
	 * 위시리스트 삭제 컨트롤러
	 * 
	 * @param productId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("/deleteWishlist")
	public int deleteWishlist(@RequestParam("productId") int productId, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		int result = service.deleteWishlist(productId, memberId);

		return result;
	}

	/**
	 * 위시리스트 선택 삭제 컨트롤러
	 * 
	 * @param productIds
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("/deleteSelectedWishlist")
	public int deleteSelectedWishlist(@RequestParam("productIds") List<Integer> productIds, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		int result = service.deleteSelectedWishlist(productIds, memberId);

		return result;
	}
	/**
	 * 내 게시물 선택 삭제
	 * @param boardIds
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("/deleteSelectedBoard")
	public int deleteSelectedBoard(@RequestParam("boardIds") List<Integer> boardIds, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();
		int result = service.deleteSelectedBoard(boardIds, memberId);

		return result;
	}

	/**
	 * 회원탈퇴 컨트롤러
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/secession")
	public String secession() {
		return "myPage/myPageModify";
	}
	/**
	 * 회원탈퇴
	 * @param session
	 * @param status
	 * @param req
	 * @param resp
	 * @param ra
	 * @return
	 */
	@PostMapping("/secession")
	public String secession(HttpSession session, SessionStatus status, HttpServletRequest req, HttpServletResponse resp,
			RedirectAttributes ra) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		int result = service.secession(memberId);
		String message = null;
		String path = null;

		if (result > 0) {
			message = "탈퇴 되었습니다.";
			path = "/";

			// 세션 없애기
			status.setComplete();

			// 쿠키 없애기
			Cookie cookie = new Cookie("saveId", "");
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);

		} else {
			message = "회원탈퇴에 실패하였습니다.";
			path = "/myPage/myPageModify";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}

	@GetMapping("/myPageReviewList")
	public String myPageReviewList() {
		return "myPage/myPageReviewList";
	}

	/**
	 * 메시지 목록 조회
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myPageMessage")
	public String myPageMessage(HttpSession session, Model model) {

		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();

		List<Message> messageList = service.messageList(memberId);
		boolean allN = true;
		for (Message message : messageList) {
			if (!message.getMessageSt().equals("Y")) {
				allN = false;
				break;
			}
		}

		if (allN) {
			model.addAttribute("messageList", null);
		} else {
			model.addAttribute("messageList", messageList);
		}

		return "myPage/myPageMessage";
	}
	/**
	 * 프로필 창 넘어가기
	 * @return
	 */
	@GetMapping("/profile")
	public String profile() {
		return "myPage/myPageMain";
	}
	/**
	 * 닉네임 중복검사
	 * @param memberNick
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("/nicknameDupCheck")
	public int nicknameDupCheck(@RequestParam("memberNick") String memberNick, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");

		int memberId = loginMember.getMemberId();

		int result = service.nicknameDupCheck(memberNick, memberId);

		return result;
	}
	//@GetMapping("/reviewInsert")

	/**
	 * 리뷰 작성 컨트롤러
	 * @param loginMember
	 * @param reviewImg
	 * @param reviewStar
	 * @param reviewContent
	 * @return
	 */
	@PostMapping("/reviewInsert")
	public String reviewInsert(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam(value = "reviewImg", required = false) MultipartFile reviewImg,
			@RequestParam("reviewStar") int reviewStar,
			@RequestParam("reviewContent") String reviewContent,
			@RequestParam("orderDetailId") int orderDetailId,
			HttpServletRequest req /* 파일 저장 경로 탐색용 */
			, RedirectAttributes ra, HttpSession session
			)throws IOException {
		int memberId = loginMember.getMemberId();
		Map<String, Object> map = new HashMap<>();
		String webPath = "/resources/img/review/";
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDD"+reviewImg);
		// 2) 서버 저장 폴더 경로
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("reviewImg", reviewImg);
		map.put("memberId", memberId);
		map.put("reviewContent", reviewContent);
		map.put("orderDetailId", orderDetailId);
		map.put("reviewStar", reviewStar);
		
		int reviewInsert = service.reviewInsert(map);
		
		String message = "";

		if (reviewInsert >= 1) {
			message = "리뷰가 작성 되었습니다.";
		} else {
			message = "리뷰 작성을 실패하였습니다.";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:/myPage/myPageOrderList";
	}
	/**
	 * 프로필이미지 수정
	 * 
	 * @param loginMember
	 * @param uploadImage
	 * @param map
	 * @param req
	 * @param ra
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam(value = "uploadImage", required = false) MultipartFile uploadImage /* 업로드 파일 */
			, @RequestParam Map<String, Object> map /* delete 담겨있음 */
			, HttpServletRequest req /* 파일 저장 경로 탐색용 */
			, RedirectAttributes ra, HttpSession session) throws IOException {

		// 1) 웹 접근 경로 (/comm/resources/images/memberProfile/)
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

		if (result > 0) {
			message = "프로필 이미지가 변경되었습니다.";

			// DB-세션 동기화

			// 서비스 호출 시 전달된 map은 실제로는 주소만 전달(얕은복사)
			// -> 서비스에서 추가된 "profileImage"는 원본 map에 그대로 남아있음~!

			loginMember.setProfileImage((String) map.get("profileImage"));

		} else {
			message = "프로필 이미지 변경 실패...";
		}

		ra.addFlashAttribute("message", message);
		return "redirect:profile";
	}

	/**
	 * 개인정보 수정 컨트롤러
	 */
	@PostMapping("/modify")
	public String updateInfo(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam Map<String, Object> paramMap, // 요청 시 전달된 파라미터를 구분하지 않고 모두 Map에 담아서 얻어옴
			String[] updateAddress, RedirectAttributes ra) {

		String memberAddress = String.join(",,", updateAddress);
		if (memberAddress.equals(",,,,"))
			memberAddress = null;

		paramMap.put("memberId", loginMember.getMemberId());
		paramMap.put("memberAddr", memberAddress);

		int result = service.updateInfo(paramMap);

		String message = null;
		String updateEmailOptIn = null;

		if (result > 0) {
			message = "회원 정보가 수정되었습니다.";

			// DB - Session의 회원정보 동기화(얕은 복사 활용)
			loginMember.setMemberNick((String) paramMap.get("memberNickname"));
			loginMember.setMemberSns((String) paramMap.get("memberSNS"));
			loginMember.setMemberAddr((String) paramMap.get("memberAddr"));
			if (paramMap.get("emailOptIn") == "1") {
				updateEmailOptIn = "Y";
			} else {
				updateEmailOptIn = "N";
			}
			loginMember.setEmailOptIn(updateEmailOptIn);
		} else {
			message = "회원 정보 수정 실패";
		}
		ra.addFlashAttribute("message", message);

		return "redirect:myPageModify";
	}
	/**
	 * 채팅방 중복검사 + 채팅방 생성 + 불러오기!
	 * @param loginMember
	 * @param model
	 * @return
	 */
	@ResponseBody
	@GetMapping("/openChatRoom")
	public Map<String, Object> openChatRoom(@ModelAttribute("loginMember") Member loginMember, Model model) {
	    // 로그인 멤버의 ID를 가져옴
	    int memberId = loginMember.getMemberId();
	    String memberNick = loginMember.getMemberNick();
	    // 채팅방 조회 또는 생성을 위한 서비스 호출
	    int chatRoomId = cservice.getChatRoomId(memberId, memberNick);
	    
	    List<ChatMessage> chatMessages = cservice.getChatMessagesByChatRoomId(chatRoomId);
	    
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("chatRoomId", chatRoomId);
	    responseData.put("chatMessages", chatMessages);
	    
	    return responseData;
	}
	/**
	 * 채팅 insert
	 * @param loginMember
	 * @param inputVal
	 * @param chatId
	 * @return
	 */
	@ResponseBody
	@PostMapping("/insertChatMessage")
	public int insertChatMessage(@ModelAttribute("loginMember") Member loginMember,
								@RequestParam("inputVal") String inputVal,
								@RequestParam("chatId") String chatId) {
		int memberId = loginMember.getMemberId();
		String memberEmail = loginMember.getMemberEmail();
		String memberNick = loginMember.getMemberNick();
		
		int chatRoomId = Integer.parseInt(chatId);
		int result = cservice.insertChatMessage(memberId, memberEmail, memberNick, inputVal, chatRoomId);
		
		return result;
	}
	/**
	 * 취소 사유 insert
	 */
	@GetMapping("/cancelOrder")
	public String cancelOrder(@RequestParam("orderId") String orderId, 
			@RequestParam("cancelReason") String cancelReason,
			@ModelAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		
		int memberId = loginMember.getMemberId();
		int cancelOrder = service.cancelOrder(orderId, cancelReason,memberId);
		
		String message = "";

		if (cancelOrder >= 1) {
			message = "취소 신청 되었습니다.";
		} else {
			message = "취소 신청을 실패하였습니다.";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:/myPage/myPageOrderList";
	}
}
