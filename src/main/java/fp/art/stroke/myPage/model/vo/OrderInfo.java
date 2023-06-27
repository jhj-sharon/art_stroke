package fp.art.stroke.myPage.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderInfo {
	private String orderDate;
	private String orderId;
	private int productId;
	private String productImage;
	private String productName;
	private String optionInfo;
	private String orderStatus;
	private int memberId;
}
