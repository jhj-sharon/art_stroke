//package fp.art.stroke.member.controller;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//import org.springframework.social.oauth2.OAuth2Parameters;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
////import fp.art.stroke.member.auth.bo.KakaoLoginBO;
//import fp.art.stroke.member.auth.bo.NaverLoginBO;
//import fp.art.stroke.member.model.service.MemberService;
//import fp.art.stroke.member.model.vo.Member;
//
//
//@Controller
//@RequestMapping("/login") 
//@SessionAttributes({ "loginMember" })
//public class LoginController {
//	
//	private Logger logger = LoggerFactory.getLogger(MemberController.class);
//
//	@Autowired
//	private MemberService service;
//	
//	
//    
//	/* GoogleLogin */
//	@Autowired
//	private GoogleConnectionFactory googleConnectionFactory;
//	@Autowired
//	private OAuth2Parameters googleOAuth2Parameters;
//	
//	
//	/*네이버 로그인*/
//	/*NaverLoginBO*/
//	private NaverLoginBO naverLoginBO;
//	//private KakaoLoginBO kakaoLoginBO;
//	
//	private String apiResult=null;
//	
//	
//	
//
//	
//	
////	@Autowired
////    private void setKakaoLoginBO(KakaoLoginBO kakaoLoginBO) { this.kakaoLoginBO = kakaoLoginBO; }
////	
////    
//	
//	
//	@GetMapping("/login") // Get방식 : /comm/login 요청
//	public String login(Model model,HttpSession session) {
//		
//		/*네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationURL메소드 호출*/
//		String naverAuthUrl=naverLoginBO.getAuthorizationUrl(session);
//		/*인증요청문 확인 */
//		System.out.println("네이버:"+naverAuthUrl);
//		/*객체바인딩 */
//		model.addAttribute("urlNaver",naverAuthUrl);
//		
//		/*생성한 인증 URL을 view로 전달 */
//		
//		return "member/login";
//	}
//
//	@PostMapping("/normal")
//	public String login(@ModelAttribute Member inputMember, Model model, RedirectAttributes ra,
//			HttpServletResponse resp, HttpServletRequest req,
//			@RequestParam(value = "saveId", required = false) String saveId) {
//
//		// @ModelAttribute 생략 가능
//		// -> 커맨드 객체 (@ModelAttribute가 생략된 상태에서 파라미터가 필드에 세팅된 객체)
//
//		logger.info("로그인 기능 수행됨");
//
//		// 아이디, 비밀번호가 일치하는 회원 정보를 조회하는 Service 호출 후 결과 반환 받기
//		Member loginMember = service.login(inputMember);
//
//		/*
//		 * Model : 데이터를 맵 형식(K:V) 형태로 담아 전달하는 용도의 객체 -> request, session을 대체하는 객체
//		 * 
//		 * - 기본 scope : request - session scope로 변환하고 싶은 경우 클래스 레벨로 @SessionAttributes를
//		 * 작성되면 된다.
//		 */
//
//		// @SessionAttributes 미작성 -> request scope
//
//		if (loginMember != null) { // 로그인 성공 시
//			model.addAttribute("loginMember", loginMember); // == req.setAttribute("loginMember", loginMember);
//
//			System.out.println("로그인됨");
//
//			// 로그인 성공 시 무조건 쿠키 생성
//			// 단, 아이디 저장 체크 여부에 따라서 쿠기의 유지 시간을 조정
//			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
//
//			if (saveId != null) { // 아이디 저장이 체크 되었을 때
//
//				cookie.setMaxAge(60 * 60 * 24 * 365); // 초 단위로 지정 (1년)
//
//			} else { // 체크 되지 않았을 때
//				cookie.setMaxAge(0); // 0초 -> 생성 되자마자 사라짐 == 쿠키 삭제
//			}
//
//			// 쿠키가 적용될 범위(경로) 지정
//			cookie.setPath(req.getContextPath());
//
//			// 쿠키를 응답 시 클라이언트에게 전달
//			resp.addCookie(cookie);
//
//			return "redirect:/";
//
//		} else {
//			// model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
//
//			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
//			System.out.println("로그인안됨");
//			// -> redirect 시 잠깐 Session scope로 이동후
//			// redirect가 완료되면 다시 Request scope로 이동
//
//			// redirect 시에도 request scope로 세팅된 데이터가 유지될 수 있도록 하는 방법을
//			// Spring에서 제공해줌
//			// -> RedirectAttributes 객체 (컨트롤러 매개변수에 작성하면 사용 가능)
//
//			return "redirect:/member/login";
//		}
//
//		// session.setAttribute("loginMember", loginMember);
//
//	}
//
//	// 로그아웃
//	@GetMapping("/logout")
//	public String logout( /* HttpSession session, */
//			SessionStatus status) {
//
//		// 로그아웃 == 세션을 없애는 것
//
//		// * @SessionAttributes을 이용해서 session scope에 배치된 데이터는
//		// SessionStatus라는 별도 객체를 이용해야만 없앨 수 있다.
//		logger.info("로그아웃 수행됨");
//
//		// session.invalidate(); // 기존 세션 무효화 방식으로는 안된다!
//
//		status.setComplete(); // 세센이 할 일이 완료됨 -> 없앰
//
//		return "redirect:/"; // 메인페이지 리다이렉트
//
//	}
//
//}
