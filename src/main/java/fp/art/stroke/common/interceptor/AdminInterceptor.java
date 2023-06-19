package fp.art.stroke.common.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
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
import fp.art.stroke.admin.model.service.AdminProductService;
import fp.art.stroke.admin.model.vo.AdminType;
import fp.art.stroke.member.model.vo.Member;

public class AdminInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Autowired
    private AdminMainService service;

    @Autowired
    private AdminProductService productService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ServletContext application = request.getServletContext();

        if (application.getAttribute("adminTypeList") == null) {
            List<AdminType> adminTypeList = service.selectAdminType();
            application.setAttribute("adminTypeList", adminTypeList);
        }

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String targetURI = requestURI.substring(contextPath.length());

        if (loginMember == null && !targetURI.equals("/admin/adminLogin")) {
            String message = "로그인이 필요합니다.";
            response.sendRedirect(contextPath + "/admin/adminLogin?message=" + URLEncoder.encode(message, "UTF-8"));
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        // Do nothing
    }

}
