package fp.art.stroke.board.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fp.art.stroke.board.controller.BoardController;
import fp.art.stroke.board.model.dao.BoardDAO;
import fp.art.stroke.board.model.vo.Alarm;
import fp.art.stroke.board.model.vo.AlarmDetail;
import fp.art.stroke.board.model.vo.AlarmImage;
import fp.art.stroke.board.model.vo.Board;
import fp.art.stroke.board.model.vo.BoardDetail;
import fp.art.stroke.board.model.vo.BoardGood;
import fp.art.stroke.board.model.vo.BoardImage;
import fp.art.stroke.board.model.vo.BoardType;
import fp.art.stroke.board.model.vo.Message;
import fp.art.stroke.board.model.vo.Pagination;
import fp.art.stroke.board.model.vo.Report;
import fp.art.stroke.common.Util;
import fp.art.stroke.member.controller.MemberController;
import fp.art.stroke.member.model.dao.MemberDAO;
import fp.art.stroke.member.model.service.MemberService;
import fp.art.stroke.member.model.vo.Follow;
import fp.art.stroke.member.model.vo.Member;
import fp.art.stroke.product.model.dao.ProductDAO;
import fp.art.stroke.product.model.vo.Product;

@Service
public class BoardServiceImpl implements BoardService{
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
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
		List<Board> boardBestList = dao.selectBestList(boardCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList",boardList);
		map.put("boardCode", boardCode);
		map.put("boardBestList", boardBestList);
		return map;
	}
	
	//상세페이지 이동
	@Override
	public BoardDetail selectBoardDetail(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectBoardDetail(boardId);
	}
	
	//작가페이지 이동
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
	
	//작가상세페이지이동
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
	
	//쪽지 보내기
	@Override
	public int sendLetter(Message message) {
		// TODO Auto-generated method stub
		
		int temp = memberdao.selectWriter(message.getReceiverId());
		int result = 0;
		
		if(temp>0) {
			result = dao.sendLetter(message);
		}
		
		
		
		return result;
	}
	
	//안쓰지만 냅둠
	@Override
	public int storeImage(Map<String, Object> map) throws Exception{
//		// TODO Auto-generated method stub
//		List<MultipartFile> files = (List<MultipartFile>) map.get("imageList");
//		List<BoardImage> fileList = null;
//		BoardImage boardImage = new BoardImage();
//		int i = 0;
//		while(i< files.size()) {
//			MultipartFile file = files.get(i);
//			
//			
//				//파일명 변경
//				//resources/images/memberProfile/ + 경로
//				boardImage.setImageReName(Util.fileRename(file.getOriginalFilename()));
//				boardImage.setImageLevel(i);
//				boardImage.setBoardNo((int) map.get("boardId"));
//				boardImage.setImageOriginal(file.getOriginalFilename());
//				fileList.add(boardImage);
//				i++;
//		}
//				map.put("profileImage",fileList);
				int result = dao.insertImage(map);
//				if(result > 0 && map.get("profileImage")!= null) {
//					int j = 0;
//					while(j<fileList.size()) {
//						files.get(j).transferTo(new File(map.get("folderPath")+fileList.get(j).getImageReName()));//파일을 지정된 경로로 보내줌.
//					}
//				}
				return result;
	}
	//게시판 작성
	@Override
	public int writeBoard(int boardCode, String title, String smartEditor, int memberId, String memberNick,String type,int boardId,String memberProfileImage) {
		// TODO Auto-generated method stub
		BoardDetail detail = new BoardDetail();
		String test = boardId+"";
		logger.info(test);
		if(type.equals("update")) {
			detail.setBoardId(boardId);
		}
		detail.setProfileImage(memberProfileImage);
		detail.setBoardTitle(title);
		detail.setBoardContent(smartEditor);
		detail.setMemberId(memberId);
		detail.setMemberNickname(memberNick);
		detail.setBoardCode(boardCode);
		logger.info(type);
		String srcPattern = "src=\"([^\"]+)\"";//바뀐이름
        String titlePattern = "title=\"([^\"]+)\"";//원래이름.
        List<BoardImage> imageList = new ArrayList();
     // src 속성 값 추출
        Pattern pattern = Pattern.compile(srcPattern);
        Matcher matcher = pattern.matcher(smartEditor);
        int i = 0;
        while (matcher.find()) {
            String srcValue = matcher.group(1);
            BoardImage image = new BoardImage();
            image.setImageLevel(i);
            image.setImageReName(srcValue);
            if(i ==0) {
            	detail.setBoardThumbNail(srcValue);
            }
            if(type.equals("update")) {
            	image.setBoardId(boardId);
            }
            imageList.add(image);
            i++;
        }
  
     // title 속성 값 추출
        i=0;
        pattern = Pattern.compile(titlePattern);
        matcher = pattern.matcher(smartEditor);
        while (matcher.find()) {
            String titleValue = matcher.group(1);
            imageList.get(i).setImageOriginal(titleValue);
            i++;
        }
		detail.setImageList(imageList);
		int temp =0;
		if(type.equals("update")) {
			temp = dao.updateBoard(detail);
		}else {
			temp = dao.writeBoard(detail);
		}
		int result = 0;
		if(temp >0) {
			if(imageList.size() >=1) {
				if(type.equals("update")) {
					int delete = dao.deleteBeforeImage(boardId);
					result = dao.updateBoardImage(detail);
				}
				else {
					result = dao.insertBoardImage(detail);
				}
			}else {
				result=1;
			}
		}else {
			result = 0;
		}
		
		return result;
	}
	//게시판 삭제
	@Override
	public int deleteBoard(int boardCode, int no) {
		// TODO Auto-generated method stub
		Map<String, Object>map = new HashMap();
		map.put("boardCode", boardCode);
		map.put("boardId", no);
		return dao.deleteBoard(map);
	}
	
