package fp.art.stroke.member.model.service;

import fp.art.stroke.member.model.vo.GoogleMember;
import fp.art.stroke.member.model.vo.Member;

public interface MemberApiService {

	int selectApiMemberCount(Member member);

	Member selectApiMember(Member member);

	int signUpApiMember(Member member);

}
