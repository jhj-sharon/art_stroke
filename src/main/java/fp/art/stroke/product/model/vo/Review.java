package fp.art.stroke.product.model.vo;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {
	
	private int reviewId;
	private String reviewContent;
	private String reviewDt;
	private int reviewStar;
	private String reviewImg;
	private int memberId;
	private int orderDetailId;
	private String memberNick;
	private String socialType;
	private String memberEmail;
	private Member member;
	private Product product;
	
	public Member getMember() {
		return member;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

}
