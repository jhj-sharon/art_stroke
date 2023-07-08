package fp.art.stroke.admin.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.ProductImage;

public interface AdminMainService {

	Member adminLogin(Member inputMember);

	List<AdminType> selectAdminType();

	Board selectBestBoardOne();
 

}
