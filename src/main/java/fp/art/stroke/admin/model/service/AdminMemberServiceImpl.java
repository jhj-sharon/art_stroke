package fp.art.stroke.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminMemberDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.Product;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {
	
	@Autowired
	private AdminMemberDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AdminMemberServiceImpl.class);
 
	@Override
	public Map<String, Object> selectMemberList(int cp, int adminCode) {

		int listCount = dao.getListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Member> memberList = dao.selectMemberList(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		map.put("adminCode", adminCode);
		
		
		return map;
	}

	@Override
	public Map<String, Object> searchMemberList(Map<String, Object> paramMap) {
		int listCount = dao.searchListCount( paramMap  );
		
		
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<Member> memberList = dao.searchMemberList(paramMap, pagination);
		
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		
		logger.info("service Search" + memberList + map + paramMap);
		return map;
	}
	
	
	
}
