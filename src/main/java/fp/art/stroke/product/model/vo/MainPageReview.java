package fp.art.stroke.product.model.vo;

import fp.art.stroke.member.model.vo.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MainPageReview {
	
	private Product product;
	private Member member;
	private OrderDetail orderDetail;
	private Review review;


}
