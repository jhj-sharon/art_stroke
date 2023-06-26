
package fp.art.stroke.admin.controller;

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.gson.Gson;

import fp.art.stroke.admin.model.service.AdminMainService;
import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatMessage;
import fp.art.stroke.member.model.vo.Member;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"loginMember"})
public class AdminMainController {
   
   @Autowired
   private AdminMainService service;
   
   @Autowired
   private ChatService cservice;
   
   private Logger logger = LoggerFactory.getLogger(AdminMainController.class);
   
   // 관리자 로그인 페이지
   @GetMapping("/adminLogin")
   public String adminLogin() {
      return "admin/adminLogin";
   }
   
   // 관리자 메인페이지
   @GetMapping("/adminMain")
   public String adminMain() {
      return "admin/adminMain";
   }
    
   // 관리자 로그인
   @PostMapping("/adminLogin")
   public String  adminLogin(@ModelAttribute Member inputMember,
            Model model,
            RedirectAttributes ra,
            HttpServletRequest req,
            HttpServletResponse resp,
            @RequestParam(value="saveId", required=false) String saveId) {
      
      Member loginMember = service.adminLogin(inputMember);
      
      String message = null;
      String path = null; 
      
      if(loginMember != null && loginMember.getAuth() == 1) { 
          
         path = "adminMain";
         model.addAttribute("loginMember", loginMember);  
         Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
         
         if(saveId != null) { 
            
            cookie.setMaxAge(60 * 60 * 24 * 365); 
            
         } else { 
            cookie.setMaxAge(0);
         } 
         
         cookie.setPath(req.getContextPath());
          
         resp.addCookie(cookie); 
         
         return "redirect:" + path;
    
    
      } else { 
       
         ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
         
         path = "adminLogin";
         return "redirect:" + path;
      }
       
   }
   
   // 관리자 로그아웃
   @GetMapping("/adminLogout")
   public String logout(SessionStatus status) {
      
      logger.info("로그아웃 수행됨");
      
      status.setComplete();  
      
      return "redirect:adminLogin"; 
      
   }
   
   /** 관리자 메세지 insert
    * @param loginMember
    * @param inputVal
    * @param chatId
    * @return
    */
   @ResponseBody
   @PostMapping("/chat/insertAdminChatMessage")
   public int insertChatMessage(@ModelAttribute("loginMember") Member loginMember,
                        @RequestParam("inputVal") String inputVal,
                        @RequestParam("chatId") int chatRoomId) {
      int memberId = loginMember.getMemberId();
      String memberEmail = loginMember.getMemberEmail();
      String memberNick = loginMember.getMemberNick();
      
      logger.info("chatRoomId!!!!!!!!!!!!!    " + chatRoomId);
      int result = cservice.insertChatMessage(memberId, memberEmail, memberNick, inputVal, chatRoomId);
      logger.info("result!!!!!!!!!!!!!    " + result);
      return result;
   }

   
   @ResponseBody
   @GetMapping("/chat/selectChatMessage")
   public Map<String, Object> selectChatMessage(@ModelAttribute("loginMember") Member loginMember,
                        @RequestParam("chatId") int chatRoomId) {
      logger.info("chatRoomId   " + chatRoomId);
         int memberId = loginMember.getMemberId();
       List<ChatMessage> chatMessages = cservice.getChatMessagesByChatRoomId(chatRoomId);
       
       Map<String, Object> responseData = new HashMap<>();
       
     
       responseData.put("chatMessages", chatMessages);
       responseData.put("memberId", memberId);
       logger.info("responseData   " + responseData);
      logger.info("chatMessages   " + chatMessages);
       
      
      logger.info("memberId   " + memberId);
         
       return responseData;
      
   }

   @GetMapping("/example")
   public String example() {
      return "admin/example";
   }
    
   
   
   
}