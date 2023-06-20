package fp.art.stroke.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.event.model.vo.Coupon;
import fp.art.stroke.myPage.model.vo.Addr;
import fp.art.stroke.product.controller.ProductController;
import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Cart;
import fp.art.stroke.product.model.vo.Pagination;
import fp.art.stroke.product.model.vo.Product;
import fp.art.stroke.product.model.vo.WishList;

@Service
public class ProductServiceImpl implements ProductService {
	
	

	@Autowired
	private ProductDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


	//상품 목록 가져오기
	@Override
	public List<Product> loadProductList() {
		
		return dao.loadProductList();
	}

	//상품 상세 페이지
	@Override
	public Product loadProductDetail(int productId) {
		
		return dao.loadProductDetail(productId);
	}


	//위시리스트 추가
	@Override
	public int addWishList(WishList wishList) {
	
		return dao.addWishList(wishList);
	}
	


	//위시리스트 중복검사
	@Override
	public int wishListCheck(WishList wishList) {
		
		return dao.wishListCheck(wishList);
	}

	//위시리스트 로드
	@Override
	public List<Integer> loadWishlist(int memberId) {
		
		return dao.loadWishlist(memberId);
	}
	
	//위시리스트 삭제
	@Override
	public int wishListDelete(int productId) {
		return dao.wishListDelete(productId);
	}
	
	//상세페이지 이동
	@Override
	public Product getProductById(int productId) {
		
		return dao.getProductById(productId);
	}
	
	//JSTL 상품 목록 만들기
	@Override
	public Map<String, Object> loadProductMain(Map<String, Object> paramMap) {
		   
		//1) 전체 상품 목록 조회
		List<Product> productList = new ArrayList<>();
		productList = dao.loadProductList();
		
		//2) wishList 가져오기
		int memberId = (int) paramMap.get("memberId");
		List<WishList> wishList = new ArrayList<>();
    	
    	wishList = dao.loadWishlistObj(memberId);
    	
    	//3) map 만들어 담기
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("productList", productList);
    	map.put("wishList", wishList);
		
		return map;
	}
	//쿼리스트링 조건에 만족하는 상품 목록 만들기
	@Override
	public List<Product> loadProductList2(Map<String, Object> paramMap) {
		
        List<Product> productList = dao.loadProductList2(paramMap);

        
        return productList;
	}
	
    //위시리스트 객체 로드하기
	@Override
	public List<WishList> loadWishlistObj(int memberId) {
		// TODO Auto-generated method stub
		return dao.loadWishlistObj(memberId);
	}
	
	//best 상품 보기
	@Override
	public List<Product> loadProductBest(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return dao.loadProductBest(paramMap);
	}

	//NEW 상품 정렬
	@Override
	public List<Product> loadProductNew(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return dao.loadProductNew(paramMap);
	}
	//장바구니 중복체크
	@Override
	public int checkCart(Cart cart) {
		// TODO Auto-generated method stub
		return dao.checkCart(cart);
	}
	
	//장바구니 중복시 수량증가
	@Override
	public int updateCartQuantity(Cart cart) {
		
		return dao.updateCartQuantity(cart);
	}
	//장바구니 추가
	@Override
	public int addCart(Cart cart) {
		
		return dao.addCart(cart);
	}
	//장바구니 로드
	@Override
	public List<Cart> loadCart(int memberId) {
		// TODO Auto-generated method stub
		return dao.loadCart(memberId);
	}
	//장바구니 삭제
	@Override
	public int deleteCart(List<Integer> cartIdList) {
		// TODO Auto-generated method stub
		return dao.deleteCart(cartIdList);
	}
	//쿠폰정보 들고오기
	@Override
	public List<Coupon> loadCoupon(int memberId) {
		// TODO Auto-generated method stub
		return dao.loadCoupon(memberId);
	}
	//주소정보 들고오기
	@Override
	public List<Addr> loadAddr(int memberId) {
		// TODO Auto-generated method stub
		return dao.loadAddr(memberId);
	}
	//결제페이지에서 주소등록
	@Override
	public int newAddr(Addr addr) {
		// TODO Auto-generated method stub
		return dao.newAddr(addr);
	}

	
	
	
	
	
	
}
