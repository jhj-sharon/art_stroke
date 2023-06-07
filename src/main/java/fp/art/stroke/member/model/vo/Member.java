package fp.art.stroke.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Member {

	   private int memberId;
	   private String memberEmail;
	   private String memberPw;
	   private String memberName;
	   private String memberNick;
	   private String memberTel;
	   private String memberAddr;
	   private String profileImage;
	   private String memberEd;
	   private String secessionFl;
	   private String socialFl;
	   private String memberIntro;
	   private int auth;
	   private String memberSns;
	   private String EmailOptIn;
}
