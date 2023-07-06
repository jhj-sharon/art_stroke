package fp.art.stroke.admin.model.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds; 
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.myPage.model.vo.CancelOrder;
import fp.art.stroke.product.model.vo.Order;

@Repository
public class AdminOrderDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	Logger logger = LoggerFactory.getLogger(AdminOrderDAO.class);
	
	
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("orderMapper.getListCount", adminCode);
	}

	public List<Order> selectOrderList(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		 logger.info("DAO ROWBOUNDS : " + rowBounds);
		return sqlSession.selectList("orderMapper.selectOrderList", adminCode, rowBounds);
	}
	 

	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("orderMapper.searchListCount", paramMap);
	}

	
	public List<Order> searchOrderList(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("orderMapper.searchOrderList", paramMap, rowBounds);
 
	}

	public List<String> selectAdminDateList(Map<String, Object> paramMap) {
		 logger.info("DAO paramMap" + paramMap);
	    return sqlSession.selectList("orderMapper.selectAdminDateList", paramMap);
	}

 
	public List<CancelOrder> selectCancelOrderList(Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("myPageMapper.selectCancelOrderList", null, rowBounds);
	}

	public int getCancelOrderListCount() {
		return sqlSession.selectOne("myPageMapper.getCancelOrderListCount");
	}

	public int searchCancelOrderListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("myPageMapper.searchCancelOrderListCount", paramMap);
	}
	
	public List<CancelOrder> searchCancelOrderList(Map<String, Object> paramMap, Pagination pagination) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("myPageMapper.searchCancelOrderList", paramMap, rowBounds);
 
	}

 
	 
	public int approvalAdminCancelOrder(Integer cancelId, List<Integer> cancelChk) {
	      Map<String, Object> params = new HashMap<>(); 
	      params.put("cancelId", cancelId);
	      params.put("cancelChk", cancelChk);
	      params.put("approvalDate", new Date());
	       
	      return sqlSession.update("myPageMapper.updateAdminCancelOrder", params);
	       
	   }

	   public int approvalNotAdminCancelOrder(List<Integer> cancelChk, Integer cancelId) {
	      Map<String, Object> params = new HashMap<>();
	      params.put("cancelChk", cancelChk);
	      params.put("cancelId", cancelId);
	      params.put("approvalDate", new Date());
	       
	      return sqlSession.update("myPageMapper.updateAdminNotCancelOrder", params);
	       
	   }

	public int processOrders(List<String> orderIds) {
		 Map<String, Object> params = new HashMap<>();
	      params.put("orderIds", orderIds);
		return sqlSession.update("orderMapper.processOrders", params);
	}

	public int deleteProcessOrders(List<String> orderIds) {
		Map<String, Object> params = new HashMap<>();
	      params.put("orderIds", orderIds);
		return sqlSession.update("orderMapper.deleteProcessOrders", params);
	}
 
 

	 
}
