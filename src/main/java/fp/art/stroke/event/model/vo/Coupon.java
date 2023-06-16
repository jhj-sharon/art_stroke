package fp.art.stroke.event.model.vo;

import java.util.List;

import fp.art.stroke.board.model.vo.BoardImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class Coupon {
	private String couponId;
	private int couponCategory;
	private String couponName;
	private String discountAmount;
	private String issuanceDate;
	private String expirationDate;
	private int memberId;
}
