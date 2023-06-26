package fp.art.stroke.member.model.service;

import fp.art.stroke.member.model.vo.GoogleMember;
import fp.art.stroke.member.model.vo.Member;

public interface MemberApiService {

	int selectApiMemberCount(String email, String type);

	Member selectApiMember(String email, String type);

	int insertApiMember(GoogleMember googleMember);

}
