package fp.art.stroke.admin.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fp.art.stroke.admin.model.dao.AdminProductDAO;
import fp.art.stroke.admin.model.vo.Pagination;
import fp.art.stroke.common.Util;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail;
import fp.art.stroke.product.model.vo.ProductImage; 

@Service
public class AdminProductServiceImpl implements AdminProductService {

	@Autowired
	private AdminProductDAO dao;
 
	
	private Logger logger = LoggerFactory.getLogger(AdminProductServiceImpl.class);

	
	// ���� 紐⑸� 議고�� ��鍮��� 援ы��
	@Override
	public Map<String, Object> selectProductList(int cp, int adminCode) {

		int listCount = dao.getListCount(adminCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Product> productList = dao.selectProductList(pagination, adminCode);
		
		// map留��ㅼ�� �닿린
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("productList", productList);
		map.put("adminCode", adminCode);
		
		return map;
	}


	// 寃��� ���� 紐⑸� 議고�� ��鍮��� 援ы��
	@Override
	public Map<String, Object> searchProductList(Map<String, Object> paramMap) {
		
		int listCount = dao.searchListCount( paramMap  );
		
		// ���댁��ㅼ�댁�� 媛�泥� ����
		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
		
		// 寃��� 議곌굔�� 留��� 寃���湲� 紐⑸� 議고��(���댁� 泥�由� ����)
		List<Product> productList = dao.searchProductList(paramMap, pagination);
		
		// map留��ㅼ�� �닿린
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("productList", productList);
		
		return map;
	}

	 

	@Override
	public ProductDetail selectProductDetail(int productId) {
		return dao.selectProductDetail(productId);
	}


 
	@Override
	public List<String> selectDBList() {
		return dao.selectDBList();
	}


	@Transactional(rollbackFor = { Exception.class })
	@Override
	public int insertProduct(ProductDetail detail, List<MultipartFile> imageList, String webPath, String folderPath, List<String> option1, List<String> option2) throws IOException  {
		
		detail.setProductName(  Util.XSSHandling(detail.getProductName())  );
		detail.setProductContent(  Util.XSSHandling(detail.getProductContent())  );
		detail.setProductContent(  Util.newLineHandling(detail.getProductContent())  );
		
		int productId = dao.insertProduct(detail, option1, option2);

		if(productId > 0) {
	
			List<ProductImage> productImageList = new ArrayList<ProductImage>();
			List<String> reNameList = new ArrayList<String>();
			
	
			for(int i=0 ; i<imageList.size() ; i++) {
				
				if( imageList.get(i).getSize() > 0  ) { 
		
					String reName = Util.fileRename( imageList.get(i).getOriginalFilename()  );
					reNameList.add(reName);
					
					ProductImage img = new ProductImage();
					img.setProductId(productId);
					img.setImageLevel(i); 
					img.setImageOriginal( imageList.get(i).getOriginalFilename() ); 
					img.setImageReName( webPath + reName ); 
					
					productImageList.add(img);
				}
			} 
			
			if( !productImageList.isEmpty()  ) {
				
				int result = dao.insertProductImageList(productImageList);
				
				if(result == productImageList.size()) { 
					
					for(int i=0 ; i < productImageList.size() ; i++) {
						int index = productImageList.get(i).getImageLevel();
						
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i) ));  
					}
				}
			}
		}
		return productId;
	}


	/** 관리자 상품 이미지
	 *
	 */
	@Override
	public List<Product> productImageOne(Product productId) {
		return dao.productImageOne(productId);
	}


	/** 관리자 상품 삭제
	 *
	 */
	@Override
	public int deleteAdminProduct(List<Integer> productChk) {
		
		int result = 0;
			if(productChk != null) {
				for(Integer productId : productChk) {
					result = dao.deleteAdminProduct(productChk, productId);
					
					logger.info("업데이트 된 deleteProduct 값" + result);
				}
			}
		
		return result;
	}


	 
	

}
	
	
	
	
	
	
	
	 
	
	
	 
	 
	
	 
	
 
