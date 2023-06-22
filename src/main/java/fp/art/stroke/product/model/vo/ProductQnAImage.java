package fp.art.stroke.product.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductQnAImage {
	private int imageNo;
	private String imageReName;
	private String imageOriginal;
	private int imageLevel;
	private int qnaId;
	private String image_ST;
}
