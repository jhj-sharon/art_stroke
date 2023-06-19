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
public class ProductStock {
	private int stockId;
	private String productName;
	private String productType;
	private String productArtist;
	private String productOption1;
	private String productOption2;
	private int stockIn;
	private int stockOut;
	private int stockAmt;
	private int productPrice;
	private Date stockDt;
	private String stockStatus;
	private int productId;
	
	


}
