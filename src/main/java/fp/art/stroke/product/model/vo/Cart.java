package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart {
	private int cartId;
	private String cartOption;
	private int quantity; 
	private int productId;
	private int memberId;
}
