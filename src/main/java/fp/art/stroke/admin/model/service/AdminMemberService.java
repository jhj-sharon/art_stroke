package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductQnA;

public interface AdminMemberService {
 
	// Member List
	Map<String, Object> selectMemberList(int cp, int adminCode);

	Map<String, Object> searchMemberList(Map<String, Object> paramMap);

	
	// Member Q&A
	Map<String, Object> selectAdminMemberQA(int cp, int adminCode);

	Map<String, Object> searchAdminMemberQA(Map<String, Object> paramMap);
 
	int updateAdminMemberQA(List<Integer> selectedIds);

	
	// Member Report
	Map<String, Object> selectMemberReport(int cp);

	Map<String, Object> searchMemberReport(Map<String, Object> paramMap);

	int updateAdminMemberReport(List<Integer> reportChk);

	int updateAdminAuth(List<Integer> authChk);

	Map<String, Object> selectAdminReview(int cp);

	Map<String, Object> searchAdminReview(Map<String, Object> paramMap);

	int deleteAdminReview(List<Integer> reviewChk);

	int adminDeleteMember(List<Integer> authChk);

	int sendBack(String memberNick, String sendName, String messageTitle, String messageContent, int senderId,
			int memberId);

	 

}
