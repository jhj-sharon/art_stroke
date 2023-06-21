package fp.art.stroke.member.model.service;

import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;

public interface MemberService {

	/**로그인서비스
	 * @param inputMember
	 * @return loginMember
	 */

	
	public abstract Member login(Member inputMember);

	public abstract int emailDupCheck(String memberEmail);

	public abstract int nicknameDupCheck(String memberNick);

	public abstract int signUp(Member inputMember);
	
//06/12 ey
	public abstract int insertCertification(String inputEmail, String cNumber);

	public abstract int checkNumber(String inputEmail, String cNumber);
	
	public abstract int telInsertCertification(String inputTel, String smsCNumber);

	public abstract int checkSmsNumber(String inputTel, String smsCNumber);


	public abstract int insertFollow(Follow follow);

	public abstract int deleteFollow(Follow follow);
	public abstract int addCouponDiscount(int memberId, String couponId1, int couponCategory, String couponName1,
			String couponInfo, double discountAmount1);

	public abstract int addCouponFreeShipping(int memberId, String couponId2, int couponCategory, String couponName2,
			String couponInfo, double discountAmount2);

	//0620 ey 
	public abstract int updateCouponOptIn(int memberId, String couponOptIn);

	//0621 ey 구글로그인
	public abstract Member loginMemberByGoogle(Member member);

	public abstract void joinMemberByGoogle(Member member);


	
	











	

	



}
