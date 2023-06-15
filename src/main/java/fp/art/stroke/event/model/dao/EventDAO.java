package fp.art.stroke.event.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fp.art.stroke.event.model.vo.Coupon;

@Repository
public class EventDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	/** 룰렛 이벤트 참여 확인 
	 * @param memberId
	 * @return
	 */
	public int rouletteEventCheck(int memberId) {
		
		System.out.println("check dao member id: " +  memberId);
		
		return sqlSession.selectOne("eventMapper.rouletteEventCheck", memberId);
	}
	
	

	/** 이벤트 룰렛 쿠폰 insert
	 * @param memberId
	 * @param discountRateInt
	 * @return
	 */
	public int insertEventCoupon(String couponId, int memberId, int discountRateInt) {
		
		Map<String, Object> evnetCouponMap = new HashMap<>();
		evnetCouponMap.put("couponId", couponId);
		evnetCouponMap.put("couponName", "룰렛 이벤트" + discountRateInt + "% 할인쿠폰");
		evnetCouponMap.put("discountRate", discountRateInt);
		evnetCouponMap.put("memberId", memberId);
		
		return sqlSession.insert("eventMapper.insertEventCoupon", evnetCouponMap);
	}


	/** 쿠폰 id 체크 
	 * @param couponId
	 * @return
	 */
	public int rouletteEventIdCheck(String couponId) {
		System.out.println("check dao couponId: " +  couponId);
		return sqlSession.selectOne("eventMapper.rouletteEventIdCheck", couponId);
	}



}
