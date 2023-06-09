package fp.art.stroke.product.model.service;

import java.util.List;
import java.util.Map;

import fp.art.stroke.product.model.vo.Product;

public interface ProductService {

	//전체 상품 리스트 조회 서비스
	List<Product> loadProductList();

}
