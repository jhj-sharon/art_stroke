package fp.art.stroke.product.model.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Product {
	
	private int productId;
    private String productName;
    private String productType;
    private String productArtist;
    private int productPrice;
    private String productOption1;
    private String productOption2;
    private Date productRDate;
    private String productImage;
    private String productContent;
    private String productCategory;
    private int sales;
	 
	}
 
