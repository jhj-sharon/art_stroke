package fp.art.stroke.myPage.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Repository
public class MyPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*
	 * public int addrReg(Map<String, Object> map) { return
	 * sqlSession.insert("myPageMapper.addrReg", map); }
	 */

	public List<Addr> selectAddrList(int memberId) {
		return sqlSession.selectList("myPageMapper.selectAddrList", memberId);
	}

	/**
	 * 배송지 중복검사
	 * 
	 * @param addrId
	 * @return
	 */
	public Addr getAddressByAddrId(int addrId) {
		return sqlSession.selectOne("myPageMapper.getAddressByAddrId", addrId);
	}

	public int updateAddress(Addr updateAddress) {
		return sqlSession.update("myPageMapper.updateAddress", updateAddress);
	}

	public int insertAddress(Addr newAddress) {
		return sqlSession.insert("myPageMapper.insertAddress", newAddress);
	}

	/**
	 * 배송지 삭제 DAO
	 * 
	 * @param addrId
	 * @return
	 */
	public int deleteAddr(int addrId) {

		return sqlSession.delete("myPageMapper.deleteAddr", addrId);
	}

	/**
	 * 프로필 이미지 수정
	 * 
	 * @param map
	 * @return result
	 */
	public int updateProfile(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.updateProfile", map);
	}

	/**
	 * 관심 목록 가져오기
	 * 
	 * @param memberId
	 * @return
	 */
	public List<WishList> myPageWishList(int memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.myPageWishList", memberId);
	}

	public int cartInsert(int productId, int memberId, String selectedOption, int productPrice) {
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		map.put("memberId", memberId);
		map.put("selectedOption", selectedOption);
		map.put("productPrice", productPrice);
		return sqlSession.insert("myPageMapper.cartInsert", map);
	}

	/**
	 * 카트 목록 조회
	 * 
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public Cart getCartList(int memberId, int productId, String selectedOption) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		map.put("selectedOption", selectedOption);
		return sqlSession.selectOne("myPageMapper.getCartList", map);
	}

	/**
	 * 조회된 결과가 있으면 갯수 ++;
	 * 
	 * @param cartNum
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public int cartUpdate(int cartNum, int memberId, int productId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		map.put("cartNum", cartNum);
		return sqlSession.update("myPageMapper.cartUpdate", map);
	}

	/**
	 * 위시리스트 삭제 dao
	 * 
	 * @param productId
	 * @param memberId
	 * @return
	 */
	public int deleteWishlist(int productId, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteWishlist", map);
	}

	public int secession(int memberId) {

		return sqlSession.update("myPageMapper.secession", memberId);
	}

	/**
	 * 선택된 위시리스트 삭제 dao
	 * 
	 * @param productIds
	 * @param memberId
	 * @return
	 */
	public int deleteSelectedWishlist(List<Integer> productIds, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("productIds", productIds);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteSelectedWishlist", map);
	}

	/**
	 * 회원정보 수정 DAO
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateInfo(Map<String, Object> paramMap) {

		return sqlSession.update("myPageMapper.updateInfo", paramMap);
	}

	public int nicknameDupCheck(String memberNick, int memberId) {

		return sqlSession.selectOne("myPageMapper.nicknameDupCheck", memberNick);
	}

	/**
	 * 내 게시글 가져오기 DAO
	 * 
	 * @param memberId
	 * @return
	 */
	public List<Board> selectBoardList(int memberId) {

		return sqlSession.selectList("myPageMapper.selectBoardList", memberId);
	}

	/**
	 * 내 게시물 삭제 DAO
	 * 
	 * @param boardIds
	 * @param memberId
	 * @return
	 */
	public int deleteSelectedBoard(List<Integer> boardIds, int memberId) {
		Map<String, Object> map = new HashMap<>();
		map.put("boardIds", boardIds);
		map.put("memberId", memberId);

		return sqlSession.delete("myPageMapper.deleteSelectedBoard", map);
	}

	public List<Product> recentProduct(int[] recentListInt) {
		return sqlSession.selectList("myPageMapper.recentProduct", recentListInt);
	}

}
