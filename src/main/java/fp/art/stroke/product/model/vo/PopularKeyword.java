package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PopularKeyword {
	
	private int popularKeywordId;
	private String popularKeyword;
	private int searchCount;

}
