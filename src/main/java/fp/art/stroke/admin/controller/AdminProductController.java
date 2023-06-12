package fp.art.stroke.admin.controller;

 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fp.art.stroke.admin.model.service.AdminProductService;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail;
import fp.art.stroke.product.model.vo.ProductImage;
 
@Controller
@RequestMapping("/admin/product")
@SessionAttributes({"loginMember"})
public class AdminProductController {
	
	@Autowired
	private AdminProductService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminProductController.class);
	
		// 관리자 - 상품요청
		@GetMapping("request")
		public String productRequest() {
			return "admin/productRequest";
		}
		 	
		
 
		
		// 관리자 - 상품목록
		@GetMapping("{adminCode}")
		public String selectProductList(@PathVariable("adminCode") int adminCode,
									@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
									Model model, ProductImage imageNo,
									Product productId,
									@RequestParam Map<String, Object> paramMap) {
			
			
			List<Product> list = service.productImageOne(productId);
			 
			logger.info("이미지 경로 리스트 값" + list);
			
			Map<String, Object> map = null;
			
			map = service.selectProductList(cp, adminCode);
			if(paramMap.get("key") == null) {   
				
			}else {   
				
				paramMap.put("cp", cp);   
				paramMap.put("adminCode", adminCode);
				paramMap.put("productId", productId);
				map = service.searchProductList(paramMap);
			 
				logger.info("PRODUCT MANAGER CONTROLLER" + map);
				
			} 
			model.addAttribute("productImageList",list);
			model.addAttribute("map", map); 
			return "admin/productList";
		}
 		
	  
		
		
		
		
		// 게시글 상세 조회
		@GetMapping("/{adminCode}/detail/{productId}")
		public String productDetail(@PathVariable("productId") int productId
								,@RequestParam(value="cp", required=false, defaultValue="1") int cp) {
			  
			ProductDetail detail = service.selectProductDetail(productId);
	  
			return "admin/productDetail";
		}
		 
		 
		
		// 게시글 작성 화면 전환
		@GetMapping("/{adminCode}/productWriteForm")
		public String productWriteForm(@PathVariable("adminCode") int adminCode, 
									  @RequestParam(value="no", required=false, defaultValue = "0") int ProductId) {
			
			return "admin/productWriteForm";
		}
		
	 
		
	 
				 
		 
	 
		// 관리자 상품 등록
		@PostMapping("/{adminCode}/productWrite")
		public String productWrite (ProductDetail detail // ProductTitle, ProductContent, ProductNo(수정)
				, @RequestParam(value="images", required=false) List<MultipartFile> imageList // 업로드 파일(이미지) 리스트
				, @PathVariable("adminCode") int adminCode
				, String mode 
				, HttpServletRequest req
				, RedirectAttributes ra
				, @RequestParam(value="option1", required=false) List<String> option1
				, @RequestParam(value="option2", required=false) List<String> option2
				, @RequestParam(value="deleteList", required=false) String deleteList
				, @RequestParam(value="cp", required=false, defaultValue="1") int cp
				) throws IOException {
				
			String webPath = "/resources/img/thumbnail/";
			String folderPath = req.getSession().getServletContext().getRealPath(webPath);
			     
			 	detail.setProductOption1(option1);
			 	detail.setProductOption2(option2);
			 	
				
				int productId = service.insertProduct(detail, imageList, webPath, folderPath, option1, option2);
				
				logger.info("상품등록에 값" + productId );
				logger.info("디테일" + detail);
				logger.info("이미지리스트" + imageList);
				logger.info("웹패스" + webPath);
				logger.info("폴더패스" + folderPath);
				
				
				String path = null;
				String message = null;
				
				if(productId > 0) {
					
					path = "../" + adminCode;
					message = "상품이 등록되었습니다.";
					
				}else {
					path = req.getHeader("referer");
					message = "상품 등록 실패 ...";
				}
				
				ra.addFlashAttribute("message", message);
				
				return "redirect:" + path;
		 
}
		
		@ResponseBody
		@GetMapping("productTableList")
		public String productTableList(Product productId) {
		  List<Object> data = service.productTableList(productId);
		  logger.info("데이터테이블 값" + data);
		  logger.info("테이블리스트 productId" + productId);
		  return new Gson().toJson(data);
		}
}













