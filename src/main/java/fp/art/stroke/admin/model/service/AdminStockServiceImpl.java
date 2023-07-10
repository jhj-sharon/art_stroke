package fp.art.stroke.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.admin.model.dao.AdminStockDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.product.model.vo.ProductStock;

@Service
public class AdminStockServiceImpl implements AdminStockService {

	@Autowired
	private AdminStockDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AdminStockServiceImpl.class);
	
	@Override
	public Map<String, Object> selectStockList(int cp, int adminCode) {

		int listCount = dao.getStockListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<ProductStock> stockList = dao.selectStockList(pagination, adminCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("stockList", stockList);
		map.put("adminCode", adminCode);
		
		
		return map;
	}

	@Override
	public Map<String, Object> searchStockList(Map<String, Object> paramMap) {
		int listCount = dao.searchStockListCount( paramMap  );
		
	 
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<ProductStock> stockList = dao.searchStockList(paramMap, pagination);
		
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("stockList", stockList);
		
		return map;
	}
	
}
