package fp.art.stroke.chat.websocket;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import fp.art.stroke.chat.model.service.ChatService;
import fp.art.stroke.chat.model.vo.ChatMessage;
//@ServerEndpoint("/websocket")
public class ChatWebsocketHandler extends TextWebSocketHandler {

   @Autowired
   private ChatService service;

   private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

   @RequestMapping("/stroke/myPage/myPageMain")
   public String chatPage() {
      return "myPageMain"; // WebSocket을 사용하는 페이지의 뷰 이름
   }

   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {

      
      System.out.println(session.getId() + " 연결됨!!!!!!!!!!!!!"); // 세션 아이디 확인
      

      sessions.add(session);

   }

   // 클라이언트로부터 텍스트 메시지를 전달 받았을 때 수행
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

      System.out.println("전달된 메세지: " + message.getPayload());

      ObjectMapper objectMapper = new ObjectMapper();

      ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

      // 시간 세팅
      chatMessage.setChatCreateDT(new Date(System.currentTimeMillis()));

      // WebSocketSession에 설정된 속성들 가져오기
      int memberId = (Integer) session.getAttributes().get("memberId");
      String memberEmail = (String) session.getAttributes().get("memberEmail");
      String memberNick = (String) session.getAttributes().get("memberNick");
      int chatRoomId = (Integer) session.getAttributes().get("chatRoomId");
      String inputVal = message.getPayload(); // 자바스크립트로부터 전달된 값

      chatMessage.setMessage(inputVal);
      // chatMessage에 속성 값 설정
      chatMessage.setMemberId(memberId);
      chatMessage.setMemberEmail(memberEmail);
      chatMessage.setMemberNick(memberNick);
      chatMessage.setMessage(inputVal);
      chatMessage.setChatRoomId(chatRoomId);

      System.out.println(chatMessage);

      int result = service.insertChatMessage(chatMessage.getMemberId(), chatMessage.getMemberEmail(),
            chatMessage.getMemberNick(), chatMessage.getMessage(), chatMessage.getChatRoomId());

      if (result > 0) {

         for (WebSocketSession s : sessions) {
            // WebSocketSession == HttpSession을 가로챈 것(로그인정보, 채팅방번호)
            int sessionChatRoomId = (Integer) s.getAttributes().get("chatRoomId");

            if (sessionChatRoomId == chatMessage.getChatRoomId()) {
               // 같은 방 클라이언트에게 JSON 형식의 메세지를 보냄
               s.sendMessage(new TextMessage(new Gson().toJson(chatMessage)));
            }
         }
      }
   }

   // 클라이언트와 연결이 종료되면 수행
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

      sessions.remove(session);

   }

}