package fp.art.stroke.product.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.model.dao.MainpageDAO;
import fp.art.stroke.product.model.vo.Product;

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



}
