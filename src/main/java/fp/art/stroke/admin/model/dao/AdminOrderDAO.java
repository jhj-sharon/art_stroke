package fp.art.stroke.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Order;

@Repository
public class AdminOrderDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getListCount(int adminCode) {
		return sqlSession.selectOne("orderMapper.getListCount", adminCode);
	}

	public List<Order> selectOrderList(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
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

}
