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
public class WishList {
	private int wishListId;
	private int memberId;
	private int productId;

}
