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

	/** 이벤트 룰렛 쿠폰 
	 * @param memberId
	 * @param discountRateInt
	 * @return
	 */
	public int insertEventCoupon(int memberId, int discountRateInt) {
		
		Map<String, Object> evnetCouponMap = new HashMap<>();
		evnetCouponMap.put("memberId", memberId);
		evnetCouponMap.put("discountRate", discountRateInt);
		
		return sqlSession.insert("eventMapper.insertEventCoupon", evnetCouponMap);
	}

	
	
	/** 룰렛 이벤트 참여 확인 
	 * @param memberId
	 * @return
	 */
	public int rouletteEventCheck(int memberId) {
		
		return sqlSession.selectOne("eventMapper.rouletteEventCheck", memberId);
	}

}
