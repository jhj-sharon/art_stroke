//package fp.art.stroke.member.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import fp.art.stroke.member.aouth.bo.KakaoLoginBO;
//import fp.art.stroke.member.aouth.bo.NaverLoginBO;
//import fp.art.stroke.member.model.service.MemberService;
//
//@Controller // 생성된 bean이 Controller임을 명시 + bean 등록
//
//
//@RequestMapping(value = "/login/*")
//@SessionAttributes({ "loginMember" }) // Model에 추가된 값의 key와 어노테이션에 작성된 값이 같으면
//										// 해당 값을 session scope 이동시키는 역할
//public class MemberLoginController {
//
//	@Autowired
//    private MemberService service;
//	
//	   /* NaverLoginBO */
//    private NaverLoginBO naverLoginBO;
//    private KakaoLoginBO kakaoLoginBO;
//    private String apiResult = null;
//    
//
//    @Autowired
//    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
//        this.naverLoginBO = naverLoginBO;
//    }
//
//	
//    @Autowired
//    private void setKakaoLoginBO(KakaoLoginBO kakaoLoginBO) { this.kakaoLoginBO = kakaoLoginBO; }
//
//	
//    
//	
//	
//	
//	
//}
