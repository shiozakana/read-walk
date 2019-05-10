/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2018
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.rtmart.readwalk.core.interceptor;

import com.rtmart.readwalk.article.dto.AdminUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 页面跳转登录拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception exception) {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object obj, ModelAndView model) {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws IOException {
//        String uri = req.getRequestURI();
//        if (uri.startsWith("/admin/login")) {
//            return true;
//        }
//        HttpSession session = req.getSession();
//        AdminUserDto adminUserDto = (AdminUserDto) session.getAttribute("user");
//        if (adminUserDto != null) {
//            return true;
//        }
//        resp.sendRedirect("/admin/login");
        return true;
    }

}
