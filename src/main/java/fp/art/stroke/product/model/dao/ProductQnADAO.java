package fp.art.stroke.product.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.product.model.vo.ProductQnA;

@Repository 
public class ProductQnADAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertProductQna(ProductQnA qna) {
		
		return sqlSession.insert("productQnAMapper.insertProductQna");
		
	}
	
}
