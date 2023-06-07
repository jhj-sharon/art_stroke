package fp.art.stroke.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.art.stroke.board.model.dao.BoardDAO;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.member.model.dao.MemberDAO;
import fp.art.stroke.member.model.service.MemberService;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Product;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAO dao;
	@Autowired
	ProductDAO productdao;
	@Autowired
	MemberDAO memberdao;
	@Override
	public List<BoardType> selectBoardType() {
		// TODO Auto-generated method stub
		return dao.selectBoardType();
	}
	@Override
	public Map<String, Object> selectBoardList(int cp, int boardCode) {
		// TODO Auto-generated method stub
		//�ش� ����Ʈ ��ücount�� ��������.
		int listCount = dao.getListCount(boardCode);
		Pagination pagination = new Pagination(cp,listCount);
		//3. �Խ��� ��� ��ȸ
		List<Board> boardList = dao.selectBoardList(pagination, boardCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList",boardList);
		map.put("boardCode", boardCode);
		return map;
	}
	@Override
	public BoardDetail selectBoardDetail(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectBoardDetail(boardId);
	}
	@Override
	public Map<String, Object> selectWriter(int cp) {
		// TODO Auto-generated method stub
		int listCount = dao.getListCount();
		Pagination pagination = new Pagination(cp,listCount);
		
		List<Board> writerList = dao.selectWriterList(pagination);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("writerList", writerList);
		return map;
	}
	@Override
	public Map<String, Object> selectWriterDetail(int memberId) {
		// TODO Auto-generated method stub
		List<Product> productList = productdao.getWriterProductList(memberId);
		List<Board> boardList = dao.getWriterBoardList(memberId);
		Member member = memberdao.getWriter(memberId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList",productList);
		map.put("boardList",boardList);
		map.put("member", member);
		return map;
	}
	@Override
	public int sendLetter(int memberId, String writerName, String sendName, String sendTitle,String sendText) {
		// TODO Auto-generated method stub
		
		int temp = memberdao.selectWriter(memberId);
		int result = 0;
		Map<String, Object> map = new HashMap();
		if(temp>0) {
			map.put("memberId", memberId);
			map.put("writerName", writerName);
			map.put("sendName", sendName);
			map.put("sendTitle", sendTitle);
			map.put("sendText", sendText);
			result = dao.sendLetter(map);
		}
		
		
		
		return result;
	}


}
