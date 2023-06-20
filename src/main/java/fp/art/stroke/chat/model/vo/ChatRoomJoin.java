package fp.art.stroke.chat.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatRoomJoin {
	private int memberId;
	private int chatRoomId;
}
