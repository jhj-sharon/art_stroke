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
import fp.art.stroke.myPage.model.vo.CancelOrder;
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
		 
		 logger.info("SERVICE ORDERLIST : " + map);
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

	@Override
	public Map<String, Object> selectCancelOrder(int cp) {
		int listCount = dao.getCancelOrderListCount();
		Pagination pagination = new Pagination(cp, listCount);
		
		List<CancelOrder> cancelOrderList = dao.selectCancelOrderList(pagination);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("cancelOrderList", cancelOrderList);
	 
		 
		
		return map;
	}

	@Override
	public Map<String, Object> searchCancelOrder(Map<String, Object> paramMap) {
		int listCount = dao.searchCancelOrderListCount( paramMap  );
		
		 
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
	 
		List<CancelOrder> cancelOrderList = dao.searchCancelOrderList(paramMap, pagination);
		
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("cancelOrderList", cancelOrderList);
		 
		
		logger.info("service CANCEL Search" + cancelOrderList + map + paramMap);
		return map;
	}

	@Override
	public int approvalAdminCancelOrder(List<Integer> cancelChk, List<String> orderIds) {
	    int result = 0;
	    logger.info("!!!!업데이트된 cancelChk 큐앤에이: " + cancelChk);
	    logger.info("!!!!업데이트된 orderIds 큐앤에이: " + orderIds);
	    
	    if (cancelChk != null) {
	        for (Integer cancelId : cancelChk) {
	            result = dao.approvalAdminCancelOrder(cancelId, cancelChk);
	            
	            if (result > 0 && orderIds != null) {
	                result += dao.processOrders(orderIds);
	                logger.info("업데이트된 orderIds 큐앤에이: " + orderIds);
	            } 
	            
	            logger.info("업데이트된 cancelChk 큐앤에이: " + cancelChk);
	            logger.info("서비스임플 result: " + result);
	        }
	    }
	    
	    return result;
	}


	   @Override
	   public int approvalNotAdminCancelOrder(List<Integer> cancelChk, List<String> orderIds) {
	        int result =0;
	          if (cancelChk != null) {
	              for (Integer cancelId : cancelChk) {
	                  result = dao.approvalNotAdminCancelOrder(cancelChk, cancelId);
	               
	                  if (result > 0 && orderIds != null) {
	  	                result += dao.deleteProcessOrders(orderIds);
	  	                logger.info("업데이트된 orderIds 큐앤에이: " + orderIds);
	  	            } 
	  	            
	  	            logger.info("업데이트된 cancelChk 큐앤에이: " + cancelChk);
	  	            logger.info("서비스임플 result: " + result);
	  	        }
	  	    }
	  	    
	  	    return result;
	  	}
	   


 


	
}
