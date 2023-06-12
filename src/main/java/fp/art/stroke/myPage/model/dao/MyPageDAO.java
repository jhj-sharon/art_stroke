package fp.art.stroke.myPage.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.model.vo.Cart;
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
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public Cart getCartList(int memberId, int productId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productId", productId);
		
		return sqlSession.selectOne("myPageMapper.getCartList", map);
	}
	/**
	 * 조회된 결과가 있으면 갯수 ++;
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
		return sqlSession.update("myPageMapper.cartUpdate",map);
	}

}
