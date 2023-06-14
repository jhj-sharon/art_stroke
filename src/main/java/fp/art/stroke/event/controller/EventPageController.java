package fp.art.stroke.event.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fp.art.stroke.event.model.service.EventService;
import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.controller.MyPageController;
import fp.art.stroke.myPage.model.service.MyPageService;

@Controller
@RequestMapping("/event")
@SessionAttributes({"loginMember"})
public class EventPageController {
	
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@Autowired 
	private EventService service;   
	
	// 이벤트페이지 연결 
	@GetMapping("/rouletteEvent")
	public String myPageMain() {
		return "event/rouletteEvent";
	}
	
	
	
	/** 룰렛 이벤트 참여 체크 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("/rouletteEventCheck")
	public int RouletteEventCheck(HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberId = loginMember.getMemberId();		
		
		int result = service.rouletteEventCheck(memberId);
		
		return result;
	}
	
	
	

		/** 이벤트룰렛 쿠폰 
		 * @param ra
		 * @param discountRate
		 * @param session
		 * @return
		 */
		@ResponseBody
		@PostMapping("/rouletteEvent")
		public String InsertEventCoupon(RedirectAttributes ra,	
										@RequestParam("discountRate") String discountRate,
										HttpSession session
									   ) {
			
			Member loginMember = (Member) session.getAttribute("loginMember");
			int memberId = loginMember.getMemberId();		
			int discountRateInt = Integer.parseInt(discountRate);
			
			int result = service.insertEventCoupon(memberId,discountRateInt);
			
			return new Gson().toJson(discountRate);
		}
}
