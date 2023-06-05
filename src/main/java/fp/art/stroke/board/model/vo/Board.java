package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {
	private int boardId;
	private int boardCode;
	private String boardTitle;
	private String boardContent;
	private String board_files;
	private String boardDt;
	private int boardCNT;
	private String auth;
	private int memberId;
}
