package fp.art.stroke.chat.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatRoom {
	private int chatRoomId;
	private String chatTitle;
	private String chatStatus;
	private int memberId;
	private String memberNick;
	private int chatCnt; // 참여자 수
}
