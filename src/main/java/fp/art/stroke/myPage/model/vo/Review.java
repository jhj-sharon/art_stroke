package fp.art.stroke.myPage.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {
	
	private int reviewId;
	private String reviewContent;
	private Date reviewDt;
	private int reviewStar;
	private String reviewImage;
	private int memberId;
	private int orderDetailId;
	
}
