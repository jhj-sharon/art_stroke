package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

public interface AdminBoardService {

	Map<String, Object> selectBoardList(int cp, int adminCode);

	Map<String, Object> searchBoardList(Map<String, Object> paramMap);

	int deleteAdminBoard(List<Integer> boardChk); 
}