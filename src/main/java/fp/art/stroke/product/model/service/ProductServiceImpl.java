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
	public List<Product> loadProductList() {
		
		return dao.loadProductList();
	}
	

}
