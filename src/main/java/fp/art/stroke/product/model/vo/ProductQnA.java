package fp.art.stroke.product.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString


public class ProductQnA {
	
	private int qnaId;
    private String productImage;
    private String qnaTitle;
    private String qnaContent;
    private String qnaWriter;
    private Date qnaRDate;
    private Integer qnaSecret;
    private int userId;
    private String qnaFile1;
    private String qnaFile2;
    private String qnaSecretPw;
    private String qnaCheck;
    private int productId;

}
