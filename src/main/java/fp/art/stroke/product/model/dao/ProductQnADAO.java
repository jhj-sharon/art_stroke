package fp.art.stroke.product.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.product.model.vo.QnAReply;
import fp.art.stroke.product.model.vo.ProductQnA;

@Repository 
public class ProductQnADAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertProductQna(ProductQnA qna) {
		
		return sqlSession.insert("productQnAMapper.insertProductQna");
		
	}

	public int writeQnA(ProductQnA qna) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productQnAMapper.writeQnA",qna);
	}

	public int insertQnAImage(ProductQnA qna) {
		// TODO Auto-generated method stub
		return sqlSession.insert("productQnAMapper.insertQnAImage",qna);
	}

	public List<ProductQnA> selectQnaList(int productId, Pagination pagination) {
		// TODO Auto-generated method stub
		int offset = (pagination.getCurrentPage()-1)* pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("productQnAMapper.selectQnaList",productId,rowBounds);
	}

	public int getListCount(int productId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("productQnAMapper.getListCount",productId);
	}

	public String selectQnAPw(int qnaId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("productQnAMapper.getQnAPw",qnaId);
	}

	public List<QnAReply> selectQnAReplyList(int qnaId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("productQnAMapper.selectQnAReplyList",qnaId);
	}

	public ProductQnA selectQnADetail(int qnaId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("productQnAMapper.selectQnADetail",qnaId);
	}
	
}
