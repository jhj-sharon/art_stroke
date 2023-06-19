package fp.art.stroke.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Follow {
	private int followId;//시퀀스 primary key
	private int writerId;
	private int followerId;
	private String writerNick;
	private String followerNick;
}