	//신고하기
	@Override
	public int reportIt(Report report) {
		// TODO Auto-generated method stub
		return dao.reportIt(report);
	}
	
	//좋아요 삽입
	@Override
	public int insertGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		int result = 0;
		int temp = dao.selectMemberGood(detail);
		if(temp > 0) {
			result = 0;
		}else{
			result = dao.insertGood(detail);
		}
		return result;
	}
	
	//게시판에 좋아요 리스트 가져오기
	@Override
	public List<BoardGood> selectBoardGoodList(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectBoardGoodList(boardId);
	}
	
	//좋아요 삭제
	@Override
	public int deleteGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return dao.deleteGood(detail);
	}
	
	//
	@Override
	public int selectGoodList(BoardDetail detail) {
		// TODO Auto-generated method stub
		return dao.selectGoodList(detail);
	}
	@Override
	public int updateBoardGood(BoardDetail detail) {
		// TODO Auto-generated method stub
		return dao.updateBoardGood(detail);
	}
	
	//팔로우 리스트 가져오기
	@Override
	public List<Follow> selectFollowList(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectFollowList(boardId);
	}
	
	//조회수 증가
	@Override
	public int updateReadCount(int boardId) {
		// TODO Auto-generated method stub
		return dao.updateReadCount(boardId);
	}
	
	//artist에서 좋아요리스트 가져오기
	@Override
	public List<Follow> selectmemberFollowList(int memberId) {
		// TODO Auto-generated method stub
		return dao.selectmemberFollowList(memberId);
	}
	
	//정렬하기.
	@Override
	public Map<String, Object> selectBoardSortList(int cp, int boardCode, String sort, String key, String query) {
		// TODO Auto-generated method stub
		int listCount = 0;
		if(query != null) {
			listCount = dao.getListSearchCount(boardCode, query, key);
		}else {
			listCount = dao.getListCount(boardCode);
		}
		Pagination pagination = new Pagination(cp,listCount);
		
		List<Board> boardList = dao.selectBoardSortList(pagination, boardCode, sort, key, query);
		
		Map<String, Object> map = new HashMap<String, Object>();
			List<Board> boardBestList = dao.selectBestList(boardCode);
			map.put("boardBestList", boardBestList);
		map.put("pagination", pagination);
		map.put("boardList",boardList);
		map.put("boardCode", boardCode);
		
		return map;
	}
	@Override
	public Map selectBoardSearchList(int boardCode, int cp, String query, String key) {
		// TODO Auto-generated method stub
		int listCount = dao.getListSearchCount(boardCode,query,key);
		
		Pagination pagination = new Pagination(cp,listCount);
		
		List<Board> boardList = dao.selectBoardSearchList(pagination, boardCode, query, key);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> boardBestList = dao.selectBestList(boardCode);
		map.put("boardBestList", boardBestList);
		map.put("pagination", pagination);
		map.put("boardList",boardList);
		map.put("boardCode", boardCode);
		
		
		return map;
	}
	@Override
	public AlarmDetail selectAlarm(int boardId) {
		// TODO Auto-generated method stub
		return dao.selectAlarm(boardId);
	}
	@Override
	public Map<String,Object> selectAlarmList(int cp, String key, String query) {
		// TODO Auto-generated method stub
		int listCount = 0;
		
		listCount = dao.getListAlarmCount(query, key);
		
		Pagination pagination = new Pagination(cp,listCount);
		List<Alarm> alarmList = dao.selectAlarmList(pagination,key,query);
		Map<String,Object> result = new HashMap<>();
		result.put("alarmList", alarmList);
		result.put("pagination", pagination);
		return result;
	}
	@Override
	public int writeAlarm(AlarmDetail alarm, String type,int alarmId) {
		String srcPattern = "src=\"([^\"]+)\"";//바뀐이름
        String titlePattern = "title=\"([^\"]+)\"";//원래이름.
        List<AlarmImage> imageList = new ArrayList();
     // src 속성 값 추출
        Pattern pattern = Pattern.compile(srcPattern);
        Matcher matcher = pattern.matcher(alarm.getAlarmContent());
        int i = 0;
        while (matcher.find()) {
            String srcValue = matcher.group(1);
            AlarmImage image = new AlarmImage();
            image.setImageLevel(i);
            image.setImageReName(srcValue);
            if(i ==0) {
            	alarm.setAlarmThumbNail(srcValue);
            }
            if(type.equals("update")) {
            	image.setAlarmId(alarmId);
            }
            imageList.add(image);
            i++;
        }
  
     // title 속성 값 추출
        i=0;
        pattern = Pattern.compile(titlePattern);
        matcher = pattern.matcher(alarm.getAlarmContent());
        while (matcher.find()) {
            String titleValue = matcher.group(1);
            imageList.get(i).setImageOriginal(titleValue);
            i++;
        }
		alarm.setImageList(imageList);
		int temp =0;
		if(type.equals("update")) {
			temp = dao.updateAlarm(alarm);
		}else {
			temp = dao.insertAlarm(alarm);
		}
		int result = 0;
		if(temp >0) {
			if(imageList.size() >=1) {
				if(type.equals("update")) {
					int delete = dao.deleteBeforeAlarmImage(alarmId);
					result = dao.updateAlarmImage(alarm);
				}
				else {
					result = dao.insertAlarmImage(alarm);
				}
			}else {
				result=1;
			}
		}else {
			result = 0;
		}
		
		return result;
	}
	@Override
	public int updateAlarmCnt(int alarmId) {
		// TODO Auto-generated method stub
		return dao.updateAlarmCnt(alarmId);
	}
	@Override
	public Alarm selectPrevAlarm(int alarmId) {
		// TODO Auto-generated method stub
		return dao.selectPrevAlarm(alarmId);
	}
	@Override
	public Alarm selectNextAlarm(int alarmId) {
		// TODO Auto-generated method stub
		return dao.selectNextAlarm(alarmId);
	}
	
	

	





}
