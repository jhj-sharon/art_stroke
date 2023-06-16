package fp.art.stroke.product.model.service;

import java.util.List;

import fp.art.stroke.product.model.vo.Product;

public interface MainpageService {

	List<Product> selectMainBestProdcut(String productName);

}
