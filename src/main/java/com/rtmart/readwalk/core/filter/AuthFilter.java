package com.rtmart.readwalk.core.filter;

import com.rtmart.readwalk.article.current.AuthInfo;
import com.rtmart.readwalk.article.dto.UserDto;
import com.rtmart.readwalk.core.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebFilter(urlPatterns = {"/v1/*"})
public class AuthFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    private List<String> ignorePatterns = Arrays.asList("/v1/sessions/login/admin","/v1/sessions/login","/v1/sessions/sign");

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse rsp = (HttpServletResponse) response;
        rsp.setHeader("Access-Control-Allow-Origin", "*");
        rsp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        rsp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        for (final String ignorePattern : ignorePatterns) {
            if (PATH_MATCHER.match(ignorePattern, req.getServletPath())) {
                chain.doFilter(req, rsp);
                return;
            }
        }

        boolean validateFlag = false;
        String authorization = req.getHeader("Authorization");
        if(StringUtils.isNotBlank(authorization)){
            try{
                Claims jwtBody = JwtUtils.getJwtBody(authorization);
                Date iat = jwtBody.get("iat", Date.class);
                if(iat.getTime()>System.currentTimeMillis()*1000){
                    validateFlag = true;
                    UserDto userDto = new UserDto();
                    userDto.setUserNo(jwtBody.get("userNo",String.class));
                    userDto.setStore(jwtBody.get("store",String.class));
                    userDto.setName(jwtBody.get("name",String.class));
                    userDto.setPrivilege(jwtBody.get("privilege",String.class));
                    userDto.setToken(authorization);

                    AuthInfo.setAuthInfo(userDto);
                }
            }catch (Exception e){
                validateFlag = false;
            }
        }
        if(validateFlag){
            chain.doFilter(req, rsp);
        }else{
            onNotLogin(rsp);
        }
    }

    @Override
    public void destroy() {

    }

    private void onNotLogin(final HttpServletResponse response) {

        response.setContentType("application/json");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write("{\"code\":\"100001\",\"message\":\"用户未登录！\",\"body\":null}");

            writer.flush();
            writer.close();
        } catch (final IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public List<String> getIgnorePatterns() {

        return ignorePatterns;
    }

    public void setIgnorePatterns(final List<String> ignorePatterns) {

        this.ignorePatterns = ignorePatterns;
    }
}
