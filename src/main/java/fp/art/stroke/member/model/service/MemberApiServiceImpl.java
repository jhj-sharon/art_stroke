package fp.art.stroke.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.member.model.dao.MemberApiDAO;
import fp.art.stroke.member.model.dao.MemberDAO;
import fp.art.stroke.member.model.vo.GoogleMember;
import fp.art.stroke.member.model.vo.Member;

@Service
public class MemberApiServiceImpl implements MemberApiService{
	@Autowired //bean으로 등록된 객체중 같은 타입이 있으면 의존성 주입(DI)
	private MemberApiDAO dao;

	// 암호화를 위한 bcrypt 객체 의존성 주입(DI)
	
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public int selectApiMemberCount(Member member) {
		// TODO Auto-generated method stub
		return dao.selectApiMemberCount(member);
	}

	@Override
	public Member selectApiMember(Member member) {
		// TODO Auto-generated method stub
		return dao.selectaApiMember(member);
	}

	

	@Override
	public int signUpApiMember(Member member) {
		// TODO Auto-generated method stub
		return dao.signUpApiMember(member);
	}
	
}
