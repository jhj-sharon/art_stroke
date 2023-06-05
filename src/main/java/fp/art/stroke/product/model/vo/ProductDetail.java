package fp.art.stroke.product.model.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetail {
 
		private int productId;
		private String productName;
		private String productType;
		private String productArtist;
	    private int productPrice;
	    private String productOption1;
	    private String productOption2;
		private String productContent;
		private Date productRDate;
		private Date productUDate;  
		private String productImage; 
		private String productCategory;
		 
		private List<ProductImage> imageList;
		
		private int adminCode;
		
}
