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
public class ProductQnAList {
	
	private int qnaId;
    private String qnaTitle;
    private String memberNick;
    private Date qnaRDate;
    private String qnaPw;
    private String qnaCheck;
    private int productId;

}
