package fp.art.stroke.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds; 
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail;
import fp.art.stroke.product.model.vo.ProductImage; 

@Repository
public class AdminProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	Logger logger = LoggerFactory.getLogger(AdminProductDAO.class);
	
	public int getListCount(int adminCode) {
		return sqlSession.selectOne("productMapper.getListCount", adminCode);
	}
	
	
	 
	
	public List<Product> selectProductList(Pagination pagination, int adminCode) {
		
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productMapper.selectProductList", adminCode, rowBounds);
	}
 
	

	public int searchListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("productMapper.searchListCount", paramMap);
	}


	public List<Product> searchProductList(Map<String, Object> paramMap, Pagination pagination) {
		
		int offset = ( pagination.getCurrentPage() - 1 ) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("productMapper.searchProductList", paramMap, rowBounds);
	}




	public int insertProduct(ProductDetail detail) {
		int result = sqlSession.insert("productMapper.insertProduct", detail ); // 0 또는 1
		if(result > 0)	result = detail.getProductId();
		
		// 게시글 삽입 성공 시
		// <selectKey> 태그를 이용해 세팅된 boardNo 값을 반환함 --> 게시글 번호 사용 가능
		
		return result;

	}


	public List<String> selectDBList() {
		return sqlSession.selectList("productMapper.selectDBList");
	}




	public ProductDetail selectProductDetail(int productId) {
		return sqlSession.selectOne("productMapper.selectProductDetail", productId);
	}

	
	public int insertProductImage(ProductImage img) {
		return sqlSession.insert("productMapper.insertProductImage", img);
	}


	public int insertProductImageList(List<ProductImage> productImageList) {
		return sqlSession.insert("productMapper.insertProductImageList", productImageList);
	}




	public List<ProductImage> productImageList() {
		return sqlSession.selectList("productMapper.productImageList");
	}




 
	



 
	
	

	
	
	
	
	
	
	
	
	
 
 




	 

	 
	 

}
