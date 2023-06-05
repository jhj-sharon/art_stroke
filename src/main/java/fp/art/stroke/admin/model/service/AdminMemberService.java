package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.product.model.vo.Product;

public interface AdminMemberService {
 
	Map<String, Object> selectMemberList(int cp, int adminCode);

	Map<String, Object> searchMemberList(Map<String, Object> paramMap);

}
