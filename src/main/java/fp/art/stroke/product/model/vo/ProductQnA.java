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
public class ProductQnA {
	
	private int qnaId;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaRdate; 
    private String qnaPw;
    private int qnaCheck;
    private String qnaFile;
    
    private int memberId; //외래키
    private String memberNick; //JOIN
    private int productId; //외래키
    private String productImage; // JOIN

}
