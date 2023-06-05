package fp.art.stroke.product.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.model.dao.ProductQnADAO;

@Service
public class ProductQnAServiceImpl implements ProductQnAService {
	
	@Autowired
	private ProductQnADAO dao;

}
