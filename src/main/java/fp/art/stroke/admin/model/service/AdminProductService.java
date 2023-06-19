package fp.art.stroke.admin.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail; 

public interface AdminProductService {

 
	Map<String, Object> selectProductList(int cp, int adminCode);

	Map<String, Object> searchProductList(Map<String, Object> paramMap);
 
	ProductDetail selectProductDetail(int productId);
 
	List<String> selectDBList();

	int insertProduct(ProductDetail detail, List<MultipartFile> imageList, String webPath, String folderPath, List<String> option1, List<String> option2) throws IOException;

	List<Product> productImageOne(Product productId);

	int deleteAdminProduct(List<Integer> productChk);
 

	 
 
	
}
