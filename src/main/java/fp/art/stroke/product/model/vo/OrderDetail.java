package fp.art.stroke.product.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetail {
	private int orderDetailId;
	private String option;
	private String orderId;
	private int quantity;
	private String reviewStatus;
	private int productId;
	private int memberId;
	
}
