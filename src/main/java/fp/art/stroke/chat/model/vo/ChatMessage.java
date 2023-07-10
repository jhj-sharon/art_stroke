package fp.art.stroke.chat.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatMessage {
	private int chatMessageId;
	private String message;
	private Date chatCreateDT;
	private int chatRoomId;
	private int memberId;
	private String memberEmail;
	private String memberNick;
}
