package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItems {
	
	private int orderItemsId;
	private int cartId;
	private String Option;
	private int quantity; 
	private int productId;
	private int memberId;
	
	//장바구니 view를 위한 Joined Column
	private String productName;
    private int productPrice;
    private String productImage;

}
