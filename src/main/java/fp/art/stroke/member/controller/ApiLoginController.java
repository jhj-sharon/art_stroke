package fp.art.stroke.member.controller;

import java.net.URI;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import fp.art.stroke.member.model.service.MemberApiService;
import fp.art.stroke.member.model.vo.GoogleLoginResponse;
import fp.art.stroke.member.model.vo.GoogleMember;
import fp.art.stroke.member.model.vo.GoogleOAuthRequest;
import fp.art.stroke.member.model.vo.Member;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;

@RequestMapping("/apiLogin")
@Slf4j
@SessionAttributes({ "loginMember" })
@Controller
//여기서 RestController이 있는데 이것은 json형식으로 전달해주는 것이므로 RestController를 쓰면 ViewResolver이 써지지 않는다.
//그러므로 Controller를 써야 ViewResolver가 제대로 전달된다.
public class ApiLoginController {
//	@Value("${google.auth.url}")
//    private String googleAuthUrl;
//
//    @Value("${google.login.url}")
//    private String googleLoginUrl;
//
//    @Value("${google.client.id}")
//    private String googleClientId;
//
//    @Value("${google.redirect.url}")
//    private String googleRedirectUrl;
//
//    @Value("${google.secret}")
//    private String googleClientSecret;
//    
//    
//    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);
//    @Autowired
//    private MemberApiService apiService;
//    @GetMapping("/getGoogleAuthUrl")
//    public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {
//
//        String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
//                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";
//
//        logger.info("myLog-LoginUrl : {}",googleLoginUrl);
//        logger.info("myLog-ClientId : {}",googleClientId);
//        logger.info("myLog-RedirectUrl : {}",googleRedirectUrl);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create(reqUrl));
//
//        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
//    }
//    @GetMapping("/oauth_google_check")
//    public String oauth_google_check(HttpServletRequest request,
//                                     @RequestParam(value = "code") String authCode,
//                                     Model model) throws Exception {
//        int isLogin = 0;
//        String googleUid = null;
//
//        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
//                .builder()
//                .clientId(googleClientId)
//                .clientSecret(googleClientSecret)
//                .code(authCode)
//                .redirectUri(googleRedirectUrl)
//                .grantType("authorization_code")
//                .build();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(googleAuthUrl + "/token", googleOAuthRequest, GoogleLoginResponse.class);
//        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();
//
//        logger.info("responseBody {}", googleLoginResponse.toString());
//
//        String googleToken = googleLoginResponse.getId_token();
//
//        String requestUrl = UriComponentsBuilder.fromHttpUrl(googleAuthUrl + "/tokeninfo").queryParam("id_token", googleToken).toUriString();
//
//        String resultJson = restTemplate.getForObject(requestUrl, String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        GoogleMember googleMember = objectMapper.readValue(resultJson, GoogleMember.class);
//        if (googleMember != null) {
//            isLogin = apiService.selectApiMemberCount(googleMember.getEmail(), "google");
//        } else {
//        	
//        }
//        if (isLogin > 0) {
//            // 로그인 유저가 있으면 로그인을 진행.
//            Member loginMember = apiService.selectApiMember(googleMember.getEmail(), "google");
//            model.addAttribute("loginMember", loginMember);
//            return "redirect:/"; // 내 페이지로의 뷰 이름으로 수정하세요.
//        } else {
//            model.addAttribute("googleMember", googleMember);
//            // int signUp = service.insertApiMember(googleMember);
//            return "member/sns_signUp";
//        }
//    }
    
}
