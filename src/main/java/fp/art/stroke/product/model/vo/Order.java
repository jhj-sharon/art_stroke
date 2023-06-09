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
public class Order {
	private int orderId;
	private String userId;
	private Date orderDT;
	private int quantity;
	private int shippingFee;
	private int totalPrice;
	private String receiver;
	private String address;
	private String receiverPhone;
	private String memo;
	private String payment;
	private String orderStatus;
	private String reviewStatus;
	private String invoiceId;
	private int memberId;
	private int productId;
	private int addrId;
}
 