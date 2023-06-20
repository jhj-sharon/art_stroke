package fp.art.stroke.common.listener.model.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fp.art.stroke.common.listener.model.vo.VisitCountVO;
@Component
@Service
public class VisitorSessionListener implements HttpSessionListener {
   
   @Autowired
   private VisitCountDAO visitCountDAO;
   
   private Logger logger = LoggerFactory.getLogger(VisitorSessionListener.class);
   
   @Override
   public void sessionCreated(HttpSessionEvent event) {
       HttpSession session = event.getSession();
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
       
       // WebApplicationContext를 가져옴
       WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
       // VisitCountDAO 빈을 가져옴
       VisitCountDAO visitCountDAO = wac.getBean(VisitCountDAO.class);
       
       VisitCountVO vo = new VisitCountVO();
       vo.setVisit_ip(request.getRemoteAddr());
       vo.setVisit_agent(request.getHeader("User-Agent")); // 브라우저 정보
       vo.setVisit_refer(request.getHeader("referer")); // 접속 전 사이트 정보
       
       logger.info("SESSION VISIT COUNT " + vo);
       
       try {
           visitCountDAO.insertVisitor(vo);
           
           // 세션에 VisitCountVO 저장
           session.setAttribute("visitCountVO", vo);
       } catch (Exception e) {
           // 처리할 예외 처리 코드 작성
       }
   }

}
