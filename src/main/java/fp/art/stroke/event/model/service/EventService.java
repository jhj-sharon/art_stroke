package fp.art.stroke.event.model.service;

public interface EventService {

	/** 이벤트 룰렛 쿠폰 
	 * @param memberId
	 * @param discountRateInt
	 * @return
	 */
	int insertEventCoupon(String couponId, int memberId, int discountRateInt);

	
	
	/** 룰렛 이벤트 참여 확인 
	 * @param memberId
	 * @return
	 */
	int rouletteEventCheck(int memberId);



	/** 쿠폰 id 체크 
	 * @param couponId
	 * @return
	 */
	int rouletteEventIdCheck(String couponId);

}
