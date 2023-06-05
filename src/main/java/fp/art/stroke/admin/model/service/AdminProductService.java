package fp.art.stroke.admin.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail; 

public interface AdminProductService {

 
	Map<String, Object> selectProductList(int cp, int adminCode);

	Map<String, Object> searchProductList(Map<String, Object> paramMap);
 
	ProductDetail selectProductDetail(int productId);
 
	List<String> selectDBList();

	int insertProduct(ProductDetail detail, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException;
 
	 

	 

	 
 
	
}
