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

	public List<Product> selectMainBestProdcut(String productName) {
		
		return sqlSession.selectList("mainpageMapper.selectMainBestProdcut", productName);
	}

}
