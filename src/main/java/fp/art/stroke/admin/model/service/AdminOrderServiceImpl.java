package fp.art.stroke.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminOrderDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Order;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private AdminOrderDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AdminOrderServiceImpl.class);

	@Override
	public Map<String, Object> selectOrderList(int cp, int adminCode) {

		int listCount = dao.getListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Order> orderList = dao.selectOrderList(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("orderList", orderList);
		map.put("adminCode", adminCode);
		 
		
		return map;
	}

	@Override
	public Map<String, Object> searchOrderList(Map<String, Object> paramMap) {
		int listCount = dao.searchListCount( paramMap  );
		
	 
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<Order> orderList = dao.searchOrderList(paramMap, pagination);
		
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("orderList", orderList);
		
		logger.info("service Search" + orderList + map + paramMap);
		return map;
	}

	@Override
	public List<String> selectAdminDateList(Map<String, Object> paramMap) {
		List<String> list = dao.selectAdminDateList(paramMap); 
		
		logger.info("service DATE List " + list);
		return list;
	}
	
	
	
}
