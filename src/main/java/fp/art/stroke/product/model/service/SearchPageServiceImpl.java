package fp.art.stroke.product.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.product.model.dao.SearchPageDAO;



@Service
public class SearchPageServiceImpl implements SearchPageService{
	
	@Autowired
	private SearchPageDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

}
