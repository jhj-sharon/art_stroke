package fp.art.stroke.member.model.service;

import fp.art.stroke.member.model.vo.Member;

public interface MemberService {

	/**로그인서비스
	 * @param inputMember
	 * @return loginMember
	 */

	
	public abstract Member login(Member inputMember);

	public abstract int emailDupCheck(String memberEmail);

}
