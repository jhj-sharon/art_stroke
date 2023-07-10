package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {
	private int paymentId;
	private String orderId;
	private String paymentDate;
	private String paymethod;
	private int totalPrice;
	private int memberId;
	private String depositName;

}
