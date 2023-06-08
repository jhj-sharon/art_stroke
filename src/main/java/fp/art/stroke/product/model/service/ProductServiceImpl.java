package fp.art.stroke.product.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.controller.ProductController;
import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	

	@Autowired
	private ProductDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);


	//상품 목록 가져오기
	@Override
	public Map<String, Object> loadProductList(int cp) {
		
		//1)페이지네이션 객체 생성(listCount)
		int listCount = dao.getListCount();
		Pagination pagination = new Pagination(cp, listCount);
		
		//2)게시글 목록 조회
		List<Product> productList = dao.loadProductList(pagination);
		
		//3)Map에 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("productList", productList);
		
		return map;
	}
	

}
