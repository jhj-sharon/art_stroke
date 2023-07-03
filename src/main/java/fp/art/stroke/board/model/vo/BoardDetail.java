package fp.art.stroke.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDetail {
	private int boardId;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private int readCount;
	private String memberNickname;
	private String boardThumbNail;
	private String profileImage;
	private int memberId;
	private int boardGood;
	private String memberSocialType;
	
	private List<BoardImage> imageList;
	
	private int boardCode;
}
