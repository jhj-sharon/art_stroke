package fp.art.stroke.product.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;

@Repository 
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Product> getWriterProductList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productMapper.getWriterProductList",memberId);
	}

	/**전체 상품 수 조회 DAO
	 * @return
	 */
	public int getListCount() {
		
		return sqlSession.selectOne("productMapper.getListCountP");
	}

	/**상품 목록 조회 DAO
	 * @param pagination
	 * @return
	 */
	public List<Product> loadProductList(Pagination pagination) {
		 
		//현재 페이지에 해당하는 만큼의 상품만 가져오기
		
		int offset = (pagination.getCurrentPage() - 1 ) *pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productMapper.loadProductList", rowBounds);
	}
}
