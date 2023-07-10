package fp.art.stroke.myPage.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Addr {
	private int addrId;
	private String deliveryName;
	private String receiverName;
	private String addr;
	private String addrTel;
	private String addrSt;
	private int memberId;
}
