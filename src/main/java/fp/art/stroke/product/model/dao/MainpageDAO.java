package fp.art.stroke.product.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.Product;


@Repository
public class MainpageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 베스트 상품 불러오기 
	 * @param productName
	 * @return
	 */
	public List<Product> selectMainBestProdcut(String productName) {
		
		return sqlSession.selectList("mainpageMapper.selectMainBestProdcut", productName);
	}

	
	
	/** 키매, 하이퍼펜션 상품 불러오기
	 * @return
	 */
	public List<Product> selectMainArtistProdcut() {
		
		return sqlSession.selectList("mainpageMapper.selectMainArtistProdcut");
	}

}
