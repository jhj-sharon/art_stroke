package fp.art.stroke.common.interceptor;

 
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import fp.art.stroke.admin.model.service.AdminMainService;
import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.member.model.vo.Member;

public class AdminInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	
	@Autowired
	private AdminMainService service;

  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		ServletContext application = request.getServletContext();
		
		if( application.getAttribute("adminTypeList") == null ) {
			
			List<AdminType> adminTypeList = service.selectAdminType();
			
			application.setAttribute("adminTypeList", adminTypeList);
		}
		
	 
		
//		HttpSession session = request.getSession();
//		   
//		Member loginMember = (Member)session.getAttribute("loginMember");
//		    
//		    if(loginMember == null && loginMember.getAuth() != 1) {
//		     String message = "권한이 없습니다.";
//		     
//		     String script = "<script>alert('" + message + "');</script>";
//
//		        // response에 스크립트를 출력합니다.
//		        response.getWriter().write(script);
//		        
//		     
//		     return false;
//		    }
		    
			return HandlerInterceptor.super.preHandle(request, response, handler);
		     
	 
	}



	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
	
	
	
	
	
}
