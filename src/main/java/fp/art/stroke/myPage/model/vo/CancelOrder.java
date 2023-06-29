package fp.art.stroke.myPage.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CancelOrder {
	private int cancelorderId;
	private String orderId;
	private String productIds;
	private String requestedDate;
	private String approvalDate;
	private String cancellationReason;
	private String approvalStatus;
	private int memberId;
}
