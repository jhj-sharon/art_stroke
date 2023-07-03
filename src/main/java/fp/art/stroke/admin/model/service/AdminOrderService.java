package fp.art.stroke.admin.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdminOrderService {

	Map<String, Object> selectOrderList(int cp, int adminCode);

	Map<String, Object> searchOrderList(Map<String, Object> paramMap);
 

	List<String> selectAdminDateList(Map<String, Object> paramMap);

	Map<String, Object> selectCancelOrder(int cp);

	Map<String, Object> searchCancelOrder(Map<String, Object> paramMap);

	int approvalAdminCancelOrder(List<Integer> cancelChk);

	int approvalNotAdminCancelOrder(List<Integer> cancelChk);

}
