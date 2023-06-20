package fp.art.stroke.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AlarmDetail {
	private int alarmId;
	private String alarmTitle;
	private String alarmContent;
	private String alarmDate;
	private String alarmUDT;
	private int readCount;
	private String memberNickname;
	private String alarmThumbNail;
	private String profileImage;
	private int memberId;
	
	private List<AlarmImage> imageList;
}
