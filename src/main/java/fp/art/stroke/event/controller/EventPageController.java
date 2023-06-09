package fp.art.stroke.event.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fp.art.stroke.myPage.controller.MyPageController;
import fp.art.stroke.myPage.model.service.MyPageService;

@Controller
@RequestMapping("event")
@SessionAttributes({"loginMember"})
public class EventPageController {
	
//	@Autowired
//	private EventPageService service;
	
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@GetMapping("/rouletteEvent")
	public String myPageMain() {
		return "event/rouletteEvent";
	}

}
