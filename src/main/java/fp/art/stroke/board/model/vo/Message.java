package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
	private int messageId;
	private int senderId;
	private int receiverId;
	private String messageTitle;
	private String messageContent;
	private String messageDt;
	private String messageOpen;
	private String messageSt;
	//메시지 출력용
	private String memberNick;
}
