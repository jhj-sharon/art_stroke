package fp.art.stroke.chat.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class Websocket {
    // 접속한 클라이언트를 유지하는 Set
    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void handleOpen(Session session) {
        // 클라이언트가 접속하면 클라이언트 세션을 추가한다.
        clients.add(session);
        System.out.println("Client is now connected. Session ID: " + session.getId());
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        // 받은 메시지를 다른 클라이언트에게 브로드캐스트한다.
        System.out.println("Received from client: " + message);
        broadcast(message);
    }

    @OnClose
    public void handleClose(Session session) {
        // 클라이언트가 접속을 끊으면 클라이언트 세션을 제거한다.
        clients.remove(session);
        System.out.println("Client is now disconnected. Session ID: " + session.getId());
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    private void broadcast(String message) {
        // 모든 클라이언트에게 메시지를 브로드캐스트한다.
        for (Session client : clients) {
            try {
                client.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


