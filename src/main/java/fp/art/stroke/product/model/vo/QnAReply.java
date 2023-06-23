package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QnAReply {
	private int replyId;
	private String replyContent;
	private String replyDt;
	private String memberNick;
	private String profileImage;
	private int replyMemberId;
	private int replyQnANo;
	private int replyParentId;
	private String replySt;
}
