package fp.art.stroke.member.controller;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.member.model.service.MemberService;
import fp.art.stroke.member.model.vo.Member;


@Controller // 생성된 bean이 Contorller임을 명시 + bean 등록

@RequestMapping("/member") // localhost:8080/art_stroke/member 이하의 요청을 처리하는 컨트롤러

@SessionAttributes({"loginMember"}) // Model에 추가된 값의 key와 어노테이션에 작성된 값이 같으면
									// 해당 값을 session scope 이동시키는 역할
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	
	
	
	@GetMapping("/login")  // Get방식 : /comm/member/login 요청
	public String login() {
		return "member/login";
	}
	
	
	
	
	@PostMapping("/login")
	public String login( @ModelAttribute Member inputMember
						, Model model
						, RedirectAttributes ra
						, HttpServletResponse resp 
						, HttpServletRequest req
						, @RequestParam(value="saveId", required = false) String saveId ) {
												
		// @ModelAttribute 생략 가능 
		// -> 커맨드 객체 (@ModelAttribute가 생략된 상태에서 파라미터가 필드에 세팅된 객체)
		
		logger.info("로그인 기능 수행됨");
		
		
		// 아이디, 비밀번호가 일치하는 회원 정보를 조회하는 Service 호출 후 결과 반환 받기
		Member loginMember = service.login(inputMember);
		
		
		/* Model : 데이터를 맵 형식(K:V) 형태로 담아 전달하는 용도의 객체
		 * -> request, session을 대체하는 객체
		 * 
		 * - 기본 scope : request
		 * - session scope로 변환하고 싶은 경우
		 *   클래스 레벨로 @SessionAttributes를 작성되면 된다.
		 * */
		
		// @SessionAttributes 미작성 -> request scope
		
		if(loginMember != null) { // 로그인 성공 시
			model.addAttribute("loginMember", loginMember); // == req.setAttribute("loginMember", loginMember);
		    
			System.out.println("로그인됨");
			
			// 로그인 성공 시 무조건 쿠키 생성
			// 단, 아이디 저장 체크 여부에 따라서 쿠기의 유지 시간을 조정
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { // 아이디 저장이 체크 되었을 때
				
				cookie.setMaxAge(60 * 60 * 24 * 365); // 초 단위로 지정 (1년)
				
			} else { // 체크 되지 않았을 때
				cookie.setMaxAge(0); // 0초 -> 생성 되자마자 사라짐 == 쿠키 삭제
			}
			
			
			// 쿠키가 적용될 범위(경로) 지정
			cookie.setPath(req.getContextPath());
			
			// 쿠키를 응답 시 클라이언트에게 전달
			resp.addCookie(cookie);
			
			return  "redirect:/";
			
		} else {
			//model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			System.out.println("로그인안됨");
			// -> redirect 시 잠깐 Session scope로 이동후
			//    redirect가 완료되면 다시 Request scope로 이동
			
			// redirect 시에도 request scope로 세팅된 데이터가 유지될 수 있도록 하는 방법을
			// Spring에서 제공해줌
			// -> RedirectAttributes 객체  (컨트롤러 매개변수에 작성하면 사용 가능)
			
			return "redirect:/member/login";
		}
		
		//session.setAttribute("loginMember", loginMember);
		

	
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout( /*HttpSession session,*/
						SessionStatus status) {
		
		// 로그아웃 == 세션을 없애는 것
		
		// * @SessionAttributes을 이용해서 session scope에 배치된 데이터는
		//   SessionStatus라는 별도 객체를 이용해야만 없앨 수 있다.
		logger.info("로그아웃 수행됨");
		
		// session.invalidate(); // 기존 세션 무효화 방식으로는 안된다!
		
		status.setComplete(); // 세센이 할 일이 완료됨 -> 없앰
		
		return "redirect:/"; // 메인페이지 리다이렉트
		
	}
	
	
	// 회원 가입 화면 전환
		@GetMapping("/signUp")  // Get방식 : /stoke/member/signUp 요청
		public String signUp() {
			return "member/signUp";
		}
		
		// 회원 가입
		@PostMapping("/signUp")
		public String signUp( Member inputMember
							, String[] memberAddr
							, RedirectAttributes ra) {
			
			// 커맨드 객체를 이용해서 입력된 회원 정보를 잘 받아옴
			// 단, 같은 name을 가진 주소가 하나의 문자열로 합쳐서 세팅되어있음.
			// -> 도로명 주소에 " , " 기호가 포함되는 경우가 있어 이를 구분자로 사용할 수 없음.
			
			
			//  String[] memberAddress : 
			//    name이 memberAddress인 파라미터의 값을 모두 배열에 담아서 반환
			
//			inputMember.setMemberAddr(  String.join(",,", memberAddress)  );
			String firstAddr = memberAddr[1];
			String middleAddr = memberAddr[0];
			String lastAddr = memberAddr[2];
			
			inputMember.setMemberAddr(firstAddr + " " + middleAddr + " " + lastAddr);

			// String.join("구분자", 배열)
			// 배열을 하나의 문자열로 합치는 메서드
			// 중간에 들어가 구분자를 지정할 수 있다.
			// [a, b, c]  - join 진행 ->  "a,,b,,c"
			
			if( inputMember.getMemberAddr().equals(",,,,") ) { // 주소가 입력되지 않은 경우
				
				inputMember.setMemberAddr(null); // null로 변환
			}
			
			// 회원 가입 서비스 호출
			int result = service.signUp(inputMember);
			
			String message = null;
			String path = null;
			
			if(result > 0) { // 회원 가입 성공
				message = "회원가입이 성공하였습니다.";
				path = "redirect:/"; // 메인페이지
				
			}else { // 실패
				message = "회원가입이 실패하였습니다.";
				path = "redirect:/member/signUp"; // 회원 가입 페이지
			}
			
			ra.addFlashAttribute("message", message);
			return path;
		}
		
		
		// 이메일 중복 검사
		@ResponseBody  // ajax 응답 시 사용!
		@GetMapping("/emailDupCheck")
		public int emailDupCheck(String memberEmail) {
			int result = service.emailDupCheck(memberEmail);
			
			// 컨트롤러에서 반환되는 값은 forward 또는 redirect를 위한 경로인 경우가 일반적
			// -> 반환되는 값은 경로로 인식됨
			
			// -> 이를 해결하기위한 어노테이션 @ResponseBody 가 존재함
			
			// @ResponseBody : 반환되는 값을 응답의 몸통(body)에 추가하여 
			//				   이전 요청 주소로 돌아감
			// -> 컨트롤러에서 반환되는 값이 경로가 아닌  "값 자체"로 인식됨.
			
			return result;
			
		}	
		
		// 닉네임 중복 검사
		@ResponseBody  
		@GetMapping("/nicknameDupCheck")
		public int nicknameDupCheck(String memberNick) {
			int result = service.nicknameDupCheck(memberNick);
			
			return result;
			
		}
	
		//id/비밀번호 화면전환
		@GetMapping("/searchIdPw")  // Get방식 : /stoke/member/signUp 요청
		public String searchIdPw() {
			return "member/searchIdPw";
		}
		
		
		@GetMapping("/terms")  // Get방식 : /comm/member/terms 요청
		public String terms() {
			return "member/terms";
		}
		
	
}
