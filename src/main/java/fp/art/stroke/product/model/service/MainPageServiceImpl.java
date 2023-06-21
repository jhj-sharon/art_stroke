package fp.art.stroke.product.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.model.dao.MainpageDAO;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Service
public class MainPageServiceImpl implements MainpageService{
	
	@Autowired
	private MainpageDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	/**
	 * 베스트 상품 불러오기 
	 */
	@Override
	public List<Product> selectMainBestProdcut(String productName) {
		
		return dao.selectMainBestProdcut(productName);
	}

	
	
	/**
	 * 키매, 하이퍼펜션 상품 불어오기
	 */
	@Override
	public List<Product> selectMainArtistProdcut() {

		return dao.selectMainArtistProdcut();
	}



	/**
	 * 로그인한 회원의 위시리스트에 있는 productId 얻어오기
	 */
	@Override
	public List<WishList> selectWishProductId(int memberId) {
		
		return dao.selectWishProductId(memberId);
	}



	/**
	 * 위시리스트 추가 
	 */
	@Override
	public int addMainWishList(int memberId, int productId) {
		
		return dao.addMainWishList(memberId, productId);
	}



	/**
	 * 위시리스트 삭제 
	 */
	@Override
	public int deleteMainWishList(int memberId, int productId) {
		
		return dao.deleteMainWishList(memberId, productId);
	}



}
