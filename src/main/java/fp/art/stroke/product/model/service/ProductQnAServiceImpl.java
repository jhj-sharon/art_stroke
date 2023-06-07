package fp.art.stroke.product.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.common.Util;
import fp.art.stroke.product.model.dao.ProductQnADAO;
import fp.art.stroke.product.model.vo.ProductQnA;

@Service
public class ProductQnAServiceImpl implements ProductQnAService {
	
	@Autowired
	private ProductQnADAO dao;

	@Override
	public int insertProductQna(ProductQnA qna) {
		// 1) XSS 방지 처리 + 개행문자 처리
		
		qna.setQnaTitle( Util.XSSHandling(qna.getQnaTitle()));
		qna.setQnaContent(Util.XSSHandling(qna.getQnaContent()));
		qna.setQnaContent(Util.newLineHandling(qna.getQnaContent()));
		
		// 2) 게시글 삽입 DAO 호출 후 게시글 번호 반환 받기
		
		//* 게시글 번호를 먼저 따로 생성했던 이유
		// 1. 서비스 결과 반환 후 컨트롤러에서 상세조회로 리다이렉트 하기 위해
		// 2. 동일한 시간에 삽입이 2회이상 진행된 경우 시퀀스 번호가 의도와 달리 여러번 증가해서
		//    이후에 작성된 이미지 삽입 코드에 영향을 미치는걸 방지하기 위해서
		
		int qndId =dao.insertProductQna(qna);
		
		return qndId;
	}

	@Override
	public int updateQna(ProductQnA qna) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
