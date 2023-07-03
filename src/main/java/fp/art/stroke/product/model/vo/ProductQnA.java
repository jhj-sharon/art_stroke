package fp.art.stroke.product.model.vo;

import java.util.Date;
import java.util.List;

import fp.art.stroke.board.model.vo.BoardImage;
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
    private String qnaRdate; 
    private String qnaPw;
    private int qnaCheck;
    private String qnaFile;
    private String qnaAnswer;
    private int memberId; //외래키
    private String memberNick; //JOIN
    private int productId; //외래키
    private String productImage; // JOIN
    private List<ProductQnAImage> imageList;
     
}
