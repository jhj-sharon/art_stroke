package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminMainDAO;
import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.member.model.vo.Member;

@Service
public class AdminMainServiceImpl implements AdminMainService {

	@Autowired
	private AdminMainDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AdminMainServiceImpl.class);
	
	@Override
	public List<AdminType> selectAdminType() {
		return dao.selectAdminType();
	}

	
	
	// 로그인 
	@Override
	public Member adminLogin(Member inputMember) {
		
		logger.debug( inputMember.getMemberPw() + " / " +  bcrypt.encode(inputMember.getMemberPw()) );
		
		Member loginMember = dao.adminLogin(inputMember);
		
		if(loginMember != null) { // 일치하는 이메일을 가진 회원 정보가 있을 경우
			
			// 입력된 비밀번호(평문) , 조회된 비밀번호(암호화) 비교 (같으면 true)
							 		//평문                  ,   암호화
			if( bcrypt.matches(  inputMember.getMemberPw()   ,  loginMember.getMemberPw() ) ) {
				// 비밀번호가 일치할 경우
				
				loginMember.setMemberPw(null); // 비교 끝났으면 비밀번호 지우기
				
			} else { // 비밀번호가 일치하지 않은 경우
				loginMember = null; // 로그인을 실패한 형태로 바꿔줌
				
			}
		}
		
		return loginMember;
	}



	@Override
	public Board selectBestBoardOne() {
		Board board = dao.selectBestBoardOne();
		
		return dao.selectBestBoardOne();
	}

	 
	 


}
