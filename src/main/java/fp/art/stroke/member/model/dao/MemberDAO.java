package fp.art.stroke.member.model.dao;

import java.util.HashMap;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;

@Repository // 영속성을 가지는 DB/파일과 연결되는 클래스임을 명시 + bean 등록
public class MemberDAO {
	// DAO는 DB랑 연결하기 위한 Connection이 공통적으로 필요하다!
	// -> 필드에 선언
	// + Mybatis 영속성 프레임워크를 이용하려면 Connection을 이용해 만들어진 객체
	// SqlSessionTemplate을 사용

	@Autowired // root-context.xml 에서 생성된 SqlSessionTemplate bean을 의존성 주입(DI)
	private SqlSessionTemplate sqlSession;

	private Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	public Member login(Member inputMember) {

		// 1행 조회(파라미터 X) 방법
		// int count = sqlSession.selectOne("namespace값.id값");

		// int count = sqlSession.selectOne("memberMapper.test1");
		// logger.debug(count + "");

		// 1행 조회(파라미터 O) 방법
		// String memberNickname = sqlSession.selectOne("memberMapper.test2",
		// inputMember.getMemberEmail() );
		// logger.debug(memberNickname);

		// 1행 조회(파라미터가 VO인 경우)
		// String memberTel = sqlSession.selectOne("memberMapper.test3", inputMember);
		// memberEmail, memberPw
		// logger.debug(memberTel);

		// 1행 조회(파라미터가 VO, 반환되는 결과도 VO)
		Member loginMember = sqlSession.selectOne("memberMapper.login", inputMember);

		return loginMember;
	}

	// 이메일 중복검사
	public int emailDupCheck(String memberEmail) {
		return sqlSession.selectOne("memberMapper.emailDupCheck", memberEmail);
	}

	// 닉네임중복검사
	public int nicknameDupCheck(String memberNick) {
		return sqlSession.selectOne("memberMapper.nicknameDupCheck", memberNick);
	}

	public int signUp(Member inputMember) {
		return sqlSession.insert("memberMapper.signUp", inputMember);
	}

	public Member getWriter(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.getWriter", memberId);
	}

	public int selectWriter(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.selectWriter", memberId);
	}

	// 06/12 ey
	public int updateCertification(String inputEmail, String cNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("inputEmail", inputEmail);
		params.put("cNumber", cNumber);
		return sqlSession.update("memberMapper.updateCertification", params);
	}

	public int insertCertification(String inputEmail, String cNumber) {

		Map<String, Object> params = new HashMap<>();
		params.put("inputEmail", inputEmail);
		params.put("cNumber", cNumber);
		return sqlSession.insert("memberMapper.insertCertification", params);
	}

	public int checkNumber(String inputEmail, String cNumber) {

		Map<String, Object> params = new HashMap<>();
		params.put("inputEmail", inputEmail);
		params.put("cNumber", cNumber);

		return sqlSession.selectOne("memberMapper.checkNumber", params);
	}

	public int telUpdateCertification(String inputTel, String smsCNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("inputTel", inputTel);
		params.put("smsCNumber", smsCNumber);
		return sqlSession.update("memberMapper.telUpdateCertification", params);
	}

	public int telInsertCertification(String inputTel, String smsCNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("inputTel", inputTel);
		params.put("smsCNumber", smsCNumber);
		return sqlSession.insert("memberMapper.telInsertCertification", params);
	}

	public int checkSmsNumber(String inputTel, String smsCNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("inputTel", inputTel);
		params.put("smsCNumber", smsCNumber);

		return sqlSession.selectOne("memberMapper.checkSmsNumber", params);
	}

	public int addCouponDiscount(int memberId, String couponId1, int couponCategory, String couponName1,
			String couponInfo, double discountAmount1) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("couponId", couponId1);
		params.put("couponCategory", couponCategory);
		params.put("couponName", couponName1);
		params.put("couponInfo", couponInfo);
		params.put("discountAmount", discountAmount1);
		return sqlSession.insert("memberMapper.addCouponDiscount", params);
	}

	public int addCouponFreeShipping(int memberId, String couponId2, int couponCategory, String couponName2,
			String couponInfo, double discountAmount2) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("couponId", couponId2);
		params.put("couponCategory", couponCategory);
		params.put("couponName", couponName2);
		params.put("couponInfo", couponInfo);
		params.put("discountAmount", discountAmount2);
		return sqlSession.insert("memberMapper.addCouponFreeShipping", params);
	}

	public int deleteFollow(Follow follow) {
		// TODO Auto-generated method stub
		return sqlSession.delete("memberMapper.deleteFollow", follow);
	}

	public int insertFollow(Follow follow) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.insertFollow", follow);
	}

	public int updateCouponOptIn(int memberId, String couponOptIn) {
		Map<String, Object> param = new HashMap<>();
		param.put("couponOptIn", couponOptIn);
		param.put("memberId", memberId);
		return sqlSession.update("memberMapper.updateCouponOptIn", param);
	}

	public Member readMemberWithIDPW(String memberEmail, String memberPw) {
		Map<String, Object> param = new HashMap<>();
		param.put("memberEmail", memberEmail);
		param.put("memberPw", memberPw);
		return sqlSession.selectOne("memberMapper.readMemberWithIDPW", param);
	}

	public int joinMemberByGoogle(Member member) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.joinMemberByGoogle", member);
	}

//0625 ey 네이버/카카오 소셜로그인/회원가입
	public int insertMemberNaver(Member member) {
		return sqlSession.insert("memberMapper.insertMemberNaver", member);

	}

	public Member snsLogin(String email, String socialType) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("socialType", socialType);

		return sqlSession.selectOne("memberMapper.snsLogin", paramMap);
	}

	public int insertMemberKakao(Member member) {
		return sqlSession.insert("memberMapper.insertMemberKakao", member);
	}

	public Member selectApiMember(Member member) {
		return sqlSession.selectOne("membrMapper.selectApiMember", member);
	}
	
	
	//0628 ey 아이디비밀번호찾기
	//0629

	public String memberTelToEmail(String memberName, String memberTel) {
		Map<String, Object> param = new HashMap<>();
		param.put("memberName", memberName);
		param.put("memberTel", memberTel);
		return sqlSession.selectOne("memberMapper.memberTelToEmail", param);
	}



	
	//비밀번호 전화번호 sms로 찾기
	public Member memberTelToPw(String memberName, String memberTel) {
		Map<String,Object>param=new HashMap<>();
		param.put("memberName",memberName);
		param.put("memberTel", memberTel);
		return sqlSession.selectOne("memberMapper.memberTelToPw",param);
		
	}



	public int updateMemberPw(String memberName,String memberTel, String updatePw) {
		Map<String,Object>param=new HashMap<>();
		param.put("memberName", memberName);
		param.put("memberTel", memberTel);
		param.put("updatePw", updatePw);
		return sqlSession.update("memberMapper.updateMemberPw",param);
	}

	
	//비밀번호 이메일로 찾기
	public Member memberEmailToPw(String memberName, String memberEmail) {
		Map<String,Object>param=new HashMap<>();
		param.put("memberName",memberName);
		param.put("memberEmail", memberEmail);
		return sqlSession.selectOne("memberMapper.memberEmailToPw",param);
	}
	
	
	public int updatePwByEmail(String memberName, String memberEmail, String updatePw) {
		Map<String,Object>param=new HashMap<>();
		param.put("memberName", memberName);
		param.put("memberEmail", memberEmail);
		param.put("updatePw", updatePw);
		return sqlSession.update("memberMapper.updatePwByEmail",param);
	}

	
	
	



}
