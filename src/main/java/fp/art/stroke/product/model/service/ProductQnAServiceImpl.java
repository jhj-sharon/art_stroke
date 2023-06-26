package fp.art.stroke.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fp.art.stroke.board.model.vo.BoardImage;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.common.Util;
import fp.art.stroke.product.controller.ProductController;
import fp.art.stroke.product.model.dao.ProductQnADAO;
import fp.art.stroke.product.model.vo.ProductQnA;
import fp.art.stroke.product.model.vo.ProductQnAImage;
import fp.art.stroke.product.model.vo.QnAReply;

@Service
public class ProductQnAServiceImpl implements ProductQnAService {
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private ProductQnADAO dao;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Override
	public int insertProductQna(ProductQnA qna) {
		// 1) XSS 방지 처리 + 개행문자 처리
		
//		qna.setQnaTitle( Util.XSSHandling(qna.getQnaTitle()));
//		qna.setQnaContent(Util.XSSHandling(qna.getQnaContent()));
//		qna.setQnaContent(Util.newLineHandling(qna.getQnaContent()));
		
		// 2) 게시글 삽입 DAO 호출 후 게시글 번호 반환 받기
		
		//* 게시글 번호를 먼저 따로 생성했던 이유
		// 1. 서비스 결과 반환 후 컨트롤러에서 상세조회로 리다이렉트 하기 위해
		// 2. 동일한 시간에 삽입이 2회이상 진행된 경우 시퀀스 번호가 의도와 달리 여러번 증가해서
		//    이후에 작성된 이미지 삽입 코드에 영향을 미치는걸 방지하기 위해서
		qna.setQnaPw(bcrypt.encode(qna.getQnaPw()));
		String srcPattern = "src=\"([^\"]+)\"";//바뀐이름
        String titlePattern = "title=\"([^\"]+)\"";//원래이름.
        List<ProductQnAImage> imageList = new ArrayList();
     // src 속성 값 추출
        Pattern pattern = Pattern.compile(srcPattern);
        Matcher matcher = pattern.matcher(qna.getQnaContent());
        int i = 0;
        while (matcher.find()) {
            String srcValue = matcher.group(1);
            ProductQnAImage image = new ProductQnAImage();
            image.setImageLevel(i);
            image.setImageReName(srcValue);
            if(i ==0) {
            	//일단 넣긴했는데 이건 누나한테 물어봐서 그냥 조인문으로 처리할건지 여부확인.
            	qna.setQnaFile(srcValue);
            }
            imageList.add(image);
            i++;
        }
  
     // title 속성 값 추출
        i=0;
        pattern = Pattern.compile(titlePattern);
        matcher = pattern.matcher(qna.getQnaContent());
        while (matcher.find()) {
            String titleValue = matcher.group(1);
            imageList.get(i).setImageOriginal(titleValue);
            i++;
        }
		qna.setImageList(imageList);
		int temp = dao.writeQnA(qna);
		
		int result = 0;
		if(temp >0) {
			if(imageList.size() >=1) {
				
					result = dao.insertQnAImage(qna);
			}else {
				result=1;
			}
		}else {
			result = 0;
		}
		
		return result;
	}



	@Override
	public Map<String,Object> selectQnaList(int productId,int cp) {
		Map<String,Object> map = new HashMap();
		int listCount = dao.getListCount(productId);
		Pagination pagination = new Pagination(cp,listCount);
		List<ProductQnA> qnaList = dao.selectQnaList(productId,pagination);
		map.put("qnaList",qnaList);
		map.put("pagination",pagination);
		return map;
	}



	@Override
	public Map<String, Object> selectQnADetail(int qnaId, String qnaPw) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		List<QnAReply> replyList = null;
		ProductQnA qna = null;
		int success = 0;
		if( bcrypt.matches(qnaPw,dao.selectQnAPw(qnaId))){
			logger.info("일치합니다.");
			replyList = dao.selectQnAReplyList(qnaId);
			qna = dao.selectQnADetail(qnaId);			
			map.put("replyList", replyList);
			map.put("qna", qna);
			map.put("success",1);
		}
		return map;
	}
	
	

}
