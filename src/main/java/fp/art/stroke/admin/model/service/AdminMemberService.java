package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductQnA;

public interface AdminMemberService {
 
	Map<String, Object> selectMemberList(int cp, int adminCode);

	Map<String, Object> searchMemberList(Map<String, Object> paramMap);

	Map<String, Object> selectAdminMemberQA(int cp, int adminCode);

	Map<String, Object> searchAdminMemberQA(Map<String, Object> paramMap);

	List<Integer> updateAdminMemberQA(List<Integer> qnaIdList);

	 

}
