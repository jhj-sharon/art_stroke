package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reply {
	private int replyId;
	private String replyContent;
	private String replyDt;
	private String memberNickname;
	private String profileImage;
	private int replyMemberId;
	private int replyBoardNo;
	private int replyParentId;
}
