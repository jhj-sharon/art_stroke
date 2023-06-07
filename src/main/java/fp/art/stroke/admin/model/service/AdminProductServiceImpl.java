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
		
		// 1. 게시글 삽입
		
		// 1) XSS 방지 처리 + 개행문자 처리
		detail.setProductName(  Util.XSSHandling(detail.getProductName())  );
		detail.setProductContent(  Util.XSSHandling(detail.getProductContent())  );
		detail.setProductContent(  Util.newLineHandling(detail.getProductContent())  );
		
		//  2) 게시글 삽입 DAO 호출 후 게시글 번호 반환 받기
		
		//* 게시글 번호를 먼저 따로 생성했던 이유
		// 1. 서비스 결과 반환 후 컨트롤러에서 상세조회로 리다이렉트 하기 위해
		// 2. 동일한 시간에 삽입이 2회이상 진행된 경우 시퀀스 번호가 의도와 달리 여러번 증가해서
		//    이후에 작성된 이미지 삽입 코드에 영향을 미치는걸 방지하기 위해서
		
		int productId = dao.insertProduct(detail, option1, option2);
		 
		//int a = 10 / 0;
		
		if(productId > 0) {
			// 이미지 삽입 
			
			// imageList : 실제 파일이 담겨있는 리스트
			// ProductImageList : DB에 삽입할 이미지 정보만 담겨있는 리스트
			// reNameList : 변경된 파일명이 담겨있는 리스트
			
			List<ProductImage> productImageList = new ArrayList<ProductImage>();
			List<String> reNameList = new ArrayList<String>();
			
			// imageList에 담겨있는 파일 정보 중 실제 업로도된 파일만 분류하는 작업
			for(int i=0 ; i<imageList.size() ; i++) {
				
				if( imageList.get(i).getSize() > 0  ) { // i번째 요소에 업로드된 이미지가 있을 경우
					
					// 변경된 파일명 저장
					String reName = Util.fileRename( imageList.get(i).getOriginalFilename()  );
					reNameList.add(reName);
					
					// ProductImage 객체를 생성하여 값 세팅 후 ProductImageList에 추가
					ProductImage img = new ProductImage();
					img.setProductId(productId); // 게시글 번호
					img.setImageLevel(i); // 이미지 순서(파일 레벨)
					img.setImageOriginal( imageList.get(i).getOriginalFilename() ); // 원본 파일명
					img.setImageReName( webPath + reName ); // 웹 접근 경로 + 변경된 파일명
					
					productImageList.add(img);
				}
			} // for 종료
			
			
			// 분류 작업 종료 후 ProductImageList가 비어있지 않은 경우 == 파일이 업로드가 된 경우
			if( !productImageList.isEmpty()  ) {
				
				int result = dao.insertProductImageList(productImageList);
				
				// result == 삽입 성공한 행의 개수
				
				if(result == productImageList.size()) { // 삽입된 행의 개수와 업로드 이미지 수가 같을 경우  
					
					// 서버에 이미지 저장
					
					for(int i=0 ; i < productImageList.size() ; i++) {
						int index = productImageList.get(i).getImageLevel();
						
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i) ));  
					}
			
				}
				
				
			}
			
		}
		
		return productId;
	}


	@Override
	public List<Product> productImageOne(Product productId) {
		return dao.productImageOne(productId);
	}


	@Override
	public List<Object> productTableList(Product productId) {
		return dao.productTableList(productId);
	}


	 
	

}
	
	
	
	
	
	
	
	 
	
	
	 
	 
	
	 
	
 
