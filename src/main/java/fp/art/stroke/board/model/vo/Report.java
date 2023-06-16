package fp.art.stroke.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Report {
	private int reportId;//SEQUENCE
	private String reportTargetTitle;//게시판제목 OR댓글내용
	private String reportTargetContent;//데이터베이스 서브쿼리로 가져오기.
	private String reportContent;//신고내용
	private int reportTarget;//신고대상아이디
	private int reportSendId;//신고자아이디
	private String reportSendNick;//신고자 닉넴
	private String reportTargetType;//신고타입
	private String reportTargetNick;//신고대상닉네임 
}
