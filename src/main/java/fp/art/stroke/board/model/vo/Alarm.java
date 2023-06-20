package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Alarm {
	private int alarmId;
	private String alarmTitle;
	private String alarmContent;
	private String alarmThumbNail;
	private String writerProfileImage;
	private String alarmDt;
	private int alarmCnt;
	private int memberId;
	private String memberNick;
}
