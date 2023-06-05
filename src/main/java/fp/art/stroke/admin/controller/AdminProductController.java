package fp.art.stroke.admin.controller;

 
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fp.art.stroke.admin.model.service.AdminProductService;
import fp.art.stroke.common.Util;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.ProductDetail;
 
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
									Model model,
									@RequestParam Map<String, Object> paramMap) {
			
			Map<String, Object> map = null;
	
			map = service.selectProductList(cp, adminCode);
			if(paramMap.get("key") == null) {   
				
			}else {   
				
				paramMap.put("cp", cp);   
				paramMap.put("adminCode", adminCode);
				
				map = service.searchProductList(paramMap);
			 
				logger.info("관리자상품" + map);
			} 
			
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
				, @RequestParam(value="deleteList", required=false) String deleteList
				, @RequestParam(value="cp", required=false, defaultValue="1") int cp
				) throws IOException {
				
			String webPath = "/resources/img/thumbnail/";
			String folderPath = req.getSession().getServletContext().getRealPath(webPath);
			     
			 
				
				int productId = service.insertProduct(detail, imageList, webPath, folderPath);
				
				logger.info("상품등록에 값" + productId );
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
		
}
