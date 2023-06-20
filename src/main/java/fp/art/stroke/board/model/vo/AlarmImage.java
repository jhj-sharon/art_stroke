package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AlarmImage {
	private int imageNo;
	private String imageReName;
	private String imageOriginal;
	private int imageLevel;
	private int alarmId;
	private String image_ST;
}
