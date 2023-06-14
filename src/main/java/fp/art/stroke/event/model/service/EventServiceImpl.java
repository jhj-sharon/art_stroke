package fp.art.stroke.event.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.event.model.dao.EventDAO;
import fp.art.stroke.event.model.vo.Coupon;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAO dao;

	
	
	/**
	 * 이벤트 룰렛 쿠폰 
	 */
	@Override
	public int insertEventCoupon(int memberId, int discountRateInt) {
		
		return dao.insertEventCoupon(memberId, discountRateInt);
	}



	/**
	 * 	룰렛 이벤트 참여 확인 
	 */
	@Override
	public int rouletteEventCheck(int memberId) {
		
		return dao.rouletteEventCheck(memberId);
	}


		
	
	
	
}
