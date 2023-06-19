package fp.art.stroke.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.ProductStock;

@Repository
public class AdminStockDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	public int getStockListCount(int adminCode) {
		return sqlSession.selectOne("productMapper.getStockListCount", adminCode);
	}

 
	public List<ProductStock> selectStockList(Pagination pagination, int adminCode) {
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productMapper.selectStockList", adminCode, rowBounds);
	}


	public int searchStockListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("productMapper.searchStockListCount", paramMap);
	}


	public List<ProductStock> searchStockList(Map<String, Object> paramMap, Pagination pagination) {

		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productMapper.searchStockList", paramMap, rowBounds);
	}
	
	
	
	
}
