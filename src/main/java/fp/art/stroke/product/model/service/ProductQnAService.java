package fp.art.stroke.product.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.product.model.vo.ProductQnA;

public interface ProductQnAService {

	int insertProductQna(ProductQnA qna);

	Map<String, Object> selectQnaList(int productId, int cp);

	Map<String, Object> selectQnADetail(int qnaId, String qnaPw);

}
