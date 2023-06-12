package fp.art.stroke.member.model.service;

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



}
