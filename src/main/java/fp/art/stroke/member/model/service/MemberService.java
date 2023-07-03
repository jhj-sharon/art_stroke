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

	  //0626 ey ey 네이버/카카오 소셜로그인/회원가입
	public abstract int insertMemberNaver(Member member);

	public abstract Member snsLogin(String email,String socialType);

	public abstract int insertMemberKakao(Member member);

	public abstract Member selectApiMember(Member member);

	
	//0628 ey 이메일 비밀번호 찾기

	public abstract String memberTelToEmail(String memberName, String memberTel);


	public abstract Member memberTelToPw(String memberName, String memberTel);

	public abstract int sendSmsFindPw(String memberName,String memberTel, String updatePw);

	public abstract Member memberEmailToPw(String memberName, String memberEmail);
	
	public abstract int updatePwByEmail(String memberName, String memberEmail, String updatePw);



	
	


	


	
	











	

	



}
