package fp.art.stroke.myPage.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

public interface MyPageService {

	int addrReg(String addrName, String receiverName, String postcode, String roadAddress, String detailAddress,
			String addrTel, int memberId, int addrId);

	List<Addr> selectAddrList(int memberId);

	int deleteAddr(int addrId);

	int updateProfile(Map<String, Object> map)throws IOException;

	List<WishList> myPageWishList(int memberId);

	int cartInsert(int productId, int memberId,  String selectedOption, int productPrice);

	int deleteWishlist(int productId, int memberId);

	int secession(int memberId);

	int deleteSelectedWishlist(List<Integer> productIds, int memberId);

	int updateInfo(Map<String, Object> paramMap);

	int nicknameDupCheck(String memberNick, int memberId);

	List<Board> selectBoardList(int memberId);

	int deleteSelectedBoard(List<Integer> boardIds, int memberId);

	List<Product> recentProduct(int[] recentListInt);





}
