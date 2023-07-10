package fp.art.stroke.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

	
	/*
	 * http://localhost:8080/stroke(현재경로)/main(목표경로)
	 * 
	 * http://localhost:8080/stroke/ -> 실제 나타나는 주소(포워드는 경로이동 없음)
	 * */
	@RequestMapping("/main")
	public String mainFoward() {
		
		return "common/main"; //View Resolver에게 이동
	}
}
